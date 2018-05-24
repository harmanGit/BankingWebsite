<%-- 
    Document   : NewCustomer
    Created on : May 5, 2017, 1:11:52 AM
    Author     : hsd77849

    Jsp is used to allow user to create a new member. Requires the user to enter
    username, password, first name, last name, street address, city, state, 
    zip code, select account type.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Page</title>
    </head>
    <body>
        <center>
        <p>Welcome New User!</p>
         <%-- Form directed to the servlet Controller. Also allows users to enter
              all information user to create a account. --%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <%-- input for username --%>
            <p>Username:</p>
            <input name="newUsername" type="text" placeholder="Username Name" required>
            <%-- input for password --%>
            <p>Password:</p>
            <input name="newPassword" type="text" placeholder="******" required>
            <%-- input for first name --%>
            <p>First Name:</p>
            <input name="newFname" type="text" placeholder="Legal FirstName" required>
            <%-- input for password --%>
            <p>Last Name:</p>
            <input name="newLname" type="text" placeholder="Legal LastName" required>
            <%-- input for address --%>
            <p>Street Address:</p>
            <input name="newAddress" type="text" placeholder="123 Love Street" required>
            <%-- input for city --%>
            <p>City:</p>
            <input name="newCity" type="text" placeholder="City Name" required>
            <%-- input for state --%>
            <p>State:</p>
            <input name="newState" type="state" placeholder="State Ex. VA" required>
            <%-- input for zip --%>
            <p>ZIP Code:</p>
            <input name="newZip" type="number" placeholder="XXXXX" required>
            <%-- drop down check list --%>
            <p>Account Type:</p>
             <select name="MAccounts" size="1">
                <option>Checking</option>
                <option>Saving</option>
                <option>Money Market</option>
            </select>
            <p>Opening Deposit:    ~Must deposit more than 50 dollars!~</p>
            <%-- input for the amount to add to the new account, atleast 50 --%>
            <input name="newAmount" type="number" placeholder="$$$" required>
            <br/><br/>
            <%-- submit all the info to create the new member --%>
            <input name="action" type="submit" value="Become A Member">
        </form>
        <br>
        <%-- button leaves the page and goes back to the login page --%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <input name="action" type="submit" value="Leave Page">
        </form>
        </center>
    </body>
</html>
