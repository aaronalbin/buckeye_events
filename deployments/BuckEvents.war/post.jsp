<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	String cookieName = "buckevents-username";
	Cookie cookies[] = request.getCookies();
	String cookieUsername = null;
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(cookieName)) {
				cookieUsername = cookies[i].getValue();
				break;
			}
		}
	}
	if (cookieUsername == null) {
		response.sendRedirect("home");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="site_files/css/style.css" media="all" />
<link rel="stylesheet" media="all" href="site_files/css/folks.css" />
<link rel="stylesheet" media="all" href="site_files/css/contact.css" />
<script type="text/javascript" src="site_files/js/jquery.infieldlabel.min.js"></script>
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="site_files/css/ie7.css" media="all" />
<![endif]-->

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="site_files/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="site_files/js/scripts.js"></script>

<title>Buckeye Events</title>
</head>
<body>

<div id="container"> 
  <!-- Begin Header Wrapper -->
  <div id="page-top">
    <div id="header-wrapper"> 
      <!-- Begin Header -->
      <div id="header">
        <div id="logo"><a href="index.html"><img src="site_files/images/logo.png" alt="Delphic" /></a></div>
        <!-- Logo --> 
       <!-- Begin Menu -->
        <div id="menu-wrapper">
          <div id="smoothmenu1" class="ddsmoothmenu">
            <ul>
              <li><a href="home">Home</a></li>
              <li><a href="viewSaved">Saved</a></li>
              <li><a href="post.jsp">Post</a></li>
              <li><a href="logout">Logout</a></li>
            </ul>
          </div>
        </div>
        <!-- End Menu --> 
      </div>
      <!-- End Header --> 
    </div>
  </div>
  <!-- End Header Wrapper --> 
  
  <!-- Begin Wrapper -->
  <div id="wrapper"> 
    
    <!-- Begin Content -->
    <div class="content">
      <h3>Post a New Event</h3>
      <p>Please enter the details about your event.  All Fields are Required.</p>
      <br />
      <!-- Begin Form -->
      <div class="contact-left">
        <div id="contact-form"> 
          
          <!--begin:notice message block-->
          <div id="note"></div>
          <!--begin:notice message block-->
          
          <form action="postEvent" method="post">
            <div class="labels">
              <p>
				Name: <span class="error">${messages.errorname}</span>
                <br />
                <input type="text" name="name" id="name" value="" />
              </p>
              <p>
                Date: <span class="error">${messages.errordate}</span>
                <br />
                <input type="text" name="date" id="date" value="" />
              </p>
              <p>
                Time: <span class="error">${messages.errortime}</span>
                <br />
                <input type="text" name="time" id="time" value="" />
              </p>
              <p>
  				Location:  <span class="error">${messages.errorlocation}</span>
                <br />
                <input type="text" name="location" id="location" value="" />
              </p>
              <p>
                <br />
                <span class="optionlabel">Category: </span> 
                <select name="category">
					<option value="social">Social</option>
					<option value="fitness">Fitness</option>
					<option value="food">Food</option>
					<option value="lecture">Lecture</option>
					<option value="arts">Arts</option>
				</select><span class="error">${messages.errorcategory}</span>
              </p><br />
            </div>
            <div class="comments">
              <p>
               Description: <span class="error">${messages.errordescription}</span>
                <textarea class="textbox" name="description" rows="6" cols="30"></textarea>
              </p>
              <br />
              
            </div>
            <input id="submit-button" type="submit" name="submit" value="Submit" />
          </form>
        </div>
        <!-- End Form --> 
      </div>
      <div class="contact-right">
      <img src="site_files/images/events/g2.jpg">
      </div>
      <div class="clear"></div>
    </div>
    <!-- End Content --> 
    
  </div>
  <!-- End Wrapper -->
  
  <div class="clearfix"></div>
  <div class="push"></div>
</div>

<!-- Begin Footer -->
<div id="footer-wrapper">
  <div id="footer">
    <div id="footer-content"> 
      
      <!-- Begin Copyright -->
      <div id="copyright">
        <p>© Copyright 2012 Buckeye Events</p>
      </div>
      <!-- End Copyright --> 
      
    </div>
  </div>
</div>
<!-- End Footer -->

</body>
</html>