<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "session" class = "db.DBBean" >
</jsp:useBean>
<jsp:setProperty name = "dbbean" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>
    <%
        String submit = request.getParameter("Login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        
        int check = Integer.parseInt(usertype);
        
    %>        
    <%  if( check == 1 ) {
            if( dbbean.checkAdmin(username,password) ){
                session.setAttribute("username", username);
                response.sendRedirect("admin.jsp");
            } else {%>
                <jsp:forward page="loginError.html"/>
            <%}
        } else if ( check == 2 ) {
            if( dbbean.checkDonator(username,password) ){
                session.setAttribute("username", username);
                response.sendRedirect("donator.jsp");
            } else {%>
                <jsp:forward page="loginError.html"/>
            <%}
        } else if ( check == 3 ) {
            if( dbbean.checkFoundation(username,password) ){
                session.setAttribute("username", username);
                response.sendRedirect("foundation.jsp");
            } else {%>
                <jsp:forward page="loginError.html"/>
            <%}
        } else {%>
            <jsp:forward page="loginError.html"/>
        <%}%>        
</body>
</html>
