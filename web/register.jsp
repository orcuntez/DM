
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "application" class = "db.DBBean" >
</jsp:useBean>
<jsp:setProperty name = "dbbean" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register Page</title>
</head>
<body>
    <%
    String submit = request.getParameter("Register");
        
    if(submit.equals("Register")){    
    dbbean.setFirstname(request.getParameter("firstname"));
    dbbean.setLastname(request.getParameter("lastname"));
    dbbean.setUsername(request.getParameter("username"));
    dbbean.setPassword(request.getParameter("password"));
    dbbean.setGender(request.getParameter("gender"));
    dbbean.setAge(request.getParameter("age"));
    dbbean.setTelephone(request.getParameter("telephone"));
    dbbean.setEmail(request.getParameter("email"));
    dbbean.setAddress(request.getParameter("address"));
    dbbean.setUsertype(request.getParameter("usertype"));    
    dbbean.addUser();%>
    <jsp:forward page="userRegistered.html"/>
    <%}    
    %>
</body>
</html>
