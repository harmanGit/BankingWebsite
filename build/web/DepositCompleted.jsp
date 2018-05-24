<%-- 
    Document   : DepositCompleted
    Created on : May 5, 2017, 1:03:34 AM
    Author     : hsd77849

    Jsp simply displays that the deposit was completed and then redirects back
    to Menu page

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <%-- Used to redirect back to the Menu after 2 seconds --%>
      <meta http-equiv="refresh" content="2;url=/finalProjectJAVA3/MenuJsp.jsp"/>
        <title>Deposit Completed Page</title>
    </head>
    <body>
        <center>
        <h1>Deposit Completed</h1>
        <h1>Redirecting in 2 seconds...</h1>
        </center>
</html>
