/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1poe;

import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class Part3 {
     private ArrayList<Message> messages;

    // Constructor
    public Part3() {
        messages = new ArrayList<>();
    }

    // Add message to array
    public void addMessage(Message message) {
        messages.add(message);
    }

    // Get messages
    public ArrayList<Message> getMessages() {
        return messages;
    }

    // Display longest message
    public String displayLongestMessage() {

        if (messages.isEmpty()) {
            return "No messages available.";
        }

        Message longest = messages.get(0);

        for (Message msg : messages) {

            if (msg.getMessage().length()
                    > longest.getMessage().length()) {

                longest = msg;
            }
        }

        return longest.getMessage();
    }

    // Search message by recipient
    public String searchMessageByRecipient(String recipient) {

        for (Message msg : messages) {

            if (msg.getRecipient().equals(recipient)) {
                return msg.getMessage();
            }
        }

        return "Message not found.";
    }

    // Delete message using message hash
    public boolean deleteMessageByHash(String hash) {

       if (hash == null || hash.isEmpty()) {
        return false;
    }

    for (int i = 0; i < messages.size(); i++) {

        Message msg = messages.get(i);

        if (msg.getMessageHash() != null
                && msg.getMessageHash().equalsIgnoreCase(hash.trim())) {
            
        }
       

                messages.remove(i);
                return true;
            }
        

        return false;
    }

    // Read and display stored messages
    public String displayStoredMessages() {

        ArrayList<Message> storedMessages =
                Message.readMessagesFromFile();

        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }

        StringBuilder output = new StringBuilder();

        for (Message msg : storedMessages) {

            output.append("Message ID: ")
                    .append(msg.getMessageID()).append("\n");

            output.append("Recipient: ")
                    .append(msg.getRecipient()).append("\n");

            output.append("Message: ")
                    .append(msg.getMessage()).append("\n");

            output.append("Message Hash: ")
                    .append(msg.getMessageHash()).append("\n\n");
        }

        return output.toString();
    }

    // Display message report
    public String displayMessageReport() {

        if (messages.isEmpty()) {
            return "No messages available for report.";
        }

        StringBuilder report = new StringBuilder();

        for (Message msg : messages) {

            report.append("Message ID: ")
                    .append(msg.getMessageID()).append("\n");

            report.append("Message Hash: ")
                    .append(msg.getMessageHash()).append("\n");

            report.append("Recipient: ")
                    .append(msg.getRecipient()).append("\n");

            report.append("Message: ")
                    .append(msg.getMessage()).append("\n");

            report.append("----------------------\n");
        }

        return report.toString();
    }
}

