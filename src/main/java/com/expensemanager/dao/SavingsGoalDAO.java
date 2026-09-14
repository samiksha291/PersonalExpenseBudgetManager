package com.expensemanager.dao;

import com.expensemanager.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SavingsGoalDAO {

    // Add a new savings goal
    public boolean addGoal(String goalName,
                           double targetAmount,
                           double currentAmount) {

        String sql = """
                INSERT INTO savings_goal
                (goal_name, target_amount, current_amount)
                VALUES (?, ?, ?)
                """;

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, goalName);
            statement.setDouble(2, targetAmount);
            statement.setDouble(3, currentAmount);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateGoal(int goalId,
                              String goalName,
                              double targetAmount,
                              double currentAmount) {

        String sql = """
            UPDATE savings_goal
            SET goal_name = ?,
                target_amount = ?,
                current_amount = ?
            WHERE goal_id = ?
            """;

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, goalName);
            statement.setDouble(2, targetAmount);
            statement.setDouble(3, currentAmount);
            statement.setInt(4, goalId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get the latest savings goal
    public ResultSet getLatestGoal(Connection connection)
            throws Exception {

        String sql = """
                SELECT goal_id,
                       goal_name,
                       target_amount,
                       current_amount,
                       target_date
                FROM savings_goal
                ORDER BY goal_id DESC
                LIMIT 1
                """;

        PreparedStatement statement =
                connection.prepareStatement(sql);

        return statement.executeQuery();
    }
}