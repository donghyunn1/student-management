<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2025-03-19
  Time: 오전 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>학생 등록</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            width: 20%;
        }
        input[type=text], input[type=number] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .button {
            margin-top: 10px;
            padding: 8px 16px;
        }
    </style>
</head>
<body>
<h1>학생-등록</h1>

<form method="post" action="/student/register">
    <table>
        <tr>
            <th>ID</th>
            <td><input type="text" name="id" required></td>
        </tr>
        <tr>
            <th>이름</th>
            <td><input type="text" name="name" required></td>
        </tr>
        <tr>
            <th>성별</th>
            <td>
                <input type="radio" id="male" name="gender" value="남" checked>
                <label for="male">남</label>
                <input type="radio" id="female" name="gender" value="여">
                <label for="female">여</label>
            </td>
        </tr>
        <tr>
            <th>나이</th>
            <td><input type="number" name="age" min="1" required></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="등록" class="button">
</form>
</body>
</html>
