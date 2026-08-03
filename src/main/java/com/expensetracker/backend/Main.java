package com.expensetracker.backend;

import com.expensetracker.backend.model.User;
import com.expensetracker.backend.service.UserService;

public class Main {

    public static void main(String[] args) {

        // Create User Object
        User user = new User();

        user.setFullName("Siddhi");
        user.setEmail("siddhi@gmail.com");
        user.setPassword("123456");

        // Create Service Object
        UserService userService = new UserService();

        // Register User
        boolean isSaved = userService.registerUser(user);

        if (isSaved) {
            System.out.println("✅ User Registered Successfully!");
        } else {
            System.out.println("❌ User Registration Failed!");
        }
    }
}