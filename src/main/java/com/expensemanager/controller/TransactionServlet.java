package com.expensemanager.controller;

import com.expensemanager.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/transactions")
public class TransactionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String sql = """
                SELECT income_date AS transaction_date,
                       'Income' AS type,
                       source AS category,
                       description,
                       amount
                FROM income

                UNION ALL

                SELECT e.expense_date AS transaction_date,
                       'Expense' AS type,
                       c.category_name AS category,
                       e.description,
                       e.amount
                FROM expenses e
                JOIN categories c
                ON e.category_id = c.category_id

                ORDER BY transaction_date DESC
                """;

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            request.setAttribute("resultSet", resultSet);
            request.setAttribute("connection", connection);
            request.setAttribute("statement", statement);

            request.getRequestDispatcher("/transactions.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(
                    "Failed to load transactions."
            );
        }
    }
}