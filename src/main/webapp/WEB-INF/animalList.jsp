<%@ page import="com.theIronYard.entity.Animal" %>
<%@ page import="com.theIronYard.entity.AnimalType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Animal Shelter</title>
    <link rel="stylesheet" href="/css/styles.css" />
</head>
<body>
    <h1>Chris Dave and Jeff's Animal Shelter</h1>

    <nav>
        <ul>
            <li><a href="/">Animals</a></li>
            <li><a href="/animalForm">Add an Animal</a></li>
        </ul>
    </nav>

    <H2>List Animals</H2>
    <form action="/" method="post">
        <div>
            <label for="name">
                Search for an Animal:
            </label>
            <input type="text" name="name" id="name" placeholder="animal's name">
        </div>

        <div>
            <label for="typeid">
                Type:
            </label>
            <select name="typeid" id="typeid">
                <% for(AnimalType animalType : (ArrayList<AnimalType>)request.getAttribute("types")) { %>
                    <option value="<% animalType.getTypeId() %>"
                            <%= animalType.getTypeName().equals(((Animal)request.getAttribute("animal")).getType().getTypeName()) ? "select= 'true'" : ""%> >
                        <%= animalType.getTypeName() %>
                    </option>
                <% } %>
            </select>
        </div>
        
    </form>
</body>
</html>
