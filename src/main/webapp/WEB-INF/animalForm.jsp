<%@ page import="com.theIronYard.entity.Animal" %>
<%@ page import="com.theIronYard.entity.AnimalBreed" %>
<%@ page import="com.theIronYard.entity.AnimalType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.theIronYard.entity.Note" %>
<%--
  Created by IntelliJ IDEA.
  User: dave
  Date: 9/15/16
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty loginError">
    <script>
        window.addEventListener("load",function(){
            alert("${loginError}");
        }
    </script>
</c:if>

<html>
<head>
    <title>Add / Edit Animal</title>
    <link rel="stylesheet" href= "/css/styles.css"/>
</head>
<body>

<h1>Chris, Dave and Jeff's Animal Shelter</h1>

<nav>
    <ul>
        <li><a href="/">List Animals</a></li>
        <li><a href="/animalForm">Add an Animal</a></li>
    </ul>
</nav>

<h2> Add / Edit an Animal</h2>


<p><span class="required">*</span> indicates a required field</p>

<form action="/animalForm" method="post">

    <input type="hidden" name="id" value="${animal.getId()}" />

    <div>
        <label for="name">
            <strong>Name:</strong> <span class="required">*</span>
        </label>
        <input type="text" name="name" id="name" value="${animal.getName()}">
    </div>

    <div>
        <label for="typeId">
            <strong>Type:</strong> <span class="required">*</span>
        </label>
        <select name="typeId" id="typeId">
            <option></option>
            <% for(AnimalType animalType : (ArrayList<AnimalType>)request.getAttribute("types")) { %>
            <option value="<%= animalType.getTypeId() %>"
                    <%= animalType.getTypeName().equals(((Animal)request.getAttribute("animal")).getType().getTypeName()) ? "selected='true'" : "" %>>
                <%= animalType.getTypeName()%>
            </option>
            <% } %>
        </select>
    </div>
    <label for="breedId">
        <strong>Breed:</strong>
    </label>
    <select name="breedId" id="breedId">
        <option></option>
        <% for(AnimalBreed animalBreed : (ArrayList<AnimalBreed>)request.getAttribute("breeds")) { %>
        <option value="<%= animalBreed.getTypeId() %>"
                <%= animalBreed.getName().equals(((Animal)request.getAttribute("animal")).getBreed().getName()) ? "selected='true'" : ""%> >
            <%= animalBreed.getName() %>
        </option>
        <% } %>
    </select>
    <div>
        <label for="color">
            <strong>Color:</strong>
        </label>
        <input type="text" name="color" id="color" value="${animal.getColor()}">
    </div>
    <div>
        <label for="description">
            <strong>Description:</strong>
        </label>
        <input type="text" name="description" id="description" value="${animal.getDescription()}">
    </div>
    <div>
        <label for="notes">
            <strong>Notes</strong>
        </label>
        <table id="notes">
            <tr>
                <th>Date</th>
                <th>Note</th>
            </tr>

            <% Animal animal = (Animal)request.getAttribute("animal"); %>
            <% for(Note note : animal.getNotes()){ %>
            <tr>
                <td>
                    <!-- TODO: format the date!!!! -->
                    <%= note.getDate() %>
                </td>
                <td>
                    <%= note.getContent() %>
                </td>
            </tr>
            <% } %>
        </table>

    </div>
    <div class="buttonbar">
        <button type="submit" name="saveAnimal" value="save">Save Animal</button>
        <!-- TODO: implement an are you sure check see TODO in AnimalFormServlet -->
        <!-- <button type="submit" name="editNotes" value="editNotes">Edit Notes</button> -->
        <button type="submit" name="deleteAnimal" value="delete">Delete Animal</button>
    </div>
</form>

<img id="mainImage" src="images/captainmycaptain.jpg" />
</body>
</html>