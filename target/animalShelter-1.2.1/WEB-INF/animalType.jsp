<%@ page import="com.theIronYard.entity.Animal" %>
<%@ page import="com.theIronYard.entity.AnimalType" %><%--
  Created by IntelliJ IDEA.
  User: dave
  Date: 9/17/16
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Animal Type</title>
    <link rel="stylesheet" href="/css/styles.css" />
</head>
<body>

<h1>Chris, Dave and Jeff's Animal Shelter</h1>
<ul>
    <li><a href="/">List Animals</a></li>
    <li><a href="/animalForm">Add an Animal</a></li>
</ul>
<div class="animal">
    <img src="/images/captainmycaptain.jpg" />

    <div class="detail">
        <a href="animalForm?id=${animal.getId()}">${animal.getName()}</a><br/>
        <strong>Type:</strong> ${animal.getType().getTypeName()}<br/>
        <strong>Breed:</strong> ${animal.getBreed().getBreed}<br/>
        <strong>Color:</strong> ${animal.getColor()}<br/>
        <strong>Description:</strong> ${animal.getDescription()}<br/>
    </div>
</div>
<table>
    <tr>
        <th>Type</th>
        <th></th>
    </tr>
    <% Animal animal = (Animal)request.getAttribute("animal"); %>
    <% for(AnimalType type : animal.getType()){ %>
    <tr>
        <td>
            <%= type.getTypeName() %>
        </td>
        <td>
        </td>
        <td>
        </td>
    </tr>
    <% } %>
</table>

<form action="/animalType" method="post">

    <input type="hidden" name="id" value="${animal.getId()}" />

    <div>
        <label for="type" class="typeLabel">
            <strong>Type:</strong>
        </label>
        <textarea name="type" id="type"></textarea>

    </div>

</form>


</body>
</html>
