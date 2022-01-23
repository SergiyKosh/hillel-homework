package application;

import application.configurations.AppConfig;
import application.exceptions.DepartmentBusinessException;
import application.exceptions.EmployeeBusinessException;
import application.utils.Menu;

public class Main {
    public static void main(String[] args) throws DepartmentBusinessException, EmployeeBusinessException {
        AppConfig.init();
        new Menu().menu();
    }
}
