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
        <label for="name">
            Search for an Animal:
        </label>
        <input type="text" name="name" id="name" placeholder="animal's name">

        
    </form>
</body>
</html>
