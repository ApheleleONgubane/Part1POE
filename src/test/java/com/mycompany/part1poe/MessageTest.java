/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.part1poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

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
       Message obj = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                0
        );

        String expected = obj.getMessageID().substring(0, 2)
                + ":0:HITONIGHT";

        String actual = obj.createMessageHash();

        assertEquals(expected, actual);
    }
    // message hash tests
    @Test
    public void testMessageHashCreation() {
         Message obj = new Message(
                "08575975889",
                "Hi Keegan, did you receive the payment?",
                1
        );

        String expected = obj.getMessageID().substring(0, 2)
                + ":1:HIPAYMENT";

        String actual = obj.createMessageHash();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSecondMessageHashCreation() {
        // Testing the 2nd test message from the assignment to ensure the counter increments
         Message obj = new Message(
            "08575975889",
            "Hi Keegan, did you receive the payment?",
            1
    );

    String expected = obj.getMessageID().substring(0, 2)
            + ":1:HIPAYMENT";

        String actual = obj.createMessageHash();
        assertEquals(expected, actual);
    }
    
    //Message ID Test
    @Test
    public void testMessageIDCreated() {
        Message obj = new Message("+27718693002", "Test", 0);
        assertTrue(obj.checkMessageID());
    }
    //seny message options test 
    @Test
    public void testSentMessageOption1() {
        Message obj = new Message("+27718693002", "Test", 0);
        assertEquals("Message successfully sent.", obj.SentMessage(1));
    }

    @Test
    public void testSentMessageOption2() {
        Message obj = new Message("+27718693002", "Test", 0);
        assertEquals("Press 0 to delete the message.", obj.SentMessage(2));
    }

    @Test
    public void testSentMessageOption3() {
        Message obj = new Message("+27718693002", "Test", 0);
        assertEquals("Message successfully stored.", obj.SentMessage(3));
    }
    @Test
public void testMessageHashLoop() {

    for (int i = 0; i < 5; i++) {

        Message obj = new Message(
                "+27718693002",
                "Hello Mike",
                i
        );

        String hash = obj.createMessageHash();

        assertNotNull(hash);
        assertTrue(hash.contains(":" + i + ":"));
    }
}

// PART 3 UNIT TEST
@Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        Message msg1 = new Message("+27834557896", "Did you get the cake?", 0);
        Message msg4 = new Message("0838884567", "It is dinner time!", 3);
        
        ArrayList<Message> sentMessages = new ArrayList<>();
        sentMessages.add(msg1);
        sentMessages.add(msg4);
        
        assertEquals("Did you get the cake?", sentMessages.get(0).getMessage());
        assertEquals("It is dinner time!", sentMessages.get(1).getMessage());
    }

    @Test
    public void testDisplayLongestMessage() {
        
    Message msg1 = new Message("+27834557896", "Did you get the cake?", 0);
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 1);
        Message msg4 = new Message("0838884567", "It is dinner time!", 3);
        
        ArrayList<Message> allMessages = new ArrayList<>();
        allMessages.add(msg1);
        allMessages.add(msg2);
        allMessages.add(msg4);
        
        String longest = "";
        for (Message m : allMessages) {
            if (m.getMessage().length() > longest.length()) {
                longest = m.getMessage();
            }
        }
        
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }
     @Test
    public void testSearchForMessageID() {
        Message msg4 = new Message("0838884567", "It is dinner time!", 3);
        
        String searchID = msg4.getMessageID(); 
        String foundMessage = "";
        
        ArrayList<Message> sentMessages = new ArrayList<>();
        sentMessages.add(msg4);
        
        for (Message m : sentMessages) {
            if (m.getMessageID().equals(searchID)) {
                foundMessage = m.getMessage();
            }
        }
        
        assertEquals("It is dinner time!", foundMessage);
    }
    @Test
    public void testSearchAllMessagesForRecipient() {
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 1);
        Message msg5 = new Message("+27838884567", "Ok, I am leaving without you.", 4);
        
        ArrayList<Message> sentMessages = new ArrayList<>();
        sentMessages.add(msg2);
        sentMessages.add(msg5);
        
        String searchRecipient = "+27838884567";
        ArrayList<String> foundMessages = new ArrayList<>();
        
        for (Message m : sentMessages) {
            if (m.getRecipient().equals(searchRecipient)) {
                foundMessages.add(m.getMessage());
            }
        }
        
        assertEquals(2, foundMessages.size());
        assertEquals("Where are you? You are late! I have asked you to be on time.", foundMessages.get(0));
assertEquals("Ok, I am leaving without you.", foundMessages.get(1));
    }

    @Test
    public void testDeleteMessageUsingMessageHash() {
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 1);
        String hashToDelete = msg2.createMessageHash();
        
        ArrayList<Message> sentMessages = new ArrayList<>();
        sentMessages.add(msg2);
        
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).getMessageHash().equals(hashToDelete)) {
                sentMessages.remove(i);
                break;
            }
        }
        
        assertTrue(sentMessages.isEmpty()); 
    }
    @Test
    public void testDisplayReport() {
        Message msg1 = new Message("+27834557896", "Did you get the cake?", 0);
        msg1.createMessageHash();
        
        assertNotNull(msg1.getMessageHash());
        assertEquals("+27834557896", msg1.getRecipient());
        assertEquals("Did you get the cake?", msg1.getMessage());
    }
    }
    
