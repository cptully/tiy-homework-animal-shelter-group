package com.theIronYard.servlet;

import com.theIronYard.entity.Animal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by chris on 9/16/16.
 */
@WebServlet("/animalBreed")
public class AnimalBreedServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // get the animal id
            Integer id = getParameterAsInt(req, "id");
            req.setAttribute("id", id);

            // get the animal
            Animal animal = animalService.getAnimal(id);

            req.setAttribute("animal", animal);

        } catch (SQLException e) {
            throw new ServletException("Error showing animal breeds", e);
        }

        req.getRequestDispatcher("WEB-INF/animalType.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // get the id of the animal we're adding a breed to
            Integer id = getParameterAsInt(req, "id");
            req.setAttribute("id", id);

            // get the animal
            Animal animal = animalService.getAnimal(id);


            //String breed = getParameterAsString(req, "breed");

            // make a breed
            //AnimalBreed animalBreed = new AnimalBreed();

            // add the breed to the animal
            //animal.getBreed().add(animalBreed);

            // save the animal
            animalService.editAnimal(animal);

            resp.sendRedirect("/animalBreed?id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
