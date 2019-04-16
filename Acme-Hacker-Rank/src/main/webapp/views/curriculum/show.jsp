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

<acme:display code="curriculum.personalData.fullname" path="${curriculum.personalData.fullname}"/>
<acme:display code="curriculum.personalData.github" path="${curriculum.personalData.github}"/>
<acme:display code="curriculum.personalData.linkedin" path="${curriculum.personalData.linkedin}"/>
<acme:display code="curriculum.personalData.phone" path="${curriculum.personalData.phone}"/>
<acme:display code="curriculum.personalData.statement" path="${curriculum.personalData.statement}"/>

<acme:button url="curriculum/hacker/list.do" code="curriculum.back"/>

