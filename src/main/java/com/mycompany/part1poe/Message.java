/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1poe;

/**
 *
 * @author Student
 */
public class Message {
    // Variables 
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;
    
    // Constructor to set up the data when a new Message is created
    public Message(String recipient, String message, int numMessagesSent) {
        this.recipient = recipient;
        this.message = message;
        this.numMessagesSent = numMessagesSent;
        this.messageID = generateMessageID(); // Auto-generate the 10-digit ID
    }
   
    // Helper method to generate a random 10-digit number
    private String generateMessageID() {
        String id = "00";
        for (int i = 0; i < 8; i++) {
            int digit = (int) (Math.random() * 10);
            id = id + digit;
        }
          return id;
    }
    // METHOD 1: Check Message ID (Not more than 10 characters)
    public boolean checkMessageID() {
        if (this.messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }
   
    // METHOD 2: Check Recipient Cell
    public String checkRecipientCell() {
        // Regex: Must start with +27 and have 9 digits after (total 12 chars)
        if (!this.recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        return "Cell phone number successfully captured.";
    }
   
    // METHOD 3: Create Message Hash
    public String createMessageHash() {
        // 1. Get the first two digits of the ID
        String firstTwoID = this.messageID.substring(0, 2);
       
        // 2. Split the message into words to find first and last
        String[] words = this.message.split(" ");
       
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        // 3. Clean up punctuation (like commas or question marks) from the words
        firstWord = firstWord.replaceAll("[^a-zA-Z]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z]", "");
       
        // 4. Combine them: ID + ":" + MessageNum + ":" + FIRSTWORD + LASTWORD
        String hash = firstTwoID + ":" + this.numMessagesSent + ":" + (firstWord + lastWord).toUpperCase();
       
        this.messageHash = hash;
        return hash;
    }
    // METHOD 4: Sent Message (1 = Send, 2 = Disregard, 3 = Store)
    public String SentMessage(int choice) {
        if (choice == 1) {
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid choice.";
        }
    }
    
    //Method 5: Store message as JSON
 public String storeMessage() {
        // We create the JSON text format manually
        String jsonFormat = "\n  {\n" +
                "    \"messageID\": \"" + this.messageID + "\",\n" +
                "    \"recipient\": \"" + this.recipient + "\",\n" +
                "    \"message\": \"" + this.message + "\",\n" +
                "    \"messageHash\": \"" + this.messageHash + "\"\n" +
                "  }";

        // We use FileWriter to save it to a file called messages.json
        try (java.io.FileWriter file = new java.io.FileWriter("messages.json", true)) {
            file.write(jsonFormat);
            return "Message successfully stored.";
        } catch (java.io.IOException e) {
            return "Error storing message: " + e.getMessage();
        }
    }
    // --- GETTERS (So we can print the details in the Main class later) ---
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }
}
    

