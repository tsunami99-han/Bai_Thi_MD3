<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 7/12/2021
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách</title>
</head>
<body>
<h3><a href="/products?action=create">Add New Product</a></h3>
<form action="" method="get">
    <input type="hidden" name="action" value="search">
    <input type="text" placeholder="Enter key" name="key"> <button>Search</button>
</form>
<table border="1px">
    <tr>
        <th>#</th>
        <th>ProductName</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Color</th>
        <th>Description</th>
        <th>Category</th>
        <th>Action</th>
    </tr>
    <c:if test="${listProduct.size()>0}">
        <c:forEach var="i" begin="0" end="${listProduct.size()-1}">
            <tr>
                <td>${listProduct.get(i).getId()}</td>
                <td>${listProduct.get(i).getName()}</td>
                <td>${listProduct.get(i).getPrice()}</td>
                <td>${listProduct.get(i).getQuantity()}</td>
                <td>${listProduct.get(i).getColor()}</td>
                <td>${listProduct.get(i).getDesc()}</td>
                <td>${listCategory.get(i).getName()}</td>
                <td><a href="/products?action=edit&id=${listProduct.get(i).getId()}">Edit</a> | <a href="/products?action=delete&id=${listProduct.get(i).getId()}">Delete</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
