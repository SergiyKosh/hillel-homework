package controllers;

import entities.Employee;
import services.EmployeeManageService;
import utils.Menu;

import java.util.Scanner;

public class EmployeeController {
    private final EmployeeManageService ems;
    private Scanner scanner = new Scanner(System.in);

    public EmployeeController() {
        this.ems = new EmployeeManageService();
    }

    public void addEmployee() {
        Employee employee = new Employee();
        System.out.print("name: ");
        employee.setName(scanner.nextLine());
        System.out.print("salary: ");
        employee.setSalary(scanner.nextInt());
        System.out.print("department_id: ");
        employee.setDepartmentId(scanner.nextLong());
        System.out.print("chief_id: ");
        employee.setChiefId(scanner.nextLong());
        ems.add(employee);
    }

    public void updateEmployee(Menu menu, Long id) {
        Employee employee = ems.get(id);

        if (employee == null) {
            System.out.println("\nEmployee with id = " + id + " does not exists!\n");
            menu.employeeMenu();
        }

        employee.setId(id);

        System.out.print(
                "Select field for update:\n" +
                        "1 - name\n" +
                        "2 - salary\n" +
                        "3 - department_id\n" +
                        "4 - chief_id\n" +
                        "-> "
        );

        switch (scanner.nextLine()) {
            case "1":
                System.out.print("name: ");
                employee.setName(scanner.nextLine());
                break;
            case "2":
                System.out.print("salary: ");
                employee.setSalary(scanner.nextInt());
                break;
            case "3":
                System.out.print("department_id: ");
                employee.setDepartmentId(scanner.nextLong());
                break;
            case "4":
                System.out.print("chief_id: ");
                employee.setChiefId(scanner.nextLong());
                break;
            default:
                System.out.print("Menu item that you choose does not exists, try another!");
                updateEmployee(menu, id);
                break;
        }

        ems.update(employee);
    }

    public void deleteEmployee(Menu menu, Long id) {
        if (ems.get(id) == null) {
            System.out.println("\nEmployee with id = " + id + " does not exists!\n");
            menu.employeeMenu();
        }

        ems.delete(id);
    }

    public Employee get(Menu menu, Long id) {
        if (ems.get(id) == null) {
            System.out.println("\nEmployee with id = " + id + " does not exists!\n");
            menu.employeeMenu();
        }

        return ems.get(id);
    }
}
