<%--
 * list.jsp
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
<br/><spring:message code="application.moment"/>: <jstl:out value="${application.moment}"></jstl:out>
<br/><spring:message code="application.status"/>: <jstl:out value="${application.status}"></jstl:out>
<br/><spring:message code="application.submitMoment"/>: <jstl:out value="${application.submitMoment}"></jstl:out>
<br/><spring:message code="application.hacker"/>: <jstl:out value="${application.hacker.name}"></jstl:out>
<br/><spring:message code="application.curriculum"/>: <jstl:out value="${application.curriculum.idName}"></jstl:out>
<acme:button url="/curriculum/company/show.do?applicationId=${application.id}" code="application.show"/>

<br/><spring:message code="application.problem"/>: <jstl:out value="${application.problem.title}"></jstl:out>
<br/><spring:message code="application.position"/>: <jstl:out value="${application.position.title}"></jstl:out>
<br/><spring:message code="application.company"/>: <jstl:out value="${application.position.company.name}"></jstl:out>
<br/><spring:message code="application.explanation"/>: <jstl:out value="${application.explanation}"></jstl:out>
<br/><spring:message code="application.codeLink"/>: <jstl:out value="${parade.moment}"></jstl:out>

