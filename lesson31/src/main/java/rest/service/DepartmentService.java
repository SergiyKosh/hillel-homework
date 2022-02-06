package rest.service;

import jakarta.servlet.http.HttpServletRequest;
import rest.exceptions.DepartmentBusinessException;
import rest.exceptions.DepartmentDaoException;
import rest.model.dao.DepartmentDao;
import rest.model.dao.DepartmentDatabaseDao;
import rest.model.entity.Department;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static rest.util.FieldsConst.*;

public class DepartmentService {
    private final DepartmentDao departmentDao;

    public DepartmentService() {
        this.departmentDao = new DepartmentDatabaseDao();
    }

    public void add(HttpServletRequest request) throws DepartmentBusinessException {
        try {
            int counter;
            StringBuilder str = new StringBuilder();

            while ((counter = request.getInputStream().read()) != -1) {
                str.append((char) counter);
            }

            String[] params = str.toString().split("&");
            Object[] paramsObj = Arrays.stream(params)
                    .map(s -> s.replaceAll("[a-zA-Z]", "")
                            .replaceAll("&", "")
                            .replaceAll("=", "")
                    ).toArray();
            params = Arrays.copyOf(paramsObj, paramsObj.length, String[].class);
            long id = Long.parseLong(params[0]);
            Department department = Department.builder()
                    .id(id)
                    .name(params[1])
                    .build();

            departmentDao.add(department);
        } catch (DepartmentDaoException | IOException e) {
            throw new DepartmentBusinessException(e);
        }
    }

    public void update(HttpServletRequest request) throws DepartmentBusinessException {
        try {
            int counter;
            StringBuilder str = new StringBuilder();

            while ((counter = request.getInputStream().read()) != -1) {
                str.append((char) counter);
            }

            String[] params = str.toString().split("&");
            Object[] paramsObj = Arrays.stream(params)
                    .map(s -> s.replaceAll("[a-zA-Z]", "")
                            .replaceAll("&", "")
                            .replaceAll("=", "")
                    ).toArray();
            params = Arrays.copyOf(paramsObj, paramsObj.length, String[].class);
            long id = Long.parseLong(params[0]);

            Department department = Department.builder()
                    .id(id)
                    .name(params[1])
                    .build();

            departmentDao.update(department);
        } catch (DepartmentDaoException | IOException e) {
            throw new DepartmentBusinessException(e);
        }
    }

    public void delete(HttpServletRequest request) throws DepartmentBusinessException, IOException {
        try {
            int counter;
            StringBuilder str = new StringBuilder();
            while ((counter = request.getInputStream().read()) != -1) {
                str.append((char) counter);
            }

            long id = Long.parseLong(
                    str.toString().replaceAll("[a-zA-z]", "")
                            .replaceAll("=", "")
            );

            departmentDao.delete(id);
        } catch (DepartmentDaoException e) {
            throw new DepartmentBusinessException(e);
        }
    }

    public Department get(HttpServletRequest request) throws DepartmentBusinessException {
        try {
            long id = Long.parseLong(request.getParameter(DEP_ID));
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
