package com.expensetracker.backend.dao;

import com.expensetracker.backend.config.DBConnection;
import com.expensetracker.backend.model.Budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetDAO {

    public boolean saveBudget(Budget budget) {

        String sql = """
                INSERT INTO budgets
                (user_id, category, amount, month, year)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, budget.getUserId());
            preparedStatement.setString(2, budget.getCategory());
            preparedStatement.setBigDecimal(3, budget.getAmount());
            preparedStatement.setInt(4, budget.getMonth());
            preparedStatement.setInt(5, budget.getYear());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Budget> getBudgetsByUser(int userId) {

        List<Budget> budgets = new ArrayList<>();

        String sql = """
                SELECT *
                FROM budgets
                WHERE user_id = ?
                ORDER BY year DESC, month DESC
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Budget budget = new Budget();

                budget.setId(resultSet.getInt("id"));
                budget.setUserId(resultSet.getInt("user_id"));
                budget.setCategory(resultSet.getString("category"));
                budget.setAmount(resultSet.getBigDecimal("amount"));
                budget.setMonth(resultSet.getInt("month"));
                budget.setYear(resultSet.getInt("year"));

                budgets.add(budget);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return budgets;
    }

    public boolean updateBudget(Budget budget){
        String sql = """
                Update budgets 
                SET category = ?, amount = ?, month = ?, year = ?,
                WHERE id = ?,
                """;

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setString(1, budget.getCategory());
            preparedStatement.setBigDecimal(2, budget.getAmount());
            preparedStatement.setInt(3, budget.getMonth());
            preparedStatement.setInt(4, budget.getYear());
            preparedStatement.setInt(5, budget.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected>0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBudget(int budgetId) {

        String sql = "DELETE FROM budgets WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, budgetId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}