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


	<div id="myCourse">
		<form method="get" modelAttribute="qpagination"
			class="form-inline mb5">




			<div class="search-bar">

				<div class="searchboard">
					
					<input path="searchWord" class="searchWord"
						placeholder="SEACHWORD"
						style="width=300px;width:300px; padding-top: 3px;padding-bottom: 3px;" />

					<button type="submit" class="searchWord">검색</button>



				</div>
		</form>
		<h2>내 수강 내역</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>년도</th>
					<th>학기</th>
					<th>과목코드</th>
					<th>과목명</th>
					<th>이수구분</th>
					<th>학점</th>
					<th>성적등급</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="myCourse" items="${ myCourses }">
					<tr>
						<td>${ myCourse.id }</td>
						<td>${ myCourse.year }</td>
						<td>${ myCourse.semester }</td>
						<td>${ myCourse.courseId }</td>
						<td>${ myCourse.subjectName }</td>
						<td>${ myCourse.category }</td>
						<td>${ myCourse.credits }</td>
						<td>${ myCourse.grade }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>




	</div>

</body>
</html>