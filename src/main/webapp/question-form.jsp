<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<a href="https://www.xadmin.net" class="navbar-brand"> ProTest Questionnaire Application </a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${question != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${question == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${question != null}">
            			Edit Question
            		</c:if>
						<c:if test="${question == null}">
            			Add New Question
            		</c:if>
					</h2>
				</caption>

				<c:if test="${question != null}">
					<input type="hidden" name="examquestionid" value="<c:out value='${question.examquestionid}' />" />
				</c:if>
				
				<c:if test="${question == null}">
					<fieldset class="form-group">
					<label>Question Id</label> <input type="text"
						value="<c:out value='${question.examquestionid}' />" class="form-control"
						name="examquestionid" required="required">
					</fieldset>
				</c:if>

				<fieldset class="form-group">
					<label>Exam Id</label> <input type="text"
						value="<c:out value='${question.examid}' />" class="form-control"
						name="examid" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Question</label> <input type="text"
						value="<c:out value='${question.question}' />" class="form-control"
						name="question">
				</fieldset>

				<fieldset class="form-group">
				    <label>Question Answers</label>
				    <textarea class="form-control" name="answer" rows="4">
				        <c:out value="${question.answer.answer1}" />
				        <c:out value="${question.answer.answer2}" />
				        <c:out value="${question.answer.answer3}" />
				        <c:out value="${question.answer.answer4}" />
				    </textarea>
				</fieldset>
				
				
				<fieldset class="form-group">
					<label>Created By</label> <input type="text"
						value="<c:out value='${question.createdBy}' />" class="form-control"
						name="createdBy">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>