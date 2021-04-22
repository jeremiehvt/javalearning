package com.exemple.serialization;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SerializationEngine {
	
	/**
	 * 
	 * @param object
	 * @param writer
	 * @throws Exception
	 */
	public static void writeObject(Object object, PrintWriter writer) throws Exception{
		Class<?> metadata = object.getClass();
		if (
				metadata == Byte.class ||
				metadata == Integer.class ||
				metadata == Long.class ||
				metadata == Short.class ||
				metadata == Double.class ||
				metadata == Float.class ||
				metadata == Boolean.class
		) {
			writer.print( "" + object );
		} else if(metadata == String.class || metadata == Character.class) {
			writer.print("\""+object+"\"");
		} else if ( metadata.isArray() || object instanceof List ) {
	        // --- On gère les tableaux et les collections ---
	        @SuppressWarnings("rawtypes") 
	        List collection = object instanceof List 
	                ? (List) object : ArrayUtils.toObjectList( object ) ;
	        int size = collection.size();
	        int i = 0;

	        writer.print( "[" );
	        for( Object value : collection ) {
	            writeObject( value, writer );
	            if ( i++ < size - 1 ) writer.print( "," );
	        }
	        writer.print( "]" );
	    } else {
	    	writer.print("{");
			Field [] fields = metadata.getDeclaredFields();
			
			for(int i=0; i<fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				writer.write(String.format("\"%s\": ", field.getName()));
				writeObject(field.get(object), writer);
				if (i < fields.length-1) writer.write(", ");
			}
			writer.print("}");
		}
    	
	}
	
	/**
	 * 
	 * @param metadata
	 * @param scanner
	 * @return
	 * @throws Exception
	 */
	public static Object readObject(Class<?> metadata, Scanner scanner) throws Exception {
		if ( metadata == Byte.class || metadata == byte.class ) {
	        return Byte.parseByte( scanner.findInLine( "[0-9]+" ) );
	    } else if ( metadata == Short.class || metadata == short.class  ) {
	        return Short.parseShort( scanner.findInLine( "[0-9]+" ) );
	    } else if ( metadata == Integer.class || metadata == int.class ) {
	        return Integer.parseInt( scanner.findInLine( "[0-9]+" ) );
	    } else if ( metadata == Long.class || metadata == long.class  ) {
	        return Long.parseLong( scanner.findInLine( "[0-9]+" ) );
	    } else if ( metadata == Float.class || metadata == float.class  ) {
	        return Float.parseFloat( scanner.findInLine( "[0-9]+(\\.[0-9]+)?" ) );
	    } else if ( metadata == Double.class || metadata == double.class ) {
	        return Double.parseDouble( scanner.findInLine( "[0-9]+(\\.[0-9]+)?" ) );
	    } else if ( metadata == Boolean.class || metadata == boolean.class  ) {
	        return Boolean.parseBoolean( scanner.findInLine( "true|false" ) );
	    } else if ( metadata == String.class || metadata == Character.class || metadata == char.class ) {
	        String value = scanner.findInLine( "\".*?\"" );
	        return value.substring( 1, value.length()-1 );
	    } else if ( metadata.isArray() || List.class.isAssignableFrom( metadata ) ) {            
	        // --- Désérialisation de tableaux ou de collections ---
	        throw new Exception( "Not actually implemented" );
	    } else {
	        // --- Désérialisation d'un objet ---
	        Object object = metadata.newInstance();
	        scanner.findInLine( "\\{\\s*" );
	        while( ! scanner.hasNext( "\\s*\\}" ) ) {
	            String attributeName = (String) readObject( String.class, scanner );
	            scanner.findInLine( ":\\s*" );                
	            Field field = metadata.getDeclaredField( attributeName );
	            field.setAccessible( true );
	            Object value = readObject( field.getType(), scanner );
	            field.set( object, value );
	        }
	        scanner.findInLine( "\\s*\\}" );
	        return object;
	    }
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T readObject(Class<?> metadata, Reader reader) throws Exception {
		try(Scanner scanner = new Scanner(reader)) {
			scanner.useLocale(Locale.US);
			return (T) readObject(metadata, scanner);
		}
	}
}
