<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>ProTest Questionnaire Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> ProTest Questionnaire </a>
			</div>

			
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Questions</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Question</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Exam Question Id</th>
						<th>ExamId</th>						
						<th>Question</th>
						<th>Answer</th>
						<th>Created Time</th>
						<th>Created By</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="question" items="${listQuestions}">

						<tr>
							<td><c:out value="${question.examquestionid}" /></td>
							<td><c:out value="${question.examid}" /></td>							
							<td><c:out value="${question.question}" /></td>
							<td>
							    <c:out value="${question.answer.answer1}" /><br/>
							    <c:out value="${question.answer.answer2}" /><br/>
							    <c:out value="${question.answer.answer3}" /><br/>
							    <c:out value="${question.answer.answer4}" /><br/>
							</td>
						
							<td><c:out value="${question.createdTime}" /></td>
							<td><c:out value="${question.createdBy}" /></td>
							<td><a href="edit?id=<c:out value='${question.examquestionid}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${question.examquestionid}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>