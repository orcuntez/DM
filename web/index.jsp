
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "dbbean" scope = "session" class = "db.DBBean" >
</jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css" />
</head>
<body>
    <div class="pagewidth">

        <div class="header">
            <!-- TITLE -->
            <h1><a href="index.jsp">Donation Manager System</a></h1>
            <h2>Işık University</h2>
            <!-- END TITLE -->
        </div>

        <div class="nav">
            <ul>	
                <!-- MENU -->
                <li class="selected"><a href="index.jsp">Home</a></li>
                <li><a href="register.html">Register</a></li>
                <li><a href="login.html">User Login</a></li>
                <!-- END MENU -->
            </ul>
        </div>

        <div class="page-wrap">
            <div class="content">

                <!-- CONTENT -->
                <h2>Introduction</h2>
                <p>Welcome to Donation Manager System</p>	

                <p> You can donate;</p>

                <ul>
                    <li>Money</li>
                    <li>Blood</li>
                    <li>Stuff</li>
                </ul>

                <h3>Register for donation.</h3>

                <p> If you want to donate, please register first.</p>

                <h3>Deneme</h3>
                <p> Deneme deneme deneme </p>

                <h3>Deneme 2</h3>	
                <p> Deneme 1-2-3</p>


                <br /><br />

                <!-- END CONTENT -->

            </div>

                <div class="sidebar">	

                <!-- SIDEBAR -->	

                <h4>About us</h4>	
                <p>A little bit of information about your website, what you do, what's on offer and why visitors should stick around</p>


                <h4>Links</h4>
                <ul class="blocklist">
                    <li><a href="#">Deneme</a></li>
                    <li><a href="#">Deneme</a></li>
                    <li><a href="#">Deneme</a></li>
                    <li><a href="#">Deneme</a></li>
                    <li><a href="#">Deneme</a></li>

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
