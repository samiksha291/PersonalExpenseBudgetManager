<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Transaction History</title>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/transactions.css">
</head>

<body>

<div class="transactions-page">

    <div class="transactions-header">
        <h1>Transaction History</h1>
    </div>

    <div class="transactions-table-card">

        <table class="transactions-table">

            <tr>
                <th>Date</th>
                <th>Type</th>
                <th>Category / Source</th>
                <th>Description</th>
                <th>Amount</th>
            </tr>

            <%
                ResultSet resultSet =
                        (ResultSet) request.getAttribute("resultSet");

                while (resultSet.next()) {
            %>

            <tr>

                <td>
                    <%= resultSet.getDate("transaction_date") %>
                </td>

                <td>
                    <%= resultSet.getString("type") %>
                </td>

                <td>
                    <%= resultSet.getString("category") %>
                </td>

                <td>
                    <%= resultSet.getString("description") %>
                </td>

                <td>
                    &#8377;<%= resultSet.getDouble("amount") %>
                </td>

            </tr>

            <%
                }
            %>

        </table>

    </div>

    <div class="transactions-actions">

        <a href="add-income.html">
            + Add Income
        </a>

        <a href="add-expense.html">
            + Add Expense
        </a>

        <a href="expense-history">
            Expense History
        </a>

        <a href="income-history">
            Income History
        </a>

        <a href="dashboard">
            ← Back to Dashboard
        </a>

    </div>

</div>

</body>
</html>