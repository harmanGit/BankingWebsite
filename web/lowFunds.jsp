<%-- 
    Document   : lowFunds
    Created on : May 5, 2017, 2:11:05 AM
    Author     : hsd77849

    Error page for when the user enters a amount lower than 50 when creating a
    new membership
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- page is redirects back to the new customer page, so the user can
             try entering a amount again above 50. redirect after 3 seconds--%>
        <meta http-equiv="refresh" content="3;url=/finalProjectJAVA3/NewCustomer.jsp"/></div>
        <title>Low funds Error Page</title>
    </head>
    <body>
       <center>
        <h1>AMOUNT ENTERED WAS LESS THAN $50 --> TRY AGAIN</h1>
       </center>
    </body>
</html>
