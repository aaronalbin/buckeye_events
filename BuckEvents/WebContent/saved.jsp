<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="site_files/css/ie7.css" media="all" />
<![endif]-->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="site_files/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="site_files/js/scripts.js"></script>

<% int index = 0;%>
<script type="text/javascript">
$(document).ready(function() {         
    $('#newslist-next').click(function() {
    	var url = 'loadSavedEvents?q=next';
        $.get(url, function(responseJson) {  
        	var i = 0;
			for (i = 0; i < 5; i++) {
				$('#eventcontainer'+i).fadeOut(function() {
            	});
			}
            
            $.each(responseJson, function(index, e) {   
            	$('#eventcontainer'+index).fadeOut(function() {
            		$('#eventid'+index).val(e.id);   
            		$('#eventname'+index).val(e.name);        
            		$('#eventcategory'+index).text(e.category);
            		$('#eventdate'+index).text('- ' + e.date);
            		$('#eventdescription'+index).text(e.description);
            	}).fadeIn();
            });
        });
    });
    $('#newslist-prev').click(function() {
    	var url = 'loadSavedEvents?q=prev';
        $.get(url, function(responseJson) {  
        	var i = 0;
			for (i = 0; i < 5; i++) {
				$('#eventcontainer'+i).fadeOut(function() {
            	});
			}
            
            $.each(responseJson, function(index, e) {  
            	$('#eventcontainer'+index).fadeOut(function() {
            		$('#eventid'+index).val(e.id);   
            		$('#eventname'+index).val(e.name);        
            		$('#eventcategory'+index).text(e.category);
            		$('#eventdate'+index).text('- ' + e.date);
            		$('#eventdescription'+index).text(e.description);
            	}).fadeIn();
            });
        });
    });
});
</script>


<title>Buckeye Events</title>
</head>
<body>

<!-- Begin Header Wrapper -->
<div id="container">
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
    <div id="news"> 
    
    <script type="text/javascript">
	
    </script>
     
      <!-- Begin News Navigation -->
      <div id="newslist">

        <ul>
        
          <c:forEach var="e" items="${userevents}">
          <!-- Begin Entry Heading 1 -->
          <li id="<%="eventcontainer"+index%>"><a href="#"><img src="site_files/images/events/thumbnail/blog-th1.jpg" alt="" class="left" /></a>
	            <div class="topcontent">
		            <form action="viewEvent" method="post">
						<input id="<%="eventid"+index%>" type="hidden" name="eventid" value="${e.getId()}">
						<input id="saved" type="hidden" name="saved" value="true">
						<input id="<%="eventname"+index%>" class="linkbutton" type="submit" value="${e.getName()}" />
					</form>
					<span id="<%="eventdate"+index%>" class="topdate">- ${e.getDate()}</span>
					<span id="<%="eventcategory"+index%>" class="categoryright">${e.getCategory()}</span>
				</div>
				<span id="<%="eventdescription"+index%>" class="leftdescription">${e.getDescription()}...</span>
          </li>
          <% index = index+1; %>
          <!-- End Entry Heading 1 -->
          </c:forEach>
          
        </ul>
      </div>
      
      <div class="clearfix"></div>
      <div id="scroll"> <a href="#" id="newslist-prev" class="jbutton"></a> <a href="#" id="newslist-next" class="jbutton"></a> </div>
      <div class="clearfix"></div>
    </div>
    <!-- End News Navigation --> 
    
  </div>
  <div class="clearfix"></div>
  <div class="push"></div>
</div>
<!-- End Wrapper --> 

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