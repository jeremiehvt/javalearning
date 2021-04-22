package com.exemple.demo.reflexion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Date;

public class TestReflection extends Base implements Runnable, Serializable {
	private int aNumericValue;
    protected String aString;
    double aPrice;      // Visibilité "package" par défaut
    public Date aDate;
	public static void testReflection() {
		// Récupération des méta-données à partir de la classe.
        Class<?> metadata = TestReflection.class;
        
        // Récupération des méta-données à partir d'une instance.
        TestReflection object = new TestReflection();
        Class<? extends TestReflection> metadata2 = object.getClass();
        
        // Dans les deux cas, nous avons les mêmes informations
        System.out.println( metadata == metadata2 );
        
        Field [] attributes = metadata.getFields();
        Field [] dAttributes = metadata.getDeclaredFields();
        // On affiche des informations sur ces attributs publics.
        for( Field attribute : attributes ) {
            System.out.printf( 
                    "Attribute %s of type %s (isPrimitive: %b)\n",
                    attribute.getName(), 
                    attribute.getType().getName(),
                    attribute.getType().isPrimitive()
            );
        }
        for( Field attribute : dAttributes ) {
        	Class<?> atbMetadata = attribute.getType();
        	System.out.printf( 
                    "\t%-10s %s of type %s (isPrimitive: %b)\n",
                    Modifier.toString( attribute.getModifiers() ),
                    attribute.getName(), 
                    atbMetadata.getName(),
                    atbMetadata.isPrimitive()
            );
        }
        
        // On remonte sur le type parent, s'il y en a un.
//        metadata = metadata.getSuperclass();
//        if ( metadata == null );
        
        Method [] methods  = metadata.getDeclaredMethods();
        for( Method method : methods ) {
            System.out.printf( 
                    " %s method %s \n",
                    Modifier.toString(method.getModifiers()),
                    method.getName()
            );
        }
        
        Constructor<?> [] constructors = metadata.getDeclaredConstructors();
        for( Constructor<?> constructor : constructors) {
        	System.out.println( constructor.toString() );
        }
        
        StringBuilder builder = new StringBuilder(metadata.getName());
        while(true) {
        	metadata = metadata.getSuperclass();
        	if ( metadata == null ) break;
        	// On insert en tête du StringBuilder le nouveau niveau d'héritage
            builder.insert( 0, metadata.getName() + " -> " );
        }
        
        // On affiche la hiérarchie d'héritage
        System.out.println( builder.toString() );
        
        metadata = object.getClass();
        Class<?> [] implementedInterfaces = metadata.getInterfaces();
        for(Class<?> interfaceMetadata : implementedInterfaces ) {
        	System.out.println(interfaceMetadata.getName());
        }
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
