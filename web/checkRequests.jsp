
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "session" class = "db.DBBean" >
</jsp:useBean>
<jsp:setProperty name = "dbbean" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
    <title>Check Requests</title>
</head>
<body>
    <%
        if (session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp");
        }
        String username = (String) session.getAttribute("username");
        String firstname = dbbean.takeAdminname(username);

        ResultSet table = dbbean.takeRequests("pending");
        int stockAmount;
        
    %>
<div class="pagewidth">

    <div class="header">
        <!-- TITLE -->
        <h1><a href="admin.jsp">Donation Manager System</a></h1>
        <h2>Işık University</h2>
        <!-- END TITLE -->
    </div>

    <div class="nav">
        <ul>	
            <!-- MENU -->
            <li><a href="admin.jsp">Home</a></li>
            <li><a href="logout.jsp">Logout</a></li>
            <!-- END MENU -->
        </ul>
    </div>

    <div class="page-wrap">
        <div class="content">

            <!-- CONTENT -->
            <h2>Check Requests</h2>
            <p>
            <form method="post" action="checkRequestsAction.jsp">
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Type</th>
                        <th>Category</th>
                        <th>Requested Amount</th>
                        <th>Now In Stock</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Which one?</th>
                    </tr>
                    <% while (table.next()) {%>   
                    <tr>       
                        <td>
                            <%out.print(dbbean.takeFoundationsUsername(table.getString("FK_uid")));%> 
                        </td>
                        <td>            
                            <%out.print(table.getString("rtype"));%> 
                        </td>
                        <td>
                            <%out.print(table.getString("rcategory"));%>           
                        </td>
                        <td>
                            <%out.print(table.getInt("amount"));%>           
                        </td>
                        <td>
                            <% stockAmount = dbbean.takeStockAmount(table.getString("rtype"), table.getString("rcategory")); %>
                            <%out.print(stockAmount);%>           
                        </td>
                        <td>
                            <%out.print(table.getString("description"));%>           
                        </td>
                        <td>
                            <%out.print(table.getString("status"));%>           
                        </td>
                        <td>
                            <input type="radio" name="requestid" value="<%out.print(table.getInt("rid"));%>"> <%out.print(table.getInt("rid"));%>
                        </td>
                    </tr>
                    <%}%>
                </table>
                
                
                    <% if( table.first()){ %>
                        <p align="center">
                            <select size = "1" name = "status" style="width: 125px">
                                <option value = "approved">Approve</option>
                                <option value = "rejected">Reject</option>
                            </select>
                            <br/>
                            <input type = "submit" name = "Submit" value = "Submit" style="width: 125px;"/>
                        </p>
                    <% } else {%>    
                    <% }%>
            </form>


            <br/><br/>

            <!-- END CONTENT -->

        </div>

        <div class="sidebar">	

            <!-- SIDEBAR -->	

            <a href="admin.jsp"><h4 style="color: darkorange;">Welcome Admin: <%= firstname %> </h4></a>	

            <h4>Links</h4>
            <ul class="blocklist">
                <li><a href="stocklist.jsp">Stock Room</a></li>
                <li><a href="allDonations.jsp">All Donations</a></li>
                <li><a href="allReceiveds.jsp">All Donations Sent</a></li>
                <li><a href="checkDonations.jsp">Check Donations</a></li>
                <li><a href="checkRequests.jsp">Check Requests</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>

            <!-- SIDEBAR -->

        </div> 

        <div class="clear"></div>		
    </div>

    <div class="footer">
        <p> &copy; Donation Manager System </p> 
    </div>	
</div>

</body>
</html>


