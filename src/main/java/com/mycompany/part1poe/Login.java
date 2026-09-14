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
    String UserName;
    String Password;
    String PhoneNumber;
    String FirstName;
    String LastName;

    // METHOD 1: Check Username
    public boolean checkUserName(String username) {
        boolean hasUnderscore = false;
       
        // 1. Check length (not more than 5)
        if (username.length() > 5) { // If it's longer than 5, it fails immediately
            return false;
        }

        // 2. Loop through each letter to look for an underscore
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == '_') {
                hasUnderscore = true; // Found one!
            }
        }

        // 3. Return true only if we found the underscore
        if (hasUnderscore == true) {
            return true;
        } else {
            return false;
        }
    }
 
}
