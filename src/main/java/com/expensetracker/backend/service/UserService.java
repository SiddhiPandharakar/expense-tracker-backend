package com.expensetracker.backend.service;

import com.expensetracker.backend.dao.UserDAO;
import com.expensetracker.backend.model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {

        if (user.getFullName() == null || user.getFullName().isBlank()) {
            System.out.println("Full name cannot be empty.");
            return false;
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            System.out.println("Email cannot be empty");
            return false;
        }
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            System.out.println("Password cant be null else Password must be at least 8 characters.");
            return false;
        }
        User existingUser = userDAO.findUserByEmail(user.getEmail());
        if (existingUser != null) {
            System.out.println("Email already registered.");
            return false;
        }
        return userDAO.saveUser(user);
    }

    public boolean loginUser(String email, String password) {


        if (email == null || email.isBlank()) {
            System.out.println("Email cannot be empty.");
            return false;
        }

        if (password == null || password.isBlank()) {
            System.out.println("Password cannot be empty.");
            return false;
        }

        User user = userDAO.findUserByEmail(email);

        if (user == null) {
            System.out.println("User not found.");
            return false;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Invalid password.");
            return false;
        }

        return true;
    }

}