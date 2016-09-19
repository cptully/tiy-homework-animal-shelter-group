package com.theIronYard.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Jeff on 9/18/16.
 */
@WebServlet("/deleteAnimalBreed")
public class DeleteAnimalBreedServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Integer breedId = getParameterAsInt(req, "breedId");

            animalService.deleteBreed(breedId);

            resp.sendRedirect("/animalBreed");
        } catch (SQLException e) {
            throw new ServletException("Animal breed deletion error! Something went wrong!", e);

        }
    }

}
