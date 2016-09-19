<%@ page import="com.theIronYard.entity.Animal" %>
<%@ page import="com.theIronYard.entity.AnimalType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.theIronYard.entity.AnimalBreed" %>
<%@ page import="com.theIronYard.entity.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Animal Shelter</title>
    <link rel="stylesheet" href="/css/styles.css" />
</head>
<body>
    <h1>Chris, Dave and Jeff's Animal Shelter</h1>

    <nav>
        <ul>
            <li><a href="/">List Animals</a></li>
            <li><a href="/animalForm">Add an Animal</a></li>
            <li><a href="/animalType">Manage Animal Types</a></li>
            <li><a href="/animalBreed">Manage Animal Breeds</a></li>
        </ul>
    </nav>

    <H2>List Animals</H2>
    <form action="/" method="post">
        <h3>Search for an Animal:</h3>
        <div>
            <label for="name">
                <Strong>Name:</Strong>
            </label>
            <input type="text" name="name" id="name" placeholder="animal's name" value="${animal.getName()}">
        </div>

        <div>
            <label for="typeId">
                <strong>Type:</strong>
            </label>
            <select name="typeId" id="typeId">
                <option>select...</option>
                <% for(AnimalType animalType : (ArrayList<AnimalType>)request.getAttribute("types")) { %>
                    <option value="<%= animalType.getTypeId() %>"
                            <%= request.getAttribute("typeId") != null && animalType.getTypeId() == (int)request.getAttribute("typeId") ? "selected='true'" : "" %> >
                        <%= animalType.getTypeName() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div>
            <label for="breedId">
                <strong>Breed:</strong>
            </label>
            <select name="breedId" id="breedId">
                <option>select...</option>
                <% for(AnimalBreed animalBreed : (ArrayList<AnimalBreed>)request.getAttribute("breeds")) { %>
                <option value="<%= animalBreed.getTypeId() %>"
                        <%= request.getAttribute("breedId") != null && animalBreed.getBreedId() == (int)request.getAttribute("breedId") ? "selected='true'" : ""%> >
                    <%= animalBreed.getName() %>
                </option>
                <% } %>
            </select>
        </div>


        <div>
            <label for="animalid">
                <strong>Animal ID:</strong>
            </label>
            <input type="text" name="animalid" id="animalid" value="${animal.getId()}">
        </div>

        <button>Search</button>

    </form>



<section>
    <% for(Animal animal : (ArrayList<Animal>)request.getAttribute("animals")) { %>

    <div class="animal">
        <img src="images/captainmycaptain.jpg" />

        <div class="detail">
            <a href="/animalForm?id=<%= animal.getId() %>"><%= animal.getName()%></a><br/>
            <strong>Type:</strong> <%= animal.getType().getTypeName()%><br/>
            <strong>Breed:</strong> <%= animal.getBreed().getName()%><br/>
            <strong>Color:</strong> <%= animal.getColor()%><br/>
            <strong>Description:</strong> <%= animal.getDescription()%><br/>
            <strong>Notes:</strong> <a href="/animalNotes?id=<%= animal.getId()%>"><%= animal.getNoteCount()%> notes</a><br/>

        </div>
    </div>

    <% } %>

</section>
</body>
</html>
