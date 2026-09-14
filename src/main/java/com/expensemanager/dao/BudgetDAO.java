package com.expensemanager.dao;

import com.expensemanager.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BudgetDAO {

    public boolean setBudget(int month,
                             int year,
                             double amount) {

        String monthYear =
                String.format("%04d-%02d", year, month);

        String sql = """
                INSERT INTO budget
                (month_year, budget_amount)
                VALUES (?, ?)
                ON DUPLICATE KEY UPDATE
                budget_amount = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, monthYear);
            statement.setDouble(2, amount);
            statement.setDouble(3, amount);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getBudget(int month,
                            int year) {

        String monthYear =
                String.format("%04d-%02d", year, month);

        String sql = """
                SELECT budget_amount
                FROM budget
                WHERE month_year = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, monthYear);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("budget_amount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}