package com.expensemanager.controller;

import com.expensemanager.dao.IncomeDAO;
import com.expensemanager.model.Income;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/income-history")
public class IncomeHistoryServlet extends HttpServlet {

    private final IncomeDAO incomeDAO = new IncomeDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String sourceParam = request.getParameter("source");
        String dateParam = request.getParameter("incomeDate");
        String amountParam = request.getParameter("amount");

        java.time.LocalDate incomeDate = null;
        Double amount = null;

        if (dateParam != null && !dateParam.isBlank()) {
            incomeDate = java.time.LocalDate.parse(dateParam);
        }

        if (amountParam != null && !amountParam.isBlank()) {
            amount = Double.parseDouble(amountParam);
        }

        List<Income> incomes =
                incomeDAO.searchIncome(
                        sourceParam,
                        incomeDate,
                        amount
                );

        request.setAttribute("incomes", incomes);

        request.getRequestDispatcher("/income-history.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int incomeId =
                Integer.parseInt(request.getParameter("incomeId"));

        incomeDAO.deleteIncome(incomeId);

        response.sendRedirect("income-history");
    }
}