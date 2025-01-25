package org.lms;

/* Manage the LMS Menu the user will see while using the application */

import java.util.List;
import java.util.Scanner;

public class OptionsMenu
{
    // Allows the user to enter input through the CLI
    private Scanner scanner;
    private int userChoice;
    private LMS lms;

    public OptionsMenu()
    {
        this.scanner = new Scanner(System.in);
        this.userChoice = 0;
        this.lms = new LMS();
    }

    // Introduce the LMS to the user
    private void introduceLMS()
    {
        System.out.println("\n\n\t\tWelcome to the Patron Library Management System\n\n");
        System.out.println("We know it is overwhelming to manage your patron's data. Hence, all you need to do that is here");
        System.out.println("What are you waiting to get started?\n\n");

    }

    // Display the options the user has
    private void introduceMenuOptions()
    {
        System.out.println("\n\nOPTIONS MENU: ");
        System.out.println(AnsiColorCodes.BLUE_COLOR + "\t\t1: Get Details of All Registered Patrons" + AnsiColorCodes.DEFAULT_COLOR);
        System.out.println(AnsiColorCodes.BLUE_COLOR + "\t\t2: Get Details of a Patron By Its ID" + AnsiColorCodes.DEFAULT_COLOR);
        System.out.println(AnsiColorCodes.BLUE_COLOR + "\t\t3: Import Patrons From External Text File" + AnsiColorCodes.DEFAULT_COLOR);
        System.out.println(AnsiColorCodes.BLUE_COLOR + "\t\t4: Export Registered Patrons To External Text File" + AnsiColorCodes.DEFAULT_COLOR);
        System.out.println(AnsiColorCodes.GREEN_COLOR + "\t\t5: Add Patron to the System" + AnsiColorCodes.DEFAULT_COLOR);
        System.out.println(AnsiColorCodes.GREEN_COLOR + "\t\t6: Update Patron Details By Its ID" + AnsiColorCodes.DEFAULT_COLOR);
        System.out.println(AnsiColorCodes.RED_COLOR + "\t\t7: Delete Patron From The System By Its ID: " + AnsiColorCodes.DEFAULT_COLOR);
        System.out.println(AnsiColorCodes.RED_COLOR + "\t\t8: EXIT" + AnsiColorCodes.DEFAULT_COLOR);
    }

    private void displayAllPatrons()
    {
        // Get list of Registered patrons from LMS
        List<Patron> registeredPatrons = this.lms.getAllRegisteredPatrons();

        System.out.println(AnsiColorCodes.BLUE_COLOR + "[\n" + AnsiColorCodes.DEFAULT_COLOR);

        for (int i = 0; i < registeredPatrons.size(); i++)
        {
            Patron registeredPatron = registeredPatrons.get(i);
            System.out.println(AnsiColorCodes.BLUE_COLOR + "\t" + registeredPatron.toString() + "\n" + AnsiColorCodes.DEFAULT_COLOR);

            if (i < registeredPatrons.size() - 1)
            {
                System.out.println(AnsiColorCodes.BLUE_COLOR + "," + AnsiColorCodes.DEFAULT_COLOR);
            }
        }

        System.out.println(AnsiColorCodes.BLUE_COLOR + "]\n" + AnsiColorCodes.DEFAULT_COLOR);
    }


    // Option to get list of all registered patrons
    private void getRegisteredPatronsDetailsOptions()
    {
        System.out.println("\n\nYou've chosen to get a list of all registered patrons");
        System.out.println("\nRegistered Patrons List: ");

        // Get list of Registered patrons from LMS
        displayAllPatrons();
    }

    // Menu option to get patron details by its id
    private void getPatronDetailsByIdOption()
    {
        System.out.println("\n\nYou've chosen to read the details of a specific patron");

        // Get the Patron's ID
        String patronID = askForPatronDetails("\nPlease enter the Patron ID you're looking for: ");

        // Read patron by ID
        try
        {
            Patron readPatron = this.lms.getPatronById(patronID);
            System.out.println(AnsiColorCodes.BLUE_COLOR + readPatron + "\n" + AnsiColorCodes.DEFAULT_COLOR);
        }

        catch (ValidationError | PatronNotFoundError e)
        {
            System.out.println(e.getMessage());
        }
    }

    // Menu option to import patrons from external text file
    private void importPatronsFromExternalTextFileOptions()
    {
        System.out.println("\n\nYou've chosen to import patrons from an external file");
        String fileName = askForPatronDetails("\nPlease enter the name of the file you want to import (with the .txt extension): ");

        String importedPatronsFeedback = this.lms.addImportedPatrons(fileName);
        System.out.println(importedPatronsFeedback);
        displayAllPatrons();


    }

    // Menu option to export patrons to external file
    private void exportPatronsToExternalFileOptions()
    {
        System.out.println("\n\nYou've chosen to export the registered patrons to an external file");
        String fileName = askForPatronDetails("\nPlease enter the name of the file you want to export your patrons to (without the .txt extension): ");

        String exportedPatronsFeedback = this.lms.exportCurrentPatronsList(fileName);
        System.out.println(exportedPatronsFeedback);
        displayAllPatrons();
    }

    // Menu option to add patron the system directly
    private void addPatronManuallyToTheSystemOptions()
    {
        System.out.println("\n\nYou've chosen to add a patron manually to the system");

        String newPatronId = askForPatronDetails("\n Please enter the new's Patron ID: ");
        String newPatronName = askForPatronDetails("\nPlease enter the new's Patron Name: ");
        String newPatronAddress = askForPatronDetails("\nPlease enter the new's Patron Address: ");
        String newPatronsOverdueFine = askForPatronDetails("\n Please enter the new's Patron Overdue Fine: ");

        String addedPatronFeedback = this.lms.addPatron(newPatronId, newPatronName, newPatronAddress, newPatronsOverdueFine);
        System.out.println(addedPatronFeedback);
        displayAllPatrons();
    }

    // Menu option to update patron by ID
    private void updatePatronByIdOption()
    {
        System.out.println("\n\nYou've chosen to update the details of a patron");

        String patronToUpdateId = askForPatronDetails("\nPlease enter the ID of the patron to update: ");
        String updatedPatronName = askForPatronDetails("\n Please enter the new name for the patron: ");
        String updatedPatronAddress = askForPatronDetails("\nPlease enter the new address for the patron: ");
        String updatedOverdueFine = askForPatronDetails("\nPlease enter the new overdue fine for the patron: ");

        String updatedPatronFeedback = this.lms.updatePatronById(patronToUpdateId, updatedPatronName, updatedPatronAddress, updatedOverdueFine);
        System.out.println(updatedPatronFeedback);
        displayAllPatrons();
    }

    // Menu option to delete patron by ID
    private void deletePatronByIdOptions()
    {
        System.out.println("\n\nYou've chosen to delete a patron from the system");
        String patronToDeleteId = askForPatronDetails("\nPlease enter the ID of the patron to delete: ");

        String deletedPatronFeedback = this.lms.deletePatronById(patronToDeleteId);
        System.out.println(deletedPatronFeedback);
        displayAllPatrons();
    }

    // Refactor the code for asking the user for a detail about the patron
    private String askForPatronDetails(String prompt)
    {
        System.out.print(prompt);
        return this.scanner.nextLine();
    }

    // Display the interactive LMS menu to the user
    public void displayMenu()
    {
        // Run a do while loop as long as the user hasn't chosen to exit
        // Introduce the program to the user
        introduceLMS();

        // Introduce menu Options
        introduceMenuOptions();

        // Get user's choice
        this.userChoice = getUserChoice();

        while (this.userChoice != 8)
        {
            switch (this.userChoice)
            {
                case 1 -> {
                    getRegisteredPatronsDetailsOptions();
                    introduceMenuOptions();
                    this.userChoice = getUserChoice();
                }
                case 2 -> {
                    getPatronDetailsByIdOption();
                    introduceMenuOptions();
                    this.userChoice = getUserChoice();
                }
                case 3 -> {
                    importPatronsFromExternalTextFileOptions();
                    introduceMenuOptions();
                    this.userChoice = getUserChoice();
                }
                case 4 -> {
                    exportPatronsToExternalFileOptions();
                    introduceMenuOptions();
                    this.userChoice = getUserChoice();
                }
                case 5 -> {
                    addPatronManuallyToTheSystemOptions();
                    introduceMenuOptions();
                    this.userChoice = getUserChoice();
                }
                case 6 -> {
                    updatePatronByIdOption();
                    introduceMenuOptions();
                    this.userChoice = getUserChoice();
                }
                case 7 -> {
                    deletePatronByIdOptions();
                    introduceMenuOptions();
                    this.userChoice = getUserChoice();
                }
                default -> {
                    System.out.println(AnsiColorCodes.RED_COLOR + "\n\nThe option you've chosen is invalid" + AnsiColorCodes.DEFAULT_COLOR);
                    System.out.println(AnsiColorCodes.RED_COLOR + "Try again" + AnsiColorCodes.DEFAULT_COLOR);
                    introduceMenuOptions();
                    this.userChoice = getUserChoice();
                }
            }

        }
        // Once the user chooses to exit, this will run
        System.out.println("\n\nYou've chosen to exit the program");
        System.out.println("\nHave a wonderful day");
        this.scanner.close();
    }

    // Extract the choice of the user will do
    private int getUserChoice()
    {
        // Get options choice from the user
        int choice = 0;
        System.out.print("\nPlease enter your choice (1-8): ");

        try
        {
            choice = Integer.parseInt(this.scanner.nextLine());

            if (choice < 1 || choice > 8)
            {
                throw new Exception();
            }
        }

        catch (Exception e)
        {
            System.out.println(AnsiColorCodes.RED_COLOR + "Please enter a number between 1 and 8" + AnsiColorCodes.DEFAULT_COLOR);
        }
        
        return choice;
    }
}
