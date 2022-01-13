package services;

import dao.DepartmentDao;
import dao.DepartmentSimpleDao;
import model.Department;

import java.util.List;

public class DepartmentManageService {
    private final DepartmentDao departmentDao;

    public DepartmentManageService() {
        this.departmentDao = new DepartmentSimpleDao();
    }

    public Long add(Department department) {
        return departmentDao.add(department);
    }

    public void update(Department department) {
        departmentDao.update(department);
    }

    public void delete(Long id) {
        departmentDao.delete(id);
    }

    public Department get(Long id) {
        return departmentDao.get(id);
    }

    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
