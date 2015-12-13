
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
    <title>Make Money Donation Page</title>
</head>
<body>
    <%
        if(session.getAttribute("username") == null){
            response.sendRedirect("index.jsp");
        }
        String username = (String)session.getAttribute("username");
        String firstname = dbbean.takeFirstname(username);
        
    %>
    <div class="pagewidth">

        <div class="header">
            <!-- TITLE -->
            <h1><a href="donator.jsp">Donation Manager System</a></h1>
            <h2>Işık University</h2>
            <!-- END TITLE -->
        </div>

        <div class="nav">
            <ul>	
                <!-- MENU -->
                <li><a href="donator.jsp">Home</a></li>
                <li><a href="logout.jsp">Logout</a></li>
                <!-- END MENU -->
            </ul>
        </div>

        <div class="page-wrap">
            <div class="content">

                <!-- CONTENT -->
                <h2>Make Money Donation!</h2>
                <p>
                <form method="post" action="donationAction.jsp">
                    <input type="hidden" name="dtype" value="money">
                    <table>
                        <tr>
                            <td>Category</td>
                            <td><select name="dcategory" style="width: 383px">
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

                <a href="donator.jsp"><h4 style="color: blue;">Welcome Donator: <%= firstname %> </h4></a>

                <h4>Links</h4>
                <ul class="blocklist">
                    <li><a href="myDonations.jsp">My Donations</a></li>
                    <li><a href="makeMoneyDonation.jsp">Make Money Donation</a></li>
                    <li><a href="makeBloodDonation.jsp">Make Blood Donation</a></li>
                    <li><a href="makeStuffDonation.jsp">Make Stuff Donation</a></li>
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


