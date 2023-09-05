package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class HourlyEmployeeTest {
    private HourlyEmployee emp;

    @Before
    public void setUp() {
        emp = new HourlyEmployee("Orion", Date.valueOf("2013-01-08"),15.0,30.0);
    }

    @Test
    public void testPay() {
        assertEquals(450.0, emp.pay(), .001);
    }

    @Test
    public void testPayTaxes() {
        assertEquals(112.5, emp.payTaxes(), .001);
    }

}   // end of class