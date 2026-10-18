/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part1poe;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Part1POE {

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
     
       if(registrationMessage=="Registration successful"){
        
        // ---- Now try to log in ----
        System.out.println("\n--- Login ---");
        System.out.print("Enter username to login: ");
        String loginUsername = input.nextLine();
        
        System.out.print("Enter password to login: ");
        String loginPassword = input.nextLine();
        
       
         boolean isSuccess = myLoginSystem.loginUser(loginUsername, loginPassword);
       
        // Print the final message
        System.out.println(myLoginSystem.returnLoginStatus(isSuccess));
        
        //Part 2 code 
        if (isSuccess) {
            System.out.println("\nWelcome to QuickChat.");
            
            boolean running = true;
            
            while (running) {
           System.out.println("\nMenu:");
           System.out.println("1) Send Messages");
           System.out.println("2) Show recently sent messages");
           System.out.println("3) Quit");
           System.out.print("Choose an option: ");
                
            String choice = input.nextLine();
                
         if (choice.equals("1")) {
        System.out.print("How many messages do you wish to enter? ");
         int numMessages = Integer.parseInt(input.nextLine());
                    
        for (int i = 0; i < numMessages; i++) {
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
                        
 System.out.println("\n--- Message Details ---");
 System.out.println("Message ID: " + newMsg.getMessageID());
 System.out.println("Message Hash: " + newMsg.createMessageHash());
 System.out.println("Recipient: " + recipient);
 System.out.println("Message: " + msgText);
                        
 System.out.println("\nOptions:");
    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store Message");
    
    System.out.print("Choice: ");
   int action = Integer.parseInt(input.nextLine());
       }
      

         }
            }
        }  
        input.close();
    }
                  

