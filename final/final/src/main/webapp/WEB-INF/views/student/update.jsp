<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
      body { font-family: 굴림체; }
      input.form-control, select.form-control { width: 200px; }
  </style>
</head>
<body>

<div class="container">

<h1>정보수정</h1>
<hr />

<form method="post" modelAttribute="User">
  
  
  <div class="form-group">
    <label>비밀번호</label>
    <input type="password" class="form-control" name="pw" />
  </div>
   <div class="form-group">
    <label>학과</label>
     <input type="text" class="form-control" name="department"  />
    </select>
  </div>
  
  <div class="form-group">
    <label>학년</label>
    <select class="form-control" name="grade">
      <option value="1">1학년</option>
      <option value="2">2학년</option>
      <option value="3">3학년</option>
      <option value="4">4학년</option>
    </select>
  </div>
  
  <div class="form-group">
    <label>휴대폰</label>
    <input type="text" class="form-control" name="phone" />
  </div>
  
  <div class="form-group">
    <label>이메일</label>
    <input type="email" class="form-control" name="email" />
  </div>
  
  <button type="submit" class="btn btn-primary">
    <i class="glyphicon glyphicon-ok"></i> 저장
  </button>
</form>

<hr />

</div>
</body>
</html>
