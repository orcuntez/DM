
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "application" class = "db.DBBean" >
</jsp:useBean>
<jsp:setProperty name = "dbbean" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Donation Action</title>
</head>
<body>
    <%  
        if(session.getAttribute("username") == null){
            response.sendRedirect("index.jsp");
        }
        String username = (String)session.getAttribute("username");
        
        String submit = request.getParameter("Submit");

        if (submit.equals("Submit")) {
            dbbean.setDtype(request.getParameter("dtype"));
            dbbean.setDcategory(request.getParameter("dcategory"));
            dbbean.setAmount(request.getParameter("amount"));
            dbbean.setDescription(request.getParameter("description"));
            
            boolean isSuccess = dbbean.addDonation(username);
            if (isSuccess) {%>
                <jsp:forward page="donationSuccessful.jsp"/>
            <% } else {%>
                <jsp:forward page="donationError.jsp"/>
            <% }

        }
    %>
</body>
</html>
