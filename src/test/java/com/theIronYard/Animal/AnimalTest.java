package com.theIronYard.Animal;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by chris on 8/28/16.
 */
@RunWith(JUnitParamsRunner.class)
public class AnimalTest {
    private Animal newAnimal;

    @Before
    public void setupTestEnvironment(){
        newAnimal = new Animal();
    }

    @Test
    @Parameters
    public void animalSetNameTest(String name, String expectedResult) {
        // arrange

        // act
        newAnimal.setName(name);

        // assert
        assertThat(newAnimal.getName(), is(expectedResult));

    }

    private Object[] parametersForAnimalSetNameTest() {
        return new Object[] {
                new Object[] {"George", "George"},
                new Object[] {"myst", "myst"},
                new Object[] {"99", "99"}
        };
    }

    @Test
    public void animalSetSpeciesTest() {
        // arrange
        AnimalType animalType = new AnimalType(-1, "canine");

        // act
        newAnimal.setType(animalType);

        // assert
        assertThat(newAnimal.getType().getTypeName(), is("canine"));
    }

    @Test
    public void animalSetBreedTest() {
        // arrange
        AnimalBreed animalBreed = new AnimalBreed(-1, "border collie", 1);

        // act
        newAnimal.setBreed(animalBreed);

        // assert
        assertThat(newAnimal.getBreed().getName(), is("border collie"));
    }

    @Test
    public void animalSetColorTest() {
        // arrange

        // act
        newAnimal.setColor("black");

        // assert
        assertThat(newAnimal.getColor(), is("black"));
    }

    @Test
    @Parameters
    public void animalSetDescriptionTest(String description, String expectedResult) {
        // arrange

        // act
        newAnimal.setDescription(description);

        // assert
        assertThat(newAnimal.getDescription(), is(expectedResult));
    }

    private Object[] parametersForAnimalSetDescriptionTest() {
        return new Object[] {
                new Object[] {"short", "short"},
                new Object[] {"This is a long sentence description of an animal.", "This is a long sentence description of an animal."},
                new Object[] {"", ""}
        };
    }


    @Test
    public void animalToStringTest() {
        // arrange
        AnimalType animalType = new AnimalType(-1, "feline");
        AnimalBreed animalBreed = new AnimalBreed(-1, "calico", 2);
        newAnimal = new Animal("myst", animalType, animalBreed, "grey", "description");

        // act
        String result = newAnimal.toString();

        // assert
        assertThat(result, is("myst, feline, calico, grey"));
    }

    @Test
    public void animalToStringVerboseTest() {
        // arrange
        AnimalType animalType = new AnimalType(-1, "feline");
        AnimalBreed animalBreed = new AnimalBreed(-1, "calico", 2);
        newAnimal = new Animal("myst", animalType, animalBreed, "grey", "description");

        // act
        String result = newAnimal.toString("v");

        // assert
        assertThat(result, is("Name:\t\t\tmyst\nType:\t\tfeline\nBreed:\t\t\tcalico\nColor:\t\t\tgrey\nDescription:\tdescription"));
    }
}
