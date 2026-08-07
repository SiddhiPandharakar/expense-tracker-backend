package com.expensetracker.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income {

    private int id;
    private int userId;
    private BigDecimal amount;
    private String source;
    private LocalDate incomeDate;

    public Income() {
    }

    public Income(int id, int userId, BigDecimal amount, String source, LocalDate incomeDate) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.source = source;
        this.incomeDate = incomeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", source='" + source + '\'' +
                ", incomeDate=" + incomeDate +
                '}';
    }
}