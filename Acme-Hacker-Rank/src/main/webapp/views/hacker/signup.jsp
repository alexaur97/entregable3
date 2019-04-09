<%--
 * action-1.jsp
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

<form:form id="form" action="hacker/signup.do"
	modelAttribute="hackerRegisterForm" method="POST">
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="hacker.personal.data" />
		</legend>
		<acme:textbox code="hacker.name" path="name" />
		<acme:textbox code="hacker.surnames" path="surnames" />
		<acme:textbox code="hacker.vat" path="VAT" />
		<acme:textbox code="hacker.photo" path="photo" />
		<acme:textbox code="hacker.email" path="email" />
		<acme:textbox code="hacker.phone" path="phone" />
		<acme:textbox code="hacker.address" path="address" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="hacker.userAccount" />
		</legend>
		<acme:textbox code="hacker.username" path="username" />
		<acme:password code="hacker.password" path="password" />
		<acme:password code="hacker.confirmPassword" path="confirmPassword" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="hacker.creditCard" />
		</legend>
		<acme:textbox code="hacker.holderName" path="holderName" />
		<acme:textbox code="hacker.brandName" path="brandName" />
		<acme:textbox code="hacker.number" path="number" />
		<acme:textbox code="hacker.expirationMonth" path="expirationMonth" />
		<acme:textbox code="hacker.expirationYear" path="expirationYear" />
		<acme:textbox code="hacker.cvv" path="cvv" />
	</fieldset>
	<spring:message code="hacker.check" />
	<form:checkbox path="terms" />
	<form:errors path="terms" cssClass="error" />
	<br/>

	<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<button type="submit" onclick="return validatePhoneNumber()" name="save">
				<spring:message code="hacker.save" />
			</button>
		</jstl:when>
		<jstl:otherwise>
			<button type="submit" onclick="return validatePhoneNumberEs()" name="save">
				<spring:message code="hacker.save" />
			</button>
		</jstl:otherwise>
	</jstl:choose>
	<acme:cancel url="/#" code="hacker.cancel" />

<script type="text/javascript">
		function validatePhoneNumber() {
			var phoneNumber = document.getElementById("phone");
			if (!(phoneNumber.value).match("\\+\d{3}([ ]{1}[(]{1}\\d{1,3}[)]{1})? \\d{4,}|\\+\\d{3} \\d{4,}|^\\d{4,}$|^$")) { return confirm("Phone number doesn't adhere to the correct pattern. Do you want to continue?"); }
		}
		function validatePhoneNumberEs() {
			var phoneNumber = document.getElementById("phone");
			if (!(phoneNumber.value).match("\\+\\d{3}([ ]{1}[(]{1}\\d{1,3}[)]{1})? \\d{4,}|\\+\\d{3} \\d{4,}|^\\d{4,}$|^$")) { return confirm("El teléfono no se ajusta al patrón correcto. ¿Desea continuar?"); }
		}
	</script>
</form:form>