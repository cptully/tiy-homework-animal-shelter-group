package com.theIronYard.servlet;

import com.theIronYard.entity.Animal;
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
 * Created by chris on 9/16/16.
 */
@WebServlet("/animalType")
public class AnimalTypeServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // get the animal id
            Integer id = getParameterAsInt(req, "id");
            req.setAttribute("id", id);

            // get the animal
            ArrayList<AnimalType> animalTypes = animalService.listTypes();
            req.setAttribute("types", animalTypes);

        } catch (SQLException e) {
            throw new ServletException("Error showing animal types", e);
        }

        req.getRequestDispatcher("WEB-INF/animalType.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // get the posted data
            if (req.getParameter("addAnimalType") != null) {
                String typeName = getParameterAsString(req, "typeName");
                if (!(typeName == null || typeName.equals(""))) {
                    AnimalType animalType = new AnimalType(typeName);
                    animalService.addType(animalType);
                }
            }
            resp.sendRedirect("/animalType");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

