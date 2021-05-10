package com.exemple.demo;

import com.exemple.demo.exceptions.RationalException;
import org.junit.jupiter.api.Test;
import com.exemple.demo.Rational;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RationalTest {
    @Test
    public void testAddition() throws RationalException {
        Rational r1 = new Rational(1,3);
        Rational r2 = new Rational(2,1);
        Rational result = r1.add(r2);

        assertEquals(7,result.getNumerator());
        assertEquals(3,result.getDenominator());
    }

    @Test
    public void testSimplify() throws RationalException {

        Rational r = new Rational( 5*7*11*13, 11*13*17 );
        r.simplify();

        assertEquals( 35, r.getNumerator() );
        assertEquals( 17, r.getDenominator() );
    }
}
