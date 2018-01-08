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


<%--кнопки для выполнения пользователем ходов--%>
    <table>
        <tr>
            <td> <form action="/in/step" method="get"><input type="submit" value="1"  name="stepParam"></form> </td>
            <td> <form action="/in/step" method="get"><input type="submit" value="2"  name="stepParam"></form> </td>
            <td> <form action="/in/step" method="get"><input type="submit" value="3"  name="stepParam"></form> </td>

        </tr>
        <tr>
            <td> <form action="/in/step" method="get"><input type="submit"  value="4" name="stepParam"></form> </td>
            <td> <form action="/in/step" method="get"><input type="submit" value="5" name="stepParam"></form> </td>
            <td> <form action="/in/step" method="get"><input type="submit"  value="6" name="stepParam"></form> </td>
        </tr>
        <tr>
            <td> <form action="/in/step" method="get"><input type="submit" value="7" name="stepParam"></form> </td>
            <td> <form action="/in/step" method="get"><input type="submit" value="8" name="stepParam"></form> </td>
            <td> <form action="/in/step" method="get"><input type="submit" value="9" name="stepParam"></form> </td>
        </tr>
    </table>
<hr>




</body>
</html>
