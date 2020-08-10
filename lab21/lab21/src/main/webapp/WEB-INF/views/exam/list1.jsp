<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- tag library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${R}res/common.js"></script>
<link rel="stylesheet" href="${R}res/common.css">
</head>
<body>
	<div class="container">
		<table class="table table-bordered mt5">
			<thead>
				<tr>
					<th>id</th>
					<th>이름</th>
					<th>이메일</th>
					<th>직위</th>
					<th>전화</th>
					<th>도시</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="employees" items="${ list }">
					<tr>
						<td>${ employees.id }</td>
						<td>${ employees.lastName}</td>
						<td>${ employees.emailAddress}</td>
						<td>${ employees.jobTitle }</td>
						<td>${ employees.businessPhone }</td>
						<td>${ employees.city }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
