package org.lms;

public class PatronValidator
{
    public static boolean isValidId(String id)
    {
        return id.matches("[0-9]+") && id.length() == 7;
    }

    public static boolean isValidName(String name)
    {
        return !name.isEmpty() && name.length() <= 15;
    }

    public static boolean isValidAddress(String address)
    {
        return !address.isEmpty();
    }

    public static boolean isValidOverdueFine(double overdueFine)
    {
        return overdueFine < 250;
    }

    public static void validatePatron(Patron patron) throws ValidationError
    {
        if (!PatronValidator.isValidId(patron.getId()))
        {
            throw new ValidationError("Remember the patron ID needs to be a 7-digit PIN");
        }

        if (!PatronValidator.isValidName(patron.getName()))
        {
            throw new ValidationError("Remember the name must contain between 1 and 15 characters");
        }

        if (!PatronValidator.isValidAddress(patron.getAddress()))
        {
            throw new ValidationError("Remember to provide an address for your patron");
        }

        if (!PatronValidator.isValidOverdueFine(patron.getOverdueFine()))
        {
            throw new ValidationError("Remember the overdue Fine cannot exceed 250 dollars");
        }
    }

    public static void validatePatronUpdate(Patron patron) throws ValidationError
    {
        // Validate each field for Patron update
        if (!PatronValidator.isValidName(patron.getName()))
        {
            throw new ValidationError("Remember the name must contain between 1 and 15 characters");
        }

        if (!PatronValidator.isValidAddress(patron.getAddress()))
        {
            throw new ValidationError("Remember to provide a new address");
        }

        if (!PatronValidator.isValidOverdueFine(patron.getOverdueFine()))
        {
            throw new ValidationError("Remember that the new overdue fine cannot exceed 250 dollars");
        }

    }
}
