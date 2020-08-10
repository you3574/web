<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- tag library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
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
		<h1>학생목록</h1>
		<div class="pull-right mb5">
			<a href="create.do" class="btn btn-info"> <span
				class="glyphicon glyphicon-user"></span> 학생등록
			</a>
		</div>

		<div class="col-md-4 col-sm-12 col-xs-12">
			<!-- Blog Sidebar -->
			<div class="blog-sidebar">
				<!-- Start Search Form -->
				<div class="single-sidebar form">
					로그인 : ${loginUser.name }
					<form:form method="get" modelAttribute="pagination"
						class="form-inline mb5">
						<form:hidden path="now" value="1" />

						<span>정렬순서:</span>

						<form:select path="orderBy" class="form-control autosubmit"
							itemValue="value" itemLabel="label" items="${ orderBy }" />

						<br>

						<form:select path="category" class="form-control ml30"
							itemValue="value" itemLabel="label" items="${ category }" />

						<br>

						<form:input path="searchWord" class="form-control"
							placeholder="검색문자열" />

						<br>

						<span class="ml30">페이지 크기:</span>

						<form:select path="size" class="form-control autosubmit">

							<form:option value="10" />
							<form:option value="15" />
							<form:option value="30" />

						</form:select>

						<br>

						<button type="submit" class="btn btn-default">
							<i class="glyphicon glyphicon-search"></i> 검색
						</button>

						<c:if test="${ pagination.category > 0 || pagination.orderBy > 0}">
							<a class="btn btn-default" href="list?now=1"> <i
								class="glyphicon glyphicon-ban-circle"></i> 검색취소
							</a>
						</c:if>


					</form:form>
				</div>
				<!--/ End Search Form -->
			</div>
			<!--/ End Blog Sidebar -->
			<my:pagination pageSize="${ pagination.size }"
				recordCount="${ pagination.recordCount }" />
		</div>
		<table class="table table-bordered mt5">
			<thead>
				<tr>
					<th>id</th>
					<th>학번</th>
					<th>이름</th>
					<th>학과</th>
					<th>학년</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${ students }">
					<tr data-url="edit?id=${ student.id }">
						<td>${ student.id }</td>
						<td>${ student.studentNumber }</td>
						<td>${ student.name }</td>
						<td>${ student.departmentName }</td>
						<td>${ student.year }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
