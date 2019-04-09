 * Copyright (C) 2019 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<br/>
<spring:message code="company.commercialName"/>: <jstl:out value="${company.commercialName}"></jstl:out>
<spring:message code="company.name"/>: <jstl:out value="${company.name}"></jstl:out>
<spring:message code="company.surnames"/>: <jstl:out value="${company.surnames}"></jstl:out>
<spring:message code="company.VAT"/>: <jstl:out value="${company.VAT}"></jstl:out>
<spring:message code="company.photo"/>: <jstl:out value="${company.photo}"></jstl:out>
<spring:message code="company.email"/>: <jstl:out value="${company.email}"></jstl:out>
<spring:message code="company.phone"/>: <jstl:out value="${company.phone}"></jstl:out>
<spring:message code="company.address"/>: <jstl:out value="${company.address}"></jstl:out>
<spring:message code="company.positions"/>
<ul>
	<jstl:forEach items="${positions}" var="x">
		<li><a href="position/display.do?positionId=${x.id}"><jstl:out value="${x.title}" /></a></li>
	</jstl:forEach>
</ul>



	