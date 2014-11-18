<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="site_files/css/style.css" media="all" />
<link rel="stylesheet" media="all" href="site_files/css/folks.css" />
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
             <% if (cookieUsername == null) { %>
            	<form action="login" method="post">
					<li><input type="text" id="username" name="username" size="15" placeholder="Username" /></li>
					<li><input type="password" id="password" name="password" size="15" placeholder="Password" /></li>
					<li><input type="submit" value="login"></li>
				</form><span class="error">${loginmessages.error}</span>
            	<% } else { %>
	              <li><a href="home">Home</a></li>
	              <li><a href="viewSaved">Saved</a></li>
	              <li><a href="post.jsp">Post</a></li>
	              <li><a href="logout">Logout</a></li>
             	<%}%>
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
    <div id="post-wrapper">
      <div class="post">
        <h2 class="title">${event.getName()}</h2>
        <div class="meta">
          <div class="top-border"></div>
          <div class="date">${event.getDate()}</div> at  <div class="date">${event.getTime()}</div>
        </div>
          
        <img src="site_files/images/events/blog4.jpg" alt="" />
        <p>Location: ${event.getLocation()}</p>
        <p>${event.getDescription()}</p>
        <div class="top-border"></div>
        <div class="tags"> Category: ${event.getCategory()} </div>
      </div>
      
    </div>
    <div id="sidebar">
      <div class="sidebox">
       <% if (cookieUsername != null) { %>
	      <%if (request.getAttribute("saved").equals("false")) {%>
	        <form action="saveEvent" method="post">
				<input type="hidden" name="eventid" value="${event.getId()}">
				<input class="savebutton" type="submit" value="Add To My Events" />
			</form>
			<%} else { %>
			<form action="removeEvent" method="post">
				<input type="hidden" name="eventid" value="${event.getId()}">
				<input class="removebutton" type="submit" value="Remove From My Events" />
			</form>
			<% } %>
		<% } %>
      </div>
    </div>
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