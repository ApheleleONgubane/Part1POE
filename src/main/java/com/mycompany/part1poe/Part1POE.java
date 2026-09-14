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
               String UserName = input.nextLine();

        System.out.print("Enter username: ");
       
        System.out.print("Enter password: ");
        String Password = input.nextLine();
       
        System.out.print("Enter cell phone number (start with +27): ");
        String PhoneNumber = input.nextLine();

        // Call the register method
        
        
        input.close();
    }
}                  

