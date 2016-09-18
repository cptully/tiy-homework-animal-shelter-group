<%@ page import="com.theIronYard.entity.Animal" %>
<%@ page import="com.theIronYard.entity.Note" %>
<%@ page import="com.theIronYard.entity.AnimalType" %><%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 9/16/16
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Animal Brteeds</title>
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
</body>
</html>
