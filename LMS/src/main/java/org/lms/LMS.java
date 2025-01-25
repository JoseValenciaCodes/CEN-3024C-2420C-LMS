package org.lms;

import java.util.ArrayList;
import java.util.List;

/* Perform CRUD operations on the patrons list of the LMS */

public class LMS
{
    private final List<Patron> patrons;
    LMSFileHandler lmsFileHandler;

    public LMS()
    {
        this.patrons = new ArrayList<Patron>();
        this.lmsFileHandler = new LMSFileHandler();
    }

    private boolean patronExists(String id)
    {
        return this.patrons.stream().anyMatch(patron -> patron.getId().equals(id));
    }

    public String addPatron(String id, String name, String address, String overdueFine)
    {
        try
        {
            // If ID is already in the system, throw a PatronAlreadyExistsError
            if (this.patronExists(id))
            {
                throw new PatronAlreadyExistsError(AnsiColorCodes.RED_COLOR + "A patron of id: " + id + " already exists in the system" + AnsiColorCodes.DEFAULT_COLOR);
            }

            Patron patron = new Patron(id, name, address, Double.parseDouble(overdueFine));

            // First Validate all patron fields
            PatronValidator.validatePatron(patron);

            // Then add the patron
            patrons.add(patron);
        }

        catch (NumberFormatException e)
        {
            System.out.println(AnsiColorCodes.RED_COLOR + "Please provide a number for the overdue fine" + AnsiColorCodes.DEFAULT_COLOR);
            return "";
        }

        catch (ValidationError | PatronAlreadyExistsError e)
        {
            System.out.println(AnsiColorCodes.RED_COLOR + e.getMessage() + AnsiColorCodes.DEFAULT_COLOR);
            return "";
        }

        return AnsiColorCodes.GREEN_COLOR + "\nA new patron was successfully added to the system\n" + AnsiColorCodes.DEFAULT_COLOR;
    }

    // Import Patrons from a file
    public String addImportedPatrons(String fileName)
    {
        try
        {
            // Instantiate LMS File Handler to deal with files
            LMSFileHandler lmsFileHandler = new LMSFileHandler();

            // Import patrons from the list
            List<Patron> importedPatrons = lmsFileHandler.importPatronsFromFile(fileName);

            // Add all imported patrons to the list of patrons
            this.patrons.addAll(importedPatrons);

            return AnsiColorCodes.GREEN_COLOR + "\nAll imported patrons were successfully added to the system\n" + AnsiColorCodes.DEFAULT_COLOR;
        }

        catch ( Exception e)
        {
            return e.getMessage();
        }
    }

    // Return the list of all registered patrons
    public List<Patron> getAllRegisteredPatrons()
    {
        return this.patrons;
    }

    // Get a patron by its ID
    public Patron getPatronById(String id) throws ValidationError, PatronNotFoundError
    {
        // Validate the given id
        if (!PatronValidator.isValidId(id))
        {
            throw new ValidationError(AnsiColorCodes.RED_COLOR + "The given Patron id: " + id + " is invalid" + AnsiColorCodes.DEFAULT_COLOR);
        }

        // Loop through all patrons
        for (Patron patron : patrons)
        {
            // If the patron's id is equal to the given one, return it
            if (patron.getId().equals(id))
            {
                return patron;
            }
        }

        // By this time throw a Patron Not Found Error
        throw new PatronNotFoundError(AnsiColorCodes.RED_COLOR + "No patron could be found of id: " + id + AnsiColorCodes.DEFAULT_COLOR);
    }

    // Modify data about a patron
    public String updatePatronById(String id, String name, String address, String overdueFine)
    {
        try
        {
            // Validate the given id
            if (!PatronValidator.isValidId(id))
            {
                throw new ValidationError(AnsiColorCodes.RED_COLOR + "The given Patron id: " + id + " is invalid" + AnsiColorCodes.DEFAULT_COLOR);
            }

            // Loop through all patrons
            for (int i = 0; i < this.patrons.size(); i++)
            {
                Patron patron = this.patrons.get(i);

                // If the patron's id is equal to the given one, modify its data
                if (patron.getId().equals(id))
                {
                    // Change patron name
                    patron.setName(name);

                    // Change patron address
                    patron.setAddress(address);

                    // Change patron overdue fine
                    patron.setOverdueFine(Double.parseDouble(overdueFine));

                    PatronValidator.validatePatronUpdate(patron);

                    // Save the changes
                    this.patrons.set(i, patron);

                    // Return empty patron
                    return AnsiColorCodes.GREEN_COLOR + "The details of the given patron were successfully updated" + AnsiColorCodes.DEFAULT_COLOR;
                }
            }

            // By this time throw a Patron Not Found Error
            throw new PatronNotFoundError(AnsiColorCodes.RED_COLOR + "No patron could be found of id: " + id + AnsiColorCodes.DEFAULT_COLOR);
        }

        catch (NumberFormatException e)
        {
            System.out.println(AnsiColorCodes.RED_COLOR + "Please provide a number for the overdue fine " + id + AnsiColorCodes.DEFAULT_COLOR);
        }

        catch (ValidationError | PatronNotFoundError e)
        {
            System.out.println(e.getMessage());
        }

        return "";
    }

    public String deletePatronById(String id)
    {
        try
        {
            // Validate the given id
            if (!PatronValidator.isValidId(id))
            {
                throw new ValidationError(AnsiColorCodes.RED_COLOR + "The given Patron id: " + id + " is invalid" + AnsiColorCodes.DEFAULT_COLOR);
            }

            // Loop through all patrons
            for (Patron patron : patrons)
            {
                // If the patron's id is equal to the given one, remove that patron
                if (patron.getId().equals(id))
                {
                    this.patrons.remove(patron);
                    return AnsiColorCodes.GREEN_COLOR + "The patron of id: " + id + " was successfully removed from the system" + AnsiColorCodes.DEFAULT_COLOR;
                }
            }

            // By this time throw a Patron Not Found Error
            throw new PatronNotFoundError(AnsiColorCodes.RED_COLOR + "No patron could be found of id: " + id + AnsiColorCodes.DEFAULT_COLOR);
        }

        catch (PatronNotFoundError | ValidationError e)
        {
            System.out.println(e.getMessage());
        }

        return "";
    }

    /* Grab the current list of registered patrons and export them to a file */
    public String exportCurrentPatronsList(String fileName)
    {
        // Call the function to export the patrons
        return lmsFileHandler.exportPatronsToFile(fileName, this.patrons);
    }
}
