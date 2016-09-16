package com.theIronYard.repository;

import com.theIronYard.entity.Animal;

import java.sql.*;

/**
 * Created by chris on 8/20/16.
 */
public class AnimalRepository {
    // properties
    private Connection connection;
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/animal2";


    // constructor
    public AnimalRepository(String jdbcUrl) throws SQLException {
        if (!jdbcUrl.equals("")) {
            this.jdbcUrl = jdbcUrl;
        }
        this.connection = DriverManager.getConnection(this.jdbcUrl);
    }

    // private methods

    // methods used by other entity Service
    public ResultSet getAnimal(int id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT a.id, a.name, t.typeid, t.typename, b.breedid, b.breed, b.typeid, a.color, a.description " +
                        "FROM animal AS a " +
                        "JOIN animaltype AS t " +
                        "ON a.typeid = t.typeid " +
                        "JOIN breed AS b " +
                        "ON a.breedid = b.breedid " +
                        "WHERE a.id = ?");

        preparedStatement.setInt(1, id);

        return preparedStatement.executeQuery();
    }

    public Animal addAnimal(Animal animal) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("INSERT INTO animal (name, typeid, breedid, color, description) " +
                        "VALUES (?, ?, ?, ?, ?) RETURNING id");

        preparedStatement.setString(1, animal.getName());
        preparedStatement.setInt(2, animal.getType().getTypeId());
        preparedStatement.setInt(3, animal.getBreed().getBreedId());
        preparedStatement.setString(4, animal.getColor());
        preparedStatement.setString(5, animal.getDescription());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            animal.setId(resultSet.getInt("id"));
        }

        return animal;
    }

    public void removeAnimal(int id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM animal WHERE id=?");

        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        //return new entity();
    }

    public boolean containsAnimal(int id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT count(1) FROM animal WHERE id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) == 1;
        }
        return false;
    }

    public boolean containsAnimal(Animal animal) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT count(1) FROM animal WHERE id = ?");
        preparedStatement.setInt(1, animal.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) == 1;
        }
        return false;
    }

    public ResultSet list() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT a.id, a.name, a.typeid, t.typename, a.breedid, b.breed, b.typeid, a.color, a.description " +
                "FROM animal AS a " +
                "JOIN animaltype AS t " +
                "ON t.typeid = a.typeid " +
                "JOIN breed AS b " +
                "ON b.breedid = a.breedid " +
                "ORDER BY a.id");
    }

    public void editAnimal(Animal animal) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE animal " +
                        "SET name = ?, " +
                        "color = ?, " +
                        "description = ?, " +
                        "typeid = ?, " +
                        "breedid = ? " +
                        "WHERE id = ?;");

        preparedStatement.setString(1, animal.getName());
        preparedStatement.setString(2, animal.getColor());
        preparedStatement.setString(3, animal.getDescription());
        preparedStatement.setInt(4, animal.getType().getTypeId());
        preparedStatement.setInt(5, animal.getBreed().getBreedId());
        preparedStatement.setInt(6, animal.getId());

        preparedStatement.execute();
    }

    public int size() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(id) FROM animal");

        resultSet.next();
        return resultSet.getInt("count");
    }

    public ResultSet searchByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT a.id, a.name, t.typeid, t.typename," +
                        " b.breedid, b.breed, a.color, a.description " +
                        "FROM animal AS a " +
                        "JOIN animaltype as t " +
                        "ON a.typeid = t.typeid " +
                        "JOIN breed AS b " +
                        "ON a.breedid = b.breedid " +
                        "WHERE a.name = ?");
        preparedStatement.setString(1, name);
        return preparedStatement.executeQuery();
    }

    public ResultSet searchByType(int typeId) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT a.id, a.name, t.typeid, t.typename," +
                        " b.breedid, b.breed, a.color, a.description " +
                        "FROM animal AS a " +
                        "JOIN animaltype as t " +
                        "ON a.typeid = t.typeid " +
                        "JOIN breed AS b " +
                        "ON a.breedid = b.breedid " +
                        "WHERE a.typeid = ?");
        preparedStatement.setInt(1, typeId);
        return preparedStatement.executeQuery();
    }

    public ResultSet searchByBreed(int breedId) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT a.id, a.name, t.typeid, t.typename," +
                        " b.breedid, b.breed, a.color, a.description " +
                        "FROM animal AS a " +
                        "JOIN animaltype as t " +
                        "ON a.typeid = t.typeid " +
                        "JOIN breed AS b " +
                        "ON a.breedid = b.breedid " +
                        "WHERE a.breedid = ?");
        preparedStatement.setInt(1, breedId);
        return preparedStatement.executeQuery();
    }
}


