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
		
		<ul class="square">
			<li><img alt="" src='<c:url value="/static/images/atosworldline_logo.gif"></c:url>'></li>
			<li><img alt="" src='<c:url value="/static/images/chtijug_logo.png"></c:url>'></li>
			<li>
					<em>Ch'ti JUG</em> " El groupe Java din ch'Nord ed' la France "
			</li>
		</ul>
		
	</body>
</html>
