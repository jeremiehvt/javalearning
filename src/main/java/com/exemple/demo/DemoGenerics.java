package com.exemple.demo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DemoGenerics {
	// La méthode générique : elle rapatrie les éléments d'un tableau dans une collection.
    public static <T> void fromArrayToCollection( T[] array, Collection<T> collection ) {
        for ( T element : array ) {
            collection.add(element);
        }
    }
    
    private static <T> void fromArrayToMap(T[] array, Map<String, Integer> element) {
    	element.putAll(element);
    }
    
    // On teste notre méthode générique avec des chaînes de caractères.
    public static void tryWithStrings() {
        String [] messages = {
                "Hello", "World", "End"
        };
        
        List<String> messageList = new ArrayList<>();
        fromArrayToCollection( messages, messageList );
        
        for( String message : messageList ) {
            System.out.println( message );
        }
    }

    // On teste notre méthode générique avec des nombres flottants.
    public static void tryWithDoubles() {
        Double [] values = {
                20.5, 78.2, 13.8, 47.4, 63.1
        };
        
        List<Double> valueList = new ArrayList<>();
        fromArrayToCollection( values, valueList );
        
        for( double message : valueList ) {
            System.out.println( message );
        }
    }
    
    public static void tryWithMap() {
    	Map<String,Integer> mapTest = new HashMap<>();
    	Integer [] values = {1,2,3};
    	fromArrayToMap( values, mapTest );
    	System.out.println(mapTest.toString());
    	
    	for(Entry<String, Integer> mapElement : mapTest.entrySet() ) {
            System.out.println( mapElement.getKey());
            System.out.println( mapElement.getValue().toString());
        }
    	
    }

	
}
