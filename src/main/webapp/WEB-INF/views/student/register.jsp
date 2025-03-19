<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2025-03-19
  Time: 오후 4:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학생 - 등록</title>
</head>
<body>
    <form method="post" action="${action}">
        <table border="1">
            <tbody>
            <tr>
                <th>아이디</th>
                <td>
                    <input type="text" name ="id" value="${student.id}" required ${not empty student ? 'readonly' : ''} />
                </td>
            </tr>
            <tr>
                <th>이름</th>
                <td>
                    <input type="text" name ="name" value="${student.name}" required />
                </td>
            </tr>
            <tr>
                <th>성별</th>
                <td>
                    <input type="radio" name="gender" value="M" ${student.gender eq 'M' ? 'checked' : ''} >남성&nbsp;&nbsp;
                    <input type="radio" name="gender" value="F" ${student.gender eq 'F' ? 'checked' : ''} >여성
                </td>
            </tr>
            <tr>
                <th>나이</th>
                <td>
                    <input type="number" min="20" max="30" name="age" value="${student.age}" />
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <button type="submit">
                        <c:choose>
                            <c:when test="${empty student}">학생등록</c:when>
                            <c:otherwise>학생수정</c:otherwise>
                        </c:choose>
                    </button>
                </td>
            </tr>
            </tfoot>
        </table>

    </form>
</body>
</html>
