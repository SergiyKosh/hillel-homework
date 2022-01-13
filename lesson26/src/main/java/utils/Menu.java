package utils;

import controllers.DepartmentController;
import controllers.EmployeeController;
import services.DepartmentManageService;
import services.EmployeeManageService;

import java.util.Scanner;

public final class Menu {
    private Scanner scanner;
    private String operation;

    public void menu() {
        scanner = new Scanner(System.in);

        System.out.print("Menu:\n1 - employee menu\n2 - department menu\n0 - exit\n-> ");

        operation = scanner.nextLine();

        switch (operation) {
            case "1":
                employeeMenu();
                break;
            case "2":
                departmentMenu();
                break;
            case "0":
                break;
            default:
                System.out.println("This menu item does not exists, try another!");
                menu();
                break;
        }
    }

    public void employeeMenu() {
        EmployeeManageService ems = new EmployeeManageService();
        EmployeeController ec = new EmployeeController();
        scanner = new Scanner(System.in);

        System.out.print(
                "Employee menu:\n" +
                        "1 - add employee\n" +
                        "2 - edit employee\n" +
                        "3 - delete employee\n" +
                        "4 - select all employees\n" +
                        "5 - select employee by id\n" +
                        "0 - back to the main menu\n" +
                        "-> "
        );

        operation = scanner.nextLine();

        switch (operation) {
            case "1":
                ec.addEmployee();
                employeeMenu();
                break;
            case "2":
                System.out.print("Input employee id: ");
                ec.updateEmployee(this, scanner.nextLong());
                employeeMenu();
                break;
            case "3":
                System.out.print("Input employee id: ");
                ec.deleteEmployee(this, scanner.nextLong());
                employeeMenu();
                break;
            case "4":
                System.out.println();
                ems.findAll().forEach(System.out::println);
                employeeMenu();
                break;
            case "5":
                System.out.print("Input employee id: ");
                System.out.println();
                System.out.println(ec.get(this, scanner.nextLong()));
                employeeMenu();
                break;
            case "0":
                menu();
                break;
            default:
                System.out.println("This menu item does not exists, try another!");
                employeeMenu();
                break;
        }
    }

    public void departmentMenu() {
        DepartmentManageService dms = new DepartmentManageService();
        DepartmentController dc = new DepartmentController();
        scanner = new Scanner(System.in);

        System.out.print(
                "Department menu:\n" +
                        "1 - add department\n" +
                        "2 - edit department\n" +
                        "3 - delete department\n" +
                        "4 - select all departments\n" +
                        "5 - select department by id\n" +
                        "0 - back to the main menu\n" +
                        "-> "
        );

        operation = scanner.nextLine();

        switch (operation) {
            case "1":
                dc.addDepartment();
                departmentMenu();
                break;
            case "2":
                System.out.print("Input department id: ");
                dc.updateDepartment(this, scanner.nextLong());
                departmentMenu();
                break;
            case "3":
                System.out.print("Input department id: ");
                dc.deleteDepartment(this, scanner.nextLong());
                departmentMenu();
                break;
            case "4":
                System.out.println();
                dms.findAll().forEach(System.out::println);
                departmentMenu();
                break;
            case "5":
                System.out.print("Input department id: ");
                System.out.println();
                System.out.println(dc.get(this, scanner.nextLong()));
                departmentMenu();
                break;
            case "0":
                menu();
                break;
            default:
                System.out.println("This menu item does not exists, try another!");
                departmentMenu();
                break;
        }
    }
}
