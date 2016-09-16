<%@ page import="com.theIronYard.entity.Animal" %>
<%@ page import="com.theIronYard.entity.AnimalBreed" %>
<%@ page import="com.theIronYard.entity.AnimalType" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: dave
  Date: 9/15/16
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add / Edit Animal</title>
    <link rel="stylesheet" href= "/css/styles.css"/>
</head>
<body>

<h1>Chris, Dave and Jeff's Animal Shelter</h1>

<ul>
    <li><a href="/">List Animals</a></li>
    <li><a href="/animalForm">Add an Animal</a></li>
</ul>

<h2> Add / Edit an Animal</h2>

<p>* indicates a required field</p>

<form action="/animalForm" method="post">

    <input type="hidden" name="id" value="${animal.getId()}" />

    <div>
        <label for="name">
            Name:
        </label>
        <input type="text" name="name" id="name" value="${animal.getName()}">
    </div>

    <div>
        <label for="typeid">
            Type:
        </label>
        <select name="typeid" id="typeid">
            <option></option>
            <% for(AnimalType animalType : (ArrayList<AnimalType>)request.getAttribute("types")) { %>
            <option value="<%= animalType.getTypeId() %>"
                    <%= animalType.getTypeName().equals(((Animal)request.getAttribute("animal")).getType()) ? "selected='true'" : "" %>>
                <%= animalType.getTypeName()%>
            </option>
            <% } %>
        </select>
    </div>
    <label for="breedid">
        Breed:
    </label>
    <select name="breedid" id="breedid">
        <% for(AnimalBreed animalBreed : (ArrayList<AnimalBreed>)request.getAttribute("breeds")) { %>
        <option value="<%= animalBreed.getTypeId() %>"
                <%= animalBreed.getName().equals(((Animal)request.getAttribute("animal")).getBreed()) ? "select= 'true'" : ""%> >
            <%= animalBreed.getName() %>
        </option>
        <% } %>
    </select>
    <div>
        <label for="color">
            Color:
        </label>
        <input type="text" name="color" id="color" value="${animal.getColor()}">
    </div>
    <div>
        <label for="description">
            Description:
        </label>
        <input type="text" name="description" id="description" value="${animal.getDescription()}">
    </div>

    <img src="images/captainmycaptain.jpg" />

    <div>
        <button>Save Animal</button>
        <button>Delete Animal</button>
    </div>

</form>
</body>
</html>