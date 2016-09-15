package com.theIronYard.Animal;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by chris on 8/28/16.
 */
@RunWith(JUnitParamsRunner.class)
public class AnimalRepositoryTest {
    private AnimalRepository animalList;
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/animalrepository_test";

    @Before
    public void setupTestEnvironment() {
       // String jdbcUrl = "jdbc:postgresql://localhost/animalrepository";
        try {
            animalList = new AnimalRepository(jdbcUrl);
            /*for () {
                animalList.deleteAnimal(i);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Given an Animal with valid data
     * When that Animal is retrieved
     * Then the instance returned is valid
     */
    public void getAnimalNotNullTest() throws SQLException {
/*
        // arrange
        Animal newAnimal = new Animal("myst", "", "", "grey", "skittish");
        animalList.addAnimal(newAnimal);

        // act
        Animal expectedResult = animalList.getAnimal(newAnimal.getBreedId());

        // assert
        assertThat(expectedResult, equalTo(newAnimal));
*/
    }

    /**
     * Given an valid AnimalRepository with no animals
     * When getAnimal is called
     * Then an IndexOutOfBoundsException is thrown
     */
/*    @Test(expected = IndexOutOfBoundsException.class)
    public void getAnimalFailsOnEmptyDBTest() throws SQLException {

        // arrange

        // act
        Animal result = animalList.getAnimal(2);

        // assert
        assertThat(result, is(not(null)));

    }
*/
/*
    @Test
    */
/**
     * Given an Animal with valid data
     * When that Animal is added to animalList
     * Then the result is true
     *//*

    public void addAnimalNotFalseTest() throws SQLException {
        // arrange
        Animal newAnimal = new Animal("myst", "feline", "tabby", "grey", "skittish");

        // act
        boolean result = animalList.addAnimal(newAnimal);
//        boolean result = animalList.getAnimal(0);

        // assert
        assertThat(result, is(true));
    }
*/


    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When a specific animal is removed
     * Then that animal is removed
     */
    public void removeAnimalRemovesExpectedAnimalTest() throws SQLException {
/*
        // arrange
        animalList.addAnimal(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.addAnimal(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.addAnimal(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal newAnimal = new Animal("Myst", "feline", "calico", "grey", "skittish");
        animalList.addAnimal(newAnimal);

        // act
        Animal result = animalList.deleteAnimal(newAnimal.getBreedId());

        // assert
        assertThat(result, is(newAnimal));
*/
    }

    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When given an index out of bounds
     * Then nothing happens
     */
    public void removeAnimalHandlesIndexOutOfBoundsTest() throws SQLException {
/*
        // arrange
        animalList.addAnimal(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.addAnimal(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.addAnimal(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal expectedResult = new Animal();

        // act
        Animal result = animalList.deleteAnimal(expectedResult.getBreedId() + 1);

        // assert
        assertThat(result, is(expectedResult));
*/
    }

    @Test
    /**
     * Given an empty AnimalRepository
     * When containsAnimal is called with an Animal
     * Then false is returned
     */
    public void containsAnimalReturnsFalseWhenDbEmptyTest() throws SQLException {
        // arrange
        Animal newAnimal = new Animal();

        // act
        boolean result = animalList.containsAnimal(newAnimal);

        // assert
        assertThat(result, is(false));
    }
    @Test
    /**
     * Given an empty AnimalRepository
     * When containsAnimal is called with an index
     * Then false is returned
     */
    public void containsIntReturnsFalseWhenDbEmptyTest() throws SQLException {
        // arrange

        // act
        boolean result = animalList.containsAnimal(0);

        // assert
        assertThat(result, is(false));
    }


    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When a list is requested
     * Then that List is the right length
     */
    public void listAnimalReturnsTest() throws SQLException {
/*
        // arrange
        animalList.addAnimal(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.addAnimal(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.addAnimal(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal newAnimal = new Animal("Myst", "feline", "tabby", "grey", "skittish");
        animalList.addAnimal(newAnimal);

        // act
        List<String> result = animalList.list();

        // assert
        assertThat(result.size(), is(4));
*/
    }

    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When an Animal is edited
     * Then name is unchanged but any other edited values are correct
     */
    public void editAnimalObjectSucceedsTest() throws SQLException {
/*
        // arrange
        animalList.addAnimal(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.addAnimal(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.addAnimal(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal newAnimal = new Animal("Myst", "feline", "tabby", "grey", "skittish");
        Animal expectedResult = new Animal("Rags", "feline", "tabby", "grey", "skittish");

        // act
        animalList.editAnimal(newAnimal, 2);
        Animal result = animalList.getAnimal(2);

        // assert
        assertThat(result, equalTo(expectedResult));
*/
    }

    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When an Animal is edited
     * Then name is unchanged but any other edited values are correct
     */
    public void editAnimalValuesSucceedsTest() throws SQLException {
/*
        // arrange
        animalList.addAnimal(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.addAnimal(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.addAnimal(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal expectedResult = new Animal("Rags", "feline", "tabby", "grey", "skittish");

        // act
        animalList.editAnimal(2, "Myst", "feline", "tabby", "grey", "skittish");
        Animal result = animalList.getAnimal(2);

        // assert
        assertThat(result, equalTo(expectedResult));
*/
    }

}
