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
public class Part3Test {
    
   @Test
    public void testAddMessage() {

        Part3 part3 = new Part3();

        Message msg = new Message(
                "+27834557896",
                "Hello Mike",
                1
        );

        part3.addMessage(msg);

        assertEquals(1, part3.getMessages().size());
    }

    @Test
    public void testDisplayLongestMessage() {

        Part3 part3 = new Part3();

        part3.addMessage(new Message(
                "+27834557896", "Hi", 1
        ));

        part3.addMessage(new Message(
                "+27834557897",
                "This is the longest message.",
                2
        ));

        assertEquals(
                "This is the longest message.",
                part3.displayLongestMessage()
        );
    }

    @Test
    public void testSearchMessageByRecipient() {

        Part3 part3 = new Part3();

        part3.addMessage(new Message(
                "+27834557896", "Hello Mike", 1
        ));

        assertEquals(
                "Hello Mike",
                part3.searchMessageByRecipient("+27834557896")
        );
    }

    @Test
    public void testDeleteMessageByHash() {

        Part3 part3 = new Part3();

        Message msg = new Message(
                "+27834557896", "Hello Mike", 1
        );

        part3.addMessage(msg);

        assertTrue(
                part3.deleteMessageByHash(msg.getMessageHash())
        );

        assertEquals(0, part3.getMessages().size());
    }

    @Test
    public void testDisplayMessageReport() {

        Part3 part3 = new Part3();

        part3.addMessage(new Message(
                "+27834557896", "Hello Mike", 1
        ));

        assertTrue(
                part3.displayMessageReport().contains("Hello Mike")
        );
    }

   
@Test
public void testDisplayStoredMessages() {

    Message msg = new Message(
            "+27834557896",
            "Stored message test",
            1
    );

    // Store the message
    String result = msg.storeMessage();

    assertEquals("Message successfully stored.", result);

    // Read and display stored messages
    Part3 part3 = new Part3();

    String output = part3.displayStoredMessages();

    assertTrue(
            output.contains("Stored message test"),
            "Stored message should appear in the displayed output."
    );
}

    
}
