<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: katya
  Date: 06.01.2018
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>404 Error page</title>
</head>

<body class="bodyException">

<div class="error-div">
    <font size="6" style="font-family: sans-serif"> 404 page not found. Something went wrong...</font> <br/><br/><br/>
    <button style="margin-left: 38%" type="button" name="back" class="buttonAuth" onclick="history.back()">back</button>

</div>

<img src="/WEB-INF/images/404.jpeg" width="600" height="400" />



</body>
</html>