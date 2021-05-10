package com.exemple.demo;

import com.exemple.demo.exceptions.RationalException;

public class Rational {
    private int numerator;
    private int denominator;

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Rational() {
        this.denominator = 1;
        this.numerator = 0;
    }

    public Rational( int numerator ) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Rational(int numerator, int denominator) throws RationalException {
        this.setNumerator(numerator);
        this.setDenominator(denominator);
        this.simplify();
    }

    public Rational( double value ) {
        this.denominator = 1;
        // On teste s'il y a des chiffres après la virgule
        while( value != (int) value ) {
            // on multiplie chacune des deux parties par 10
            value *= 10;
            this.denominator *= 10;
        }
        // value devient notre numérateur
        this.numerator = (int) value;
        this.simplify();
    }

    private void setDenominator(int denominator) throws RationalException {
        if ( denominator == 0 ) {
            throw new RationalException();
        }
        this.denominator = denominator;
    }

    private void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    // Quelques méthodes de la classe

    public Rational add( Rational r2 ) {
        Rational result = new Rational();
        result.numerator = this.numerator * r2.denominator + this.denominator * r2.numerator;
        result.denominator = this.denominator * r2.denominator;
        return result;
    }

    public boolean eq( Rational r2 ) {
        return this.numerator * r2.denominator == this.denominator * r2.numerator;
    }

    // Pour les détails sur l'algorithme d'Euclide pour le calcul du PGCD
    // https://fr.wikipedia.org/wiki/Algorithme_d%27Euclide#Description_de_l'algorithme
    public void simplify() {
        int a;
        int b;

        if ( this.numerator > this.denominator ) {
            a = this.numerator;
            b = this.denominator;
        } else {
            a = this.denominator;
            b = this.numerator;
        }

        int rest;
        while( (rest = a % b) != 0 ) {
            a = b;
            b = rest;
        }

        this.numerator /= b;
        this.denominator /= b;
    }


    @Override
    public String toString() {
        return String.format( "[%d/%d]", this.numerator, this.denominator );
    }
}
