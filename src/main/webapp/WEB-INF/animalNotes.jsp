<%@ page import="com.theIronYard.entity.Note" %>
<%@ page import="com.theIronYard.entity.Animal" %>
<%--
  Created by IntelliJ IDEA.
  User:
  Date: 9/16/16
  Time: 1:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Animal Notes</title>
    <link rel="stylesheet" href="/css/styles.css" />
</head>
<body>

<h1>Chris, Dave and Jeff's Animal Shelter</h1>

<ul>
    <li><a href="/">List Animals</a></li>
    <li><a href="/animalForm">Add an Animal</a></li>
</ul>

<h2>Animal Notes</h2>

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
        <th>Date</th>
        <th>Note</th>
        <th></th>
    </tr>
    <% Animal animal = (Animal)request.getAttribute("animal"); %>
    <% for(Note note : animal.getNotes()){ %>
    <tr>
        <td>
            <%= note.getDate() %>
        </td>
        <td>
            <%= note.getContent() %>
        </td>
        <td>
            <a href="/deleteNote?animalId=<%= animal.getId() %>&noteId=<%= note.getId() %>"><img src="images/delete.jpeg" alt="Delete" /></a>
        </td>
    </tr>
    <% } %>
</table>

<form action="/animalNotes" method="post">

    <input type="hidden" name="id" value="${animal.getId()}" />

    <div>
        <label for="note" class="noteLabel">
            <strong>Add a note:</strong>
        </label>
        <textarea name="note" id="note"></textarea>

    </div>

    <button>Add Note</button>
</form>

</body>
</html>
