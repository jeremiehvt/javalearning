package com.exemple.demo.exceptions.security;

public class UserAlreadyRegisteredException extends SecurityManagerException{
    private static final long serialVersionUID = 6950191280259286311L;

    /**
     * Class constructor.
     *
     * @param message The specific exception message to display.
     */
    public UserAlreadyRegisteredException( String message ) {
        super( message );
    }

    /**
     * Class constructor.
     *
     * @param message			The specific exception message to display.
     * @param innestException	The throwable that has thrown this exception.
     */
    public UserAlreadyRegisteredException( String message, Throwable innestException ) {
        super( message, innestException );
    }
}
