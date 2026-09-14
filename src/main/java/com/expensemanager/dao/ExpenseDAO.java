package com.expensemanager.dao;

import com.expensemanager.model.Expense;
import com.expensemanager.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.time.LocalDate;

public class ExpenseDAO {

    public boolean addExpense(Expense expense) {

        String sql = """
                INSERT INTO expenses
                (category_id, amount, expense_date, description)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, expense.getCategoryId());
            statement.setDouble(2, expense.getAmount());
            statement.setDate(3,
                    java.sql.Date.valueOf(expense.getExpenseDate()));
            statement.setString(4, expense.getDescription());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Expense> getAllExpenses() {

        List<Expense> expenses = new ArrayList<>();

        String sql = """
            SELECT expense_id, category_id, amount, expense_date, description
            FROM expenses
            ORDER BY expense_date DESC
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Expense expense = new Expense();

                expense.setExpenseId(resultSet.getInt("expense_id"));
                expense.setCategoryId(resultSet.getInt("category_id"));
                expense.setAmount(resultSet.getDouble("amount"));

                expense.setExpenseDate(
                        resultSet.getDate("expense_date").toLocalDate()
                );

                expense.setDescription(
                        resultSet.getString("description")
                );

                expenses.add(expense);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return expenses;
    }
    public boolean deleteExpense(int expenseId) {

        String sql = "DELETE FROM expenses WHERE expense_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, expenseId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Expense getExpenseById(int expenseId) {

        String sql = """
            SELECT expense_id, category_id, amount, expense_date, description
            FROM expenses
            WHERE expense_id = ?
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, expenseId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Expense expense = new Expense();

                    expense.setExpenseId(
                            resultSet.getInt("expense_id"));

                    expense.setCategoryId(
                            resultSet.getInt("category_id"));

                    expense.setAmount(
                            resultSet.getDouble("amount"));

                    expense.setExpenseDate(
                            resultSet.getDate("expense_date").toLocalDate());

                    expense.setDescription(
                            resultSet.getString("description"));

                    return expense;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public boolean updateExpense(Expense expense) {

        String sql = """
            UPDATE expenses
            SET category_id = ?,
                amount = ?,
                expense_date = ?,
                description = ?
            WHERE expense_id = ?
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, expense.getCategoryId());
            statement.setDouble(2, expense.getAmount());

            statement.setDate(
                    3,
                    java.sql.Date.valueOf(expense.getExpenseDate())
            );

            statement.setString(4, expense.getDescription());
            statement.setInt(5, expense.getExpenseId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Expense> searchExpenses(
            Integer categoryId,
            LocalDate expenseDate,
            Double amount) {

        List<Expense> expenses = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
            SELECT expense_id, category_id, amount, expense_date, description
            FROM expenses
            WHERE 1=1
            """);

        List<Object> parameters = new ArrayList<>();

        if (categoryId != null) {
            sql.append(" AND category_id = ?");
            parameters.add(categoryId);
        }

        if (expenseDate != null) {
            sql.append(" AND expense_date = ?");
            parameters.add(java.sql.Date.valueOf(expenseDate));
        }

        if (amount != null) {
            sql.append(" AND amount = ?");
            parameters.add(amount);
        }

        sql.append(" ORDER BY expense_date DESC");

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Expense expense = new Expense();

                    expense.setExpenseId(
                            resultSet.getInt("expense_id"));

                    expense.setCategoryId(
                            resultSet.getInt("category_id"));

                    expense.setAmount(
                            resultSet.getDouble("amount"));

                    expense.setExpenseDate(
                            resultSet.getDate("expense_date").toLocalDate());

                    expense.setDescription(
                            resultSet.getString("description"));

                    expenses.add(expense);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return expenses;
    }
}