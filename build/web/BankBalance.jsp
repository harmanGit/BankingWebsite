<%-- 
    Document   : BankBalance
    Created on : May 4, 2017, 11:24:02 PM
    Author     : hsd77849

    JSP displays the balance table after the user has clicked 
    "Check All Account" from the Menu page

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>balance Page</title>
    </head>
    <body>
        <center>
            <%-- Table is displayed using the servlet and "transBean" --%>
           ${Table_Name}
        <h1>Balance Completed</h1>
         <%-- Button to go back to the menu --%>
        <form action="/finalProjectJAVA3/Controller" method="post">
            <input name="action" type="submit" value="Go Back">
        </form>
        </center>
    </body>
</html>
