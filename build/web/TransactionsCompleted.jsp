<%-- 
    Document   : TransactionsCompleted
    Created on : May 5, 2017, 1:04:09 AM
    Author     : hsd77849

    Jsp displays all the transaction from the user. Based of users inputted
    dates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction Page</title>
    </head>
    <body>
        <%-- displays the transactions made --%>
        <center>
            ${Table_Name}
        <h1>Transaction Completed</h1>
        <%-- button goes back to the menu --%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <input name="action" type="submit" value="Go Back">
        </form>
        
      
        </center>
</html>
