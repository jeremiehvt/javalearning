package com.exemple.demo;

import com.exemple.demo.exceptions.RationalException;
import org.junit.jupiter.api.*;
import com.exemple.demo.Rational;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RationalTest {

    @BeforeAll
    public static void beforeTest() throws Exception{
        System.out.println("before all test");
    }

    @AfterAll
    public static void afterTesting() throws Exception {
        System.out.println("after all test");
    }

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("before each test");
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("after each test");
    }

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

    @Test
//    @Timeout(value=10, unit=TimeUnit.MILLISECONDS)
    public void testBadDenominator() {
        assertThrows( RationalException.class, () -> {
            new Rational( 1, 0 );
        });
    }
}
