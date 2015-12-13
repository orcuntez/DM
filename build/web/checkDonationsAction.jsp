
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "application" class = "db.DBBean" >
</jsp:useBean>
<jsp:setProperty name = "dbbean" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Check Donation Action</title>
</head>
<body>
    <%  
        if(session.getAttribute("username") == null){
            response.sendRedirect("index.jsp");
        }
        String username = (String)session.getAttribute("username");
        
        String submit = request.getParameter("Submit");

        if (submit.equals("Submit")) {
            String did = request.getParameter("donationid");
            String status = request.getParameter("status");
            
            boolean isSuccess = dbbean.updateStatus(did,status);
            if (isSuccess) {%>
                <jsp:forward page="donationUpdateSuccessful.jsp"/>
            <% } else {%>
                <jsp:forward page="donationUpdateError.jsp"/>
            <% } 
        }
    %>
</body>
</html>
