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

@WebServlet("/edit-expense")
public class EditExpenseServlet extends HttpServlet {

    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int expenseId =
                Integer.parseInt(request.getParameter("expenseId"));

        Expense expense =
                expenseDAO.getExpenseById(expenseId);

        if (expense != null) {

            request.setAttribute("expense", expense);

            request.getRequestDispatcher("/edit-expense.jsp")
                    .forward(request, response);

        } else {
            response.getWriter().println("Expense not found.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int expenseId =
                Integer.parseInt(request.getParameter("expenseId"));

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

        expense.setExpenseId(expenseId);

        boolean result =
                expenseDAO.updateExpense(expense);

        if (result) {
            response.sendRedirect("expense-history");
        } else {
            response.getWriter().println("Failed to update expense.");
        }
    }
}