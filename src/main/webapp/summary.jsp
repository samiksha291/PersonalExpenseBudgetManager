<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Monthly Summary</title>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/summary.css">
</head>

<body>

<div class="summary-page">

    <div class="summary-header">
        <h1>Monthly Summary</h1>
    </div>

    <div class="summary-grid">

        <div class="summary-card">
            <h2>Total Income</h2>
            <p class="summary-value">
                &#8377;<%= request.getAttribute("totalIncome") %>
            </p>
        </div>

        <div class="summary-card">
            <h2>Total Expenses</h2>
            <p class="summary-value">
                &#8377;<%= request.getAttribute("totalExpenses") %>
            </p>
        </div>

        <div class="summary-card">
            <h2>Highest Expense Category</h2>
            <p class="summary-value">
                <%= request.getAttribute("highestCategory") %>
            </p>
        </div>

        <div class="summary-card">
            <h2>Remaining</h2>
            <p class="summary-value">
                &#8377;<%= request.getAttribute("remaining") %>
            </p>
        </div>

    </div>

    <div class="summary-actions">
        <a href="transactions">Transaction History</a>
        <a href="dashboard">Back to Dashboard</a>
    </div>

</div>

</body>
</html>