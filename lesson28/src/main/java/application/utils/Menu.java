package application.utils;

import application.controllers.DepartmentController;
import application.controllers.EmployeeController;
import application.entities.Department;
import application.entities.Employee;
import application.exceptions.DepartmentBusinessException;
import application.exceptions.EmployeeBusinessException;
import application.services.DepartmentManageService;
import application.services.EmployeeManageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import java.util.Scanner;

public final class Menu {
    private Scanner scanner;
    private String operation;

    public void menu() throws DepartmentBusinessException, EmployeeBusinessException {
        EmployeeManageService ems = new EmployeeManageService();
        DepartmentManageService dms = new DepartmentManageService();

        if (ems.findAll().isEmpty()) {
            initEmployees();
        }

        if (dms.findAll().isEmpty()) {
            initDepartments();
        }

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

    public void employeeMenu() throws DepartmentBusinessException, EmployeeBusinessException {
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

    public void departmentMenu() throws DepartmentBusinessException, EmployeeBusinessException {
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

    private void initEmployees() throws EmployeeBusinessException {
        EmployeeManageService ems = new EmployeeManageService();
        Employee e8 = new Employee("Волков Станислав Юрьевич", 45000, 2L, null);
        Employee e11 = new Employee("Лебедева Анна Михайловна", 20000, 3L, null);
        Employee e20 = new Employee("Колесникова Милана Родионовна", 35000, 4L, null);
        Long e8Id = ems.add(e8);
        ems.add(new Employee("Михайлов Филипп Фёдорович", 15500, 2L, e8Id));
        ems.add(new Employee("Макаров Алексей Родионович", 15000, 2L, e8Id));
        Long e11Id = ems.add(e11);
        ems.add(new Employee("Тарасова Юлия Глебовна", 22000, 3L, e11Id));
        ems.add(new Employee("Колосова Алиса Константиновна", 18000, 3L, e11Id));
        ems.add(new Employee("Сафонов Арсений Егорович", 15000, 3L, e11Id));
        ems.add(new Employee("Ларина Ирина Львовна", 15000, 3L, e11Id));
        ems.add(new Employee("Пономарева Евгения Данильевна", 15000, 3L, e11Id));
        ems.add(new Employee("Журавлева Милана Фёдоровна", 14000, 3L, e11Id));
        ems.add(new Employee("Никифоров Максим Максимович", 14500, 3L, e11Id));
        ems.add(new Employee("Макаров Алексей Родионович", 15000, 3L, e11Id));
        Long e20Id = ems.add(e20);
        ems.add(new Employee("Куприянова Евгения Даниловна", 12000, 4L, e20Id));
        ems.add(new Employee("Чистяков Владимир Артёмович", 13000, 4L, e20Id));
        ems.add(new Employee("Савицкая София Андреевна", 15000, 4L, e20Id));
        ems.add(new Employee("Борисова Анна Саввична", 37000, 4L, e20Id));
        ems.add(new Employee("Греков Артём Маркович", 17000, 4L, e20Id));
    }

    private void initDepartments() throws DepartmentBusinessException {
        DepartmentManageService dms = new DepartmentManageService();
        dms.add(
                Department.builder()
                        .id(22L)
                        .name("department1")
                        .build()
        );
        dms.add(
                Department.builder()
                        .id(23L)
                        .name("department2")
                        .build()
        );
        dms.add(
                Department.builder()
                        .id(24L)
                        .name("department3")
                        .build()
        );
        dms.add(
                Department.builder()
                        .id(25L)
                        .name("department4")
                        .build()
        );
        dms.add(
                Department.builder()
                        .id(26L)
                        .name("department5")
                        .build()
        );
        dms.add(
                Department.builder()
                        .id(27L)
                        .name("department6")
                        .build()
        );
        dms.add(
                Department.builder()
                        .id(28L)
                        .name("department7")
                        .build()
        );
        dms.add(
                Department.builder()
                        .id(29L)
                        .name("department8")
                        .build()
        );
        dms.add(
                Department.builder()
                        .id(30L)
                        .name("department9")
                        .build()
        );
        dms.add(
                Department.builder()
                        .id(31L)
                        .name("department10")
                        .build()
        );
    }
}
