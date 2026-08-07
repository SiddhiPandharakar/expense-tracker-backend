package com.expensetracker.backend.service;

import com.expensetracker.backend.dao.IncomeDao;
import com.expensetracker.backend.model.Income;


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
}
