<%@ page import="java.util.List" %>
<%@ page import="com.expensemanager.model.Income" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Income History</title>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/income-history.css">
</head>

<body>

<div class="income-page">

    <div class="income-header">
        <h1>Income History</h1>
    </div>

    <!-- Search / Filter -->

    <div class="filter-card">

        <h2>Search / Filter Income</h2>

        <form action="income-history" method="get">

            <div class="filter-row">

                <div class="filter-group">
                    <label>Source</label>

                    <input type="text"
                           name="source"
                           placeholder="Salary, Freelance">
                </div>

                <div class="filter-group">
                    <label>Date</label>

                    <input type="date"
                           name="incomeDate">
                </div>

                <div class="filter-group">
                    <label>Amount</label>

                    <input type="number"
                           name="amount"
                           step="0.01"
                           min="0">
                </div>

                <button type="submit" class="filter-button">
                    Search
                </button>

                <a href="income-history"
                   class="clear-button">
                    Clear
                </a>

            </div>

        </form>

    </div>

    <!-- Income Table -->

    <div class="table-card">

        <table class="income-table">

            <tr>
                <th>Date</th>
                <th>Source</th>
                <th>Description</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>

            <%
                List<Income> incomes =
                        (List<Income>) request.getAttribute("incomes");

                for (Income income : incomes) {
            %>

            <tr>

                <td>
                    <%= income.getIncomeDate() %>
                </td>

                <td>
                    <%= income.getSource() %>
                </td>

                <td>
                    <%= income.getDescription() %>
                </td>

                <td>
                    &#8377;<%= income.getAmount() %>
                </td>

                <td>

                    <form action="income-history"
                          method="post"
                          style="display:inline;">

                        <input type="hidden"
                               name="incomeId"
                               value="<%= income.getIncomeId() %>">

                        <button type="submit"
                                class="delete-button">
                            Delete
                        </button>

                    </form>

                </td>

            </tr>

            <%
                }
            %>

        </table>

    </div>

    <div class="income-actions">

        <a href="add-income.html">
            + Add Income
        </a>

        <a href="expense-history">
            Expense History
        </a>

        <a href="dashboard">
            ← Back to Dashboard
        </a>

    </div>

</div>

</body>
</html>