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
public class LoginTest {
    
    public LoginTest() {
    }

    @Test
    public void TestUsernameCorrectlyFormatted() {
    //create the login object so we can use it to methods
    Login obj = new Login();
    
    // 1. Set up the test data (from your assignment table)
        String username = "kyl_1";
        
        // 2. Call the method and save the result
        boolean results = obj.checkUserName(username);
        
        // 3. Check that it is true
        assertTrue(results);
    }
   @Test
    public void testUsernameIncorrectlyFormatted() {
        // 1. Create the Login object
        Login obj = new Login();
        
        // 2. Test the first method with i // 1. Set up the test data (from your assignment table)
        String username = "kyle!!!!!!";
        
        // 2. Call the method and save the result
        boolean results = obj.checkUserName(username);
        
        // 3. Check that it is false
        assertFalse(results);
        // We expect it to return FALSE
        assertFalse(obj.checkUserName("kyle!!!!!!"));
    } 
    
    
}
