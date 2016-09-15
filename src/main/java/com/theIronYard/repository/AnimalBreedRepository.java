package com.theIronYard.repository;

import com.theIronYard.entity.AnimalBreed;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chris on 9/10/16.
 */
public class AnimalBreedRepository {
    // properties
    private ArrayList<AnimalBreed> animalBreeds = new ArrayList<>();
    private Connection connection;
    private String jdbcUrl = "jdbc:postgresql://localhost/animalrepository_test";
    private Statement statement;

    // constructor
    public AnimalBreedRepository(String jdbcUrl) throws SQLException {
        if (!jdbcUrl.equals("")) {this.jdbcUrl = jdbcUrl;}
        this.connection = DriverManager.getConnection(this.jdbcUrl);
    }

    // getters and setters
    public ResultSet getBreeds() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM breed ORDER BY breedid");
    }

    public ResultSet getBreed(int breedId) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM breed WHERE breedid = ?");
        preparedStatement.setInt(1, breedId);
        return preparedStatement.executeQuery();
    }

    // methods
    public boolean addBreed(AnimalBreed animalBreed) throws SQLException {
        // write note to database
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO breed " +
                "(breed, typeid) " +
                "VALUES " +
                "(?, ?) RETURNING breedid");
        preparedStatement.setString(1, animalBreed.getName());
        preparedStatement.setInt(2, animalBreed.getTypeId());
        ResultSet resultSet = preparedStatement.executeQuery();

        // store the ID of the new note in it's object
        resultSet.next();
        int breedId = resultSet.getInt(1);
        animalBreed.setBreedId(breedId);

        // return the result of adding the note to the ArrayList in memory
        return animalBreeds.add(animalBreed);
    }

    /**
     *
     * @param breedId database id of the breed to be deleted
     * @return a count of the animals that currently use this breed
     * @throws SQLException
     */
    public int deleteBreed(int breedId) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT count(id) FROM animal WHERE breedid = ?");
        preparedStatement.setInt(1, breedId);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        int count = resultSet.getInt("count");

        if (count == 0) {
            preparedStatement = connection
                    .prepareStatement("DELETE FROM breed WHERE breedid = ?");
            preparedStatement.setInt(1, breedId);
            preparedStatement.execute();
            return 0;
        }
        return count;
    }

    public void editBreed(AnimalBreed animalBreed) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE breed SET breed = ?, typeid = ? WHERE breedid = ?");
        preparedStatement.setString(1, animalBreed.getName());
        preparedStatement.setInt(2, animalBreed.getTypeId());
        preparedStatement.setInt(3, animalBreed.getBreedId());
        preparedStatement.execute();
    }
}
