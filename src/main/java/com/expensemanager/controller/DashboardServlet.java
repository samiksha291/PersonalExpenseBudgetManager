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

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String incomeSql =
                "SELECT COALESCE(SUM(amount), 0) FROM income";

        String expenseSql =
                "SELECT COALESCE(SUM(amount), 0) FROM expenses";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement incomeStatement =
                     connection.prepareStatement(incomeSql);
             PreparedStatement expenseStatement =
                     connection.prepareStatement(expenseSql);
             ResultSet incomeResult =
                     incomeStatement.executeQuery();
             ResultSet expenseResult =
                     expenseStatement.executeQuery()) {

            double totalIncome = 0;
            double totalExpenses = 0;

            if (incomeResult.next()) {
                totalIncome = incomeResult.getDouble(1);
            }

            if (expenseResult.next()) {
                totalExpenses = expenseResult.getDouble(1);
            }

            double remaining = totalIncome - totalExpenses;

            request.setAttribute("totalIncome", totalIncome);
            request.setAttribute("totalExpenses", totalExpenses);
            request.setAttribute("remaining", remaining);

            request.getRequestDispatcher("/dashboard.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(
                    "Failed to load dashboard."
            );
        }
    }
}