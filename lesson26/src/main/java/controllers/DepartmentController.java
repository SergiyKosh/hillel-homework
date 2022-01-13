package controllers;

import model.Department;
import model.Employee;
import services.DepartmentManageService;
import utils.Menu;

import java.util.Scanner;

public class DepartmentController {
    private DepartmentManageService dms;
    private Scanner scanner;

    public DepartmentController() {
        this.dms = new DepartmentManageService();
        this.scanner = new Scanner(System.in);
    }

    public void addDepartment() {
        Department department = new Department();
        System.out.print("name: ");
        department.setName(scanner.nextLine());
        dms.add(department);
    }

    public void updateDepartment(Menu menu, Long id) {
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

    public void deleteDepartment(Menu menu, Long id) {
        if (dms.get(id) == null) {
            System.out.println("\nDepartment with id = " + id + " does not exists!\n");
            menu.departmentMenu();
        }

        dms.delete(id);
    }

    public Department get(Menu menu, Long id) {
        if (dms.get(id) == null) {
            System.out.println("\nDepartment with id = " + id + " does not exists!\n");
            menu.departmentMenu();
        }

        return dms.get(id);
    }
}
