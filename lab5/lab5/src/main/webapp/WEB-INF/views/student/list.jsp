<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
        rel="stylesheet" media="screen">
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="${R}res/common.js"></script>
  <link rel="stylesheet" href="${R}res/common.css">
</head>
<body>
<div class="container">
  <h1>학생목록</h1>
  <div class="pull-right mb5">
  <a href="create" class="btn btn-info">
    <span class="glyphicon glyphicon-user"></span> 학생등록</a>
  </div> 
  
 <!--  <form method="post">  
    <div class="form-group">
      <label>학번:</label>
      <input name="inputNumber" id="inputNumber" class="form-control w200"/>
    </div>
  	<div>
 	  <button type="submit">조회</button>
  	</div>
  </form>
   -->
   <form method="post">  
    <div class="form-group">
      <select name="type">
      	 <option value="all" <%= "all".equals("type") ? "selected" : "" %> >전체</option>
     	 <option  value="name" <%= "name".equals("type") ? "selected" : "" %>>이름</option>
     	 <option  value="studentNumber" <%= "studentNumber".equals("type") ? "selected" : "" %>>학번</option>
     	 <option  value="departmentName" <%= "departmentName".equals("type") ? "selected" : "" %>>학과</option>
      </select>
      <input name="inputText" id="inputText" class="form-control w200"/>
    </div>
  	<div>
 	  <button type="submit">조회</button>
  	</div>
  </form>
   
  
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

