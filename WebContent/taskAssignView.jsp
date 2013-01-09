<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Tasks</title>
</head>
<body>

	<h1>Assign Tasks</h1>


	<c:if test="${empty tasks}">
		<p>No tasks!</p>
	</c:if>
	<c:if test="${not empty tasks}">
		<table>
			<c:forEach items="${tasks}" var="task">
				<tr>
					<td>${task}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


</body>
</html>
