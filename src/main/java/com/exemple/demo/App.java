package com.exemple.demo;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;


import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.exemple.demo.GenericBox;
import com.exemple.demo.DemoGenerics;
import com.exemple.demo.reflexion.*;
import com.exemple.serialization.*;
import com.exemple.demo.annotations.*;

/**
 * 
 * @author jeremiehvt
 *
 */
@TestClass(category="demo")
public class App 
{
	private static Pattern datePattern = Pattern.compile("^(\\d{2}|\\d{4})-\\d{2}-\\d{2}$");
	private static Pattern emailPattern = Pattern.compile("^[\\w_.-]+@.+\\.[a-z]{2,}$");
	private static Pattern replacePattern = Pattern.compile("[-]");
	private static Pattern safePattern = Pattern.compile("<script[^>]*?>.*?</script>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	private static Pattern boldPattern = Pattern.compile("(html)", Pattern.CASE_INSENSITIVE);
	
	public static boolean isValidDate(String date) {
		return datePattern.matcher(date).matches();
	}
	
	public static boolean isValidEmail(String email) {
		return emailPattern.matcher(email).matches();
	}
	
	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
    public static void main( String[] args ) throws Exception
    {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append(" je ");
    	stringBuilder.append("suis un");
    	stringBuilder.append(" dev");
    	System.out.println(stringBuilder.toString());
        
    	System.out.println(isValidEmail("jeremiehvt@gmail.com"));
    	System.out.println(isValidEmail("jeremiehvt@gmail"));
    	System.out.println(isValidEmail("@@@.@"));
    	
    	System.out.println(isValidDate("2021-02-03"));
    	System.out.println(replacePattern.matcher("2021-02-03").replaceAll(" "));
    	
    	String requestParameter = "Begin\n"
                + "<Script type='text/javascript'>code</Script>\n"
                + "Middle\n"
                + "<script type='text/javascript'>\n"
                + "    window.alert( 'ok' );\n"
                + "</script>\n"
                + "End\n";
    	
    	System.out.println(safePattern.matcher(requestParameter).replaceAll(""));
    	
    	String message = "this is an html content";
    	System.out.println(boldPattern.matcher(message).replaceAll("<b>$1</b>"));
    	
    	Pattern htmlPattern = Pattern.compile( "<(.+?)>", Pattern.DOTALL );
        String htmlContent = "<html><head><title>Titre</title></head><body><h1>Titre visuel</h1></body></html>";
      
//        Matcher htmlMatcher = htmlPattern.matcher( htmlContent );
//        while ( htmlMatcher.find() ) {
//            System.out.println( "Expression correspondante au motif: " + htmlMatcher.group( 0 ) );
//            System.out.println( "Nom du tag: " + htmlMatcher.group( 1 ) );
//        }
        
        
        String data = "<data>\n"
                + "    <subTag attr1='value' attr2='another value' />\n"
                + "    <subTag2 attr3='value' attr4='another value' />\n"
                + "</data>\n";
        
        String tagExtractionRegExp = "<([a-zA-Z_].*?)\\s*/?>";  
        Pattern tagPattern = Pattern.compile( tagExtractionRegExp, Pattern.DOTALL );
        
        String tagNameExtractionRegExp = "([\\w_:-]+)";
        Pattern tagNamePattern = Pattern.compile( tagNameExtractionRegExp, Pattern.DOTALL );
        
        String attributeExtractionRegExp = "([\\w_:-]+)=['\"]([a-zA-Z0-9 ]*)['\"]";
        Pattern attributePattern = Pattern.compile( attributeExtractionRegExp, Pattern.DOTALL );
        
        Matcher htmlMatcher = tagPattern.matcher( data );
        while ( htmlMatcher.find() ) {
            // Récupération du contenu du tag
            String tagContent = htmlMatcher.group( 1 );
            
            // Affichage du nom du tag
            Matcher tagNameMatcher = tagNamePattern.matcher( tagContent );
            if ( tagNameMatcher.find() ) {
                System.out.println( tagNameMatcher.group( 1 ) );
            }
            
            // Affichage des attributs du tag courant
            Matcher attributeMatcher = attributePattern.matcher( tagContent );
            while( attributeMatcher.find() ) {
                System.out.printf( "    %s: %s\n", 
                                   attributeMatcher.group( 1 ),
                                   attributeMatcher.group( 2 ) );
            }
        }
        
        
//        try(Scanner scanner = new Scanner(System.in)) {
//        	scanner.useLocale(Locale.ENGLISH);
//        	scanner.useDelimiter(";");
//        	System.out.print( "Enter an IP address, a double value and an email: " );
//        	Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
//        	String ip = scanner.next(ipPattern);
//        	System.out.println( "IP address == " + ip );
//        	double value = scanner.nextDouble();
//        	System.out.println( "double == " + value );
//        	Pattern myEmailPattern = Pattern.compile("[\\w.-]+@[\\w.-]+\\.[a-z]{2,}");
//        	String email = scanner.next(myEmailPattern);
//        	System.out.println( "email == " + email );
//        }
        
        FuncInterface test = (String e) -> {System.out.println(e);};
        test.testMethod("hello i am the func interface");
        
        //peut-être pas authorisé à voir
//        FuncInterface testRef = FuncInterface::say;
        FuncInterface testRef = ReferenceTest::say;
        testRef.testMethod("hello test ref");
        
        //Test généricité
        GenericBox<String> box = new GenericBox<String>("hello");
        System.out.println(box.getElement());
        box.setElement("hello world");
        System.out.println(box.getElement());
        
        DemoGenerics.tryWithDoubles();
        DemoGenerics.tryWithStrings();
        DemoGenerics.tryWithMap();
        
        TestReflection.testReflection();
        
        //========= test moteur de serialization ========= 
        String file = System.getProperty("user.dir")+"/src/resources/file.json";
        try ( PrintWriter writer = new PrintWriter( file ) ) {
            //SerializationEngine.writeObject( 3, writer );
//            SerializationEngine.writeObject( 3.1415, writer );
//            SerializationEngine.writeObject( "Hello", writer );
            
            //double [] values = { 14.2, 15.3, 16.4 };
            //SerializationEngine.writeObject( values, writer );
            
            //List<String> languages = Arrays.asList( "Java", "Python", "C#", "C++" );
            //SerializationEngine.writeObject( languages, writer );
            
//            String [] languages = { "Java", "Python", "C#", "C++" };
//            SerializationEngine.writeObject( languages, writer );
            
            Article article = new Article(1,"pain","baguette",2.50);
            SerializationEngine.writeObject( article, writer );
        }
        try ( FileReader reader = new FileReader( new File( file ) ) ) {
            //double data = SerializationEngine.readObject( Double.class, reader );
//            String dataSer = SerializationEngine.readObject( String.class, reader );
//            System.out.println( dataSer );
        	Article article = SerializationEngine.readObject( Article.class, reader );
            System.out.println( article );
        }
        //========= Fin test moteur de serialization ========= 
        
      //========= test annotaion =========
        TestMethodAnnotation.myAnnotation();
        TestMethodAnnotation.testAnnotation();
      //========= Fin test annotaion =========
    }
}
