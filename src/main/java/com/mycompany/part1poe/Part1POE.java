/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part1poe;

import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Student
 */
public class Part1POE {

    //part 3 here 
    static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> disregardedMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();
    
    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
        Login myLoginSystem = new Login();

        System.out.println("---- REGISTRATION ----");
        System.out.print("Enter first name: ");
        String FirstName = input.nextLine();
       
        System.out.print("Enter last name: ");
        String LastName = input.nextLine();

        System.out.print("Enter username: ");
        String UserName = input.nextLine();
       
        System.out.print("Enter password: ");
        String Password = input.nextLine();
       
        System.out.print("Enter cell phone number (start with +27): ");
        String PhoneNumber = input.nextLine();

       // Login obj = new Login();
        // STEP 3: Call the registerUser method from the Login class
        // We give it all the user data, and it gives us back a message
        String registrationMessage = myLoginSystem.registerUser(UserName, Password, PhoneNumber, FirstName, LastName);
        
        System.out.println("\n--- Registration Result ---");
        System.out.println(registrationMessage);
     
       if (registrationMessage.contains("Username successfully captured.")
        && registrationMessage.contains("Password successfully captured.")
        && registrationMessage.contains("Cell phone number successfully added.")) {
        
        // ---- Now try to log in ----
        System.out.println("\n--- Login ---");
        System.out.print("Enter username to login: ");
        String loginUsername = input.nextLine();
        
        System.out.print("Enter password to login: ");
        String loginPassword = input.nextLine();
        
       
         boolean isSuccess = myLoginSystem.loginUser(loginUsername, loginPassword);
       
        // Print the final message
        System.out.println(myLoginSystem.returnLoginStatus(isSuccess));
        
        //Part 2 code and 3
        if (isSuccess) {
            System.out.println("\nWelcome to QuickChat.");
            int totalSent = 0;
            boolean running = true;
            
            while (running) {
           System.out.println("\nMenu:");
           System.out.println("1) Send Messages");
           System.out.println("2) Show recently sent messages");
           System.out.println("3) Quit");
           
         //part 3 menu options 
           
           System.out.println("4) Display Longest Message");
           System.out.println("5) Search by Message ID");
           System.out.println("6) Search by Recipient");
           System.out.println("7) Delete Message by Hash");
           System.out.println("8) Display Report");
           
           System.out.print("Choose an option: ");
                
            String choice = input.nextLine();
                
         // ---- OPTION 1: SEND MESSAGES ----
        if (choice.equals("1")) {

            System.out.print("How many messages do you wish to enter? ");

            int numMessages;

            try {

                numMessages = Integer.parseInt(input.nextLine());
             if (numMessages <= 0) {
             System.out.println("Please enter a number greater than 0.");
 continue;
          }

  } catch (NumberFormatException e) {
  System.out.println("Please enter a valid number.");
   continue;
     }
             //for loop       
        for (int i = 0; i < numMessages; i++) {
         System.out.println("\nMessage " + (i + 1));
         //enter recipient
        System.out.print("\nEnter recipient cell number (+27...): ");
       String recipient = input.nextLine();
                        
    System.out.print("Enter message: ");
   String msgText = input.nextLine();
                        
   if (msgText.length() > 250) {
   System.out.println("Message exceeds 250 characters by " + (msgText.length() - 250) + "; please reduce the size.");
  i--; 
 continue;
  }
   
   Message newMsg = new Message(recipient, msgText, i);
                        
// Check recipient cell number
    if (!newMsg.checkRecipientCell().equals(
       "Cell phone number successfully captured.")) {

     System.out.println(newMsg.checkRecipientCell());

     i--;
   continue;
      }

   // Create message hash
   newMsg.createMessageHash();

   // ---- MESSAGE OPTIONS ----
 System.out.println("\nOptions:");
  System.out.println("1) Send Message");
  System.out.println("2) Disregard Message");
  System.out.println("3) Store Message");

 System.out.print("Choose an option: ");

 int action;

  try {
   action = Integer.parseInt(input.nextLine());

   } catch (NumberFormatException e) {
   System.out.println("Invalid option. Please try again.");
      i--;
   continue;
   }                          

     // ---- SEND MESSAGE ----
    if (action == 1) {

    System.out.println(newMsg.SentMessage(1));

  // Increase total sent
    totalSent++;
    sentMessages.add(newMsg);

   // Display message details after choosing Send
   System.out.println("\n--- Message Details ---");
   System.out.println("Message ID: " + newMsg.getMessageID());
 System.out.println("Message Hash: " + newMsg.getMessageHash());
   System.out.println("Recipient: " + newMsg.getRecipient());
   System.out.println("Message: " + newMsg.getMessage());

  }

// ---- DISREGARD MESSAGE ----
  else if (action == 2) {

  System.out.println(newMsg.SentMessage(2));
 System.out.print("Enter 0 to delete the message: ");
   String deleteChoice = input.nextLine();

  if (deleteChoice.equals("0")) {
 System.out.println("Message deleted.");
 } else {
    System.out.println("Message disregarded.");
     disregardedMessages.add(newMsg);
     }

      }

   // ---- STORE MESSAGE ----
       else if (action == 3) {

        System.out.println(newMsg.storeMessage());
        storedMessages.add(newMsg);

  // Display stored message details
  System.out.println("\n--- Message Details ---");
  System.out.println("Message ID: " + newMsg.getMessageID());
   System.out.println("Message Hash: " + newMsg.getMessageHash());
   System.out.println("Recipient: " + newMsg.getRecipient());
 System.out.println("Message: " + newMsg.getMessage());

  }

   else {

   System.out.println("Invalid option. Please try again.");

  i--;
   }
    }

   }
 // ---- SHOW TOTAL SENT MESSAGES ----
 
 
   

        // Your send messages code

    // ---- OPTION 2: SHOW TOTAL SENT ----
    else if (choice.equals("2")) {

    System.out.println(
      "\nTotal messages successfully sent: "
         + totalSent
         );

     }

                // ---- OPTION 3: QUIT ----
     else if (choice.equals("3")) {

     System.out.println(
      "\nTotal messages successfully sent: "
   + totalSent
      );

    System.out.println("Thank you for using QuickChat!");

  running = false;

     }

     //part 3 options 
     else if (choice.equals("4")) {
            displayLongestMessage();
        }
        else if (choice.equals("5")) {
            searchByMessageID(input);
        }
        else if (choice.equals("6")) {
            searchByRecipient(input);
        }
        else if (choice.equals("7")) {
            deleteMessageByHash(input);
        }
        else if (choice.equals("8")) {
            displayReport();
        }
      else {

     System.out.println(
       "Invalid menu option. Please try again."
       );
       }
        }

    } else {

  System.out.println(
   "Username or password incorrect. Please try again."
            );
        }

        input.close();
       }
    }
    
    //part 3 Methods
     private static void displayLongestMessage() {
        Message longestMsg = null;
        int maxLength = 0;
        
         // Check across all arrays
        ArrayList<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(storedMessages);
        allMessages.addAll(disregardedMessages);

        for (Message msg : allMessages) {
            if (msg.getMessage().length() > maxLength) {
                maxLength = msg.getMessage().length();
                longestMsg = msg;
            }
        }

        if (longestMsg != null) {
            System.out.println("\nThe longest message is: \"" + longestMsg.getMessage() + "\"");
        } else {
            System.out.println("\nNo messages found.");
        }
    }

    private static void searchByMessageID(Scanner input) {
        System.out.print("Enter Message ID to search: ");
        String id = input.nextLine();
        
        for (Message msg : sentMessages) {
            if (msg.getMessageID().equals(id)) {
                System.out.println("\nFound! Message: \"" + msg.getMessage() + "\"");
                return;
            }
        }
        System.out.println("\nMessage ID not found in sent messages.");
    }

    private static void searchByRecipient(Scanner input) {
        System.out.print("Enter recipient number to search (+27...): ");
        String recipient = input.nextLine();
        boolean found = false;
        
        for (Message msg : sentMessages) {
            if (msg.getRecipient().equals(recipient)) {
                System.out.println("Sent: " + msg.getMessage());
                found = true;
            }
        }
        for (Message msg : storedMessages) {
            if (msg.getRecipient().equals(recipient)) {
                System.out.println("Stored: " + msg.getMessage());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    private static void deleteMessageByHash(Scanner input) {
        System.out.print("Enter Message Hash to delete: ");
        String hash = input.nextLine();
        
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).getMessageHash().equals(hash)) {
                Message deleted = sentMessages.remove(i);
                System.out.println("\nMessage: \"" + deleted.getMessage() + "\" successfully deleted.");
                return;
            }
        }
        System.out.println("\nMessage Hash not found in sent messages.");
    }

    private static void displayReport() {
        if (sentMessages.isEmpty()) {
            System.out.println("\nNo sent messages to report.");
            return;
        }
        
        System.out.println("\n--- MESSAGE REPORT ---");
        for (Message msg : sentMessages) {
            System.out.println("Message Hash: " + msg.getMessageHash());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getMessage());
            System.out.println("-------------------------");
        }
    }
}
