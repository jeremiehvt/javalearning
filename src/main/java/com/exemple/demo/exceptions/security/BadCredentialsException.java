package com.exemple.demo.exceptions.security;

public class BadCredentialsException extends SecurityManagerException {
    private static final long serialVersionUID = 8820177359207700634L;

    /**
     * Class constructor.
     *
     * @param message	The exception message
     */
    public BadCredentialsException( String message ) {
        super( message );
    }

    /**
     * Class constructor.
     *
     * @param message			The exception message.
     * @param innestException	The innest exception.
     */
    public BadCredentialsException( String message, Throwable innestException ) {
        super( message, innestException );
    }
}
