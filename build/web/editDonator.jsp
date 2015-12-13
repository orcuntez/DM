
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "application" class = "db.DBBean" >
</jsp:useBean>
<jsp:setProperty name = "dbbean" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Information Page</title>
</head>
<body>
    <%
        if(session.getAttribute("username") == null){
            response.sendRedirect("index.jsp");
        }
        String username = (String)session.getAttribute("username");
        String firstname = dbbean.takeFirstname(username);
        
        String submit = request.getParameter("Apply Changes");

        if (submit.equals("Apply Changes")) {
            dbbean.setPassword(request.getParameter("password"));
            dbbean.setFirstname(request.getParameter("firstname"));
            dbbean.setLastname(request.getParameter("lastname"));
            dbbean.setEmail(request.getParameter("email"));
            dbbean.setAge(request.getParameter("age"));
            dbbean.setTelephone(request.getParameter("telephone"));
            dbbean.setAddress(request.getParameter("address"));

            boolean isSuccess = dbbean.editDonator(username);
            if (isSuccess) {%>
                <jsp:forward page="donatorInfoSuccess.jsp"/>
            <% } else {%>
                <jsp:forward page="donatorInfoError.jsp"/>
            <% }
        }
    %>
</body>
</html>
