package com.exemple.demo.annotations;

import java.lang.reflect.Method;

import com.exemple.demo.annotations.TestingAnnotation.NoExceptionExpected;

public class TestMethodAnnotation {
	@TestingAnnotation
    public void firstMethod() { System.out.println( "Is a test method" ); }

    public void secondMethod() { System.out.println( "Is not a test method" ); }

    @TestingAnnotation( timeout = 5 )
    public void thirdMethod() { System.out.println( "Is a test method" ); }

    @TestingAnnotation( expected = ArithmeticException.class, timeout = 1 )
    public void fourthMethod() { System.out.println( "Is a test method" ); }

    
    public static void myAnnotation() throws Exception {

        // On récupère les méta-données de la classe Demo
        Class<TestMethodAnnotation> metadata = TestMethodAnnotation.class;
        
        // On parcourt toutes les méthodes de la classe Demo, y compris le main.
        for ( Method method : metadata.getDeclaredMethods() ) {
            
            // On vérifie la présence de notre annotation
        	TestingAnnotation annotation = method.getAnnotation( TestingAnnotation.class );
            if ( annotation == null ) continue;
            
            // On affiche le nom de la méthode ainsi que les attributs de l'annotation.
            System.out.printf( "%s - %s - max duration = %d\n",
                    method.getName(),
                    annotation.expected() != NoExceptionExpected.class ? 
                            annotation.expected().getName() : "no exception expected",
                    annotation.timeout()
            );
        }
        
    }
}
