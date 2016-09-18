<%@ page import="com.theIronYard.entity.Animal" %>
<<<<<<< HEAD
<%@ page import="com.theIronYard.entity.Note" %>
<%@ page import="com.theIronYard.entity.AnimalType" %><%--
=======
<%@ page import="com.theIronYard.entity.AnimalBreed" %><%--
>>>>>>> 9df49583765a39791eaecaf14dcca607bdd88500
  Created by IntelliJ IDEA.
  User: davehochstrasser
  Date: 9/17/16
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<<<<<<< HEAD
    <title>Manage Animal Brteeds</title>
=======
    <title>Animal Breed</title>
    <link rel="stylesheet" href="/css/styles.css" />
>>>>>>> 9df49583765a39791eaecaf14dcca607bdd88500
</head>
<body>
<table>
    <tr>
        <th>Type</th>
        <th></th>
    </tr>
    <% for(AnimalType animalType : types){ %>
    <tr>
        <td>
            <%= animalType.getTypeName() %>
        </td>
        <td>
            <a href="/deleteNote?animalId=<%= animal.getId() %>&noteId=<%= note.getId() %>"><img src="images/delete.png" alt="Delete" /></a>
        </td>
    </tr>
    <% } %>
</table>
<form>

<<<<<<< HEAD
    <div>
        <label for="name">
            <strong>Type:</strong>
        </label>
        <input type="text" name="name" id="name" value="${animalType.getName()}">
    </div>
</form>
<div class="buttonbar">
    <button type="submit" name="addAnimalType" value="save">Add Animal Type</button>
</div>
=======
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
    <% for(AnimalBreed animalBreed : animal.getBreed()) { %>
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


>>>>>>> 9df49583765a39791eaecaf14dcca607bdd88500
</body>
</html>
