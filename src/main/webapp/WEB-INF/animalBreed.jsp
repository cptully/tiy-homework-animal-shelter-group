<%@ page import="com.theIronYard.entity.AnimalBreed" %>
<%@ page import="com.theIronYard.entity.AnimalType" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: davehochstrasser
  Date: 9/17/16
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Animal Breeds</title>
    <link rel="stylesheet" href="/css/styles.css" />
</head>
<body>
<h1>Manage Animal Breeds</h1>
<table>
    <tr>
        <th>Breed</th>
        <th></th>
    </tr>
    <% for(AnimalBreed animalBreed : (ArrayList<AnimalBreed>)request.getAttribute("breeds")){ %>
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

<form action="/animalBreed" method="post">
    <div>
        <label for="breedName">
            <strong>Breed:</strong>
        </label>
        <input type="text" name="breedName" id="breedName" placeholder="Breed name">
    </div>
    <div>
        <label for="typeId">
            <strong>Type:</strong> <span class="required">*</span>
        </label>
        <select name="typeId" id="typeId">
            <option></option>
            <% for(AnimalType animalType : (ArrayList<AnimalType>)request.getAttribute("types")) { %>
            <option value="<%= animalType.getTypeId() %>">
                <%= animalType.getTypeName()%>
            </option>
            <% } %>
        </select>

    </div>
    <div class="buttonbar">
        <button type="submit" name="addAnimalBreed" value="addAnimalBreed">Add Animal Breed</button>
    </div>
</form>


</body>
</html>
