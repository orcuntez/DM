
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "application" class = "db.DBBean" >
</jsp:useBean>
<jsp:setProperty name = "dbbean" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Foundation Register Page</title>
</head>
<body>
    <%
        String submit = request.getParameter("Register");

        if(submit.equals("Register")){    
            dbbean.setFoundationname(request.getParameter("foundationname"));
            dbbean.setUsername(request.getParameter("username"));
            dbbean.setPassword(request.getParameter("password"));
            dbbean.setTelephone(request.getParameter("telephone"));
            dbbean.setEmail(request.getParameter("email"));
            dbbean.setAddress(request.getParameter("address"));    

            boolean isSuccess = dbbean.addFoundation();
            if (isSuccess) {%>
                <jsp:forward page="userRegistered.html"/>
            <% } else {%>
                <jsp:forward page="registerError.html"/>
            <% }
        }  
    %>
</body>
</html>
