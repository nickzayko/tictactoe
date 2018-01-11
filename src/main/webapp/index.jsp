<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<h2>It is time to play! Go...</h2>
<hr>

<%--таблица для отрисовки хода игры, может даже потом переведу на <label></label>, чтобы просто отображать текст--%>

<table>
    <tr>
        <td><input type="text" name="t1" size="1" value= ${v1} > </td>
        <td><input type="text" name="t2" size="1" value= ${v2}></td>
        <td><input type="text" name="t3" size="1" value= ${v3}></td>
    </tr>
    <tr>
        <td><input type="text" name="t4" size="1" value= ${v4} ></td>
        <td><input type="text" name="t5" size="1" value= ${v5}></td>
        <td><input type="text" name="t6" size="1" value= ${v6}></td>
    </tr>
    <tr>
        <td><input type="text" name="t7" size="1" value= ${v7}></td>
        <td><input type="text" name="t8" size="1" value= ${v8}></td>
        <td><input type="text" name="t9" size="1" value= ${v9}></td>
    </tr>
</table>


<%--<table>--%>
    <%--<c:forEach begin="0" end="8" step="1" varStatus="vsOut">--%>
    <%--<c:if test="${vsOut.current % 3 == 0}">--%>
    <%--<tr>--%>
        <%--</c:if>--%>
        <%--&lt;%&ndash;<td><c:set var="step" value="v${vsOut.count}"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:out value="${step}" default=""/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--<td><input type="text" name="t9" size="1" value= "v${vsOut.count}" ></td>--%>
        <%--</c:forEach>--%>
<%--</table>--%>


<%--кнопки для выполнения пользователем ходов--%>
<table>
    <c:forEach begin="0" end="8" step="1" varStatus="vsOut">
    <c:if test="${vsOut.current % 3 == 0}">
    <tr>
        </c:if>
        <td>
            <form action="/in/step" method="get"><input type="submit" value=${vsOut.count}  name="stepParam"></form>
        </td>
        </c:forEach>
</table>
<hr>
</body>
</html>
