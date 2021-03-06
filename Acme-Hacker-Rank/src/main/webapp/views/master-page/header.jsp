<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png"
		alt="Acme Hacker Rank Co., Inc." style="margin-bottom: 0.5em;" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="stats/administrator/display.do"><spring:message
								code="master.page.administrator.stats" /></a></li>
				</ul></li>
		</security:authorize>
		<li><a class="fNiv" href="position/search.do"><spring:message
					code="master.page.searchPosition" /></a></li>
					
		<security:authorize access="hasRole('HACKER')">
			<li><a href="finder/hacker/view.do"><spring:message code="master.page.finder" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('COMPANY')">
			<li><a class="fNiv" href="position/company/myList.do"><spring:message
						code="master.page.myPositions" /></a></li>
						<li><a class="fNiv" href="application/company/list.do"><spring:message
						code="master.page.myApplications" /></a></li>
		</security:authorize>
<security:authorize access="hasRole('HACKER')">
			<li><a class="fNiv" href="application/hacker/list.do"><spring:message
						code="master.page.myApplications" /></a></li>
		</security:authorize>

		<li><a class="fNiv" href="position/list.do"><spring:message
					code="master.page.positions" /></a></li>


		<li><a class="fNiv" href="company/list.do"><spring:message
					code="master.page.companies" /></a></li>

		<security:authorize access="hasRole('COMPANY')">
			<li><a class="fNiv" href="problem/company/list.do"><spring:message
						code="master.page.company.list" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
				
					<li class="arrow"></li>
		                    <security:authorize access="hasRole('ADMINISTRATOR')">
		            <li><a href="administrator/administrator/create.do"><spring:message code="master.page.signup.admin" /></a></li>
		                    </security:authorize>
		             
		                <security:authorize  access="hasRole('ADMINISTRATOR')" >
		            <li><a href="actor/editAdm.do"><spring:message
								code="master.page.editProfile" /></a></li>
							</security:authorize>
				
				<security:authorize access="hasAnyRole('HACKER','COMPANY')">
						   <li><a href="actor/edit.do"><spring:message
								code="master.page.editProfile" /></a></li>
				</security:authorize>
					<security:authorize access="hasRole('ADMINISTRATOR')">
					
					<li><a href="actor/administrator/list.do"><spring:message
								code="master.page.actorList" /></a></li>
		            </security:authorize>

					<security:authorize access="hasRole('HACKER')">
					<li><a href="curriculum/hacker/list.do"><spring:message
								code="master.page.curriculum" /> </a></li>
					</security:authorize>
					<li><a href="message/list.do"><spring:message
								code="master.page.message" /> </a></li>
								
					<li><a href="socialprofile/list.do"><spring:message
								code="master.page.socialProfile" /> </a></li>
								
							
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>

				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="company/signup.do"><spring:message
								code="master.page.register.company" /></a></li>
					<li><a href="hacker/signup.do"><spring:message
								code="master.page.register.hacker" /></a></li>

				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

