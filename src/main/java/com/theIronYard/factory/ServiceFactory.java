package com.theIronYard.factory;

import com.theIronYard.repository.AnimalBreedRepository;
import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.AnimalTypeRepository;
import com.theIronYard.repository.NoteRepository;
import com.theIronYard.service.AnimalService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by chris on 9/15/16.
 */
public class ServiceFactory {

    // the JDBC url
    private static String jdbcUrl = "jdbc:postgresql://localhost:5432/animal2";

    // the private animalService
    private static AnimalService animalService;

    /**
     * This method returns a single animalService that is shared among anything
     * that uses this method to load it.
     * @return AnimalService
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static AnimalService getAnimalService() throws ClassNotFoundException, SQLException, IOException {
        // if the animal service hasn't been created yet, then we need to create it.
        if(ServiceFactory.animalService == null){
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(jdbcUrl);

            AnimalRepository animalRepository = new AnimalRepository(connection);
            AnimalTypeRepository typeRepository = new AnimalTypeRepository(connection);
            AnimalBreedRepository breedRepository = new AnimalBreedRepository(connection);
            NoteRepository noteRepository = new NoteRepository(connection);

            ServiceFactory.animalService = new AnimalService(
                    animalRepository, typeRepository, breedRepository, noteRepository);
        }

        return animalService;
    }
}
