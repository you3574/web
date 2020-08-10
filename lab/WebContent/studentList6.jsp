<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, lab.*" %>
<%
int currentPage = 1;
int pageSize = 10;

String pg = request.getParameter("pg");
if (pg != null) currentPage = Integer.parseInt(pg); //전체 페이지 수 계산

List<Student> list = StudentDAO5.findAll(currentPage, pageSize); //현재페이지와 레코드 수
int recordCount = StudentDAO5.count(); //전체 레코드 수 계산
int pageCount = (int)Math.ceil((double)recordCount / pageSize); //페이지 갯수 계산
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

<table class="table table-bordered table-condensed">
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
        <% for (Student student : list) { %>
            <tr>
                <td><%= student.getId() %></td>
                <td><%= student.getStudentNumber() %></td>
                <td><%= student.getName() %></td>
                <td><%= student.getDepartmentName() %></td>
                <td><%= student.getYear() %></td>
            </tr>
        <% } %>
    </tbody>
</table>

<% if (currentPage > 1) { %>
    <a class="btn btn-default" href="studentList6.jsp?pg=<%= currentPage-1 %>"> &lt; </a> 
<% } //이전 페이지 계산 2부터 생김.%> 
<% if (currentPage < pageCount) { %>
    <a class="btn btn-default" href="studentList6.jsp?pg=<%= currentPage+1 %>"> &gt; </a>
<% } //다음페이지 계산 마지막 페이지에는 생기지않음. %>

</div>
</body>
</html>
