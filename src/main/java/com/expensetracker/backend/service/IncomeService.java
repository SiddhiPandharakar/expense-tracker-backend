package com.expensetracker.backend.service;

import com.expensetracker.backend.dao.IncomeDao;
import com.expensetracker.backend.model.Income;

import java.awt.color.ICC_ColorSpace;
import java.util.List;

public class IncomeService {

    private final IncomeDao incomeDao = new IncomeDao();

    public boolean addIncome(Income income){
        if(income.getUserId() <= 0){
            System.out.println("Invalid User ID.");
            return false;
        }
        if(income.getAmount() == null || income.getAmount().doubleValue()<=0){
            System.out.println("Amount must be greater than zero.");
            return false;
        }
        if(income.getSource() == null || income.getSource().isBlank()){
            System.out.println("Income source cant be blank.");
            return false;
        }
        if(income.getIncomeDate() == null){
            System.out.println("Income date cannot be empty.");
            return false;
        }
        return incomeDao.saveIncome(income);
    }

    public List<Income> getIncomeByUser(int userId){
        if(userId <= 0){
            throw new IllegalArgumentException("Invalid Id");
        }
        return incomeDao.getIncomeByUser(userId);
    }

    public boolean updateIncome(Income income){
        if(income.getId() <= 0) {
            System.out.println("Invalid");
            return false;
        }
        if(income.getAmount() ==null || income.getAmount().doubleValue()<=0){
            System.out.println("Amout  must be greater than zero");
            return false;
        }
        if (income.getSource() == null ||
                income.getSource().isBlank()) {

            System.out.println("Source cannot be empty.");
            return false;
        }

        return incomeDAO.updateIncome(income);
    }

    public boolean deleteIncome(int incomeId) {

        if (incomeId <= 0) {

            System.out.println("Invalid Income ID.");
            return false;
        }

        return incomeDAO.deleteIncome(incomeId);
    }
}
