<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="loginUrl" value="/login"/>

<c:if test="${!empty param.error }">
Please check your username and password!


</c:if>
<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">Login Form</div>
			</div>

			<div class="panel-body">
				<form class="form-group" method="post"  action="${loginUrl}" >
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				
					<div class="input-group">
						<input class="form-control" name="username" type="text" placeholder="Email"></input>
					</div>
					<div class="input-group">
						<input class="form-control" name="password" type="text" placeholder="Password"></input>
					</div>
					
					<div class="input-group">
						<input type="submit" class="btn-primary pull-right" value="Login" ></input>
					</div>
				</form>

			</div>

		</div>

	</div>
</div>