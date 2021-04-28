package com.exemple.demo.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Todo faire une classe dexception
public class TestException {
    public static void calculEntier() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader( System.in )
            );
            System.out.print( "Veuillez saisir un entier : " );
            String strValue =  reader.readLine();
            int value = Integer.parseInt( strValue );
            System.out.println( "Value == " + value );
        }catch( NumberFormatException exception ) {
            System.out.println( "La chaîne ne correspond pas à un entier" );
        } catch( Exception exception ) {
            System.out.println( "une exception quelconque" );
        }
    }
    public static void method3() {
        System.out.println( "BEGIN method3" );
        int divisor = (int) (Math.random() * 3);
        int value = 1 / divisor;
        System.out.println( "Value == " + value );
        System.out.println( "END method3" );
    }

    public static void method2() {
        System.out.println( "BEGIN method2" );
        method3();
        System.out.println( "END method2" );
    }

    public static void method1() {
        System.out.println( "BEGIN method1" );
        method2();
        System.out.println( "END method1" );
    }

    public static void mainMethod() {
        method1();
        calculEntier();
    }
}
