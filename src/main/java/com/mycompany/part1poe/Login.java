/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1poe;

/**
 *
 * @author Student
 */
public class Login {
    
    // Class variables (permanent storage)
    private String UserName;
    private String Password;
    private String PhoneNumber;
    private String FirstName;
    private String LastName;

    // Method 1 check the username
    public boolean checkUserName(String username) {
        boolean hasUnderscore = false;
       
        // 1. Check length (not more than 5)
        if (username.length() > 5) {
            return false;
        }

        // 2. Loop through each letter to look for an underscore
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == '_') {
                hasUnderscore = true;
            }
        }

        // 3. Return true only if we found the underscore
        if (hasUnderscore == true) {
            return true;
        } else {
            return false;
        }
    }
    // METHOD 2: Check Password Complexity
    
    public boolean checkPasswordComplexity(String password) {
        // 1. Check length
        if (password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // 2. Loop through the password to check the rest
        for (int i = 0; i < password.length(); i++) {
            char currentLetter = password.charAt(i);

            if (currentLetter >= 'A' && currentLetter <= 'Z') {
                hasCapital = true;
            } else if (currentLetter >= '0' && currentLetter <= '9') {
                hasNumber = true;
            } else if ((currentLetter >= 'a' && currentLetter <= 'z') == false 
                    && (currentLetter >= 'A' && currentLetter <= 'Z') == false 
                    && (currentLetter >= '0' && currentLetter <= '9') == false) {
                hasSpecial = true;
            }
        } // ← CLOSE THE FOR LOOP

        // 3. Check if all rules were met
        if (hasCapital == true && hasNumber == true && hasSpecial == true) {
            return true;
        } else {
            return false;
        }
    } 

    //  METHOD 3: Check Cell Phone Number
   
    public boolean checkCellPhoneNumber(String phoneNumber) {
        
        // STEP 1: Check the length (must be exactly 12 chars)
        if (phoneNumber.length() != 12) {
            return false;
        }

        // STEP 2: Check if it starts with +27
        if (phoneNumber.startsWith("+27") == false) {
            return false;
        }

        // STEP 3: Check that positions 3 to 11 are all numbers
        for (int i = 3; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
     
        // STEP 4: All checks passed!
        return true;
    }

    //  METHOD 4: Register User
    
    public String registerUser(String username, String password, String phoneNumber, String firstName, String lastName) {
        String finalMessage = "";

        // Check username
        if (checkUserName(username) == true) {
            finalMessage = finalMessage + "Username successfully captured.\n";
        } else {
            finalMessage = finalMessage + "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.\n";
        }

        // Check password
        if (checkPasswordComplexity(password) == true) {
            finalMessage = finalMessage + "Password successfully captured.\n";
        } else {
            finalMessage = finalMessage + "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n";
        }

        // Check phone
        if (checkCellPhoneNumber(phoneNumber) == true) {
            finalMessage = finalMessage + "Cell phone number successfully added.\n";
        } else {
            finalMessage = finalMessage + "Cell phone number incorrectly formatted or does not contain international code.\n";
        }

        // If EVERYTHING passed, save the details
        if (checkUserName(username) && checkPasswordComplexity(password) && checkCellPhoneNumber(phoneNumber)) {
            UserName = username;         // Save method parameter into class variable
            Password = password;
            PhoneNumber = phoneNumber;
            FirstName = firstName;
            LastName = lastName;
        }

        return finalMessage;
    }
}
