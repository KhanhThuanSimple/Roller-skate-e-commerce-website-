<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 30-Mar-25
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Excel File</title>
</head>
<body>
<h2>Upload Excel File</h2>
<form action="UploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="excelFile" accept=".xls, .xlsx" required />
    <input type="submit" value="Upload" />
</form>
</body>
</html>
