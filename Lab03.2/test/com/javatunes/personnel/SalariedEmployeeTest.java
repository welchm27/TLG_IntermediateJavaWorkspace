package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class SalariedEmployeeTest {
    private SalariedEmployee emp;
    private SalariedEmployee emp2;

    @Before
    public void setUp() {
        emp = new SalariedEmployee("Adrienne", Date.valueOf("2005-09-29"), 3500.0);
        emp2 = new SalariedEmployee("Adrienne", Date.valueOf("2005-09-29"), 3500.0);
    }

    @Test
    public void equals_shouldReturnFalse_differentName_sameHireDate_sameSalary() {
        emp2.setName("Alex");
        assertNotEquals(emp, emp2);
    }

    @Test
    public void equals_shouldReturnFalse_sameName_differentHireDate_sameSalary() {
        emp2.setHireDate(Date.valueOf("2005-01-01"));
        assertNotEquals(emp, emp2);
    }

    @Test
    public void equals_shouldReturnFalse_sameName_sameHireDate_differentSalary() {
        emp2.setSalary(3000.0);
        assertNotEquals(emp, emp2);
    }

    @Test
    public void equals_shouldReturnTrue_allPropertiesSame() {
        assertEquals(emp, emp2);
    }

    @Test
    public void testPay() {
        System.out.println("testPay");
        assertEquals(3500.0, emp.pay(), .001);
    }

    @Test
    public void testPayTaxes() {
        System.out.println("testPayTaxes");
        assertEquals(1050.0, emp.payTaxes(), .001);
    }

}   // end of class