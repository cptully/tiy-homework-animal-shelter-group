<%@ page import="com.theIronYard.entity.Animal" %>
<%@ page import="com.theIronYard.entity.AnimalBreed" %><%--
  Created by IntelliJ IDEA.
  User: davehochstrasser
  Date: 9/17/16
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Animal Breed</title>
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
        <th>Breed</th>
        <th></th>
    </tr>
    <% for(AnimalBreed animalBreed : breeds()) { %>
    <tr>
        <td>
            <%= animal.getBreed() %>
        </td>
        <td>
        </td>
        <td>
        </td>
    </tr>
    <% } %>
</table>

<form action="/animalBreed" method="post">

    <input type="hidden" name="id" value="${animal.getId()}" />

    <div>
        <label for="breed" class="typeLabel">
            <strong>Breed: </strong>
        </label>
        <textarea name="breed" id="breed"></textarea>

    </div>

</form>


</body>
</html>
