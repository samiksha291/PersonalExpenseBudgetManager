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

@WebServlet("/summary")
public class SummaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String sql = """
                SELECT
                    (SELECT COALESCE(SUM(amount), 0)
                     FROM income) AS total_income,

                    (SELECT COALESCE(SUM(amount), 0)
                     FROM expenses) AS total_expenses,

                    (SELECT COALESCE(c.category_name, 'None')
                     FROM expenses e
                     JOIN categories c
                     ON e.category_id = c.category_id
                     GROUP BY c.category_id, c.category_name
                     ORDER BY SUM(e.amount) DESC
                     LIMIT 1) AS highest_category
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {

                double totalIncome =
                        resultSet.getDouble("total_income");

                double totalExpenses =
                        resultSet.getDouble("total_expenses");

                String highestCategory =
                        resultSet.getString("highest_category");

                request.setAttribute("totalIncome", totalIncome);
                request.setAttribute("totalExpenses", totalExpenses);
                request.setAttribute("highestCategory",
                        highestCategory);

                request.setAttribute(
                        "remaining",
                        totalIncome - totalExpenses
                );
            }

            request.getRequestDispatcher("/summary.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(
                    "Failed to generate summary."
            );
        }
    }
}