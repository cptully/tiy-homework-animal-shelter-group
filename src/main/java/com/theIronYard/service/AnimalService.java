package com.theIronYard.service;

import com.theIronYard.entity.*;
import com.theIronYard.repository.AnimalBreedRepository;
import com.theIronYard.repository.AnimalRepository;
import com.theIronYard.repository.AnimalTypeRepository;
import com.theIronYard.repository.NoteRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chris on 8/12/16.
 */
public class AnimalService {
    // properties
    private AnimalRepository animalRepository;
    private AnimalBreedRepository animalBreedRepository;
    private AnimalTypeRepository animalTypeRepository;
    private NoteRepository noteRepository;

    /**
     * The AnimalService class constructor requires an AnimalRepository object
     *
     * @param animalRepository an AnimalRepository object
     */
    public AnimalService(AnimalRepository animalRepository,
                         AnimalTypeRepository animalTypeRepository,
                         AnimalBreedRepository animalBreedRepository,
                         NoteRepository noteRepository) {
        this.animalRepository = animalRepository;
        this.animalBreedRepository = animalBreedRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.noteRepository = noteRepository;
    }


    public ArrayList<AnimalType> getValidAnimalTypes() throws SQLException {
        ResultSet resultSet = animalTypeRepository.getAnimalTypes();

        ArrayList<AnimalType> types = new ArrayList<>();
        while (resultSet.next()) {
            types.add(new AnimalType(resultSet.getInt("typeid"), resultSet.getString("typename")));
        }

        return types;
    }

    public ArrayList<AnimalBreed> getValidAnimalBreeds() throws SQLException {
        ResultSet resultSet = animalBreedRepository.getBreeds();

        ArrayList<AnimalBreed> breeds = new ArrayList<>();
        while (resultSet.next()) {
            breeds.add(new AnimalBreed(
                    resultSet.getInt("breedid"),
                    resultSet.getString("breed"),
                    resultSet.getInt("typeid")));
        }
        return breeds;
    }

    /**
     * listAnimals
     *
     * @return ArrayList&lt;String&gt; of string representations of animals
     */
    public ArrayList<Animal> listAnimals() throws SQLException {
        ResultSet resultSet = animalRepository.list();

        return buildAnimalList(resultSet);
    }

    public ArrayList<AnimalType> listTypes() throws SQLException {
        ResultSet resultSet = animalTypeRepository.getAnimalTypes();

        ArrayList<AnimalType> types = new ArrayList<>();
        while (resultSet.next()) {
            AnimalType type = new AnimalType(resultSet.getInt("typeid"), resultSet.getString("typename"));
            types.add(type);
        }

        return types;
    }

    public ArrayList<Note> listNotes(int id) throws SQLException {
        ArrayList<Note> notes = new ArrayList<>();
        ResultSet animalNotes = noteRepository.getNotes(id);
        while (animalNotes.next()){
            Note note = new Note(animalNotes.getInt("noteid"),
                    animalNotes.getString("note"),
                    animalNotes.getTimestamp("date").toLocalDateTime());
            notes.add(note);
        }
        return notes;
    }

    public void addNote(int animalId, String note) throws SQLException {
        noteRepository.addNote(animalId, note);
    }

    /**
     * getAnimal gets the animal object for the submitted integer index
     * @param id - the object to be returned
     * @return an entity object
     */
    public Animal getAnimal(int id) throws SQLException {
        Animal newAnimal = new Animal();

        ResultSet resultSet = animalRepository.getAnimal(id);

        while (resultSet.next()) {
            newAnimal.setId(resultSet.getInt("id"));
            newAnimal.setName(resultSet.getString("name"));
            AnimalType newType = new AnimalType(
                    resultSet.getInt("typeid"),
                    resultSet.getString("typename"));
            newAnimal.setType(newType);
            AnimalBreed newBreed = new AnimalBreed(
                    resultSet.getInt("breedid"),
                    resultSet.getString("breed"),
                    resultSet.getInt("typeid"));
            newAnimal.setBreed(newBreed);
            newAnimal.setColor(resultSet.getString("color"));
            newAnimal.setDescription(resultSet.getString("description"));
        }

        ArrayList<Note> notes = listNotes(id);
        newAnimal.setNotes(notes);
        return newAnimal;
    }

    /**
     * addAnimal adds the supplied entity to the AnimalRepository
     *
     * @param newAnimal entity to be added
     */
    public void addAnimal(Animal newAnimal) throws SQLException {
        animalRepository.addAnimal(newAnimal);
        ResultSet resultSet =  animalTypeRepository.getAnimalTypes();

    }

    /**
     * editAnimal passes the edited animal to AnimalRepository
     *
     * @param animal the edited animal
     */
    public void editAnimal(Animal animal) throws SQLException {
        animalRepository.editAnimal(animal);
    }

    /**
     * deleteAnimal removes the selected animal from the repository
     *
     * @param id the zero based index of the animal to deleteAnimal
     */
    public void deleteAnimal(int id) throws SQLException {
        animalRepository.removeAnimal(id);}

    public boolean contains(Animal animal) throws SQLException { return animalRepository.containsAnimal(animal.getId()); }

    public boolean contains(int id) throws SQLException {
        return animalRepository.containsAnimal(id);
    }

    public void deleteNote(int animalId, int noteId) throws SQLException {
        noteRepository.removeNote(animalId, noteId);
    }

    public int size() throws SQLException {return animalRepository.size();}

    public void addBreed(AnimalBreed breed) throws SQLException {
        animalBreedRepository.addBreed(breed);
    }

    public int deleteBreed(int breedId) throws SQLException {
        return animalBreedRepository.deleteBreed(breedId);
    }

    public ArrayList<AnimalBreed> listBreeds() throws SQLException {
        ArrayList<AnimalBreed> breeds = new ArrayList<>();
        ResultSet resultSet = animalBreedRepository.getBreeds();
        while (resultSet.next()){
            AnimalBreed breed = new AnimalBreed(resultSet.getInt("breedid"),
                                                resultSet.getString("breed"),
                                                resultSet.getInt("typeid"));
            breeds.add(breed);
        }
        return breeds;
    }

    public AnimalBreed getBreed(int breedId) throws SQLException {
        ResultSet resultSet = animalBreedRepository.getBreed(breedId);
        resultSet.next();
        AnimalBreed animalBreed = new AnimalBreed(resultSet.getInt("breedid"),
                                                  resultSet.getString("breed"),
                                                  resultSet.getInt("typeid"));
        return animalBreed;
    }

    public void editBreed(AnimalBreed animalBreed) throws SQLException {
        animalBreedRepository.editBreed(animalBreed);
    }

    public void addType(AnimalType animalType) throws SQLException {
        animalTypeRepository.addType(animalType);
    }

    public AnimalType getType(int typeId) throws SQLException {
        ResultSet resultSet = animalTypeRepository.getType(typeId);

        resultSet.next();
        return new AnimalType(
                resultSet.getInt("typeid"),
                resultSet.getString("typename")
        );
    }

    public void editType(AnimalType animalType) throws SQLException{
        animalTypeRepository.editType(animalType);
    }

    public int deleteType(int typeId) throws SQLException {
        return animalTypeRepository.deleteType(typeId);
    }

    public ArrayList<Animal> searchByName(String name) throws SQLException {
        ResultSet resultSet = animalRepository.searchByName(name);
        return buildAnimalList(resultSet);
    }

    public ArrayList<Animal> searchByType(int typeId) throws SQLException {
        ResultSet resultSet = animalRepository.searchByType(typeId);
        return buildAnimalList(resultSet);
    }

    public ArrayList<Animal> searchByBreed(int breedId) throws SQLException {
        ResultSet resultSet = animalRepository.searchByBreed(breedId);
        return buildAnimalList(resultSet);
    }

    private ArrayList<Animal> buildAnimalList(ResultSet resultSet) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        while (resultSet.next()) {

            AnimalType newType = new AnimalType(
                    resultSet.getInt("typeid"),
                    resultSet.getString("typename"));
            AnimalBreed newBreed = new AnimalBreed(
                    resultSet.getInt("breedid"),
                    resultSet.getString("breed"),
                    resultSet.getInt("typeid"));
            Animal newAnimal = new Animal(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    newType,
                    newBreed,
                    resultSet.getString("color"),
                    resultSet.getString("description"));
            ArrayList<Note> notes = listNotes(newAnimal.getId());
            newAnimal.setNotes(notes);
            animals.add(newAnimal);
        }

        return animals;
    }
}
