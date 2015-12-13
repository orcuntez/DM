
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
    <title>Accept Foundations</title>
</head>
<body>
    <%
        if (session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp");
        }
        String username = (String) session.getAttribute("username");
        String firstname = dbbean.takeAdminname(username);

        ResultSet table = dbbean.takeFoundations("pending");
    %>
<div class="pagewidth">

    <div class="header">
        <!-- TITLE -->
        <h1><a href="foundation.jsp">Donation Manager System</a></h1>
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
            <h2>Check Foundations</h2>
            <p>
            <form method="post" action="checkFoundationAction.jsp">
                <table>
                    <tr>
                        <th>Foundation Name</th>
                        <th>Username</th>
                        <th>Telephone</th>
                        <th>Email</th>
                        <th>Address</th>

                    </tr>
                    <% while (table.next()) {%>   
                    <tr>       
                        <td>
                            <%out.print(table.getString("foundationname"));%> 
                        </td>
                        <td>            
                            <%out.print(table.getString("username"));%> 
                        </td>
                        <td>
                            <%out.print(table.getString("telephone"));%>           
                        </td>
                        <td>
                            <%out.print(table.getString("email"));%>           
                        </td>
                        <td>
                            <%out.print(table.getString("address"));%>           
                        </td>
                        <td>
                            <input type="radio" name="uid" value="<%out.print(table.getString("uid"));%>"> <%out.print(table.getString("uid"));%>
                        </td>
                    </tr>
                    <%}%>
                </table>
                
                <p></p>

                <table>
                    <tr>
                        <td>
                            <select size = "1" name = "status">
                                <option value = "approved">Approve</option>
                                <option value = "rejected">Reject</option>
                            </select>
                        </td>

                    </tr>
                </table>
                <p><input type = "submit" name = "Submit" value = "Submit" /></p>
            </form>


            <br/><br/>

            <!-- END CONTENT -->

        </div>

        <div class="sidebar">	

            <!-- SIDEBAR -->	

            <h4 style="color: darkorange;">Welcome Admin: <%= firstname%> </h4>	

            <h4>Links</h4>
            <ul class="blocklist">
                    <li><a href="stocklist.jsp">Stock Room</a></li>
                    <li><a href="alldonations.jsp">All Donations</a></li>
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

