<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login and Register</title>
</head>
<body>
	<c:if test="${errors != null }">
		<c:forEach items="${errors}" var="err">
			<h2>${err.defaultMessage}</h2>
		</c:forEach>
	</c:if>

		<h1>Register!</h1>
        
	<form:form action="/users/new" method="POST" modelAttribute="user">
        <p>
            <form:label path="name">Name:
            	<form:errors path="name"></form:errors>
            	<form:input path="name"></form:input>
            </form:label>
        </p>
        <p>
            <form:label path="email">Email:
            	<form:errors path="email"></form:errors>
            	<form:input path="email"></form:input>
            </form:label>
        </p>
        <p>
            <form:label path="password">Password:
	         	<form:errors path="password"></form:errors>
	           	<form:password path="password"></form:password>
            </form:label>
        </p>
        
        <p>
            <form:label path="confirm">Confirm Password:
	         	<form:errors path="confirm"></form:errors>
	           	<form:password path="confirm"></form:password>
            </form:label>
        </p>

        <input type="submit" value="Register!"/>
    </form:form>
    
   	<c:if test="${error != null }">
		<c:forEach items="${errors}" var="err">
			<h2>${err.defaultMessage}</h2>
		</c:forEach>
	</c:if>
    
    <h1>Login</h1>
    
     <form method="POST" action="/users/login">
        <p>
            <label for="email">Email:</label>
            <input type="text" name="email"/>
        </p>
        <p>
            <label for="password">Password:</label>
            <input type="password" name="password"/>
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Login!"/>
    </form><br>

</body>
</html>