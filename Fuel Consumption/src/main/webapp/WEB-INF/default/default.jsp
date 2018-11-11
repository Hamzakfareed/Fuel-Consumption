<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<link type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />

<!-- 
	This is only for javascript tagging on the profile
 -->

<link type="text/css"
	href="${pageContext.request.contextPath}/css/jquery.tagit.css"
	rel="stylesheet" />

<script
	src="https://ajax.googleapis.com//ajax/libs/jquery/1.12.4/jquery.min.js"></script>



<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/tag-it.min.js"></script>

<title><tiles:insertAttribute name="title" /></title>
</head>
<body>

	<nav class="navbar  ">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a href="/" class="navbar-brand">Webapp</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse">
			<ul class="nav navbar-nav navbar-right ">
				

				

				<%-- <sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="${pageContext.request.contextPath}/admin">Admin</a>
				</sec:authorize> --%>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/register">register</a>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/login">Login</a>
				</sec:authorize>
				
				
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="javascript:$('#logoutForm').submit();">logout</a></li>
				</sec:authorize>
				
				
			
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="/fuel">fuel</a></li>
				</sec:authorize>
				
				
				
			</ul>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/">Home</a>
				<li><a href="${pageContext.request.contextPath}/about">About</a>
			</ul>
		</div>
	</div>
	</nav>

		<c:url var="logoutUrl" value="/logout" />

	<form id="logoutForm" class="form-group" method="post" name="logout" action="${logoutUrl}">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

	</form>
	<div class="container">
		<tiles:insertAttribute name="content" />
	</div>

	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>