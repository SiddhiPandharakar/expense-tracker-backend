package com.expensetracker.backend.service;

import com.expensetracker.backend.dao.UserDAO;
import com.expensetracker.backend.model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
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
        User user = userDAO.findUserByEmail(email);
        if (user == null) {
            System.out.println("User not found");
            return false;
        }
        if (!user.getPassword().equals(password)) {
            System.out.println("Invalid");
            return false;

        }
        return true;

    }
}
