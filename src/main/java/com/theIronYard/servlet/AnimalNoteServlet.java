package com.theIronYard.servlet;

import com.theIronYard.entity.Animal;
import com.theIronYard.entity.Note;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by chris on 9/16/16.
 */
@WebServlet("/animalNotes")
public class AnimalNoteServlet extends AbstractServlet {

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
            throw new ServletException("Error showing animal notes", e);
        }

        req.getRequestDispatcher("WEB-INF/animalNotes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // get the id of the animal we're adding a note to
            Integer id = getParameterAsInt(req, "id");
            req.setAttribute("id", id);

            // get the animal
            Animal animal = animalService.getAnimal(id);

            // get the text of the note
            String noteText = getParameterAsString(req, "note");

            // make a note
            Note note = new Note(noteText);

            // add the note to the animal
            animal.getNotes().add(note);

            // save the animal
            animalService.editAnimal(animal);

            resp.sendRedirect("/animalNotes?id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}