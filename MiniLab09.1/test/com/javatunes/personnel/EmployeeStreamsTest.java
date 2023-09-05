/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.personnel;

import static org.junit.Assert.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class EmployeeStreamsTest {
    private Collection<Employee> allEmployees;

    @Before
    public void setUp() {
        allEmployees = Department.getDepartment().getEmployees();
    }

    /**
     * STARTER TASK - given to us as an example
     * TASK: find all employees with salary < 40000.0 and sort them by natural order.
     * Employee implements Comparable: natural order is id increasing.
     *
     * RESULT: Employees 2 and 15, in that order.
     */
    @Test
    public void testSalaryLessThanSortNaturalOrder() {
        List<Employee> employees = allEmployees.stream()
                .filter(emp -> emp.getSalary() < 40000.0)
                .sorted()  // with no arguments, does natural order
                .collect(Collectors.toList());

        assertEquals(2, employees.size());
        assertEquals(Long.valueOf(2),  employees.get(0).getId());
        assertEquals(Long.valueOf(15), employees.get(1).getId());
    }

    /**
     * TASK: find all employees whose name is 5 characters or less,
     * and sort them by salary increasing.
     */
    @Test
    public void findAll_nameLengthAtMost5_sortBySalary() {
        List<Employee> result = allEmployees.stream()
                .filter(emp -> emp.getName().length() <= 5)
                .sorted( (emp1, emp2) -> emp1.getSalary().compareTo(emp2.getSalary()) )
                .collect(Collectors.toList());

        dump(result);
        System.out.println();
    }

    /**
     * TASK: find all employees that make 50000+
     * and sort them by hireDate increasing (earlier first).
     */
    @Test
    public void findAll_salaryAtLeast50000_sortByHireDate() {
        List<Employee> result = allEmployees.stream()
                .filter( emp -> emp.getSalary() >= 50000.0 )
                //  .sorted( (emp1, emp2) -> emp1.getHireDate().compareTo(emp2.getHireDate()))
                //.sorted(Comparator.comparing(emp -> emp.getHireDate()))
                .sorted(comparing(emp -> emp.getHireDate()))  // able to shorten it by importing Comparators above
                .collect(Collectors.toList());

        dump (result);
        System.out.println();
    }

    /**
     * TASK: how many employees make at least 75K?
     */
    @Test
    public void count_salaryAtLeast75000() {
        long count = allEmployees.stream()
                .filter(emp -> emp.getSalary() >= 75000.0)
                .count();

        System.out.println(count);
    }

    /**
     * TASK: find the 3 highest-paid employees and sort them by name.
     */
    @Test
    public void threeHighestPaid_sortByName() {
        List<Employee> result = allEmployees.stream()
                .sorted( (emp1, emp2) -> emp2.getSalary().compareTo(emp1.getSalary()) )
                .limit(3)
                .sorted((emp1, emp2) -> emp1.getName().compareTo(emp2.getName()))
                .collect(Collectors.toList());
        dump(result);
    }

    /**
     * TASK: find all names of the employees hired in 2010 or later.
     * JUST THE NAMES, and sort them in natural order.
     */
    @Test
    public void findAllNames_hired2010Later() {
        List<String> names = allEmployees.stream()
                .filter(emp -> emp.getHireDate().getYear() >= 2010)
                .map(emp -> emp.getName())      // Stream<String> now
                .sorted()                       // passing nothing means natural order
                .collect(Collectors.toList());

        dump(names);

    }

    /**
     * TASK: pay all employees that make 50000 or less,
     * and pay them in this order: lowest-paid first, highest-paid last.
     *
     * NOTE: you are simply performing an action on them, not collecting them at the end.
     */
    @Test
    public void payAll_salaryAtMost50000_lowestPaidFirst() {
        allEmployees.stream()
                .filter(emp -> emp.getSalary() <= 50000.0)
                .sorted(Comparator.comparing(emp -> emp.getSalary()))
                .forEach(emp -> emp.pay());
    }

    /**
     * TASK: make all employees with salary 75000+ work(),
     * and do it in descending salary order, i.e., highest-paid works first, lowest-paid works last.
     */
    @Test
    public void workAll_salaryAtLeast75000_descendingSalaryOrder() {
        allEmployees.stream()
                .filter(emp -> emp.getSalary() >= 75000)
                .sorted(Comparator.comparing(Employee::getSalary).reversed()) // (emp -> emp.getSalary()) gives an error due to chaining calls to Employee
                .forEach(emp -> emp.pay());
    }

    /**
     * TASK: find all employees hired after the year 2000 that make less than 50000 dollars,
     * and give them a raise up to a clean 100K, doing so in order of least paid first.
     */
    @Test
    public void giveRaise_salaryLessThan50000_hiredAfter2000_lowestPaidFirst() {
        List<Employee> result = allEmployees.stream()
                .filter(emp -> emp.getHireDate().getYear() > 2000 && emp.getSalary() < 50000.0)
                .sorted(comparing(Employee::getSalary))
                .peek(emp -> emp.setSalary(100000.0))
                .collect(Collectors.toList());

        dump(result);
    }

    /**
     * TASK: yes or no, do we have any employees whose name starts with 'Z'?
     */
    @Test
    public void anyEmployees_nameStartWithZ() {
        boolean startsWithZ = allEmployees.stream()
                .anyMatch(emp -> emp.getName().startsWith("Z"));

        System.out.println(startsWithZ);
    }

    /**
     * TASK: yes or no, are all employees hired in 2000 or later?
     */
    @Test
    public void allEmployeesHired2000_orLater() {
        boolean laterThan2000 = allEmployees.stream()
                .allMatch(emp -> emp.getHireDate().getYear() >= 2000);

        System.out.println(laterThan2000);
    }

    /**
     * TASK: find the average salary of all employees hired in 2000 or later
     */
    @Test
    public void averageSalary_hired2000_orLater() {
        double avgSal = allEmployees.stream()
                .filter(emp -> emp.getHireDate().getYear() >= 2000)
                .collect(averagingDouble(Employee::getSalary));

        System.out.println(avgSal);
    }

    /**
     * TASK: divide the employees into two groups:
     *  - those that make at least 50K
     *  - those that don't
     * This is called a "partitioning" operation.
     * The result is always a two-row Map<Boolean,List<Employee>>
     */
    @Test
    public void twoGroups_atLeast50K_lessThan50K() {
        Map<Boolean, List<Employee>> map = allEmployees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() >= 50_000.0));

        dump(map);
    }

    /**
     * TASK: group our Employees by year of hire
     * 1999 | List<Employee> that were hired in 1999
     * 2009 | List<Employee> that were hired in 2009
     */
    @Test
    public void groupingByYearOfHire() {
        Map<Integer , List<Employee>> yearMap = allEmployees.stream()
                .collect(Collectors.groupingBy(emp -> emp.getHireDate().getYear()));
        dump(yearMap);
    }

    /**
     * TASK: sort the 'animals' list by string length, and break all ties by natural order.
     *  For example: ant, bat, cat, dog, horse, snake, tiger, giraffe, panther
     */
    @Test
    public void twoPartComparator() {
        List<String> animals = Arrays.asList(
                "monkey", "sloth", "baboon", "tiger", "snake", "panda", "parrot", "panther", "rhino", "horse");

        animals.sort(comparing(String::length).thenComparing(naturalOrder()));
        dump(animals);
    }

    // helper method to dump a Collection to stdout
    private static void dump(Collection<?> collection) {
        collection.forEach(System.out::println);
    }

    // helper method to dump a Map to stdout
    private static void dump(Map<?,?> map) {
        map.forEach((key, value) -> System.out.println(key + " | " + value));
    }
}