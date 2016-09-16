package com.theIronYard.servlet;

import com.theIronYard.entity.Animal;
import com.theIronYard.entity.AnimalBreed;
import com.theIronYard.entity.AnimalType;
import com.theIronYard.factory.ServiceFactory;
import com.theIronYard.service.AnimalService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by chris on 9/15/16.
 */
public class AnimalFormServlet extends AbstractServlet {
    private AnimalService animalService;

    @Override
    public void init() throws ServletException {

        // get link to animalService
        try {
            this.animalService = ServiceFactory.getAnimalService();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new ServletException("Something went wrong initializing servlet " + this.getClass().getCanonicalName(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get animal
        try {
            // get animal id from post
            String id = getParameterAsString(req, "id");

            // create a blank animal
            Animal animal = new Animal();

            // If the user supplied the ID of an animal to be edited, get that animal
            if (id != null) {
                // get a specific animal
                animal = animalService.getAnimal(Integer.parseInt(id));
            }

            // put the animal in the attributes to be sent to the edit form
            req.setAttribute("animal", animal);

            // animal types for dropdown
            List<AnimalType> types = animalService.listTypes();
            req.setAttribute("types", types);

            // animal breeds for drop down
            List<AnimalBreed> breeds = animalService.listBreeds();
            req.setAttribute("breeds", breeds);

        } catch (SQLException e) {
            throw new ServletException("Error getting animal data", e);
        }

        req.getRequestDispatcher("WEB-INF/animalForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // get the posted data

            // animal id
            Integer id = getParameterAsInt(req, "id");
            req.setAttribute("id", id);

            // name
            String name = getParameterAsString(req, "name");
            req.setAttribute("name", name);

            // type
            Integer typeId = getParameterAsInt(req, "typeId");
            req.setAttribute("typeId", typeId);

            // breed
            Integer breedId = getParameterAsInt(req, "breedId");
            req.setAttribute("breedId", breedId);

            // color
            String color = getParameterAsString(req, "color");
            req.setAttribute("color", color);

            // description
            String description = getParameterAsString(req, "description");
            req.setAttribute("description", description);

            // get the Animal type and breed based on the numeric Id that was posted
            AnimalType type = animalService.getType(typeId);
            AnimalBreed breed = animalService.getBreed(breedId);

            // declare an Animal to edit
            Animal animal;

            // if the Id != 0 then we are editing and existing animal
            if (id != 0) {
                // get the Animal to edit
                animal = animalService.getAnimal(id);

                // update the Animal data
                animal.setName(name);
                animal.setType(type);
                animal.setBreed(breed);
                animal.setColor(color);
                animal.setDescription(description);
            } else {
                // create a new widget using the provided data
                animal = new Animal(name, type, breed, color, description);
            }

            // TODO: validate the data provided...
            // type and breed come from drop lists other fields can be anything
            boolean valid = true;

            // if valid
            if (valid) {

                // if the id != 0 update the animal
                if (animal.getId() != 0) {
                    animalService.editAnimal(animal);
                } else {
                    animalService.addAnimal(animal);
                }


            }
        } catch (SQLException e) {
            throw new ServletException("something went wrong", e);
        }
    }
}
