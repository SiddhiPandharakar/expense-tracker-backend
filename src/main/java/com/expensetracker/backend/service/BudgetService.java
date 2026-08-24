package com.expensetracker.backend.service;

import com.expensetracker.backend.dao.BudgetDAO;
import com.expensetracker.backend.model.Budget;

import java.math.BigDecimal;
import java.util.List;

public class BudgetService {

    private final BudgetDAO budgetDAO = new BudgetDAO();

    public boolean addBudget(Budget budget) {

        if (budget.getUserId() <= 0) {
            System.out.println("Invalid User ID.");
            return false;
        }

        if (budget.getCategory() == null ||
                budget.getCategory().isBlank()) {

            System.out.println("Category cannot be empty.");
            return false;
        }

        if (budget.getAmount() == null ||
                budget.getAmount().compareTo(BigDecimal.ZERO) <= 0) {

            System.out.println("Budget amount must be greater than zero.");
            return false;
        }

        if (budget.getMonth() < 1 ||
                budget.getMonth() > 12) {

            System.out.println("Month must be between 1 and 12.");
            return false;
        }

        if (budget.getYear() < 2000) {

            System.out.println("Invalid year.");
            return false;
        }

        return budgetDAO.saveBudget(budget);
    }

    public List<Budget> getBudgetsByUser(int userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid User ID.");
        }

        return budgetDAO.getBudgetsByUser(userId);
    }

    public boolean updateBudget(Budget budget) {

        if (budget.getId() <= 0) {
            System.out.println("Invalid Budget ID.");
            return false;
        }

        if (budget.getCategory() == null ||
                budget.getCategory().isBlank()) {

            System.out.println("Category cannot be empty.");
            return false;
        }

        if (budget.getAmount() == null ||
                budget.getAmount().compareTo(BigDecimal.ZERO) <= 0) {

            System.out.println("Budget amount must be greater than zero.");
            return false;
        }

        if (budget.getMonth() < 1 ||
                budget.getMonth() > 12) {

            System.out.println("Month must be between 1 and 12.");
            return false;
        }

        if (budget.getYear() < 2000) {

            System.out.println("Invalid year.");
            return false;
        }

        return budgetDAO.updateBudget(budget);
    }

    public boolean deleteBudget(int budgetId) {

        if (budgetId <= 0) {

            System.out.println("Invalid Budget ID.");
            return false;
        }

        return budgetDAO.deleteBudget(budgetId);
    }
}