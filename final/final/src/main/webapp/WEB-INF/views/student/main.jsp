<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MAIN</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>

	<div id="myInfo">
		<h2>내정보</h2>


		<ul class="info-list">
			<li>${loginUser.name}</li>
			<li>${loginUser.id}</li>
			<li>${loginUser.department}</li>
			<li><a href="update" class="btn btn-default">
					<button class="btn btn-default">수정하기</button>
			</a></li>
	</div>

	<div id="myCourse">
		<c:if test="${empty myCourses }">
			<h1>액셀 업로드</h1>
			<hr />

			<form method="post" enctype="multipart/form-data">
				<input type="file" name="file"
					style="margin-bottom: 20px; width: 300px;" /><br />
				<button type="submit" class="btn btn-primary" name="cmd"
					value="upload">업로드</button>
			</form>
			<hr />
		</c:if>
		
		<c:if test="${ not empty myCourses }">
			<a href="search" class="btn btn-default">
					<button class="btn btn-default">내 수강 내역보러가기</button>
			</a>
		</c:if>



	</div>

</body>
</html>