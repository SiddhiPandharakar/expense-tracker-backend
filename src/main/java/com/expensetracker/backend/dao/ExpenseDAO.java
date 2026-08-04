package com.expensetracker.backend.dao;

import com.expensetracker.backend.config.DBConnection;
import com.expensetracker.backend.model.Expense;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExpenseDAO {

    public boolean saveExpense(Expense expense) {

        String sql = """
                INSERT INTO expenses
                (user_id, amount, category, description, expense_date)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, expense.getUserId());

            preparedStatement.setBigDecimal(2, expense.getAmount());

            preparedStatement.setString(3, expense.getCategory());

            preparedStatement.setString(4, expense.getDescription());

            preparedStatement.setDate(
                    5,
                    Date.valueOf(expense.getExpenseDate())
            );

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}