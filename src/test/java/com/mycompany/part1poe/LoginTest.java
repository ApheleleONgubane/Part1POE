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
       
    } 
    
    // 3. Test Cell Phone Number
    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        // Test Data: +27838968976
        Login obj = new Login();
        
        String cellphoneNumber = "+27838968976";
        boolean results = obj.checkCellPhoneNumber(cellphoneNumber);
        
        assertTrue(results);
        assertTrue(obj.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        // Test Data: 0896653
        Login obj = new Login();
        
        String cellphoneNumber = "0896653";
        boolean results = obj.checkCellPhoneNumber(cellphoneNumber);
        assertFalse(obj.checkCellPhoneNumber("0896653"));
    }

    // 4. Test Registration Messages (assertEquals)
    @Test
    public void testPasswordMeetsRequirements() {
        Login obj = new Login();
        String password = "Ch&sec@ke99!";
        boolean results = obj.checkPasswordComplexity(password);
        assertTrue(results);
    }
   @Test
    public void testPasswordDoesNotMeetRequirements() {
        Login obj = new Login();
        String password = "password";
        boolean results = obj.checkPasswordComplexity(password);
        assertFalse(results);
    }
@Test
public void testLoginSuccessful() {
    // Create a fresh Login object for THIS test
    Login login = new Login();
    
    // First, register a user so the system has data to check against
    login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "Kyle", "Smith");
   
    // Now test login
    assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
}

// 6. Test Login Failed
@Test
public void testLoginFailed() {
    // Create a fresh Login object for THIS test
    Login login = new Login();
    
    // First, register a user
    login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "Kyle", "Smith");
   
    // Test with wrong password -> should be FALSE
    assertFalse(login.loginUser("kyl_1", "wrongpassword"));
}                  
}
