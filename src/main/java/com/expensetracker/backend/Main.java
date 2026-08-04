package com.expensetracker.backend;

import com.expensetracker.backend.model.User;
import com.expensetracker.backend.service.UserService;
import com.expensetracker.backend.model.Expense;
import com.expensetracker.backend.service.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        ExpenseService expenseService = new ExpenseService();

        System.out.println("=================================");
        System.out.println(" Expense Tracker Backend ");
        System.out.println("=================================");

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Add Expense");
        System.out.println("4. Exit");

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

            case 3:

                Expense expense = new Expense();

                System.out.print("User ID: ");
                expense.setUserId(scanner.nextInt());

                System.out.print("Amount: ");
                expense.setAmount(scanner.nextBigDecimal());

                scanner.nextLine();

                System.out.print("Category: ");
                expense.setCategory(scanner.nextLine());

                System.out.print("Description: ");
                expense.setDescription(scanner.nextLine());

                System.out.print("Expense Date (yyyy-mm-dd): ");
                expense.setExpenseDate(LocalDate.parse(scanner.nextLine()));

                boolean expenseSaved = expenseService.addExpense(expense);

                if (expenseSaved)
                    System.out.println("Expense Added Successfully.");
                else
                    System.out.println("Failed to Add Expense.");

                break;

            default:
                System.out.println("Invalid Choice");
        }

        scanner.close();
    }
}