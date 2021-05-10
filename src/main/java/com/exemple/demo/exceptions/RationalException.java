package com.exemple.demo.exceptions;

public class RationalException extends Exception{

    private static final long serialVersionUID = 6677487610288558193L;

    public RationalException() {
        this( "Denominator cannot be zero" );
    }

    public RationalException( String message ) {
        super( message );
    }

    public RationalException( String message, Throwable cause ) {
        super( message, cause );
    }
}
