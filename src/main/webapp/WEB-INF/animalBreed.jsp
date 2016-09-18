<%@ page import="com.theIronYard.entity.Animal" %>
<%@ page import="com.theIronYard.entity.Note" %>
<%@ page import="com.theIronYard.entity.AnimalBreed" %><%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 9/16/16
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentBreed="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Animal Breeds</title>
</head>
<body>
<table>
    <tr>
        <th>Breed</th>
        <th></th>
    </tr>
    <% for(AnimalBreed animalBreed : breeds){ %>
    <tr>
        <td>
            <%= animalBreed.getName() %>
        </td>
        <td>
            <a href="/deleteNote?breedId=<%= animalBreed.getBreedId() %>><img src="images/delete.png" alt="Delete" /></a>
        </td>
    </tr>
    <% } %>
</table>
<form>

    <div>
        <label for="name">
            <strong>Breed:</strong>
        </label>
        <input type="text" name="name" id="name" placeholder="Breed name">
    </div>
</form>
<div class="buttonbar">
    <button type="submit" name="addAnimalBreed" value="save">Add Animal Breed</button>
</div>
</body>
</html>
