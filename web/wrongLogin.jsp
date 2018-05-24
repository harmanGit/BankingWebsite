<%-- 
    Document   : wrongLogin
    Created on : May 8, 2017, 2:37:18 PM
    Author     : hsd77849

    Error page for incorrect password.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- page redirects back to the login page so the user can try again
            after 2 seconds--%>
         <meta http-equiv="refresh" content="2;url=index.html"/>
        <title>Wrong Login</title>
    </head>
    <body>
        <center>
        <h1>Incorrect Login</h1>
        <h1>Redirecting in 2 seconds...</h1>
        </center>
    </body>
</html>
