package rest.model.dao;

import rest.exceptions.DepartmentDaoException;
import rest.model.entity.Department;

import java.util.List;

public interface DepartmentDao {
    void add(Department department) throws DepartmentDaoException;

    void update(Department department) throws DepartmentDaoException;

    void delete(Long id) throws DepartmentDaoException;

    Department get(Long id) throws DepartmentDaoException;

    List<Department> findAll() throws DepartmentDaoException;
}
