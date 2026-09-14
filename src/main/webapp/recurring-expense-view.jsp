<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.ResultSet" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Recurring Expenses</title>


<link rel="stylesheet" href="css/style.css">

<style>
    .recurring-container {
        max-width: 1100px;
        margin: 40px auto;
    }

    .recurring-title {
        margin-bottom: 25px;
    }

    .recurring-title h1 {
        margin-bottom: 8px;
    }

    .recurring-title p {
        color: #64748b;
    }

    .table-card {
        background: white;
        padding: 25px;
        border-radius: 14px;
        box-shadow: 0 6px 18px rgba(15, 23, 42, 0.08);
        overflow-x: auto;
    }

    .table-card table {
        min-width: 850px;
    }

    .recurring-actions {
        margin-top: 30px;
    }

    .recurring-actions a {
        display: inline-block;
        margin-right: 15px;
        text-decoration: none;
        font-weight: bold;
        color: #2563eb;
    }

    .no-data {
        text-align: center;
        padding: 30px;
        color: #64748b;
    }
</style>

</head>

<body>

<div class="header">
    <h1>🔄 Recurring Expenses</h1>
    <p>Manage your regularly occurring expenses.</p>
</div>

<div class="recurring-container">


<div class="recurring-title">
    <h1>🔄 Your Recurring Expenses</h1>
    <p>All your saved recurring expenses are shown below.</p>
</div>

<div class="table-card">

    <table>

        <thead>
        <tr>
            <th>Expense Name</th>
            <th>Amount</th>
            <th>Category</th>
            <th>Frequency</th>
            <th>Next Due Date</th>
            <th>Description</th>
        </tr>
        </thead>

        <tbody>

        <%
            ResultSet resultSet =
                    (ResultSet) request.getAttribute(
                            "recurringExpenses");

            boolean hasData = false;

            while (resultSet != null &&
                   resultSet.next()) {

                hasData = true;
        %>

        <tr>

            <td>
                <%= resultSet.getString("expense_name") %>
            </td>

            <td>
                &#8377;<%= resultSet.getDouble("amount") %>
            </td>

            <td>
                <%= resultSet.getString("category_name") %>
            </td>

            <td>
                <%= resultSet.getString("frequency") %>
            </td>

            <td>
                <%= resultSet.getDate("next_due_date") %>
            </td>

            <td>
                <%= resultSet.getString("description") != null
                        ? resultSet.getString("description")
                        : "-" %>
            </td>

        </tr>

        <%
            }

            if (!hasData) {
        %>

        <tr>
            <td colspan="6" class="no-data">
                No recurring expenses found.
            </td>
        </tr>

        <%
            }
        %>

        </tbody>

    </table>

</div>

<div class="recurring-actions">
    <a href="recurring-expense.html">
        ➕ Add Recurring Expense
    </a>

    <a href="dashboard">
        ← Back to Dashboard
    </a>
</div>


</div>

</body>

</html>
