<%@ page import="com.theIronYard.entity.Animal" %>
<%@ page import="com.theIronYard.entity.AnimalType" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: dave
  Date: 9/17/16
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Animal Types</title>
    <link rel="stylesheet" href="/css/styles.css" />
</head>
<body>

<h1>Chris, Dave and Jeff's Animal Shelter</h1>
<nav>
    <ul>
        <li><a href="/">List Animals</a></li>
        <li><a href="/animalForm">Add an Animal</a></li>
        <li><a href="/animalBreed">Manage Animal Breeds</a></li>
    </ul>
</nav>

<h2>Manage Animal Types</h2>
<table>
    <tr>
        <th>Type</th>
        <th></th>
    </tr>
    <% for(AnimalType animalType : (ArrayList<AnimalType>)request.getAttribute("types")){ %>
    <tr>
        <td>
            <%= animalType.getTypeName() %>
        </td>
        <td>
            <a href="/deleteAnimalType?typeId=<%= animalType.getTypeId() %>"><img class="delete-btn" src="images/delete.jpeg" alt="Delete" /></a>
        </td>
    </tr>
    <% } %>
</table>
<form action="/animalType" method="post">
    <div>
        <label for="typeName">
            <strong>Type:</strong>
        </label>
        <input type="text" name="typeName" id="typeName" placeholder="Type name">
    </div>

    <div class="buttonbar">
        <button type="submit" name="addAnimalType" value="addAnimalType">Add Animal Type</button>
    </div>
</form>
</body>
</html>
