import com.theIronYard.Animal.*;
//import com.theIronYard.Animal.Note;
//import com.theIronYard.Animal.NoteRepository;
//import com.theIronYard.Animal.Notes;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chris on 8/12/16.
 */
public class Main {

    public static void main(String [] args) {
        try {
            String jdbcUrl = "jdbc:postgresql://localhost/animalrepository_test";
            AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
            AnimalBreedRepository animalBreedRepository = new AnimalBreedRepository(jdbcUrl);
            AnimalTypeRepository animalTypeRepository = new AnimalTypeRepository(jdbcUrl);
            NoteRepository noteRepository = new NoteRepository(jdbcUrl);
            AnimalService animalService = new AnimalService(animalRepository,
                    animalBreedRepository,
                    animalTypeRepository,
                    noteRepository);

            MenuService menuService = new MenuService(animalService);

            int choice;
            int subChoice;
            boolean running = true;
            ArrayList<Animal> animals;

            while (running) {

                choice = menuService.promptForMainMenuSelection();
                switch (choice) {
                    case 1:             //2) Create an animal
                        // TODO: 9/13/16 allow breed to be blank
                        Animal newAnimal = menuService.promptForNewAnimal();
                        animalService.addAnimal(newAnimal);
                        break;
                    case 2:
                        subChoice = menuService.promptForSearchMethod();
                        //while (subChoice != 5) {
                            switch (subChoice) {
                                case 1:
                                    menuService.promptForNameToSearch();
                                    break;
                                case 2:
                                    menuService.promptForTypeToSearch();
                                    break;
                                case 3:
                                    menuService.promptForBreedToSearch();
                                    break;
                                case 4:
                                    menuService.displayAnimals();
                                    break;
                            }
                        //}
                        Animal animal = menuService.promptForAnimalToView();
                        int noteChoice = menuService.promptForEditAnimalMenuSelection();
                        switch (noteChoice) {
                            case 1:         // edit animal
                                newAnimal = menuService.promptForNewAnimalData(animal);
                                animalService.addAnimal(newAnimal);

                                break;
                            case 2:         // delete animal
                                if (menuService.promptForAnimalToDelete(animal)) {
                                    animalService.deleteAnimal(animal.getId());
                                }
                                break;
                            case 3:         // add note
                                animal = menuService.promptForNewNote(animal);
                                animalService.editAnimal(animal);
                                break;
                            case 4:         // delete note
                                int deleteId = menuService.promptForNoteToDelete(animal);
                                if (deleteId != 0) {
                                    animalService.deleteNote(animal.getId(), deleteId);
                                }
                                break;
                            default:        // return to main menu
                                break;
                        }
                        break;
                    case 3:             //6) Manage DB
                        subChoice = 0;
                        while (subChoice != 7) {
                            subChoice = menuService.promptForDbManagementSelection();
                            switch (subChoice) {
                                case 1:     //1) add type
                                    AnimalType animalType = menuService.promptForNewType();
                                    animalService.addType(animalType);
                                    break;
                                case 2:     //2) edit type
                                    int typeId = menuService.promptForTypeToEdit();
                                    menuService.promptForNewTypeName(typeId);
                                    break;
                                case 3:     //3) delete type
                                    menuService.promptForTypeToDelete();
                                    break;
                                case 4:     //3) add breed
                                    menuService.promptForNewBreed();
                                    break;
                                case 5:     //5) edit breed
                                    menuService.promptForNewBreedData();
                                    break;
                                case 6:     //6) delete breed
                                    menuService.promptForBreedToDelete();
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case 4:             //7) quit
                        running = false;
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException e){
            System.out.println("Error connecting to database!");
            e.printStackTrace();
        }

    }
}
