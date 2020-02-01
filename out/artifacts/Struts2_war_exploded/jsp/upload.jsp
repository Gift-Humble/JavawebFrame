<%--
  Created by IntelliJ IDEA.
  User: SKYTR
  Date: 2020/2/1
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>上传文件</title>
    </head>
    <body>
        <form action="/upload" method="post" enctype="multipart/form-data">
            上传文件 : <input type="file" name="doc"/><br>
            <input type="submit" value="上传">
        </form>
    </body>
</html>
