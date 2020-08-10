<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="res/common.css">
</head>
<div class="container">
	<h1>제품 수정</h1>
	<form:form method="post" modelAttribute="productModel">
		<div class="form-group">
			<label>제품코드:</label>
			<form:input path="productCode" class="form-control w200" />
		</div>
		<div class="form-group">
			<label>제품명:</label>
			<form:input path="productName" class="form-control w200" />
		</div>
		<div class="form-group">
			<label>표준가격:</label>
			<form:input path="standardCost" class="form-control w200" />
		</div>
		<div class="form-group">
			<label>표시가격:</label>
			<form:input path="listPrice" class="form-control w200" />
		</div>
		<div class="form-group">
			<label>단위수량:</label>
			<form:input path="quantity" class="form-control w300" />
		</div>
		<div class="form-group">
			<label>카테고리:</label>
			<form:input path="category" class="form-control w300" />
		</div>
		
		<button type="submit" class="btn btn-primary">
			<i class="glyphicon glyphicon-user"></i> 수정
		</button>
	</form:form>
</div>
</body>

</html>