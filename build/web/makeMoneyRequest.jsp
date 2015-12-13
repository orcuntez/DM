
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "session" class = "db.DBBean" >
</jsp:useBean>
<jsp:setProperty name = "dbbean" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css" />
    <title>Make Money Request Page</title>
</head>
<body>
    <%
        if(session.getAttribute("username") == null){
            response.sendRedirect("index.jsp");
        }
        String username = (String)session.getAttribute("username");
        String foundationname = dbbean.takeFoundationname(username);
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
                <li><a href="foundation.jsp">Home</a></li>
                <li><a href="logout.jsp">Logout</a></li>
                <!-- END MENU -->
            </ul>
        </div>

        <div class="page-wrap">
            <div class="content">

                <!-- CONTENT -->
                <h2>Request Money Donation!</h2>
                <p>
                <form method="post" action="requestAction.jsp">
                    <input type="hidden" name="rtype" value="money">
                    <table>
                        <tr>
                            <td>Category</td>
                            <td><select name="rcategory" style="width: 383px">
                                    <option value="TL">TL</option> 
                                    <option value="USD">USD</option>
                                    <option value="EURO">EURO</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td>Amount</td>
                            <td><input type="text" name="amount" size="50"/></td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td><input type="text" name="description" size="50"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="Submit" value="Submit" style="width: 125px;"/></td>
                        </tr>

                    </table>
                </form>	

                <br/><br/>

                <!-- END CONTENT -->

            </div>

                <div class="sidebar">	

                <!-- SIDEBAR -->	

                <a href="foundation.jsp"><h4 style="color: green;">Welcome Foundation: <%= foundationname %> </h4></a>	

                <h4>Links</h4>
                <ul class="blocklist">
                    <li><a href="receivedDonations.jsp">Received Donations</a></li>
                    <li><a href="myRequests.jsp">My Requests</a></li>
                    <li><a href="makeMoneyRequest.jsp">Make Money Request</a></li>
                    <li><a href="makeBloodRequest.jsp">Make Blood Request</a></li>
                    <li><a href="makeStuffRequest.jsp">Make Stuff Request</a></li>
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


