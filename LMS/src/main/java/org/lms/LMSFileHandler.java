package org.lms;

/* File meant to deal with all the required I/O Operations */

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LMSFileHandler
{
    // Import patrons from an external text file
    public List<Patron> importPatronsFromFile(String fileName) throws Exception
    {
        // Initialize the patrons list
        List<Patron> patrons = new ArrayList<Patron>();

        // Debug FileNotFound Error - File was in wrong directory
        //System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        // Read the file line-by-line
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            // Declare each line to be read
            String line;

            // Loop through each line of the file
            while ((line = reader.readLine()) != null)
            {
                // Gather data of each patron separated by dashes
                String[] patronData = line.split("-");

                // Only add patrons if all the details required are present
                if (patronData.length == 4)
                {
                    // Initialize a new Patron
                    try
                    {
                        Patron patron = new Patron(
                                patronData[0],
                                patronData[1],
                                patronData[2],
                                Double.parseDouble(patronData[3])
                        );

                        // Validate each patron
                        PatronValidator.validatePatron(patron);

                        // Add patron to the list
                        patrons.add(patron);
                    }

                    catch (NumberFormatException e)
                    {
                        throw new NumberFormatException(AnsiColorCodes.RED_COLOR + "Please provide a number for the overdue fine" + AnsiColorCodes.DEFAULT_COLOR);
                    }

                    catch (ValidationError e)
                    {
                        throw new ValidationError(AnsiColorCodes.RED_COLOR + e.getMessage() + AnsiColorCodes.DEFAULT_COLOR);
                    }
                }

                else if (patronData.length < 4)
                {
                    throw new Exception(AnsiColorCodes.RED_COLOR + "\nInsufficient Patron Data\n" + AnsiColorCodes.DEFAULT_COLOR);
                }

                else
                {
                    throw new Exception(AnsiColorCodes.RED_COLOR + "\nRedundant Patron Data\n" + AnsiColorCodes.DEFAULT_COLOR);
                }
            }

        }

        catch (FileNotFoundException e)
        {
            // Troubleshoot to find out why FileNotFoundError was being raised
            //e.printStackTrace();
            throw new FileNotFoundException(AnsiColorCodes.RED_COLOR + "\nFile Not Found: " + fileName + "\n" + AnsiColorCodes.DEFAULT_COLOR);
        }

        catch (IOException e)
        {
            throw new IOException(AnsiColorCodes.RED_COLOR + "\nAn error happened while reading the text file" + AnsiColorCodes.DEFAULT_COLOR);
        }

        return patrons;
    }

    public String exportPatronsToFile(String fileName, List<Patron> patrons)
    {
        // Get today's formatted day and time
        String todayCurrentTimeDate = fileExportationDateTime();

        // Produce compound file name
        String compoundFileName = fileName + "-" + todayCurrentTimeDate + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(compoundFileName)))
        {
            // For each patron in the list, write a new line with patron attributes separated by dashes
            for (Patron patron : patrons)
            {
                writer.write(patron.getId() + "-" + patron.getName() + "-" + patron.getAddress() + "-" + patron.getOverdueFine());
                writer.newLine();
            }
        }

        catch (IOException e)
        {
            System.out.println(AnsiColorCodes.RED_COLOR + "An error happened while exporting patrons" + AnsiColorCodes.DEFAULT_COLOR);
            System.out.println(AnsiColorCodes.RED_COLOR + "Try again later" + AnsiColorCodes.DEFAULT_COLOR);
            return "";
        }

        return AnsiColorCodes.GREEN_COLOR + "All patron data registered in the system was successfully exported to the " + compoundFileName + " file" + AnsiColorCodes.DEFAULT_COLOR;
    }

    // Returns today's date time to attach it to the exported file name
    private String fileExportationDateTime()
    {
        // Get current data and time
        LocalDateTime now = LocalDateTime.now();

        // Define the formatter with a patter suitable for filenames
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy-hh-mm-a");

        // Format the current date and time
        return now.format(formatter);
    }
}
