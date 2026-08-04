package com.expensetracker.backend;

import com.expensetracker.backend.model.User;
import com.expensetracker.backend.service.UserService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();

        System.out.println("=================================");
        System.out.println(" Expense Tracker Backend ");
        System.out.println("=================================");

        System.out.println("1. Register");
        System.out.println("2. Login");

        System.out.print("Choose Option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:

                User user = new User();

                System.out.print("Full Name: ");
                user.setFullName(scanner.nextLine());

                System.out.print("Email: ");
                user.setEmail(scanner.nextLine());

                System.out.print("Password: ");
                user.setPassword(scanner.nextLine());

                boolean registered = userService.registerUser(user);

                if (registered)
                    System.out.println("Registration Successful");
                else
                    System.out.println("Registration Failed");

                break;

            case 2:

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                boolean loggedIn = userService.loginUser(email, password);

                if (loggedIn)
                    System.out.println("Login Successful");
                else
                    System.out.println("Login Failed");

                break;

            default:
                System.out.println("Invalid Choice");
        }

        scanner.close();
    }
}