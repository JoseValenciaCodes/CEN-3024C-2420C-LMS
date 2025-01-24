package org.lms;

public class PatronAlreadyExistsError extends RuntimeException
{
    public PatronAlreadyExistsError(String message)
    {
        super(message);
    }
}
