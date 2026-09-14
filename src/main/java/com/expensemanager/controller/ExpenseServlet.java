package com.expensemanager.controller;

import com.expensemanager.dao.ExpenseDAO;
import com.expensemanager.model.Expense;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/add-expense")
public class ExpenseServlet extends HttpServlet {

    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int categoryId =
                Integer.parseInt(request.getParameter("categoryId"));

        double amount =
                Double.parseDouble(request.getParameter("amount"));

        LocalDate expenseDate =
                LocalDate.parse(request.getParameter("expenseDate"));

        String description =
                request.getParameter("description");

        Expense expense = new Expense(
                categoryId,
                amount,
                expenseDate,
                description
        );

        boolean result = expenseDAO.addExpense(expense);

        if (result) {
            response.sendRedirect("dashboard");
        } else {
            response.getWriter().println("Failed to add expense.");
        }
    }
}