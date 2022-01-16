package services;

import dao.EmployeeDao;
import dao.EmployeeDatabaseDao;
import entities.Employee;

import java.util.List;

public class EmployeeManageService {
    private final EmployeeDao employeeDao = new EmployeeDatabaseDao();

    public Long add(Employee employee) {
        return employeeDao.add(employee);
    }

    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    public void delete(Long id) {
        employeeDao.delete(id);
    }

    public Employee get(Long id) {
        return employeeDao.get(id);
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}
