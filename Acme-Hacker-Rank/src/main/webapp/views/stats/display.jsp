<%--
 * footer.jsp
 *
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

<h3>
<spring:message code="stats.position.company" /> :
</h3>
<spring:message code="stats.position.company.average" />
:
<jstl:out value="${positionsPerCompany[0][0]}" />
<br />
<spring:message code="stats.position.company.min" />
:
<jstl:out value="${positionsPerCompany[0][1]}" />
<br />
<spring:message code="stats.position.company.max" />
:
<jstl:out value="${positionsPerCompany[0][2]}" />
<br />
<spring:message code="stats.position.company.stddev" />
:
<jstl:out value="${positionsPerCompany[0][3]}" />
<br />

<h3>
<spring:message code="stats.application.hacker" /> :
</h3>
<spring:message code="stats.application.hacker.average" />
:
<jstl:out value="${applicationsPerHacker[0][0]}" />
<br />
<spring:message code="stats.application.hacker.min" />
:
<jstl:out value="${applicationsPerHacker[0][1]}" />
<br />
<spring:message code="stats.application.hacker.max" />
:
<jstl:out value="${applicationsPerHacker[0][2]}" />
<br />
<spring:message code="stats.application.hacker.stddev" />
:
<jstl:out value="${applicationsPerHacker[0][3]}" />
<br />

<h3>
<spring:message code="stats.companiesHaveOfferedMorePositions" /> :
</h3>
<ul>
	<jstl:forEach items="${companiesHaveOfferedMorePositions}" var="x">
		<li><jstl:out value="${x.commercialName}" /></li>
	</jstl:forEach>
</ul>
<br />

<h3>
<spring:message code="stats.hackersHaveMadeMoreApplications" /> :
</h3>
<ul>
	<jstl:forEach items="${hackersHaveMadeMoreApplications}" var="x">
		<li><jstl:out value="${x.name} ${x.surnames}" /></li>
	</jstl:forEach>
</ul>
<br />

<h3>
<spring:message code="stats.salaryOfferedPerPosition" /> :
</h3>
<spring:message code="stats.salaryOfferedPerPosition.average" />
:
<jstl:out value="${salaryOfferedPerPosition[0][0]}" />
<br />
<spring:message code="stats.salaryOfferedPerPosition.min" />
:
<jstl:out value="${salaryOfferedPerPosition[0][1]}" />
<br />
<spring:message code="stats.salaryOfferedPerPosition.max" />
:
<jstl:out value="${salaryOfferedPerPosition[0][2]}" />
<br />
<spring:message code="stats.salaryOfferedPerPosition.stddev" />
:
<jstl:out value="${salaryOfferedPerPosition[0][3]}" />
<br />
<br />

<h3>
<spring:message code="stats.worstPositionsSalary" /> :
</h3>
<ul>
	<jstl:forEach items="${worstPositionsSalary}" var="x">
		<li><jstl:out value="${x.title} - ${x.company.commercialName}" /></li>
	</jstl:forEach>
</ul>

<h3>
<spring:message code="stats.bestPositionsSalary" /> :
</h3>
<ul>
	<jstl:forEach items="${bestPositionsSalary}" var="x">
		<li><jstl:out value="${x.title} - ${x.company.commercialName}" /></li>
	</jstl:forEach>
</ul>
<br />

