package com.theIronYard.servlet;

import com.theIronYard.entity.AnimalBreed;
import com.theIronYard.entity.AnimalType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dave on 9/16/16.
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
            ArrayList<AnimalBreed> animalBreeds = animalService.listBreeds();
            req.setAttribute("breeds", animalBreeds);

            // get the type list
            ArrayList<AnimalType> animalTypes = animalService.listTypes();
            req.setAttribute("types", animalTypes);

        } catch (SQLException e) {
            throw new ServletException("Error showing animal breeds", e);
        }

        req.getRequestDispatcher("WEB-INF/animalBreed.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // get the posted data
            if (req.getParameter("addAnimalBreed") != null) {
                String breedName = getParameterAsString(req, "breedName");
                Integer typeId = getParameterAsInt(req, "typeId");
                if (!(typeId == null || typeId == 0 || breedName == null || breedName.equals(""))) {
                    AnimalBreed animalBreed = new AnimalBreed(breedName, typeId);
                    animalService.addBreed(animalBreed);
                }
                resp.sendRedirect("/animalBreed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
