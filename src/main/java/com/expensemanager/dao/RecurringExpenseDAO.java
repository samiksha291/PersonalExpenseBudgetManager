package com.expensemanager.dao;

import com.expensemanager.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecurringExpenseDAO {

    public boolean addRecurringExpense(
            String name,
            double amount,
            int categoryId,
            String frequency,
            String nextDueDate,
            String description) {

        String sql = """
                INSERT INTO recurring_expenses
                (expense_name, amount, category_id,
                 frequency, next_due_date, description)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setDouble(2, amount);
            statement.setInt(3, categoryId);
            statement.setString(4, frequency);

            statement.setDate(
                    5,
                    java.sql.Date.valueOf(nextDueDate)
            );

            statement.setString(6, description);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getAllRecurringExpenses(
            Connection connection) throws Exception {

        String sql = """
                SELECT r.recurring_id,
                       r.expense_name,
                       r.amount,
                       c.category_name,
                       r.frequency,
                       r.next_due_date,
                       r.description
                FROM recurring_expenses r
                JOIN categories c
                    ON r.category_id = c.category_id
                ORDER BY r.next_due_date ASC
                """;

        PreparedStatement statement =
                connection.prepareStatement(sql);

        return statement.executeQuery();
    }
}