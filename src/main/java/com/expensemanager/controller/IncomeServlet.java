package com.expensemanager.controller;

import com.expensemanager.dao.IncomeDAO;
import com.expensemanager.model.Income;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/add-income")
public class IncomeServlet extends HttpServlet {

    private final IncomeDAO incomeDAO = new IncomeDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String source =
                request.getParameter("source");

        double amount =
                Double.parseDouble(request.getParameter("amount"));

        LocalDate incomeDate =
                LocalDate.parse(request.getParameter("incomeDate"));

        String description =
                request.getParameter("description");

        Income income = new Income(
                source,
                amount,
                incomeDate,
                description
        );

        boolean result =
                incomeDAO.addIncome(income);

        if (result) {
            response.sendRedirect("income-success.html");
        } else {
            response.getWriter().println("Failed to add income.");
        }
    }
}