package dao;

import entities.Employee;

import java.util.List;

public interface EmployeeDao {
    Long add(Employee employee);

    void update(Employee employee);

    void delete(Long id);

    Employee get(Long id);

    List<Employee> findAll();
}
