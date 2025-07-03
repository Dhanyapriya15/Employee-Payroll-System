package com.payroll.app;
import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    
    public abstract double calculateSalary();
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
    }

   
    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║               EMPLOYEE PAYROLL DETAILS            ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.printf("║ %-15s │ %-6s │ %-12s           ║\n", "Name", "ID", "Salary");
        System.out.println("╠════════════════════╪════════╪══════════════════════╣");

        for (Employee employee : employeeList) {
            System.out.printf("║ %-15s │ %-6d │ $%-20.2f ║\n",
                    employee.getName(),
                    employee.getId(),
                    employee.calculateSalary());
        }

        System.out.println("╚════════════════════╧════════╧══════════════════════╝");
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();

        FullTimeEmployee emp1 = new FullTimeEmployee("John Doe", 101, 5000.0);
        PartTimeEmployee emp2 = new PartTimeEmployee("Jane Smith", 102, 30, 15.0);

        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2);

        System.out.println("===== Initial Employee Details =====");
        payrollSystem.displayEmployees();

        System.out.println("\n>>> Removing Employee with ID: 101...");
        payrollSystem.removeEmployee(101);

        System.out.println("\n===== Remaining Employee Details =====");
        payrollSystem.displayEmployees();
    }
}
