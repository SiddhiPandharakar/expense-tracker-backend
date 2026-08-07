package com.expensetracker.backend.dao;

import com.expensetracker.backend.config.DBConnection;
import com.expensetracker.backend.model.Expense;
import com.expensetracker.backend.service.ExpenseService;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
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

    public List<Expense> getExpensesByUser(int userId) {

        List<Expense> expenses = new ArrayList<>();

        String sql = """
            SELECT *
            FROM expenses
            WHERE user_id = ?
            ORDER BY expense_date DESC
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Expense expense = new Expense();

                expense.setId(resultSet.getInt("id"));
                expense.setUserId(resultSet.getInt("user_id"));
                expense.setAmount(resultSet.getBigDecimal("amount"));
                expense.setCategory(resultSet.getString("category"));
                expense.setDescription(resultSet.getString("description"));
                expense.setExpenseDate(resultSet.getDate("expense_date").toLocalDate());

                expenses.add(expense);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenses;
    }
    public boolean updateExpense(Expense expense) {

        String sql = """
            UPDATE expenses
            SET amount = ?,
                category = ?,
                description = ?,
                expense_date = ?
            WHERE id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setBigDecimal(1, expense.getAmount());
            preparedStatement.setString(2, expense.getCategory());
            preparedStatement.setString(3, expense.getDescription());
            preparedStatement.setDate(4, java.sql.Date.valueOf(expense.getExpenseDate()));
            preparedStatement.setInt(5, expense.getId());

            int rows = preparedStatement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteExpenses(int expenseId) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1, expenseId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
