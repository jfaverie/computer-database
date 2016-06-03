<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<c:set var="req" value="${pageContext.request}" />

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${req.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${req.contextPath}/resources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${req.contextPath}/resources/css/main.css" rel="stylesheet"
	media="screen">
</head>

<spring:message code="generic.computerName" var="computerName" />
<spring:message code="generic.introduced" var="introduced" />
<spring:message code="generic.discontinued" var="discontinued" />
<spring:message code="generic.company" var="company" />
<spring:message code="generic.edit" var="edit" />
<spring:message code="generic.cancel" var="cancel" />
<spring:message code="generic.or" var="orr" />
<spring:message code="index.add" var="add" />

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="home"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>${add}</h1>
					<form action="addComputer" method="POST">
						<fieldset>
							<div
								class="form-group ${ errors.contains('name') ? 'has-error' : '' }">
								<label for="computerName">${computerName}</label> <input
									type="text" class="form-control" id="name"
									placeholder="${computerName}" name="name">
							</div>
							<div
								class="form-group ${ errors.contains('introduced') ? 'has-error' : '' }">
								<label for="introduced">${introduced}</label> <input
									type="date" class="form-control" id="introduced"
									placeholder="Introduced date" name="introduced">
							</div>
							<div
								class="form-group ${ errors.contains('discontinued') ? 'has-error' : '' }">
								<label for="discontinued">${discontinued}</label> <input
									type="date" class="form-control" id="discontinued"
									placeholder="Discontinued Date" name="discontinued">
							</div>
							<div
								class="form-group ${ errors.contains('company') ? 'has-error' : '' }">
								<label for="companyId">${company}</label> <select
									class="form-control" class="dropdown-menu" id="companyId"
									name="companyId">
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="${add}" class="btn btn-primary disabled"
								id="addElement" disabled="disabled"> ${orr} <a href="home"
								class="btn btn-default">${cancel}</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<script src="${req.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${req.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${req.contextPath}/resources/js/addcomputer.js"></script>
</body>
</html>