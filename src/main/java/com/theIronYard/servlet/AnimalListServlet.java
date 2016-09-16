package com.theIronYard.servlet;

import com.theIronYard.entity.Animal;
import com.theIronYard.entity.AnimalType;
import com.theIronYard.service.AnimalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jeffreydorney on 9/15/16.
 */

@WebServlet("")
public class AnimalListServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String name = getParameterAsString(req, "name");
            req.setAttribute("name", name);

            Integer typeId = getParameterAsInt(req, "typeId");
            req.setAttribute("typeId", typeId);

            Integer breedId = getParameterAsInt(req, "breedId");
            req.setAttribute("breedId", breedId);

            Integer id = getParameterAsInt(req, "id");
            req.setAttribute("id", id);

            // need to make sure listTypes is the correct method to call
            List<AnimalType> types = animalService.listTypes(/* Possible arguments? */);
            req.setAttribute("types", types);

            // need to make sure listAnimals is the correct method to call
            List<Animal> animals = animalService.listAnimals(/* Possible arguments? */);
            req.setAttribute("animals", animals);

        } catch (SQLException e) {
            throw new ServletException("Sorry! Something went wrong!", e);
        }

        // forward req and resp to JSP
        req.getRequestDispatcher("WEB-INF/animalList.jsp").forward(req, resp);
    }

}
