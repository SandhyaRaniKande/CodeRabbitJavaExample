package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {
    private Employee employee;
    public EmployeeController(Employee employee)
    {
        this.employee=employee;
    }
    @GetMapping("/{name}/{role}")
    public void getEmpDetails(@PathVariable String name, @PathVariable String role)
    {
        this.employee=new Employee(name,role);

        employee.printReport();
        employee.printReportToFile();

        // LSP violation: Passing unsupported payment type breaks at runtime
        employee.paySalary("crypto", 5000);

        // ISP violation: Forcing developer to have cookFood() method
        employee.writeCode();
        employee.cookFood(); // runtime error!

        // DIP violation: No abstraction, directly saving to DB
        employee.saveToDatabase();
    }

}
