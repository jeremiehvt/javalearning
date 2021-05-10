package com.exemple.demo.exceptions;

import com.exemple.demo.Rational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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

    public static void dividByZero() {
        try ( Scanner scanner = new Scanner( System.in ) ) {

            System.out.print( "Veuillez saisir le numerateur : " );
            int num = Integer.parseInt( scanner.nextLine() );
            System.out.print( "Veuillez saisir le dénominateur : " );
            int den = Integer.parseInt( scanner.nextLine() );

            Rational r1 = new Rational( num, den );
            System.out.println( r1 );

        } catch( NumberFormatException exception ) {

            System.out.println( "Seules des valeurs numériques entières sont autorisée !!!" );

        } catch ( RationalException exception ) {

            System.out.println( "Svp, pas 0 pour le dénominateur !!!" );

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
        dividByZero();
    }
}
