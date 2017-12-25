<html>
<body>

<h2>It is time to play! Go...</h2>
<hr>

<%--таблица для отрисовки хода игры, может даже потом переведу на <label></label>, чтобы просто отображать текст--%>
<table>
    <tr>
        <td><input type="text" name="t1" size="1" ></td>
        <td><input type="text" name="t2" size="1" ></td>
        <td><input type="text" name="t3" size="1" ></td>
    </tr>
    <tr>
        <td><input type="text" name="t4" size="1" ></td>
        <td><input type="text" name="t5" size="1" ></td>
        <td><input type="text" name="t6" size="1" ></td>
    </tr>
    <tr>
        <td><input type="text" name="t7" size="1" ></td>
        <td><input type="text" name="t8" size="1" ></td>
        <td><input type="text" name="t9" size="1" ></td>
    </tr>
</table>


<%--кнопки для выполнения пользователем ходов--%>
    <table>
        <tr>
            <td> <form action="/in/step1" method="get"><input type="submit" name="st1" value="1"></form> </td>
            <td> <form action="/in/step2" method="get"><input type="submit" name="st2" value="2"></form> </td>
            <td> <form action="/in/step3" method="get"><input type="submit" name="st3" value="3"></form> </td>

        </tr>
        <tr>
            <td> <form action="/in/step4" method="get"><input type="submit" name="st4" value="4"></form> </td>
            <td> <form action="/in/step5" method="get"><input type="submit" name="st5" value="5"></form> </td>
            <td> <form action="/in/step6" method="get"><input type="submit" name="st6" value="6"></form> </td>
        </tr>
        <tr>
            <td> <form action="/in/step7" method="get"><input type="submit" name="st7" value="7"></form> </td>
            <td> <form action="/in/step8" method="get"><input type="submit" name="st8" value="8"></form> </td>
            <td> <form action="/in/step9" method="get"><input type="submit" name="st9" value="9"></form> </td>
        </tr>
    </table>
<hr>





</body>
</html>
