<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2025-03-19
  Time: 오후 3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학생 - 조회</title>
</head>
<body>
 <table border="1">
     <tbody>
     <tr>
         <th>아이디</th>
         <td>${student.id}</td>
     </tr>
     <tr>
         <th>이름</th>
         <td>${student.name}</td>
     </tr>
     <tr>
         <th>성별</th>
         <td>${student.gender}</td>
     </tr>
     <tr>
         <th>나이</th>
         <td>${student.age}</td>
     </tr>
     <tr>
         <th>등록</th>
         <td>${student.getCreatedAtString()}</td>
     </tr>
     </tbody>
     <tfoot>
     <tr>
         <td colspan="2">
             <a href="/student/list.do">리스트</a>&nbsp;&nbsp;
             <a href="/student/update.do?id=${student.id}">수정</a>
             <form method="post" action="/student/delete.do">
                 <input type = "hidden" name="id" value="${student.id}" />
                 <button type="submit">삭제</button>
             </form>
         </td>
     </tr>
     </tfoot>
 </table>
</body>
</html>
