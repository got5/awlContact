<%@page import="org.wl.constants.UrlAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://org.wl/jsp/jtl/custom" prefix="custo" %>

<!DOCTYPE HTML>
<html>
	<head>
		<link rel="stylesheet" href="<c:url value="/static/styles/main.css"></c:url>">
	</head>
	<body>
		<custo:menu-list classes="main-menu" currentView="${CURRENT_PAGE}" links="${MENU_LIST}" />
		
		<c:if test="${HAS_ERRORS}">
		
			<ul>
			<c:forEach items="${FIELD_ERRORS}" var="currentFieldError">
				<li>${currentFieldError.field} : ${currentFieldError.defaultMessage}</li>
			</c:forEach>
			</ul>
		
		</c:if>
		

		<c:url var="url" value="/contacts/update"></c:url>
		<form:form method="POST" action="${url}" modelAttribute="CONTACT_BEAN">
		
			<fieldset class="square">
			
				<c:if test="${CONTACT_EDIT}">
					<form:input cssClass="hidden" path="id" />
				</c:if>

				<p>
					<form:label path="nom">
						<spring:message code="menu.contacts.list.name"></spring:message>
					</form:label>
					<form:input path="nom" />
				</p>
				<p>
					<form:label path="prenom">
						<spring:message code="menu.contacts.list.forename"></spring:message>
					</form:label>
					<form:input path="prenom" />
				</p>
				<p>
					<form:label path="email">
						<spring:message code="menu.contacts.list.mail"></spring:message>
					</form:label>
					<form:input path="email" />
				</p>
				<p>
					<form:label path="dateNaissance">
						<spring:message code="menu.contacts.list.birthdate"></spring:message>
					</form:label>	
					<form:input path="dateNaissance" />	
				</p>
				
				<input type="submit" value="submit" />
			
			</fieldset>

		</form:form>

		
		
	</body>
</html>
