package rest.model.dao;

import rest.exceptions.EmployeeDaoException;
import rest.model.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    void add(Employee employee) throws EmployeeDaoException;

    void update(Employee employee) throws EmployeeDaoException;

    void delete(Long id) throws EmployeeDaoException;

    Employee get(Long id) throws EmployeeDaoException;

    List<Employee> findAll() throws EmployeeDaoException;
}
