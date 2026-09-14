package com.expensemanager.model;

import java.time.LocalDate;

public class Income {

    private int incomeId;
    private String source;
    private double amount;
    private LocalDate incomeDate;
    private String description;

    public Income() {
    }

    public Income(String source,
                  double amount,
                  LocalDate incomeDate,
                  String description) {

        this.source = source;
        this.amount = amount;
        this.incomeDate = incomeDate;
        this.description = description;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}