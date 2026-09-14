<%@ page contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Monthly Budget</title>

```
<link rel="stylesheet" href="css/style.css">

<style>
    .budget-container {
        max-width: 900px;
        margin: 40px auto;
    }

    .budget-title {
        margin-bottom: 25px;
    }

    .budget-title h1 {
        margin-bottom: 8px;
    }

    .budget-title p {
        color: #64748b;
    }

    .budget-cards {
        display: flex;
        gap: 20px;
        flex-wrap: wrap;
    }

    .budget-card {
        flex: 1;
        min-width: 220px;
        background: white;
        padding: 25px;
        border-radius: 14px;
        box-shadow: 0 6px 18px rgba(15, 23, 42, 0.08);
        border-top: 4px solid #2563eb;
    }

    .budget-card h3 {
        color: #64748b;
        margin-top: 0;
    }

    .budget-amount {
        font-size: 26px;
        font-weight: bold;
    }

    .spent {
        color: #dc2626;
    }

    .remaining {
        color: #16a34a;
    }

    .budget-actions {
        margin-top: 30px;
    }

    .budget-actions a {
        display: inline-block;
        margin-right: 15px;
        text-decoration: none;
        font-weight: bold;
        color: #2563eb;
    }
</style>
```

</head>

<body>

<div class="header">
    <h1>💳 Monthly Budget</h1>
    <p>Track your monthly budget and spending.</p>
</div>

<div class="budget-container">

```
<div class="budget-title">
    <h1>Budget for <%= request.getAttribute("month") %>/<%= request.getAttribute("year") %></h1>
    <p>Here is your current monthly budget summary.</p>
</div>

<div class="budget-cards">

    <div class="budget-card">
        <h3>💳 Monthly Budget</h3>
        <p class="budget-amount">
            &#8377;<%= request.getAttribute("budget") %>
        </p>
    </div>

    <div class="budget-card">
        <h3>💸 Total Spent</h3>
        <p class="budget-amount spent">
            &#8377;<%= request.getAttribute("totalExpenses") %>
        </p>
    </div>

    <div class="budget-card">
        <h3>💰 Remaining</h3>
        <p class="budget-amount remaining">
            &#8377;<%= request.getAttribute("remaining") %>
        </p>
    </div>

</div>

<div class="budget-actions">
    <a href="budget.html">✏️ Update Budget</a>
    <a href="dashboard">← Back to Dashboard</a>
</div>
```

</div>

</body>

</html>
