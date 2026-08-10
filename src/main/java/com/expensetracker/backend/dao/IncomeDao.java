package com.expensetracker.backend.dao;

import com.expensetracker.backend.config.DBConnection;
import com.expensetracker.backend.model.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncomeDao {

    public boolean saveIncome(Income income) {
        String sql = """
                INSERT INTO income 
                (user_id ,amount , source , income_date)
                VALUES(?,?,?,?)
                """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, income.getUserId());
            preparedStatement.setBigDecimal(2, income.getAmount());
            preparedStatement.setString(3, income.getSource());
            preparedStatement.setDate(4, java.sql.Date.valueOf(income.getIncomeDate()));
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Income> getIncomeByUser(int userId) {
        List<Income> incomes = new ArrayList<>();
        String sql = """
                SELECT * FROM income
                WHERE user_id = ?
                ORDER BY income_date DESC
                """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Income income = new Income();
                income.setId(resultSet.getInt("id"));
                income.setUserId(resultSet.getInt("user_id"));
                income.setAmount(resultSet.getBigDecimal("amount"));
                income.setIncomeDate(resultSet.getDate("income_date").toLocalDate()
                );
                incomes.add(income);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomes;
    }

    public boolean updateIncome(Income income) {
        String sql = """
                UPDATE income
                SET amount = ?, source = ?, income_date=?
                WHERE id=?
                """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setBigDecimal(1, income.getAmount());
            preparedStatement.setString(2, income.getSource());
            preparedStatement.setDate(3, java.sql.Date.valueOf(income.getIncomeDate()));
            preparedStatement.setInt(4, income.getId());

            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteIncome(int incomeId){
        String sql = " DELETE FROM income WHERE id = ?";
        try(
                Connection connection =  DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1, incomeId);

            int rowAffected  =  preparedStatement.executeUpdate();
            return rowAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    }
