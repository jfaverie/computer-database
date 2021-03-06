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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="${req.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${req.contextPath}/resources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${req.contextPath}/resources/css/main.css" rel="stylesheet"
	media="screen">
</head>

<spring:message code="generic.computerName" var= "computerName" />
<spring:message code="generic.introduced" var= "introduced"/>
<spring:message code="generic.discontinued" var= "discontinued"/>
<spring:message code="generic.company" var= "company"/>
<spring:message code="generic.edit" var= "edit"/>
<spring:message code="generic.add" var= "add"/>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<mylib:link msg="Application - Database" classRef="navbar-brand"
				context="" cursor="0" limit="10" search="" sortType="0" sortCol="0" />
				
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${nbComputers}&nbsp;<spring:message code="index.computerFound" /></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="<spring:message code="index.search"/>" /> <input
							type="submit" id="searchsubmit" value="<spring:message code="index.filter"/>"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<mylib:link msg="${add}" idRef="addComputer"
						classRef="btn btn-success" context="addComputer" cursor="0"
						limit="10" search="" sortType="0" sortCol="0" />
					<a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode('<spring:message code="generic.edit"/>', '<spring:message code="index.view"/>');"><spring:message code="generic.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="${req.contextPath}/deleteComputer"
			method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><mylib:link context="" msg="${computerName}"
								cursor="${currentPage}" limit="${limit}" search="${search}"
								sortCol="1" sortType="${computerSort}" /></th>
						<th><mylib:link context="" msg="${introduced}"
								cursor="${currentPage}" limit="${limit}" search="${search}"
								sortCol="2" sortType="${introSort}" /></th>
						<th><mylib:link context="" msg="${discontinued}"
								cursor="${currentPage}" limit="${limit}" search="${search}"
								sortCol="3" sortType="${discoSort}" /></th>
						<th><mylib:link context="" msg="${company}"
								cursor="${currentPage}" limit="${limit}" search="${search}"
								sortCol="4" sortType="${companySort}" /></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
							<td><a href="editComputer?id=${computer.id}">${computer.name}</a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.companyName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<mylib:pagination currentPage="${currentPage}" limit="${limit}"
				nbPage="${nbPage}" search="${search}" sortType="${sortType}"
				sortCol="${sortCol}" />
			<div class="btn-group btn-group-sm pull-right" role="group">
				<button onclick="self.location.href='?page=0&nbel=10'" type="button"
					class="btn btn-default">10</button>
				<button onclick="self.location.href='?page=0&nbel=50'" type="button"
					class="btn btn-default">50</button>
				<button onclick="self.location.href='?page=0&nbel=100'"
					type="button" class="btn btn-default">100</button>
			</div>
		</div>
	</footer>

	<script src="${req.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${req.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${req.contextPath}/resources/js/dashboard.js"></script>

</body>
</html>