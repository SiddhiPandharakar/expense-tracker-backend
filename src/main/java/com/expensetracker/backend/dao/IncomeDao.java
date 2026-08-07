package com.expensetracker.backend.dao;

import com.expensetracker.backend.config.DBConnection;
import com.expensetracker.backend.model.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncomeDao {

    public boolean saveIncome(Income income){
        String sql = """
                INSERT INTO income 
                (user_id ,amount , source , income_date)
                VALUES(?,?,?,?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1, income.getUserId());
            preparedStatement.setBigDecimal(2, income.getAmount());
            preparedStatement.setString(3, income.getSource());
            preparedStatement.setDate(4, java.sql.Date.valueOf(income.getIncomeDate()));
            int rows = preparedStatement.executeUpdate();
            return rows >0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
