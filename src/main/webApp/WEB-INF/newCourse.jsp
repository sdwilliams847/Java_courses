<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Course</title>
</head>
<body>
	<h1>Create a new course</h1>
	
	<c:if test="${errors != null }">
		<c:forEach items="${errors}" var="err">
			<h2>${err.defaultMessage}</h2>
		</c:forEach>
	</c:if>
	
	<form:form action="/courses/new" method="POST" modelAttribute="course">
        <p>
            <form:label path="subject">Name:
            	<form:errors path="subject"></form:errors>
            	<form:input path="subject"></form:input>
            </form:label>
        </p>
        <p>
            <form:label path="instructor">instructor:
            	<form:errors path="instructor"></form:errors>
            	<form:input path="instructor"></form:input>
            </form:label>
        </p>
        
        <p>
            <form:label path="classLimit">Limit:
	         	<form:errors path="classLimit"></form:errors>
            	<form:input type="number" path="classLimit"></form:input>
            </form:label>
        </p>

        <input type="submit" value="Create"/>
    </form:form>

</body>
</html>