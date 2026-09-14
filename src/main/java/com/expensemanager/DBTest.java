package com.expensemanager;

import com.expensemanager.dao.ExpenseDAO;
import com.expensemanager.model.Expense;

import java.time.LocalDate;

public class DBTest {

    public static void main(String[] args) {

        Expense expense = new Expense(
                1,
                250.00,
                LocalDate.now(),
                "Dinner"
        );

        ExpenseDAO expenseDAO = new ExpenseDAO();

        boolean result = expenseDAO.addExpense(expense);

        if (result) {
            System.out.println("Expense added successfully!");
        } else {
            System.out.println("Failed to add expense.");
        }
    }
}