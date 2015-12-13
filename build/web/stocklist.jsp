
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
    <title>Stock List Page</title>
</head>
<body>
    <%
        if(session.getAttribute("username") == null){
            response.sendRedirect("index.jsp");
        }
        String username = (String)session.getAttribute("username");
        String firstname = dbbean.takeAdminname(username);
        
        ResultSet table = dbbean.takeStockList();
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
                <h2>Stock Room</h2>
                <p>
                
                <table>
                    <tr>
                        <th>Type</th>
                        <th>Category</th>
                        <th>Amount</th>
                    </tr>
                    <% while(table.next()){%>   
                    <tr>       
                        <td>            
                            <%out.print(table.getString("dtype"));%> 
                        </td>
                        <td>
                            <%out.print(table.getString("dcategory"));%>           
                        </td>
                        <td>
                            <%out.print(table.getString("amount"));%>           
                        </td>
                    </tr>
                    <%}%>
                </table>
                
                

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
