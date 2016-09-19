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
@WebServlet("/deleteAnimalType")
public class DeleteAnimalTypeServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Integer typeId = getParameterAsInt(req, "typeId");

            animalService.deleteType(typeId);

            resp.sendRedirect("/animalType");
        } catch (SQLException e) {
            throw new ServletException("Animal type deletion error! Something went wrong!", e);

        }
    }

}
