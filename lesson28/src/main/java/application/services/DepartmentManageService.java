package application.services;

import application.dao.DepartmentDao;
import application.dao.DepartmentDatabaseDao;
import application.entities.Department;
import application.exceptions.DepartmentBusinessException;
import application.exceptions.DepartmentDaoException;

import java.util.List;

public class DepartmentManageService {
    private final DepartmentDao departmentDao;

    public DepartmentManageService() {
        this.departmentDao = new DepartmentDatabaseDao();
    }

    public Long add(Department department) throws DepartmentBusinessException {
        try {
            return departmentDao.add(department);
        } catch (DepartmentDaoException e) {
            throw new DepartmentBusinessException(e);
        }
    }

    public void update(Department department) throws DepartmentBusinessException {
        try {
            departmentDao.update(department);
        } catch (DepartmentDaoException e) {
            throw new DepartmentBusinessException(e);
        }
    }

    public void delete(Long id) throws DepartmentBusinessException {
        try {
            departmentDao.delete(id);
        } catch (DepartmentDaoException e) {
            throw new DepartmentBusinessException(e);
        }
    }

    public Department get(Long id) throws DepartmentBusinessException {
        try {
            return departmentDao.get(id);
        } catch (DepartmentDaoException e) {
            throw new DepartmentBusinessException(e);
        }
    }

    public List<Department> findAll() throws DepartmentBusinessException {
        try {
            return departmentDao.findAll();
        } catch (DepartmentDaoException e) {
            throw new DepartmentBusinessException(e);
        }
    }
}
