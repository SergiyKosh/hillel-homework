package application.dao;

import application.entities.Department;
import application.exceptions.DepartmentDaoException;

import java.util.List;

public interface DepartmentDao {
    Long add(Department department) throws DepartmentDaoException;

    void update(Department department) throws DepartmentDaoException;

    void delete(Long id) throws DepartmentDaoException;

    Department get(Long id) throws DepartmentDaoException;

    List<Department> findAll() throws DepartmentDaoException;
}
