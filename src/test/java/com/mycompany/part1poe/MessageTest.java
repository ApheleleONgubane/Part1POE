/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.part1poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    //Message length test
    @Test
    public void testMessageLengthSuccess() {
        String shortMsg = "Hi Mike, can you join us for dinner tonight?";
        Message obj = new Message("+27718693002", shortMsg, 0);
        assertTrue(obj.getMessage().length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "A".repeat(251); 
        Message obj = new Message("+27718693002", longMsg, 0);
        assertTrue(obj.getMessage().length() > 250);
    }
    
    //Recipient cell test
    @Test
    public void testRecipientCorrectlyFormatted() {
        Message obj = new Message("+27718693002", "Test message", 0);
        String expected = "Cell phone number successfully captured.";
        String actual = obj.checkRecipientCell();
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {
        Message obj = new Message("08575975889", "Test message", 0);
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String actual = obj.checkRecipientCell();
        assertEquals(expected, actual);
    }
    // message hash tests
    @Test
    public void testMessageHashCreation() {
        Message obj = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        String expected = "00:0:HITONIGHT";
        String actual = obj.createMessageHash();
        assertEquals(expected, actual);
    }
    
    //Message ID Test
    @Test
    public void testMessageIDCreated() {
        Message obj = new Message("+27718693002", "Test", 0);
        assertTrue(obj.checkMessageID());
    }
}
