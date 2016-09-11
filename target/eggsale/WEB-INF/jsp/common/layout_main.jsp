<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-definition>
<!doctype html>
<html>
    <head>
	<title>EggSale - ${title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="shortcut icon" href="${contextPath}/css/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/css/header.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/css/header.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/css/font.css">
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Source+Code+Pro:600'>
    </head>
    <body>
	<script> function back(){ history.back(); } </script>
	<%@include file="header.jsp" %>
		
	<div id="main">
	    <h2>${title}</h2>
	    <s:messages/>
	    <s:layout-component name="body" />
	</div>
	<footer>
	    &copy; 2013, Eggsale Inc.<br />
	    developped by ACK
	</footer>
    </body>
</html>
</s:layout-definition>