package com.example.demo.controller;

import org.springframework.stereotype.Component;

// Violates SRP, OCP, LSP, ISP, DIP all in one
@Component
class Employee {
    private final String name;
    private final String role;

    public Employee()
    {
        name="test";
        role="developer";
    }

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() { return name; }
    public String getRole() { return role; }

    // ❌ SRP Violation: Employee also responsible for reporting & payments
    public void printReport() {
        System.out.println("Employee Report: " + name + " (" + role + ")");
    }

    // ❌ OCP Violation: Adding new printer types requires modifying this class
    public void printReportToFile() {
        System.out.println("[File] Employee Report: " + name + " (" + role + ")");
    }

    // ❌ LSP Violation: Derived class breaks expected behavior
    public void paySalary(String method, double amount) {
        if (method.equals("bank")) {
            System.out.println("Paying " + amount + " via Bank Transfer.");
        } else if (method.equals("paypal")) {
            System.out.println("Paying " + amount + " via PayPal.");
        } else {
            // Unexpected behavior for clients
            throw new UnsupportedOperationException("Payment method not supported!");
        }
    }

    // ❌ ISP Violation: Forcing every Employee to implement irrelevant methods
    public void writeCode() {
        if (!role.equals("Developer")) {
            throw new UnsupportedOperationException("This employee cannot write code!");
        }
        System.out.println("Developer is coding...");
    }

    public void cookFood() {
        if (!role.equals("Chef")) {
            throw new UnsupportedOperationException("This employee cannot cook!");
        }
        System.out.println("Chef is cooking...");
    }

    // ❌ DIP Violation: High-level class depends directly on low-level details
    public void saveToDatabase() {
        System.out.println("Connecting directly to DB... saving Employee!");
    }
}