<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="addFuel" value="/fuel_added"></c:url>
<div class="row">


	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-tile">Add Fuel</div>
			</div>

			<div class="panel-body">
				<sf:form class="form-group" method="post"
					action="${addFuel}" modelAttribute="fuel">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<div class="input-group">
						<sf:input class="form-control" placeholder = "Fuel Type" path="type" />
					</div>
					<div class="input-group">
						<sf:input class="form-control" placeholder="Volume in litter" path="volumeInLiter"/>
					</div>
					<div class="input-group">
						<sf:input class="form-control" path="price" type="text" 
							placeholder="Price"/>
						
				
					<div class="input-group">
						<input type="submit" class="btn-primary pull-right" >Save</div>

					</div>
					
					
				</sf:form>

			</div>

		</div>

	</div>
</div>