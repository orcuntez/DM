
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "application" class = "db.DBBean" >
</jsp:useBean>
<jsp:setProperty name = "dbbean" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Check Foundation Action</title>
</head>
<body>
    <%  
        if(session.getAttribute("username") == null){
            response.sendRedirect("index.jsp");
        }
        String username = (String)session.getAttribute("username");
        
        String submit = request.getParameter("Submit");

        if (submit.equals("Submit")) {
            String uid = request.getParameter("uid");
            String status = request.getParameter("status");
            
            boolean isSuccess = dbbean.updateFoundationStatus(uid,status);
            if (isSuccess) {%>
                <jsp:forward page="foundationUpdateSuccessful.jsp"/>
            <% } else {%>
                <jsp:forward page="foundationUpdateError.jsp"/>
            <% } 
        }
    %>
    
</body>
</html>
