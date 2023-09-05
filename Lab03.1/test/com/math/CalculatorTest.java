/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.math;

import static org.junit.Assert.*;

import org.junit.*;

public class CalculatorTest {
    // business object(s) under test - called a "fixture"
    private Calculator calc;

    @BeforeClass
    public static void initializeEntireTestRun() {  // only fires once at the beginning of the entire test run
        System.out.println("initializeEntireTestRun");
    }

    @AfterClass
    public static void finalizeEntireTestRun() {
        System.out.println("finalizeEntireTestRun");
    }

    @Before
    public void setUp() {
        System.out.println("setUp");
        calc = new Calculator();
    }

    @After
    public void cleanUp() {  // only needed if what you create in @before needs to be cleaned up in any way
        System.out.println("cleanUp");
    }

    @Test
    public void testAdd() {
        System.out.println("testAdd");
        assertEquals(5, calc.add(1, 4));  // expected, actual
    }

    @Test
    public void testIsEven() {
        System.out.println("testIsEven");
        assertTrue(calc.isEven(10));
        assertFalse(calc.isEven(11));
    }

    @Test
    public void testDivide() {
        System.out.println("testDivide");
        assertEquals(2.5, calc.divide(5, 2), 001);
    }

}   // end of class