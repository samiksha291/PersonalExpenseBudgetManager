package com.expensemanager.dao;

import com.expensemanager.model.Income;
import com.expensemanager.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {

    public boolean addIncome(Income income) {

        String sql = """
                INSERT INTO income
                (source, amount, income_date, description)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, income.getSource());
            statement.setDouble(2, income.getAmount());

            statement.setDate(
                    3,
                    java.sql.Date.valueOf(income.getIncomeDate())
            );

            statement.setString(4, income.getDescription());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Income> getAllIncome() {

        List<Income> incomes = new ArrayList<>();

        String sql = """
                SELECT income_id, source, amount, income_date, description
                FROM income
                ORDER BY income_date DESC
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Income income = new Income();

                income.setIncomeId(
                        resultSet.getInt("income_id"));

                income.setSource(
                        resultSet.getString("source"));

                income.setAmount(
                        resultSet.getDouble("amount"));

                income.setIncomeDate(
                        resultSet.getDate("income_date").toLocalDate());

                income.setDescription(
                        resultSet.getString("description"));

                incomes.add(income);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return incomes;
    }
    public boolean deleteIncome(int incomeId) {

        String sql = "DELETE FROM income WHERE income_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, incomeId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Income> searchIncome(
            String source,
            java.time.LocalDate incomeDate,
            Double amount) {

        List<Income> incomes = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
            SELECT income_id, source, amount, income_date, description
            FROM income
            WHERE 1=1
            """);

        List<Object> parameters = new ArrayList<>();

        if (source != null && !source.isBlank()) {
            sql.append(" AND source LIKE ?");
            parameters.add("%" + source + "%");
        }

        if (incomeDate != null) {
            sql.append(" AND income_date = ?");
            parameters.add(java.sql.Date.valueOf(incomeDate));
        }

        if (amount != null) {
            sql.append(" AND amount = ?");
            parameters.add(amount);
        }

        sql.append(" ORDER BY income_date DESC");

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Income income = new Income();

                    income.setIncomeId(
                            resultSet.getInt("income_id"));

                    income.setSource(
                            resultSet.getString("source"));

                    income.setAmount(
                            resultSet.getDouble("amount"));

                    income.setIncomeDate(
                            resultSet.getDate("income_date").toLocalDate());

                    income.setDescription(
                            resultSet.getString("description"));

                    incomes.add(income);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return incomes;
    }
}