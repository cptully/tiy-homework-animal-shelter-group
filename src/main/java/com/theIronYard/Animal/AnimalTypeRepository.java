package com.theIronYard.Animal;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chris on 9/10/16.
 */
public class AnimalTypeRepository {
    // properties
    private ArrayList<AnimalType> animalTypes = new ArrayList<>();
    private Connection connection;
    private String jdbcUrl = "jdbc:postgresql://localhost/animalrepository_test";

    // constructor
    public AnimalTypeRepository(String jdbcUrl) throws SQLException {
        if (!jdbcUrl.equals("")) {this.jdbcUrl = jdbcUrl;}
        this.connection = DriverManager.getConnection(this.jdbcUrl);
    }

    // getters and setters
    public ResultSet getAnimalTypes() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM animaltype ORDER BY typeid");
    }

    public ResultSet getAnimalType(int typeId) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM animaltype WHERE typeid = ?");
        preparedStatement.setInt(1, typeId);
        return preparedStatement.executeQuery();
    }

    // methods
    public boolean addType(AnimalType animalType) throws SQLException {
        // write note to database
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO animaltype " +
                "(typename)" +
                "VALUES " +
                "(?) RETURNING typeid");
        preparedStatement.setString(1, animalType.getTypeName());
        ResultSet resultSet = preparedStatement.executeQuery();

        // store the ID of the new note in it's object
        resultSet.next();
        int breedId = resultSet.getInt(1);
        animalType.setTypeId(breedId);

        // return the result of adding the note to the ArrayList in memory
        return animalTypes.add(animalType);
    }

    public int deleteType(int typeId) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT count(id) FROM animal WHERE typeid = ?");
        preparedStatement.setInt(1, typeId);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        int count = resultSet.getInt("count");

        if (count == 0) {
            preparedStatement = connection
                    .prepareStatement("DELETE FROM animaltype WHERE typeid = ?");
            preparedStatement.setInt(1, typeId);
            preparedStatement.execute();
            return 0;
        }
        return count;
    }


    public ResultSet getType(int typeId) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM animaltype WHERE typeid = ?");
        preparedStatement.setInt(1, typeId);
        return preparedStatement.executeQuery();
    }

    public void editType(AnimalType animalType) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE animaltype SET typename = ? WHERE typeid = ?");
        preparedStatement.setString(1, animalType.getTypeName());
        preparedStatement.setInt(2, animalType.getTypeId());
        preparedStatement.execute();
    }
}
