<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buckeye Events</title>
</head>
<body>
	<a href="index.html">Home</a>
	<form action="inject" method="post">
		<input type="submit" value="Inject Data">
	</form>
    <p>${messages.success}</p>
        
</body>
</html>
