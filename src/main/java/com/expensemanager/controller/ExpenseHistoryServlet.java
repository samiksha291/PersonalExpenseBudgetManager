package com.expensemanager.controller;

import com.expensemanager.dao.ExpenseDAO;
import com.expensemanager.model.Expense;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

@WebServlet("/expense-history")
public class ExpenseHistoryServlet extends HttpServlet {

    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String categoryParam = request.getParameter("categoryId");
        String dateParam = request.getParameter("expenseDate");
        String amountParam = request.getParameter("amount");

        Integer categoryId = null;
        LocalDate expenseDate = null;
        Double amount = null;

        if (categoryParam != null && !categoryParam.isBlank()) {
            categoryId = Integer.parseInt(categoryParam);
        }

        if (dateParam != null && !dateParam.isBlank()) {
            expenseDate = LocalDate.parse(dateParam);
        }

        if (amountParam != null && !amountParam.isBlank()) {
            amount = Double.parseDouble(amountParam);
        }

        List<Expense> expenses =
                expenseDAO.searchExpenses(
                        categoryId,
                        expenseDate,
                        amount
                );

        request.setAttribute("expenses", expenses);

        request.getRequestDispatcher("/expense-history.jsp")
                .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int expenseId =
                Integer.parseInt(request.getParameter("expenseId"));

        boolean result = expenseDAO.deleteExpense(expenseId);

        response.sendRedirect("expense-history");
    }
}