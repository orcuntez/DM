
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
                <li><a href="register.html">Donator Register</a></li>
                <li><a href="registerFoundation.html">Foundation Register</a></li>
                <li><a href="login.html">User Login</a></li>
                <!-- END MENU -->
            </ul>
        </div>

        <div class="page-wrap">
            <div class="content">

                <!-- CONTENT -->
                <h2>Welcome to Donation Manager System</h2>
                <!-- <p>Welcome to Donation Manager System</p>	-->

                <p><font size="4"> You have three options;</font></p>

                <ul>
                    <li><font size="4">Money</font></li>
                    <li><font size="4">Blood</font></li>
                    <li><font size="4">Stuff</font></li>
                </ul>

                <h3><a href="register.html" title="Click to register as a Donator">Register For Donation</a></h3>

                <p> If you want to donate, please register first!</p>

                <h3><a href="registerFoundation.html" title="Click to register as a Foundation">Register For Requesting Donations</a></h3>
                <p> If you want to request donations, please register first! </p>

                  <h3>Statistics for Blood Donation Year-by-Year in Turkey</h3> 
              <!--  <p> Deneme 1-2-3</p>   -->
              <table>
      <tr>
         <td>YEAR</td>
         <td>AMOUNT</td>
         <td>PERCENTAGE OF SUPPLYING THE NEEDS </td>
      </tr>
      <tr>
         <td>2007</td>
         <td>592.965</td>
         <td>33%</td>
      </tr>
            <tr>
         <td>2008</td>
         <td>654.081</td>
         <td>36%</td>
      </tr>
            <tr>
         <td>2009</td>
         <td>848.586</td>
         <td>47%</td>
      </tr>
            <tr>
         <td>2010</td>
         <td>1.014.516</td>
         <td>61%</td>
      </tr>
            <tr>
         <td>2011</td>
         <td>1.276.211</td>
         <td>70%</td>
      </tr>
   </table>

                 
                <br /><br />

                <!-- END CONTENT -->

            </div>

                <div class="sidebar">	

                <!-- SIDEBAR -->	

                <h4>About us</h4>	
                <p>DMS was developed in 2015 by Işık University students. This project was improved and presented to public by Turkish government. Purpose of this project is to pacifice the donations in country by the government hand.</p>


                <h4>Links</h4>
                <ul class="blocklist">
                    <a href="http://www.kizilay.org.tr/" target="_blank">Kızılay</a>
                    <li><a href="#"></a></li>
                    <a href="http://www.yesilay.org.tr/tr" target="_blank">Yeşilay</a>
                    <li><a href="#"></a></li>
                    <a href="http://www.thk.org.tr/" target="_blank">Türk Hava Kurumu</a>
                    <li><a href="#"></a></li>
                    <a href="https://giris.turkiye.gov.tr/Giris/" target="_blank">E-devlet</a>
                </ul>

                <!-- SIDEBAR -->

                </div> 

            <div class="clear"></div>		
        </div>
        
        <div class="footer">
            <p><b><a href="communication.html">Contact us</a></b></p> 
            <p> &copy; Donation Manager System </p> 
        </div>	
    </div>

</body>
</html>