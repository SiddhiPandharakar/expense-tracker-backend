package com.expensetracker.backend.dao;

import com.expensetracker.backend.config.DBConnection;
import com.expensetracker.backend.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserDAO {

    public boolean saveUser(User user){
        String sql = "Insert INTO users(full_name,email,password) VALUES(?,?,?)";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return  rowsAffected > 0;
        } catch (Exception e) {
             e.printStackTrace();
             return false;
        }
    }

    public User findUserByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
