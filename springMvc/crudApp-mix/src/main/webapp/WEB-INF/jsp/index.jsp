<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://org.wl/jsp/jtl/custom" prefix="custo" %>

<!DOCTYPE HTML>
<html>
	<head>
		<link rel="stylesheet" href="<c:url value="/static/styles/main.css"></c:url>">
	</head>
	<body>
		<custo:menu-list classes="main-menu" currentView="${CURRENT_PAGE}" links="${MENU_LIST}" />
		
		<p id="partialToLoad" class="hidden">${PARTIAL_TO_LOAD}</p>
		
		<div id="container"></div>

		<script type="text/javascript" src="<c:url value="/static/js/jquery-2.1.4.js"></c:url>"></script>
		<script type="text/javascript" src="<c:url value="/static/js/manage.js"></c:url>"></script>
		<script type="text/javascript" src="<c:url value="/static/js/start.js"></c:url>"></script>
		
	</body>
</html>
