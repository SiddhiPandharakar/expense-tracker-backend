package com.expensetracker.backend.service;

import com.expensetracker.backend.dao.ExpenseDAO;
import com.expensetracker.backend.model.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ExpenseService {

    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    public boolean addExpense(Expense expense) {

        if (expense.getUserId() <= 0) {
            System.out.println("Invalid User ID.");
            return false;
        }

        if (expense.getAmount() == null ||
                expense.getAmount().compareTo(BigDecimal.ZERO) <= 0) {

            System.out.println("Amount must be greater than 0.");
            return false;
        }

        if (expense.getCategory() == null ||
                expense.getCategory().isBlank()) {

            System.out.println("Category cannot be empty.");
            return false;
        }

        if (expense.getExpenseDate() == null) {

            System.out.println("Expense date is required.");
            return false;
        }

        if (expense.getExpenseDate().isAfter(LocalDate.now())) {

            System.out.println("Expense date cannot be in the future.");
            return false;
        }

        return expenseDAO.saveExpense(expense);
    }

    public List<Expense> getExpensesByUser(int userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid User ID.");
        }

        return expenseDAO.getExpensesByUser(userId);
    }
}