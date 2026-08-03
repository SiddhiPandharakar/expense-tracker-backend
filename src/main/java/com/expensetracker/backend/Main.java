package com.expensetracker.backend;

import com.expensetracker.backend.config.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
public class Main {

    public static void main(String[] args) {

        try
        {
            Connection connection = DBConnection.getConnection();
            if(connection != null){
                System.out.println("Connected");
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(" Database Connection Failed!");
            System.out.println(e.getMessage());
        }
    }
}