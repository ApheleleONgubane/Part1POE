/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1poe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

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
   
    // METHOD 1: Generate random 10-digit Message ID
    private String generateMessageID() {

        String id = "";

        for (int i = 0; i < 10; i++) {

            int digit = (int) (Math.random() * 10);

            id = id + digit;
        }

        return id;
    }

    // METHOD 2: Check Message ID
    public boolean checkMessageID() {

        return this.messageID.matches("\\d{10}");
    }

    // METHOD 3: Check Recipient Cell Number
    public String checkRecipientCell() {

        if (this.recipient == null ||
                !this.recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        return "Cell phone number successfully captured.";
    }

    // METHOD 4: Create Message Hash
    public String createMessageHash() {

        // Get first two digits of message ID
        String firstTwoID = this.messageID.substring(0, 2);

        // Remove unnecessary spaces
        String cleanMessage = this.message.trim();

        // Find first word
        int firstSpace = cleanMessage.indexOf(" ");

        String firstWord;

        if (firstSpace == -1) {
            firstWord = cleanMessage;
        } else {
            firstWord = cleanMessage.substring(0, firstSpace);
        }

        // Find last word
        int lastSpace = cleanMessage.lastIndexOf(" ");

        String lastWord;

        if (lastSpace == -1) {
            lastWord = cleanMessage;
        } else {
            lastWord = cleanMessage.substring(lastSpace + 1);
        }

        // Remove punctuation
        firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");

        // Create message hash
        String hash = firstTwoID + ":"
                + this.numMessagesSent + ":"
                + (firstWord + lastWord).toUpperCase();

        this.messageHash = hash;

        return hash;
    }
// METHOD 5: Check message length
    public boolean checkMessageLength() {

        return this.message != null
                && this.message.length() <= 250;
    }

    // METHOD 6: Send, Disregard or Store Message
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

    // METHOD 7: Store Message as JSON without external libraries
    public String storeMessage() {

        File file = new File("messages.json");

        try {

            String existingMessages = "";

            // Read existing messages
            if (file.exists() && file.length() > 0) {

                Scanner reader = new Scanner(file);

                while (reader.hasNextLine()) {

                    existingMessages += reader.nextLine();
                }

                reader.close();

                existingMessages = existingMessages.trim();
            }

            // Create message hash
            if (this.messageHash == null) {

                createMessageHash();
            }

            // Escape special JSON characters
            String safeMessage = this.message
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");

            String safeRecipient = this.recipient
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"");

            // Create JSON object
            String newMessage = "{"
                    + "\"messageID\":\"" + this.messageID + "\","
                    + "\"recipient\":\"" + safeRecipient + "\","
                    + "\"message\":\"" + safeMessage + "\","
                    + "\"messageHash\":\"" + this.messageHash + "\""
                    + "}";

            // Add message to JSON array
            if (existingMessages.isEmpty()
                    || existingMessages.equals("[]")) {

                existingMessages = "[" + newMessage + "]";

            } else if (existingMessages.endsWith("]")) {

                existingMessages = existingMessages.substring(
                        0, existingMessages.length() - 1
                );

                existingMessages += "," + newMessage + "]";

            } else {

                return "Error: Invalid JSON file format.";
            }

            // Write updated JSON array to file
            FileWriter writer = new FileWriter(file, false);

            writer.write(existingMessages);

            writer.close();

            return "Message successfully stored.";

        } catch (IOException e) {

            return "Error storing message: " + e.getMessage();
        }
    }
    
    // METHOD 8: Read messages from JSON file
    public static ArrayList<Message> readMessagesFromFile() {

        ArrayList<Message> messages = new ArrayList<>();

        File file = new File("messages.json");

        if (!file.exists() || file.length() == 0) {
            return messages;
        }

        try {

            Scanner reader = new Scanner(file);

            String json = "";

            while (reader.hasNextLine()) {
json += reader.nextLine();
            }

            reader.close();

            json = json.trim();

            if (json.isEmpty() || json.equals("[]")) {
                return messages;
            }

            // Remove opening and closing brackets
            json = json.substring(1, json.length() - 1);

            // Split individual message objects
            String[] objects = json.split("\\},\\s*\\{");
            for (String object : objects) {

                object = object.replace("{", "");
                object = object.replace("}", "");

                String id = "";
                String recipient = "";
                String messageText = "";
                String hash = "";

                String[] fields = object.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                for (String field : fields) {

                    String[] parts = field.split(":", 2);

            if (parts.length == 2) {
             String key = parts[0].replace("\"", "").trim();

          String value = parts[1].replace("\"", "").trim();

            if (key.equals("messageID")) {
           id = value;

           } else if (key.equals("recipient")) {
            recipient = value;

         } else if (key.equals("message")) {
            messageText = value;

       } else if (key.equals("messageHash")) {   
                            
       hash = value;
                        }
                    }
                }

                Message msg = new Message(recipient, messageText, 0);

                msg.messageID = id;
                msg.messageHash = hash;

                messages.add(msg);
            }

        } catch (IOException e) {

            System.out.println("Error reading JSON file: " + e.getMessage());
        }

        return messages;
    }                  
                    
    // METHOD 9: Getters

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

    public int getNumMessagesSent() {

        return numMessagesSent;
    }

}