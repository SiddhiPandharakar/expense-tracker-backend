package com.expensetracker.backend.service;

import com.expensetracker.backend.dao.UserDAO;
import com.expensetracker.backend.model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {

        User existingUser = userDAO.findUserByEmail(user.getEmail());

        if (user.getPassword().length() < 8) {
            System.out.println("Password must be at least 8 characters.");
            return false;
        }

        return userDAO.saveUser(user);

    }

}
