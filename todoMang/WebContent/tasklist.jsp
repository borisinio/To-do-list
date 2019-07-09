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
<title>Task List</title>

</head>

<body>
	<%
	
 	List<Item> items = (List)request.getSession().getAttribute("tasks");
 	System.out.println("from task list jsp " + items);%>
	
	<div class="align-center">
		<h1>My Task List</h1>


		<div class="accordion" id="accordionExample">
			<div class="card">
				<div class="card-header" id="headingOne">
					<h2 class="mb-0">
						<button class="btn btn-link" type="button" data-toggle="collapse"
							data-target="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne">Add New Task</button>
					</h2>
				</div>

				<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
					data-parent="#accordionExample">
					<div class="card-body">
						<form action="tasklist" method="post">
							<input type="text" name="task" placeholder="task name...">
							<input type="submit" name="addBtn" value="add task">

						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="align-center">
	  <form action="loginUser" method="post">
							<input type="submit" name="outBtn"
								class="btn btn-outline-danger" value="Logout"  />
						</form action="loginUser">
	</div>
	
	


	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Task</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
    <tbody>
		      <%
		      for(int j=0;j<items.size();j++) { // put all activities in the data table 
		      %>
		        <tr>  
		    		<th><%= j+1 %></th>
		      	    <th><%= items.get(j).getTask() %></th>
		      	    	<th><form action="tasklist" method="post" >
		      	    	 <input type="hidden" name="id" value="<%= items.get(j).getId() %>"/>
							<input type="submit" name="deleteBtn" 
								class="btn btn-outline-danger" value="Done"  />
								
								</form>
	
			<form action="tasklist" method="post">
						 <input type="hidden" name="id" value="<%= items.get(j).getId() %>"/>
						 <input type="hidden" name="task" value="<%= items.get(j).getTask() %>"/>
						
						<input type="submit" name="editBtn"
						class="btn btn-outline-primary" value="edit" />
				</form></th>
				
		        
		   
		    	</tr> 
		   <%} %>
		   </tbody>
	










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