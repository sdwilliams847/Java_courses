<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<h1>Welcome, ${user.name}</h1>
	
	<h3>Courses</h3>
	
	<a href="/courses/low">Sort low to high</a>
	<a href="/courses/high">Sort high to low</a>
	<table>
		<thead>
			<tr>
				<th>Course</th>
				<th>Instructor</th>
				<th>Signups</th>
				<th>Action</th>
			</tr>
		</thead>

			<c:forEach items="${courses}" var="course">
				
					<tr>
						<td><a href="/courses/view/${course.id}">${course.subject}</a></td>
						<td>${course.instructor}</td>
						<td>${course.signups}/${course.classLimit}</td>
						
						<c:set var="exists" scope="page" value="2"/>
							<c:forEach items="${course.courses_users }" var="u">
								<c:if test="${u.user.id == user.id }">
								<c:set var="exists" value="1"/>
									<td>Already added</td>
								</c:if>
							</c:forEach>
						<c:if test="${exists == 2 }">	
							<c:if test="${course.classLimit <= course.signups}">
								<td>full</td>
							</c:if>
							<c:if test="${course.classLimit > course.signups}">
								<td><a href="/courses/add/${course.id}">Add</a></td>
							</c:if>
						</c:if>
						<c:set var="exists" value="2"/>
					</tr>
			</c:forEach>

	</table><br><br>
	
	<a href="/courses/new"><button>Add a Course</button></a>
	
</body>
</html>