package com.expensemanager.controller;

import com.expensemanager.dao.BudgetDAO;
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
import java.time.LocalDate;

@WebServlet("/budget")
public class BudgetServlet extends HttpServlet {

    private final BudgetDAO budgetDAO = new BudgetDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int month =
                Integer.parseInt(request.getParameter("month"));

        int year =
                Integer.parseInt(request.getParameter("year"));

        double amount =
                Double.parseDouble(request.getParameter("amount"));

        boolean result =
                budgetDAO.setBudget(month, year, amount);

        if (result) {
            response.sendRedirect("budget");
        } else {
            response.getWriter().println(
                    "Failed to save budget."
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        LocalDate today = LocalDate.now();

        int month = today.getMonthValue();
        int year = today.getYear();

        double budget =
                budgetDAO.getBudget(month, year);

        double totalExpenses = 0;

        String sql = """
                SELECT COALESCE(SUM(amount), 0)
                FROM expenses
                WHERE MONTH(expense_date) = ?
                AND YEAR(expense_date) = ?
                """;

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, month);
            statement.setInt(2, year);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {
                totalExpenses =
                        resultSet.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        double remaining =
                budget - totalExpenses;

        request.setAttribute(
                "month", month);

        request.setAttribute(
                "year", year);

        request.setAttribute(
                "budget", budget);

        request.setAttribute(
                "totalExpenses", totalExpenses);

        request.setAttribute(
                "remaining", remaining);

        request.getRequestDispatcher(
                "budget-view.jsp"
        ).forward(request, response);
    }
}