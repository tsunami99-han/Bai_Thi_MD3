<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 7/12/2021
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><a href="/products?action=list">Back to list product</a></h3>
<form method="post">
    <input type="hidden" name="action" value="create">
    Name : <input type="text" name="name" width="100px"> <br>
    Price : <input type="text" name="price" width="100px"> <br>
    Quantity : <input type="text" name="quantity" width="100px"> <br>
    Color : <input type="text" name="color" width="100px"> <br>
    Desc : <input type="text" name="desc" width="100px"> <br>
    Category : <select name="category" id=""> <br>
    <c:forEach items="${list}" var="category">
        <option value="${category.id}">${category.name}</option>
    </c:forEach>
</select>
    <button>Save</button>
</form>
</body>
</html>
