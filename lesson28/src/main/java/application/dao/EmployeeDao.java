package application.dao;

import application.entities.Employee;
import application.exceptions.EmployeeDaoException;

import java.util.List;

public interface EmployeeDao {
    Long add(Employee employee) throws EmployeeDaoException;

    void update(Employee employee) throws EmployeeDaoException;

    void delete(Long id) throws EmployeeDaoException;

    Employee get(Long id) throws EmployeeDaoException;

    List<Employee> findAll() throws EmployeeDaoException;
}
