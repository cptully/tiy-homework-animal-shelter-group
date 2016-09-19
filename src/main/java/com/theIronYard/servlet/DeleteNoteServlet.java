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
@WebServlet("/deleteNote")
public class DeleteNoteServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Integer animalId = getParameterAsInt(req, "animalId");

            Integer noteId = getParameterAsInt(req, "noteId");

            animalService.deleteNote(animalId, noteId);

            resp.sendRedirect("/animalNotes?id=" + animalId);
        } catch (SQLException e) {
            throw new ServletException("Note deletion error! Something went wrong!", e);

        }
    }

}
