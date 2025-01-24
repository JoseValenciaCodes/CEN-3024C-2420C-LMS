package org.lms;

/* Error meant to be raised when a patron is looked for but could not be found because it was not registered in the system */

public class PatronNotFoundError extends RuntimeException
{
    public PatronNotFoundError(String message)
    {
        super(message);
    }
}
