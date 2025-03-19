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
    <title>학생-등록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    <meta charset="UTF-8" />
</head>
<body>
<!--
    action 주소 설정
    //등록
        action = /student/register
    //수정
        action = /student/update
-->
<c:choose>
    <c:when test="${empty action}">
        <c:set var="actionUrl" value="/student/register" />
    </c:when>
    <c:otherwise>
        <c:set var="actionUrl" value="${action}" />
    </c:otherwise>
</c:choose>

<form method="post" action="${actionUrl}">
    <table>
        <tbody>
        <tr>
            <th>ID</th>
            <td>
                <c:choose>
                    <c:when test="${empty student}">
                        <input type="text" name="id" value="${student.id}" required />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="id" value="${student.id}" readonly />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <!-- input 구현 -->
        <tr>
            <th>이름</th>
            <td><input type="text" name="name" value="${student.name}" required /></td>
        </tr>
        <tr>
            <th>성별</th>
            <td>
                <input type="radio" id="male" name="gender" value="남" ${student.gender eq 'M' ? 'checked' : ''} />
                <label for="male">남</label>
                <input type="radio" id="female" name="gender" value="여" ${student.gender eq 'F' ? 'checked' : ''} />
                <label for="female">여</label>
            </td>
        </tr>
        <tr>
            <th>나이</th>
            <td><input type="number" name="age" value="${student.age}" min="1" required /></td>
        </tr>
        </tbody>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>
</body>
</html>
