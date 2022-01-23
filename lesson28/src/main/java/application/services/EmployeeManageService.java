package application.services;

import application.dao.EmployeeDao;
import application.dao.EmployeeDatabaseDao;
import application.entities.Employee;
import application.exceptions.EmployeeBusinessException;
import application.exceptions.EmployeeDaoException;

import java.util.List;

public class EmployeeManageService {
    private final EmployeeDao employeeDao = new EmployeeDatabaseDao();

    public Long add(Employee employee) throws EmployeeBusinessException {
        try {
            return employeeDao.add(employee);
        } catch (EmployeeDaoException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    public void update(Employee employee) throws EmployeeBusinessException {
        try {
            employeeDao.update(employee);
        } catch (EmployeeDaoException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    public void delete(Long id) throws EmployeeBusinessException {
        try {
            employeeDao.delete(id);
        } catch (EmployeeDaoException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    public Employee get(Long id) throws EmployeeBusinessException {
        try {
            return employeeDao.get(id);
        } catch (EmployeeDaoException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    public List<Employee> findAll() throws EmployeeBusinessException {
        try {
            return employeeDao.findAll();
        } catch (EmployeeDaoException e) {
            throw new EmployeeBusinessException(e);
        }
    }
}
