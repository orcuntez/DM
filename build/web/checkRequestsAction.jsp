
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
            int rid = Integer.parseInt(request.getParameter("requestid"));
            String status = request.getParameter("status");
            
            int amount = dbbean.takeAmountFromRid(rid);
            int stockAmount = dbbean.takeStockAmountFromRid(rid);
            
            boolean isSuccess = dbbean.answerRequest(rid, status, amount, stockAmount);
            
            if (isSuccess) {%>
                <jsp:forward page="requestUpdateSuccessful.jsp"/>
            <% } else {%>
                <jsp:forward page="requestUpdateError.jsp"/>
             <% } 
        }
    %>
</body>
</html>
