package application.controllers;

import application.entities.Department;
import application.exceptions.DepartmentBusinessException;
import application.exceptions.EmployeeBusinessException;
import application.services.DepartmentManageService;
import application.utils.Menu;

import java.util.Scanner;

public class DepartmentController {
    private DepartmentManageService dms;
    private Scanner scanner;

    public DepartmentController() {
        this.dms = new DepartmentManageService();
        this.scanner = new Scanner(System.in);
    }

    public void addDepartment() throws DepartmentBusinessException {
        Department department = new Department();
        System.out.print("name: ");
        department.setName(scanner.nextLine());
        dms.add(department);
    }

    public void updateDepartment(Menu menu, Long id) throws DepartmentBusinessException, EmployeeBusinessException {
        Department department = new Department();

        if (dms.get(id) == null) {
            System.out.println("\nDepartment with id = " + id + " does not exists!\n");
            menu.departmentMenu();
        }

        department.setId(id);

        System.out.print("new name: ");
        department.setName(scanner.nextLine());

        dms.update(department);
    }

    public void deleteDepartment(Menu menu, Long id) throws DepartmentBusinessException, EmployeeBusinessException {
        if (dms.get(id) == null) {
            System.out.println("\nDepartment with id = " + id + " does not exists!\n");
            menu.departmentMenu();
        }

        dms.delete(id);
    }

    public Department get(Menu menu, Long id) throws DepartmentBusinessException, EmployeeBusinessException {
        if (dms.get(id) == null) {
            System.out.println("\nDepartment with id = " + id + " does not exists!\n");
            menu.departmentMenu();
        }

        return dms.get(id);
    }
}
