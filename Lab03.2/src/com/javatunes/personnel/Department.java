/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.personnel;

import java.util.ArrayList;
import java.util.Collection;

public class Department {
    private String name;
    private String location;
    private final Collection<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(String name, String location) {
        setName(name);
        setLocation(location);
    }

    public void listEmployees() {
        employees.forEach(System.out::println);    // lambda: emp -> System.out.println(emp)
        }

    public void workEmployees() {
        employees.forEach(Employee::work);      // lambda: emp -> emp.work()
    }

    public void payEmployees() {
        employees.forEach(Employee::pay);  // lambda: emp -> emp.pay()
    }

    // helper method to add an Employee to the collection
    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": name=" + getName() + ", location=" + getLocation();
    }
}