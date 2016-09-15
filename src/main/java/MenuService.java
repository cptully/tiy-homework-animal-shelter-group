import com.theIronYard.Animal.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by chris on 8/12/16.
 */


class MenuService {
    private AnimalService animalService; // = new AnimalService(dataStore);

    /**
     * The MenuService constructor requires
     * AnimalService parameter that it uses in coordinating the
     * menu selected activities.
     *
     * @param animalService AnimalService object for interfacing to the Animal Repository
     */
    public MenuService(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Calls the main menu displayService function and returns the user's choice.
     *
     * @return int value representing the user's choice
     */

    int promptForMainMenuSelection() {

        // build the menu
        System.out.println("\n\n---- Main Menu ----\n" +
                "1) Create an animal\n" +
                "2) Manage animals\n" +
                "3) Manage types and breeds\n" +
                "4) Quit\n");

        int choice = waitForInt("Please choose an option:");

        //check for valid input
        if ((choice < 1) || (choice > 4)) {
            System.out.println("\n\n\nPlease choose a number between 1 and 7");
            choice = promptForMainMenuSelection();
        }

        return choice;
    }


    int promptForDbManagementSelection() {
        System.out.println("\n\n---- Database Management menu ----\n" +
                "1) Add an animal type\n" +
                "2) Edit an animal type\n" +
                "3) Delete an animal type\n" +
                "4) Add an animal breed\n" +
                "5) Edit an animal breed\n" +
                "6) Delete an animal breed\n" +
                "7) Return to Main Menu\n");

        int choice = waitForInt("Please choose an option:");

        //check for valid input
        if ((choice < 1) || (choice > 7)) {
            System.out.println("\n\n\nPlease choose a number between 1 and 7");
            choice = promptForDbManagementSelection();
        }

        return choice;
    }

    int promptForSearchMethod() {
        System.out.println("\n\n---- Select animal menu ----\n" +
                "1) Search by animal name\n" +
                "2) Search by animal type\n" +
                "3) Search by animal breed\n" +
                "4) List all animals\n" +
                "5) Return to Main Menu\n");

        int choice = waitForInt("Please choose an option:");

        //check for valid input
        if ((choice < 1) || (choice > 5)) {
            System.out.println("\n\n\nPlease choose a number between 1 and 5");
            choice = promptForSearchMethod();
        }

        return choice;
    }

    public int promptForManageAnimalMenuSelection() {
        System.out.println("\n\n---- Animal Management menu ----\n" +
                "1) Add an animal\n" +
                "2) Edit an animal\n" +
                "3) Delete an animal\n" +
                "4) Return to Main Menu\n");

        int choice = waitForInt("Please choose an option:");

        //check for valid input
        if ((choice < 1) || (choice > 4)) {
            System.out.println("\n\n\nPlease choose a number between 1 and 4");
            choice = promptForManageAnimalMenuSelection();
        }

        return choice;
    }

    int promptForEditAnimalMenuSelection(){
        int choice = waitForInt("\n\n---- Edit Animal ----\n" +
                "1) Edit animal details\n" +
                "2) Delete animal\n" +
                "3) Create new note\n" +
                "4) Delete note\n" +
                "5) Return to main menu\n");

        //check for valid input
        if ((choice < 1) || (choice > 5)) {
            System.out.println("\nPlease choose a number between 1 and 5");
            choice = promptForEditAnimalMenuSelection();
        }

        return choice;
    }

    Animal promptForNewAnimal() throws SQLException {
        ArrayList<AnimalType> types = animalService.getValidAnimalTypes();
        ArrayList<AnimalBreed> breeds = animalService.getValidAnimalBreeds();

        // name
        String name = promptForString("Name: ", true);

        // type
        String prompt = "(";
        for (AnimalType type: types) {
            prompt = prompt + type.getTypeName() + ", ";
        }
        prompt = prompt + ")";
        String type = "";
        while (!containsType(types, type)) {
            type = promptForString("Type" + prompt + ": ", true);
        }

        // breed
        prompt = "(";
        for (AnimalBreed breed: breeds) {
            prompt = prompt + breed.getName() + ", ";
        }
        prompt = prompt + ")";
        String breed = "";
        while (!containsBreed(breeds, breed)) {
            breed =  promptForString("Breed: ([" + prompt + "]): ", false);
        }

        // color
        String color = promptForString("Color: ", true);

        // description
        String description = promptForString("Description: ", true);

        AnimalType animalType = new AnimalType(type);
        for(AnimalType t : types) {
            if(t.getTypeName().equals(type)) {
                animalType.setTypeId(t.getTypeId());
                break;
            }
        }

        AnimalBreed animalBreed = new AnimalBreed(breed);
        for (AnimalBreed b : breeds) {
            if (b.getName().equals(breed)) {
                animalBreed.setBreedId(b.getBreedId());
                break;
            }
        }

        return new Animal(name, animalType, animalBreed, color, description);
    }

    void listAnimals() throws SQLException {
        displayAnimals();
    }

    void displayAnimals() throws SQLException {
        ArrayList<Animal> animalList = animalService.listAnimals();
        for (Animal animal: animalList) {
            System.out.println(animal.getId() + "\t" + animal.getName());
        }
        promptForString("Press <enter> to continue...", false);
    }

    void displayAnimals(ArrayList<Animal> animals) throws SQLException {
        for (Animal animal: animals) {
            System.out.println(animal);
        }
        promptForString("Press <enter> to continue...", false);
    }

    void displayAnimal(int id) throws SQLException {
        if (animalService.contains(id)) {
            Animal animal = animalService.getAnimal(id);
            System.out.println(animal.toString("v"));
            if (animal.getNotes().size() > 0) {
                System.out.println("\n--Notes");
                System.out.println("Id\tDate\t\t\t\t\t\tNote");
                for (Note note : animal.getNotes()) {
                    System.out.println(note.toString());
                }
            }
        } else {
            System.out.println("No animal to display!");
        }
        //return -1;
    }



    // used in multiple places to interact with AnimalService
    Animal promptForAnimalToView() throws SQLException {
        //displayAnimalList(animals);
        int animalId;
        animalId = viewAnimalDetails("view");
        Animal animal = animalService.getAnimal(animalId);

        displayAnimal(animalId);

        return animal;
    }

    private int viewAnimalDetails(String prompt) throws SQLException {
        int choice = waitForInt("\nPlease enter the index of the animal you want to " + prompt + ". ");
        if (!animalService.contains(choice)) {
            System.out.println("That is not a valid animal ID!");
            return -1;
        }
        return choice;
    }

    Animal promptForNewAnimalData(Animal animal) throws SQLException {
        ArrayList<AnimalType> types = animalService.getValidAnimalTypes();
        ArrayList<AnimalBreed> breeds = animalService.getValidAnimalBreeds();

        // name
        String name = promptForString("Name (" + animal.getName() + "): ", false);
        if (name.equals("")) name = animal.getName();

        // type
        String prompt = "(";
        for (AnimalType type: types) {
            prompt = prompt + type.getTypeName() + ", ";
        }
        prompt = prompt + ")";
        String type = "";

        while (!containsType(types, type)) {
            type = promptForString("Type (" + animal.getType().getTypeName() + "[" + prompt + "]): ", false);
            if (type.equals("")) {
                type = animal.getType().getTypeName();
                break;
            }
        }

        // breed
        prompt = "(";
        for (AnimalBreed breed: breeds) {
            prompt = prompt + breed + ", ";
        }
        prompt = prompt + ")";
        String breed = "";
        while (!containsBreed(breeds, breed)) {
            breed =  promptForString("Breed: (" + animal.getBreed().getName() + "[" + prompt + "]): ", false);
            if (breed.equals("")) {
                breed = animal.getBreed().getName();
                break;
            }
        }

        // color
        String color = promptForString("Color: (" + animal.getColor() + "): ", false);
        if (color.equals("")) color = animal.getColor();

        // description
        String description = promptForString("Description: (" + animal.getDescription() + "): ", false);
        if (description.equals("")) description = animal.getDescription();

        AnimalType animalType = new AnimalType(type);
        for(AnimalType t : types) {
            if(t.getTypeName().equals(type)) {
                animalType.setTypeId(t.getTypeId());
                break;
            }
        }

        AnimalBreed animalBreed = new AnimalBreed(breed);
        for (AnimalBreed b : breeds) {
            if (b.getName().equals(breed)) {
                animalBreed.setBreedId(b.getBreedId());
            }
        }

        return new Animal(name, animalType, animalBreed, color, description);
    }

    private boolean containsType(ArrayList<AnimalType> types, String newType) {
        for(AnimalType type : types) {
            if(type != null && type.getTypeName().equals(newType)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsBreed(ArrayList<AnimalBreed> breeds, String newBreed) {
        for(AnimalBreed breed : breeds) {
            if(breed != null && breed.getName().equals(newBreed)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNote(ArrayList<Note> notes, int id) {
        for (Note note : notes) {
            if (note != null && note.getId() == id) {
                return true;
            }
        }
        return false;
    }


    public Animal promptForNewNote(Animal animal) throws SQLException {
        String newNote = promptForString("Please enter a new note for " + animal.getName() + ":", true);
        animal.addNote(newNote);
        return animal;
    }

    int promptForNoteToDelete(Animal animal) {
        int choice = -1;

        while (!containsNote(animal.getNotes(), choice)) {
            choice = waitForInt("Please enter the ID of the note to delete. (type -1 to cancel)");
            if (choice == -1) break;
        }

        return choice;
    }

    // confirm choice to remove animal
    boolean promptForAnimalToDelete(Animal animal) throws SQLException {
        if (waitForYesNo("Are you sure you want to remove " + animal.getName() + " from the shelter?\" (y/n)").equals("y")) {
            return true;
        }
        return false;
    }

    void promptForNewBreed() throws SQLException {
        String breed = promptForString("Enter a name for the new breed:", true);
        displayTypeList();
        int typeId = waitForInt("\nPlease enter the animal type ID for " + breed + ":");
        animalService.addBreed(new AnimalBreed(breed, typeId));
    }

    AnimalBreed promptForNewBreedData() throws SQLException {
        displayBreedList(animalService);
        int breedId = waitForInt("\nPlease enter the ID of the breed to be edited:");

        AnimalBreed animalBreed = animalService.getBreed(breedId);

        String newBreed = promptForString("Enter a new name for " + animalBreed.getName() + ":", false);
        if (!newBreed.equals("")) animalBreed.setName(newBreed);
        displayTypeList();
        int newTypeId = waitForInt("\nPlease enter a new animal type ID for "
                + animalBreed.getName() +
                " [" + animalBreed.getTypeId() + "]  (-1 for no change):");
        if (newTypeId >= 0) animalBreed.setTypeId(newTypeId);
        return animalBreed;
    }

    // TODO: 9/13/16 add a way to delete a breed that is in use
    void promptForBreedToDelete() throws SQLException {
        displayBreedList();

        int breedId = waitForInt("Please enter the ID of the breed to be deleted:");
        int count = animalService.deleteBreed(breedId);
        if (count != 0) {
            AnimalBreed animalBreed = animalService.getBreed(breedId);
            deleteError("The " + animalBreed.getName() + " breed cannot be deleted because it " +
                    "is used by " + count + " animals.");
        }
    }

    private void displayBreedList() throws SQLException {
        ArrayList<AnimalBreed> animalBreeds = animalService.listBreeds();
        for (AnimalBreed animalBreed : animalBreeds) {
            System.out.println(animalBreed.getBreedId() + "\t" + animalBreed.getName());
        }
    }

    AnimalType promptForNewType() throws SQLException {
        String name = promptForString("Please enter a name for the new animal type:", true);
        return new AnimalType(name);
    }

    int promptForTypeToEdit() throws SQLException {
        displayTypeList();
        return waitForInt("Please enter the ID of the animal type to be edited:");
    }

    AnimalType promptForNewTypeName(int typeId) throws SQLException {
        AnimalType animalType = animalService.getType(typeId);
        String newType = promptForString("Please enter the new name of the " + animalType.getTypeName() + ":", false);
        if (!newType.equals("")) animalType.setTypeName(newType);
        return animalType;
    }


    // TODO: 9/13/16 add a away to delete a type that is in use
    void promptForTypeToDelete() throws SQLException {
        displayTypeList();

        int typeId = waitForInt("Please enter the ID of the type to be deleted:");
        int count = animalService.deleteType(typeId);
        if (count != 0) {
            AnimalType animalType = animalService.getType(typeId);
            deleteError("The " + animalType.getTypeName() + " type cannot be deleted because" +
                    " it is used by " + count + "animals.");
        }
    }

    void deleteError(String message) {
        System.out.println(message);
        promptForString("Press <enter> to continue...", false);
    }

    void promptForNameToSearch() throws SQLException {
        String name = promptForString("Enter the name of the animal:", true);
        ArrayList<Animal> animals = animalService.searchByName(name);
        displayAnimals(animals);
    }

    void promptForTypeToSearch() throws SQLException {
        displayTypeList();
        int typeId =  waitForInt("\nPlease enter the ID of the type to be searched:");
        ArrayList<Animal> animals = animalService.searchByType(typeId);
        displayAnimals(animals);
    }

    void promptForBreedToSearch() throws SQLException {
        displayBreedList(animalService);
        int breedId = waitForInt("\nplease entre the ID of the breed to be searched");
        ArrayList<Animal> animals = animalService.searchByBreed(breedId);
        displayAnimals(animals);
    }

    private void displayBreedList(AnimalService animalService) throws SQLException {
        ArrayList<AnimalBreed> animalBreeds = animalService.listBreeds();
        for (AnimalBreed animalBreed : animalBreeds) {
            System.out.println(animalBreed.getBreedId() + "\t" + animalBreed.getName());
        }
    }

    private void displayTypeList() throws SQLException {
        ArrayList<AnimalType> animalTypes = animalService.listTypes();
        for (AnimalType animalType : animalTypes) {
            System.out.println(animalType.getTypeId() + "\t" + animalType.getTypeName());
        }
    }

    private String promptForString(String message, boolean required) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.trim();
        if (input.isEmpty() && required) {
            System.out.println("You must enter a value for: " + message);
            promptForString(message, required);
        }
        return input.trim();
    }


    public double promptForWeight(String name) {
        //System.out.printf("%s's weight: ", name);
        return waitForDouble(name + "'s weight: ");
    }

    private int waitForInt(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input);

        } catch (Exception e) {
            System.out.println("\nPlease provide a number.\n");

            value = waitForInt(message);
        }

        return value;
    }

    private double waitForDouble(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();

        double value;
        try {
            value = Double.parseDouble(input);

        } catch (Exception e) {
            System.out.println("\nPlease provide a number.\n");

            value = waitForInt(message);
        }

        return value;
    }

    private String waitForYesNo(String message) {
        Scanner scanner = new Scanner(System.in);
        String answer;

        System.out.println(message);

        String input = scanner.nextLine();
        if (input.equals("")) {
            answer = "";
        } else {
            answer = input.substring(0, 1);
        }
        if ((answer.equals("y")) || (answer.equals("n"))) {
            return answer;
        } else {
            answer = waitForYesNo("Please enter either \"y\" for yes or \"n\" for no.");
        }

        return answer;
    }
}