<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/defaultStyle.css" />
<title>Assign tasks!</title>
</head>
<body>

	<h1>Facility Manager</h1>

	<p>Here you can see which tasks have to be done for which object
		and who has to care about it. You can also define new assignments.</p>

	<c:if test="${not empty message}">
		<br />
		<p style="color: blue; font: bold; font-size: large;">${message}</p>
	</c:if>

	<br />
	<br />

	<h2>Assignments</h2>

	<c:if test="${empty assignments}">
		<p>No assignments existing!</p>
	</c:if>
	<c:if test="${not empty assignments}">

		<table>
			<tr>
				<th>Object</th>
				<th>Task</th>
				<th>Frequency</th>
				<th>Worker</th>
				<th>Information</th>
				<th></th>
			</tr>

			<c:forEach items="${assignments}" var="assignment">
				<tr>
					<td>${assignment[4]}</td>
					<td>${assignment[3]}</td>
					<td>${assignment[1]}</td>
					<td>${assignment[5]}</td>
					<td>${assignment[2]}</td>
					<td>
						<form method="post" action=".">
							<input type="hidden" name="deleteAssignmentId"
								value="${assignment[0]}" /> <input type="submit"
								name="deleteAction" value="Delete" />
						</form>
					</td>
				</tr>

			</c:forEach>
		</table>

	</c:if>

	<br />
	<br />
	<br />

	<h2>Define new assignment</h2>

	<c:if test="${empty workObjects}">
		<p>No objects existing!</p>
	</c:if>
	<c:if test="${empty tasks}">
		<p>No tasks existing!</p>
	</c:if>
	<c:if test="${empty workers}">
		<p>No workers existing!</p>
	</c:if>
	<c:if test="${empty frequencies}">
		<p>No frequencies existing!</p>
	</c:if>


	<c:if test="${not empty workObjects}">
		<c:if test="${not empty tasks}">
			<c:if test="${not empty workers}">
				<c:if test="${not empty frequencies}">

					<form method="post" action=".">

						<table>

							<tr>
								<th>Object</th>
								<th>Task</th>
								<th>Frequency</th>
								<th>Worker</th>
								<th>Information</th>
								<th></th>
							</tr>

							<tr>
								<td><select name="selectedWorkObjectId">
										<c:forEach items="${workObjects}" var="workObject">
											<option value="${workObject[0]}">${workObject[1]};
												${workObject[2]}</option>
										</c:forEach>
								</select></td>
								<td><select name="selectedTaskId">
										<c:forEach items="${tasks}" var="task">
											<option value="${task[0]}">${task[1]}</option>
										</c:forEach>
								</select></td>

								<td><select name="selectedFrequency">
										<c:forEach items="${frequencies}" var="frequency">
											<option value="${frequency}">${frequency}</option>
										</c:forEach>
								</select></td>

								<td><select name="selectedWorkerId">
										<c:forEach items="${workers}" var="worker">
											<option value="${worker[0]}">${worker[2]}
												${worker[3]}</option>
										</c:forEach>
								</select></td>

								<td><input type="text" name="frequencyInformation" /></td>

								<td><input type="submit" name="saveAction" value="Save" />
								</td>

							</tr>
						</table>

					</form>

				</c:if>
			</c:if>
		</c:if>
	</c:if>

</body>
</html>
