<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, lab.*" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%
int currentPage=1;
int recordCount=0;

String pg = request.getParameter("pg");
if (pg != null) currentPage = Integer.parseInt(pg); //전체 페이지 수 계산

String s = request.getParameter("departmentId");
int departmentId=(s==null)? 0 : Integer.parseInt(s); //기본값 

String s1 =  request.getParameter("pageSize");
int pageSize= (s1==null)? 10 : Integer.parseInt(s1);

List<user> list;
if(departmentId==0){
   list = userDAO2.findAll(currentPage,pageSize);
   recordCount = userDAO2.count();
}
else{
   list=userDAO2.findBydepartmentId(departmentId,currentPage,pageSize);
   recordCount=userDAO2.count2(departmentId);
}
int pageCount=(int)Math.ceil((double)recordCount/pageSize);
%>
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
      thead th { background-color: #eee; }
      table.table { width: 700px; }
  </style>
</head>
<body>

<div class="container">

<h1>학생목록</h1>
<form class="form-inline">
  <div class="form-group">
    <label>학과</label>
    <select name="departmentId" class="form-control">
      <option value="0" <%= departmentId == 0 ? "selected" : "" %>>전체</option>
      <option value="1" <%= departmentId == 1 ? "selected" : "" %>>국어국문학</option>
      <option value="2" <%= departmentId == 2 ? "selected" : "" %>>영어영문학</option>
      <option value="3" <%= departmentId == 3 ? "selected" : "" %>>불어불문학</option>
      <option value="4" <%= departmentId == 4 ? "selected" : "" %>>소프트웨어공학</option>
      
    </select>
    <select name="pageSize" class="form-control">
      <option value="3" <%= pageSize == 3 ? "selected" : "" %>>3개씩</option>
      <option value="5" <%= pageSize == 5 ? "selected" : "" %>>5개씩</option>
      <option value="10" <%= pageSize == 10 ? "selected" : "" %>>10개씩</option>
    </select>
    
  </div>
  <button type="submit" class="btn btn-primary">조회</button>
</form>
<table class="table table-bordered table-condensed">
    <thead>
        <tr>
            <th>번호</th>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>이름</th>
            <th>이메일</th>
            <th>학번</th>
            <th>학과</th>
        </tr>
    </thead>
    <tbody>
        <% for (user user : list) { %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getUserid() %></td>
                <td><%= user.getPassword() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail()%></td>
               	<td><%= user.getDepartmentId() %></td>
               	<td><%= user.getDepartmentName() %></td>
            </tr>
        <% } %>
    </tbody>
</table>

<my:pagination pageSize="<%= pageSize %>" recordCount="<%= recordCount %>" queryStringName="pg" />

</div>
</body>
</html>
