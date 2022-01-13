package services;

import dao.EmployeeDao;
import dao.EmployeeSimpleDao;
import model.Employee;

import java.util.List;

public class EmployeeManageService {
    private final EmployeeDao employeeDao = new EmployeeSimpleDao();

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
