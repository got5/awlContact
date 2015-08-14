<%@page import="org.wl.constants.UrlAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://org.wl/jsp/jtl/custom" prefix="custo" %>

<!DOCTYPE HTML>
<html>
	<head>
		<link rel="stylesheet" href="<c:url value="/static/styles/main.css"></c:url>">
	</head>
	<body>
		<custo:menu-list classes="main-menu" currentView="${CURRENT_PAGE}" links="${MENU_LIST}" />
		
		<article class="square">
			<table>
				<thead>
					<tr>
						<th><spring:message code="menu.contacts.list.name"></spring:message></th>
						<th><spring:message code="menu.contacts.list.forename"></spring:message></th>
						<th><spring:message code="menu.contacts.list.mail"></spring:message></th>						
						<th><spring:message code="menu.contacts.list.birthdate"></spring:message></th>
						<th><spring:message code="menu.contacts.list.edit"></spring:message></th>
					</tr>
				</thead>
				<c:forEach items="${CONTACT_LIST}" var="contact">
					<tr>
						<td> <c:out value="${contact.nom}" /> </td>
						<td> <c:out value="${contact.prenom}" /></td>
						<td> <c:out value="${contact.email}" /></td>
						<td> <fmt:formatDate value="${contact.dateNaissance}" pattern="dd/MM/yyyy"/> </td>
						<td>
							<c:set value="<%=UrlAction.new_contacts.getUrl()%>" var="baseurl"></c:set>
							<c:set value="id=${contact.id}" var="paramurl"></c:set>
							<a href='<c:url value="${baseurl}?${paramurl}" ></c:url>'>
								<img src='<c:url value="/static/images/edit.png"></c:url>' />
							</a> 
						</td>
						<td></td>
					</tr>
				</c:forEach>
			</table>
		</article>		
		
		
	</body>
</html>
