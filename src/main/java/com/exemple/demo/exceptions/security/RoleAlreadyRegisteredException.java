package com.exemple.demo.exceptions.security;

public class RoleAlreadyRegisteredException extends SecurityManagerException {
    private static final long serialVersionUID = 7892564530043430372L;

    /**
     * Class constructor.
     *
     * @param message The specific exception message to display.
     */
    public RoleAlreadyRegisteredException( String message ) {
        super( message );
    }

    /**
     * Class constructor.
     *
     * @param message			The specific exception message to display.
     * @param innestException	The throwable that has thrown this exception.
     */
    public RoleAlreadyRegisteredException( String message, Throwable innestException ) {
        super( message, innestException );
    }
}
