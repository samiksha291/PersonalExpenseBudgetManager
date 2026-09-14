<%@ page import="java.util.List" %>
<%@ page import="com.expensemanager.model.Expense" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Expense History</title>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/expense-history.css">
</head>

<body>

<div class="expense-page">

    <div class="expense-header">
        <h1>Expense History</h1>
    </div>

    <!-- Search / Filter -->

    <div class="filter-card">

        <h2>Search / Filter Expenses</h2>

        <form action="expense-history" method="get">

            <div class="filter-row">

                <div class="filter-group">
                    <label>Category</label>

                    <select name="categoryId">
                        <option value="">All</option>
                        <option value="1">Food</option>
                        <option value="2">Travel</option>
                        <option value="3">Shopping</option>
                        <option value="4">Bills</option>
                        <option value="5">Entertainment</option>
                        <option value="6">Healthcare</option>
                        <option value="7">Education</option>
                        <option value="8">Other</option>
                    </select>
                </div>

                <div class="filter-group">
                    <label>Date</label>

                    <input type="date"
                           name="expenseDate">
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

                <a href="expense-history"
                   class="clear-button">
                    Clear
                </a>

            </div>

        </form>

    </div>

    <!-- Expense Table -->

    <div class="table-card">

        <table class="expense-table">

            <tr>
                <th>Date</th>
                <th>Category ID</th>
                <th>Description</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>

            <%
                List<Expense> expenses =
                        (List<Expense>) request.getAttribute("expenses");

                for (Expense expense : expenses) {
            %>

            <tr>

                <td>
                    <%= expense.getExpenseDate() %>
                </td>

                <td>
                    <%= expense.getCategoryId() %>
                </td>

                <td>
                    <%= expense.getDescription() %>
                </td>

                <td>
                    &#8377;<%= expense.getAmount() %>
                </td>

                <td>

                    <a class="edit-link"
                       href="edit-expense?expenseId=<%= expense.getExpenseId() %>">
                        Edit
                    </a>

                    <form action="expense-history"
                          method="post"
                          style="display:inline;">

                        <input type="hidden"
                               name="expenseId"
                               value="<%= expense.getExpenseId() %>">

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

    <div class="expense-actions">

        <a href="add-expense.html">
            + Add Another Expense
        </a>

        <a href="dashboard">
            ← Back to Dashboard
        </a>

    </div>

</div>

</body>
</html>