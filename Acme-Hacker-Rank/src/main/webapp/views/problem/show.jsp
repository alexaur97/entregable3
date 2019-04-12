<%--
 * action-2.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:display code="problem.title" path="${problem.title}"/>
<acme:display code="problem.statement" path="${problem.statement}"/>
<acme:display code="problem.hint" path="${problem.hint}"/>
<p><spring:message code="problem.attachments" />:
</p>
<ul>
<jstl:forEach items="${problem.attachments}" var="x">
<li><a href="${x}"><jstl:out value="${x}"/></a></li>
</jstl:forEach>
</ul>
<jstl:if test="${problem.mode ne 'FINAL'}">
<acme:button url="/problem/company/edit.do?problemId=${problem.id}" code="problem.edit"/>
</jstl:if>
<acme:button url="/problem/company/list.do" code="problem.cancel"/>
<acme:button url="/problem/company/delete.do?problemId=${problem.id}" code="problem.delete"/>
<jstl:if test="${problem.mode eq 'DRAFT'}">
<h3 style="color: #DD8833;"><spring:message code="problem.draft"/></h3>
</jstl:if>
