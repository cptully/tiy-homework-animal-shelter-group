<%@ page import="com.theIronYard.entity.AnimalBreed" %><%--
  Created by IntelliJ IDEA.
  User: davehochstrasser
  Date: 9/17/16
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentBreed="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Animal Breeds</title>
    <link rel="stylesheet" href="/css/styles.css" />
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
