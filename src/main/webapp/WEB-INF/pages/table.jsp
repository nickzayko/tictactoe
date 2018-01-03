<%--
  Created by IntelliJ IDEA.
  User: Nikolay
  Date: 21.12.2017
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<%@ include file= "/index.jsp" %>


<h1> ${victory} </h1>
<hr>
<form action="/in/restartGame">
    <input type="submit" value="Restart game">
</form>
<h2>${inform}</h2>


</body>
</html>
