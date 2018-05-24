<%-- 
    Document   : MenuJsp
    Created on : May 4, 2017, 10:08:51 PM
    Author     : hsd77849


    Jsp page contains the entire menu. Includes Check Account, WithDraw, 
    Deposit, Transfer, Check Transactions, Open Accounts. Allows users to enter
    to info and complete important tasks. All forms in this jsp are conneted to
    the same servlet "Controller".
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Page</title>
    </head>
    <body>
    <center>
         <%-- Form for view all your account --%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <p>Your Accounts:</p>
             <%-- Button to get a response --%>
            <input name="action" type="submit" value="Check All Accounts">
        </form>
        <br>
         <%-- separation line --%>
        <hr style="width: 60%"/>
         <%-- Form allows users to withdraw money from user selected account--%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <p>Withdraw:</p>
             <%-- Drop down check list --%>
            <select name="Accounts" size="1">
                <option>Checking</option>
                <option>Saving</option>
                <option>Money Market</option>
            </select>
            <input name="wMoney" type="number" placeholder="$$$" required>
             <%-- Button to complete the withdraw --%>
            <input name="action" type="submit" value="Withdraw">
        </form>
        <br/>
         <%-- separation line --%>
        <hr style="width: 60%"/>
         <%-- Form allows users to deposit money to user selected account --%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <p>Deposit:</p>
             <%-- Drop down check list --%>
            <select name="Accounts" size="1">
                <option>Checking</option>
                <option>Saving</option>
                <option>Money Market</option>
            </select>
            <input name="dMoney" type="number" placeholder="$$$" required>
             <%-- Button to complete the deposit --%>
            <input name="action" type="submit" value="Deposit">
        </form>
        <br>
         <%-- Separation Line --%>
        <hr style="width: 60%"/>
         <%--Form allows users to transfer money from one account to another--%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <p>Transfer:</p>
            <input name="tMoney" type="number" placeholder="$$$" required>
            <br> <br>
            <%-- Drop down check list --%>
            From what Account-->
            <select name="FAccounts" size="1">
                <option>Checking</option>
                <option>Saving</option>
                <option>Money Market</option>
            <%-- Second drop down check list --%>
            </select>
            To what Account-->
            <select name="TAccounts" size="1">
                <option>Checking</option>
                <option>Saving</option>
                <option>Money Market</option>
            </select>
            <br>
             <%-- Button to complete the transfer --%>
            <input style="margin-top: 15px" name="action" type="submit" value="Transfer" required>
        </form>
        <br/>
         <%-- Separation Line --%>
        <hr style="width: 70%"/>
         <%-- Form allows users to check Transactions they have made after
              they have entered two dates.--%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <p>Check Transactions:</p>
             <%-- Drop down check list --%>
            <select name="TrAccounts" size="1">
                <option>Checking</option>
                <option>Saving</option>
                <option>Money Market</option>
            </select>
            <br><br>
             <%-- Input for the user to see what transactions they made from a certain date --%>
            From Date -->
            <input style="margin-right: 10px" name="fDate" type="text" placeholder="YYYY-MM-DD" required>
             <%-- Input for the user to see what transactions they made to a certain date  --%>
            To Date -->
            <input name="tDate" type="text" placeholder="YYYY-MM-DD" required>
            <br><br>
             <%-- Button for to submit the transaction --%>
            <input name="action" type="submit" value="Transactions">
        </form>
        <br/>
         <%-- Separation Line --%>
        <hr style="width: 60%"/>
         <%-- form allows user to create new account --%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <p>Open New Account:</p>
             <%-- Drop down list --%>
            <select name="Accounts" size="1">
                <option>Checking</option>
                <option>Saving</option>
                <option>Money Market</option>
            </select>
             <%-- Button allows users to create a new account --%>
            <input style="margin-left: 10px" name="action" type="submit" value="New Account">
        </form> 
        <br>
         <%-- button allows users to logout and return to the login page --%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <input name="action" type="submit" value="Logout">
        </form>
    </center>
</body>
</html>
