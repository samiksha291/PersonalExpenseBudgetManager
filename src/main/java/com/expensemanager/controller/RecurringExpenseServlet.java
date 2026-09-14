package com.expensemanager.controller;

import com.expensemanager.dao.RecurringExpenseDAO;
import com.expensemanager.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

@WebServlet("/recurring-expense")
public class RecurringExpenseServlet extends HttpServlet {

    private final RecurringExpenseDAO recurringExpenseDAO =
            new RecurringExpenseDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name =
                request.getParameter("expenseName");

        double amount =
                Double.parseDouble(
                        request.getParameter("amount"));

        int categoryId =
                Integer.parseInt(
                        request.getParameter("categoryId"));

        String frequency =
                request.getParameter("frequency");

        String nextDueDate =
                request.getParameter("nextDueDate");

        String description =
                request.getParameter("description");

        boolean result =
                recurringExpenseDAO.addRecurringExpense(
                        name,
                        amount,
                        categoryId,
                        frequency,
                        nextDueDate,
                        description
                );

        if (result) {
            response.sendRedirect("recurring-expense");
        } else {
            response.getWriter().println(
                    "Failed to save recurring expense."
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection connection =
                     DBConnection.getConnection()) {

            ResultSet resultSet =
                    recurringExpenseDAO
                            .getAllRecurringExpenses(connection);

            request.setAttribute(
                    "recurringExpenses",
                    resultSet);

            request.setAttribute(
                    "connection",
                    connection);

            request.getRequestDispatcher(
                    "recurring-expense-view.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Unable to load recurring expenses."
            );
        }
    }
}