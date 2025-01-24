package org.lms;

/* Meant to be raised when a Patron's attribute is invalid */

public class ValidationError extends RuntimeException
{
    public ValidationError(String message)
    {
        super(message);
    }
}
