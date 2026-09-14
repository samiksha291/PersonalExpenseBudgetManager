<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Dashboard - Personal Expense & Budget Manager</title>


<link rel="stylesheet" href="css/style.css">

<style>
    .dashboard-hero {
        background: linear-gradient(135deg, #0f172a, #1e3a8a);
        color: white;
        padding: 35px;
        border-radius: 18px;
        margin-bottom: 30px;
        box-shadow: 0 10px 30px rgba(15, 23, 42, 0.15);
    }

    .dashboard-hero h1 {
        margin: 0 0 8px;
        font-size: 32px;
    }

    .dashboard-hero p {
        margin: 0;
        color: #dbeafe;
        font-size: 16px;
    }

    .summary-card {
        position: relative;
        overflow: hidden;
    }

    .summary-icon {
        font-size: 30px;
        margin-bottom: 10px;
    }

    .summary-label {
        color: #64748b;
        font-size: 14px;
        font-weight: bold;
        text-transform: uppercase;
        letter-spacing: 0.5px;
    }

    .summary-value {
        font-size: 28px;
        font-weight: bold;
        margin-top: 8px;
        color: #0f172a;
    }

    .section-title {
        margin: 35px 0 18px;
        color: #0f172a;
        font-size: 22px;
    }

    .action-card {
        display: flex !important;
        align-items: center;
        gap: 12px;
        text-align: left !important;
    }

    .action-icon {
        font-size: 24px;
    }

    .goal-card {
        background: white;
        padding: 25px;
        border-radius: 14px;
        box-shadow: 0 6px 18px rgba(15, 23, 42, 0.08);
        border-left: 5px solid #2563eb;
    }

    .goal-card h3 {
        margin-top: 0;
        margin-bottom: 8px;
    }

    .goal-card p {
        color: #64748b;
        margin-bottom: 15px;
    }

    .progress-container {
        width: 100%;
        height: 10px;
        background: #e2e8f0;
        border-radius: 20px;
        overflow: hidden;
    }

    .progress-bar {
        height: 100%;
        width: 64%;
        background: #2563eb;
        border-radius: 20px;
    }

    .progress-text {
        margin-top: 8px;
        color: #64748b;
        font-size: 14px;
    }

    .dashboard-footer {
        text-align: center;
        margin-top: 45px;
        padding: 25px;
        color: #64748b;
        font-size: 14px;
    }
</style>


</head>

<body>

<header class="header">
    <h1>💰 Personal Expense Manager</h1>
</header>

<div class="container">


<div class="dashboard-hero">
    <h1>Welcome to your dashboard 👋</h1>
    <p>
        Track your income, manage expenses and stay on top of your financial goals.
    </p>
</div>

<!-- SUMMARY -->

<div class="cards">

    <div class="card summary-card">
        <div class="summary-icon">💰</div>
        <div class="summary-label">Total Income</div>

        <div class="summary-value">
            &#8377;<%= request.getAttribute("totalIncome") %>
        </div>
    </div>

    <div class="card summary-card">
        <div class="summary-icon">💸</div>
        <div class="summary-label">Total Expenses</div>

        <div class="summary-value">
            &#8377;<%= request.getAttribute("totalExpenses") %>
        </div>
    </div>

    <div class="card summary-card">
        <div class="summary-icon">📊</div>
        <div class="summary-label">Remaining</div>

        <div class="summary-value">
            &#8377;<%= request.getAttribute("remaining") %>
        </div>
    </div>

</div>

<!-- QUICK ACTIONS -->

<h2 class="section-title">Quick Actions</h2>

<div class="links">

    <a href="add-income.html" class="action-card">
        <span class="action-icon">💰</span>
        <span>Add Income</span>
    </a>

    <a href="add-expense.html" class="action-card">
        <span class="action-icon">💸</span>
        <span>Add Expense</span>
    </a>

    <a href="income-history" class="action-card">
        <span class="action-icon">📈</span>
        <span>Income History</span>
    </a>

    <a href="expense-history" class="action-card">
        <span class="action-icon">📉</span>
        <span>Expense History</span>
    </a>

    <a href="transactions" class="action-card">
        <span class="action-icon">📋</span>
        <span>All Transactions</span>
    </a>

    <a href="budget" class="action-card">
        <span class="action-icon">💳</span>
        <span>Monthly Budget</span>
    </a>

    <a href="summary" class="action-card">
        <span class="action-icon">📊</span>
        <span>Monthly Summary</span>
    </a>

    <a href="savings-goal" class="action-card">
        <span class="action-icon">🎯</span>
        <span>Savings Goal</span>
    </a>

    <a href="recurring-expense" class="action-card">
        <span class="action-icon">🔄</span>
        <span>Recurring Expenses</span>
    </a>

</div>

<!-- SAVINGS -->

<h2 class="section-title">Financial Goals</h2>

<div class="goal-card">

    <h3>🎯 Keep working toward your savings goals</h3>

    <p>
        Track your savings progress and stay consistent with your financial plans.
    </p>

    <div class="progress-container">
        <div class="progress-bar"></div>
    </div>

    <div class="progress-text">
        Use the Savings Goal section to track your progress.
    </div>

</div>

<div class="dashboard-footer">
    Personal Expense Manager
    <br>
    Smartly manage your income, expenses and savings goals.
    <br>
    Developed by Samiksha
</div>


</div>

</body>

</html>
