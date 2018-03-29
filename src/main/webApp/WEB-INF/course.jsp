<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Enrollment</title>
</head>
<body>
	<h1>${course.subject }</h1>
	
	<h3>Instructor: ${course.instructor }</h3>
	
	<a href="/courses/lowDate/${course.id}">Sort old to new</a>
	<a href="/courses/highDate/${course.id}">Sort new to old</a>
	
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Date Joined</th>
				<th>Action</th>
			</tr>
		</thead>
			<c:forEach items="${course.courses_users}" var="u">
				<tr>
					<td>${u.user.name}</td>
					<td>${u.user.email}</td>
					<td>${u.user.updatedAt}</td>
					
					<c:if test="${user.id == u.user.id}">
						<c:set var="exists" scope="page" value="3"/>
						<td><a href="/courses/remove/${course.id}">Remove</a></td>
					</c:if>
				</tr>
			</c:forEach>
	</table><br><br>
	
	<c:if test="${exists != 3}">
		<a href="/courses/add/${course.id}"><button>Add Course</button></a>
	</c:if>
	<div>
		<a href="/courses/edit/${course.id}"><button>Edit</button></a>
		
		<a href="/courses/delete/${course.id}"><button>Delete</button></a>
	</div>
</body>
</html>