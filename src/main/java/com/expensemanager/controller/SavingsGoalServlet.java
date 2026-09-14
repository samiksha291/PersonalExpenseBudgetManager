package com.expensemanager.controller;

import com.expensemanager.dao.SavingsGoalDAO;
import com.expensemanager.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

@WebServlet("/savings-goal")
public class SavingsGoalServlet extends HttpServlet {

    private final SavingsGoalDAO savingsGoalDAO =
            new SavingsGoalDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String goalIdParameter =
                request.getParameter("goalId");

        String goalName =
                request.getParameter("goalName");

        double targetAmount =
                Double.parseDouble(
                        request.getParameter("targetAmount"));

        double currentAmount =
                Double.parseDouble(
                        request.getParameter("currentAmount"));

        boolean result;

        if (goalIdParameter != null &&
                !goalIdParameter.isEmpty()) {

            int goalId =
                    Integer.parseInt(goalIdParameter);

            result =
                    savingsGoalDAO.updateGoal(
                            goalId,
                            goalName,
                            targetAmount,
                            currentAmount
                    );

        } else {

            result =
                    savingsGoalDAO.addGoal(
                            goalName,
                            targetAmount,
                            currentAmount
                    );
        }

        if (result) {
            response.sendRedirect("savings-goal");
        } else {
            response.getWriter().println(
                    "Failed to save savings goal."
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
                    savingsGoalDAO.getLatestGoal(connection);

            if (resultSet.next()) {
                int goalId =
                        resultSet.getInt("goal_id");

                String goalName =
                        resultSet.getString("goal_name");

                double targetAmount =
                        resultSet.getDouble("target_amount");

                double currentAmount =
                        resultSet.getDouble("current_amount");

                double remaining =
                        targetAmount - currentAmount;

                double progress = 0;

                if (targetAmount > 0) {
                    progress =
                            (currentAmount / targetAmount) * 100;
                }

                if (progress > 100) {
                    progress = 100;
                }
                request.setAttribute(
                        "goalId", goalId);

                request.setAttribute(
                        "goalName", goalName);

                request.setAttribute(
                        "targetAmount", targetAmount);

                request.setAttribute(
                        "currentAmount", currentAmount);

                request.setAttribute(
                        "remaining", remaining);

                request.setAttribute(
                        "progress", progress);




            } else {

                request.setAttribute(
                        "goalName", null);
            }

            request.getRequestDispatcher(
                    "savings-goal-view.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Unable to load savings goal."
            );
        }
    }
}