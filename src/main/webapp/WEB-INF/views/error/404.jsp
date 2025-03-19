<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2025-03-19
  Time: 오후 5:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Resource Not Found</title>
</head>
<body>
    <h1>Student Not Found</h1>
    <h2>
        ${requestScope.get("jakarta.servlet.error.exception_type")}
    </h2>
    <h2>
        ${requestScope.get("jakarta.servlet.error.message")}
    </h2>
</body>
</html>
