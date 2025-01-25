package org.lms.tests;

import org.lms.LMS;
import org.lms.LMSFileHandler;
import org.lms.PatronValidator;

/* Rudimentary file meant to run unit tests */

public class UnitTests
{
    static class PatronValidatorUnitTests
    {
        public static boolean recognizeValidId()
        {
            return PatronValidator.isValidId("1234567");
        }

        public static boolean recognizeNoDigitsId()
        {
            return PatronValidator.isValidId("noDigitId");
        }

        public static boolean recognizeValidName()
        {
            return PatronValidator.isValidName("Jose");
        }

        public static boolean recognizeInvalidEmptyAddress()
        {
            return PatronValidator.isValidAddress("");
        }

        public static boolean recognizeValidFine()
        {
            return PatronValidator.isValidOverdueFine(145.87);
        }

        public static boolean recognizeInvalidFine()
        {
            return PatronValidator.isValidOverdueFine(560.34);
        }
    }

    static class LMSFileHandlerUnitTests
    {
        public static boolean successFullyImportFile()
        {
            try
            {
                LMSFileHandler lmsFileHandler = new LMSFileHandler();
                lmsFileHandler.importPatronsFromFile("patrons.txt");
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        public static boolean errorFileImport()
        {
            try
            {
                LMSFileHandler lmsFileHandler = new LMSFileHandler();
                lmsFileHandler.importPatronsFromFile("");

                return false;
            }

            catch (Exception e)
            {
                return true;
            }
        }

        public static boolean successFileExport()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.addPatron("1234567", "Jose Brache", "Sw 50th Place, Cooper City, FL 33330", String.valueOf(45.3));
                lms.exportCurrentPatronsList("exported-patrons-test");
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }
    }

    static class LMSUnitTests
    {
        static boolean checkPatronExists()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.getPatronById("1245789");
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        static  boolean checkForNoPatron()
        {
            try
            {
                LMS lms = new LMS();
                lms.getPatronById("1245789");
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        static boolean addPatronSuccess()
        {
            try
            {
                LMS lms = new LMS();
                lms.addPatron(
                        "1234567",
                        "Jose B",
                        "12206 51th Place, Cooper City, FL 33330",
                        String.valueOf(67.54)
                );

                lms.getPatronById("1245789");
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        static boolean addPatronError()
        {
            try
            {
                LMS lms = new LMS();
                lms.addPatron(
                        "noidsad",
                        "",
                        "1",
                        String.valueOf(67.54)
                );
                return false;
            }

            catch (Exception e)
            {
                return true;
            }
        }

        static boolean importPatronsSuccess()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        static boolean importPatronsError()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("");
                return false;
            }

            catch (Exception e)
            {
                return true;
            }
        }

        static boolean getPatronsSuccess()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.getAllRegisteredPatrons();
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        static boolean getPatronSuccess()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.getPatronById("1245789");
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        static boolean getPatronError()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.getPatronById("");
                return false;
            }

            catch (Exception e)
            {
                return true;
            }
        }

        static boolean updatePatronsSuccess()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.updatePatronById("1245789", "Camilo Ortiz", "12253 52th Place, Cooper City, 33330", String.valueOf(45.67));
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        static boolean updatePatronsError()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.updatePatronById("", "Camilo Ortiz", "12253 52th Place, Cooper City, 33330", String.valueOf(45.67));
                return false;
            }

            catch (Exception e)
            {
                return true;
            }
        }

        static boolean deletePatronsSuccess()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.deletePatronById("1245789");
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        static boolean deletePatronsError()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.deletePatronById("");
                return false;
            }

            catch (Exception e)
            {
                return true;
            }
        }

        static boolean exportPatronsSuccess()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.exportCurrentPatronsList("patrons-exported-test");
                return true;
            }

            catch (Exception e)
            {
                return false;
            }
        }

        static boolean exportPatronsError()
        {
            try
            {
                LMS lms = new LMS();
                lms.addImportedPatrons("patrons.txt");
                lms.exportCurrentPatronsList("");
                return false;
            }

            catch (Exception e)
            {
                return true;
            }
        }

    }

    public static void main(String[] args)
    {
        // Run PatronValidatorUnitTests
        System.out.println("\n\nPatron Validation Unit Tests");
        System.out.println("\n\n Make sure validation for validating patrons works as expected\n");
        System.out.println("The id is a valid ID? " + PatronValidatorUnitTests.recognizeValidId());
        System.out.println("Can there be an ID with no digits? " + PatronValidatorUnitTests.recognizeNoDigitsId());
        System.out.println("Is Jose a valid name? " + PatronValidatorUnitTests.recognizeValidName());
        System.out.println("Can there be an empty address?" + PatronValidatorUnitTests.recognizeInvalidEmptyAddress());
        System.out.println("Is 145.87 a valid overdue fine?" + PatronValidatorUnitTests.recognizeValidFine());
        System.out.println("Is 560.34 a valid overdue fine?" + PatronValidatorUnitTests.recognizeInvalidFine());

        // Run LMSFileHandlerUnitTests
        System.out.println("\n\nLibrary Management System File Handling Unit Tests");
        System.out.println("\n\n Make sure validation file operations work as expected\n");
        System.out.println("Could the patrons.txt file present in the working directory be imported: " + LMSFileHandlerUnitTests.successFullyImportFile());
        System.out.println("Could patrons be imported from a file without specifying a file name: " + LMSFileHandlerUnitTests.errorFileImport());
        System.out.println("Can patrons be successfully exported to a text file: " + LMSFileHandlerUnitTests.successFileExport());

        // Run LMSUnitTests
        System.out.println("\n\nLibrary Management System Unit Tests");
        System.out.println("\n\nMake sure LMS operations work as expected\n");
        System.out.println("If the patrons.txt file is imported will there be a patron of id '1245789'? " + LMSUnitTests.checkPatronExists());
        System.out.println("If no file is imported, will there be a patron of id '1245789'? " +LMSUnitTests.checkForNoPatron());
        System.out.println("Can a new patron be added to the system with all valid fields? " + LMSUnitTests.addPatronSuccess());
        System.out.println("Can a new patron be added to the system if some fields are empty? " + LMSUnitTests.addPatronError());
        System.out.println("Can patrons be imported if a file name is in the expected directory? " + LMSUnitTests.importPatronsSuccess());
        System.out.println("Can patrons be imported if no file name is given? " + LMSUnitTests.importPatronsError());
        System.out.println("Can data of all patrons be read? " + LMSUnitTests.getPatronsSuccess());
        System.out.println("Can we read the data of a patron by its ID? " + LMSUnitTests.getPatronSuccess());
        System.out.println("Can we read the data of a patron without providing ID? " + LMSUnitTests.getPatronError());
        System.out.println("Can we update a patron by providing a valid ID? " + LMSUnitTests.updatePatronsSuccess());
        System.out.println("Can we update a patron without providing an ID? " + LMSUnitTests.updatePatronsError());
        System.out.println("Can we delete a patron by providing a valid ID? " + LMSUnitTests.deletePatronsSuccess());
        System.out.println("Can we delete a patron by providing no ID? " + LMSUnitTests.importPatronsError());
        System.out.println("Can we exports patrons to a text file providing a file name? " + LMSUnitTests.exportPatronsSuccess());
        System.out.println("Can we export patrons to a text file by providing no file name?" + LMSUnitTests.exportPatronsError());
    }
}
