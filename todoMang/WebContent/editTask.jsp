<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.boris.todoMang.model.User"%>
<%@ page import="com.boris.todoMang.model.HibernateToDoListDAO"%>
<%@ page import="com.boris.todoMang.model.Item"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/styles.css"
	rel="stylesheet" type="text/css">
<meta charset="windows-1255">
<title>Update task</title>
</head>
<body>
<%
	
 	List<Item> items = (List)request.getSession().getAttribute("tasks");
 	%>
	<div class="align-center">
		


		<div class="accordion" id="accordionExample">
			<div class="card">
				<div class="card-header" id="headingOne">
					<h2 class="mb-0">
						<button class="btn btn-link" type="button" data-toggle="collapse"
							data-target="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne">Click to Update Your Task </button>
					</h2>
				</div>

				<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
					data-parent="#accordionExample">
					<div class="card-body">
						<form action="editTask" method="post">
						 <input type="hidden" name="id" value="<%= request.getAttribute("id") %>"/>
							<input type="text" name="task" placeholder="task name...">
							<input type="submit" name="UpdateBtn" value="Edit">

						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>





	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

</body>
</html>