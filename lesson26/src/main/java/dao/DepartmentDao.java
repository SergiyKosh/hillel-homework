package dao;

import model.Department;
import model.Employee;

import java.util.List;

public interface DepartmentDao {
    Long add(Department department);

    void update(Department department);

    void delete(Long id);

    Department get(Long id);

    List<Department> findAll();
}
