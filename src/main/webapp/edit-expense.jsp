<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.expensemanager.model.Expense" %>

<%
    Expense expense =
            (Expense) request.getAttribute("expense");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Edit Expense</title>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/edit-expense.css">
</head>

<body>

<div class="edit-expense-page">

    <div class="edit-expense-header">
        <h1>Edit Expense</h1>
    </div>

    <div class="edit-expense-card">

        <form action="edit-expense"
              method="post"
              class="edit-form">

            <input type="hidden"
                   name="expenseId"
                   value="<%= expense.getExpenseId() %>">

            <label>Category</label>

            <select name="categoryId" required>

                <option value="1"
                    <%= expense.getCategoryId() == 1 ? "selected" : "" %>>
                    Food
                </option>

                <option value="2"
                    <%= expense.getCategoryId() == 2 ? "selected" : "" %>>
                    Travel
                </option>

                <option value="3"
                    <%= expense.getCategoryId() == 3 ? "selected" : "" %>>
                    Shopping
                </option>

                <option value="4"
                    <%= expense.getCategoryId() == 4 ? "selected" : "" %>>
                    Bills
                </option>

                <option value="5"
                    <%= expense.getCategoryId() == 5 ? "selected" : "" %>>
                    Entertainment
                </option>

                <option value="6"
                    <%= expense.getCategoryId() == 6 ? "selected" : "" %>>
                    Healthcare
                </option>

                <option value="7"
                    <%= expense.getCategoryId() == 7 ? "selected" : "" %>>
                    Education
                </option>

                <option value="8"
                    <%= expense.getCategoryId() == 8 ? "selected" : "" %>>
                    Other
                </option>

            </select>

            <label>Amount</label>

            <input type="number"
                   name="amount"
                   step="0.01"
                   min="0"
                   value="<%= expense.getAmount() %>"
                   required>

            <label>Date</label>

            <input type="date"
                   name="expenseDate"
                   value="<%= expense.getExpenseDate() %>"
                   required>

            <label>Description</label>

            <input type="text"
                   name="description"
                   value="<%= expense.getDescription() %>"
                   maxlength="255">

            <button type="submit"
                    class="update-button">
                Update Expense
            </button>

        </form>

        <div class="edit-actions">

            <a href="expense-history">
                ← Back to Expense History
            </a>

            <a href="dashboard">
                Back to Dashboard
            </a>

        </div>

    </div>

</div>

</body>
</html>