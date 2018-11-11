<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="registerUserLink" value="/createuser"></c:url>
<div class="row">


	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-tile">Registeration Form</div>
			</div>

			<div class="panel-body">
				<sf:form class="form-group" method="post"
					action="${registerUserLink}" modelAttribute="driver">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<div class="input-group">
						
						<sf:input class="form-control" placeholder = "First Name" path="firstName" />
						<div><sf:errors cssClass="errors"  path="firstName"/></div>
						
						<span class="input-group-btn" style="width: 10px"></span>
						<sf:input type="text" class="form-control" placeholder="Last Name"
							path="lastName"></sf:input><div><sf:errors cssClass="errors"  path="lastName"/></div>
					</div>
					<div class="input-group">
						<sf:input class="form-control" placeholder="Email" path="email"/>
						<div><sf:errors cssClass="errors"  path="email"/></div>
					</div>
					<div class="input-group">
						<sf:input class="form-control" path="password" type="password"
							placeholder="Password"/>
							<div><sf:errors cssClass="errors"  path="lastName"/></div>
						
					</div>
					<div class="input-group">
						<input class="form-control" type="password"
							placeholder="Repeat Password"></input>
					</div>
					<div class="input-group">
						<input type="submit" class="btn-primary pull-right" />

					</div>
					
				
					
				</sf:form>

			</div>

		</div>

	</div>
</div>