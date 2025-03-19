<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2025-03-19
  Time: 오후 3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학생 - list</title>
</head>
<body>
<h1>
    <a href="/student/register">학생 등록</a>
</h1>

    <table border="1">
        <thead>
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>나이</th>
                <th>성별</th>
                <th>CMD</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${studentList}">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>${student.gender}</td>
            <td>
                <!-- .student/view?id=student1 -->
                <a href="/student/view?id=${student.id}">조회</a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
