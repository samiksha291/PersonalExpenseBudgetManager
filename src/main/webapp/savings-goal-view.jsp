<%@ page contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Savings Goal</title>


<link rel="stylesheet" href="css/style.css">

<style>
    .savings-container {
        max-width: 900px;
        margin: 40px auto;
    }

    .savings-title {
        margin-bottom: 25px;
    }

    .savings-title h1 {
        margin-bottom: 8px;
    }

    .savings-title p {
        color: #64748b;
    }

    .savings-cards {
        display: flex;
        gap: 20px;
        flex-wrap: wrap;
    }

    .savings-card {
        flex: 1;
        min-width: 200px;
        background: white;
        padding: 25px;
        border-radius: 14px;
        box-shadow: 0 6px 18px rgba(15, 23, 42, 0.08);
        border-top: 4px solid #2563eb;
    }

    .savings-card h3 {
        color: #64748b;
        margin-top: 0;
    }

    .savings-amount {
        font-size: 25px;
        font-weight: bold;
        color: #0f172a;
    }

    .progress-section {
        background: white;
        margin-top: 25px;
        padding: 25px;
        border-radius: 14px;
        box-shadow: 0 6px 18px rgba(15, 23, 42, 0.08);
    }

    .progress-bar-container {
        width: 100%;
        height: 22px;
        background: #e2e8f0;
        border-radius: 20px;
        overflow: hidden;
        margin-top: 15px;
    }

    .progress-bar {
        height: 100%;
        background: #2563eb;
        width: <%= request.getAttribute("progress") != null
                ? request.getAttribute("progress")
                : 0 %>%;
    }

    .progress-text {
        font-weight: bold;
        margin-top: 10px;
    }

    .savings-actions {
        margin-top: 30px;
    }

    .savings-actions a {
        display: inline-block;
        margin-right: 15px;
        text-decoration: none;
        font-weight: bold;
        color: #2563eb;
    }

    .no-goal {
        background: white;
        padding: 30px;
        border-radius: 14px;
        text-align: center;
        box-shadow: 0 6px 18px rgba(15, 23, 42, 0.08);
    }
</style>


</head>

<body>

<div class="header">
    <h1>🎯 Savings Goal</h1>
    <p>Track your savings progress toward your goal.</p>
</div>

<div class="savings-container">

<%
String goalName =
(String) request.getAttribute("goalName");


if (goalName != null) {


%>


<div class="savings-title">
    <h1>🎯 <%= goalName %></h1>
    <p>Here is your current savings progress.</p>
</div>

<div class="savings-cards">

    <div class="savings-card">
        <h3>🎯 Target Amount</h3>
        <p class="savings-amount">
            &#8377;<%= request.getAttribute("targetAmount") %>
        </p>
    </div>

    <div class="savings-card">
        <h3>💰 Current Savings</h3>
        <p class="savings-amount">
            &#8377;<%= request.getAttribute("currentAmount") %>
        </p>
    </div>

    <div class="savings-card">
        <h3>📊 Remaining</h3>
        <p class="savings-amount">
            &#8377;<%= request.getAttribute("remaining") %>
        </p>
    </div>

</div>

<div class="progress-section">

    <h3>📈 Savings Progress</h3>

    <div class="progress-bar-container">
        <div class="progress-bar"></div>
    </div>

    <p class="progress-text">
        <%= String.format("%.2f",
                (Double) request.getAttribute("progress")) %>%
        completed
    </p>

</div>

<div class="savings-actions">

     <form action="savings-goal" method="post">

         <input type="hidden"
                name="goalId"
                value="<%= request.getAttribute("goalId") %>">

         <input type="hidden"
                name="goalName"
                value="<%= request.getAttribute("goalName") %>">

         <label>Target Amount</label>
         <input type="number"
                name="targetAmount"
                value="<%= request.getAttribute("targetAmount") %>"
                step="0.01"
                required>

         <label>Current Savings</label>
         <input type="number"
                name="currentAmount"
                value="<%= request.getAttribute("currentAmount") %>"
                step="0.01"
                required>

         <button type="submit">
             ✏️ Update Goal
         </button>

     </form>

     <br>

     <a href="dashboard">← Back to Dashboard</a>

 </div>


<%
} else {
%>


<div class="no-goal">

    <h2>🎯 No Savings Goal Found</h2>

    <p>
        You have not created a savings goal yet.
    </p>

    <a href="savings-goal.html" class="back">
        Create Savings Goal
    </a>

</div>


<%
}
%>

</div>

</body>

</html>
