package rest.service;

import jakarta.servlet.http.HttpServletRequest;
import rest.exceptions.EmployeeBusinessException;
import rest.exceptions.EmployeeDaoException;
import rest.model.dao.EmployeeDao;
import rest.model.dao.EmployeeDatabaseDao;
import rest.model.entity.Employee;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static rest.util.FieldsConst.EMPLOYEE_ID;

public class EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDatabaseDao();

    public void add(HttpServletRequest request) throws EmployeeBusinessException {
        try {
            int counter;
            StringBuilder str = new StringBuilder();

            while ((counter = request.getInputStream().read()) != -1) {
                str.append((char) counter);
            }

            String[] params = str.toString().split("&");
            Object[] paramsObj = Arrays.stream(params)
                    .map(s -> s.replaceAll("name=", "")
                            .replaceAll("salary=", "")
                            .replaceAll("chiefId=", "")
                            .replaceAll("departmentId=", "")
                    ).toArray();
            params = Arrays.copyOf(paramsObj, paramsObj.length, String[].class);
            String name = params[0];
            Integer salary = Integer.parseInt(params[1]);
            long chiefId = Long.parseLong(params[2]);
            long departmentId = Long.parseLong(params[3]);

            Employee employee = Employee.builder()
                    .name(name)
                    .salary(salary)
                    .departmentId(departmentId)
                    .chiefId(chiefId)
                    .build();
            employeeDao.add(employee);
        } catch (EmployeeDaoException | IOException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    public void update(HttpServletRequest request) throws EmployeeBusinessException {
        try {
            int counter;
            StringBuilder str = new StringBuilder();

            while ((counter = request.getInputStream().read()) != -1) {
                str.append((char) counter);
            }

            String[] params = str.toString().split("&");
            Object[] paramsObj = Arrays.stream(params)
                    .map(s -> s.replaceAll("id=", "")
                            .replaceAll("name=", "")
                            .replaceAll("salary=", "")
                            .replaceAll("chiefId=", "")
                            .replaceAll("departmentId=", "")
                    ).toArray();
            params = Arrays.copyOf(paramsObj, paramsObj.length, String[].class);

            long id = Long.parseLong(params[0]);
            long departmentId = Long.parseLong(params[1]);
            String name = params[2];
            Integer salary = Integer.parseInt(params[3]);
            long chiefId = Long.parseLong(params[4]);

            Employee employee = Employee.builder()
                    .id(id)
                    .name(name)
                    .salary(salary)
                    .departmentId(departmentId)
                    .chiefId(chiefId)
                    .build();
            employeeDao.update(employee);
        } catch (EmployeeDaoException | IOException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    public void delete(HttpServletRequest request) throws EmployeeBusinessException {
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
            employeeDao.delete(id);
        } catch (EmployeeDaoException | IOException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    public Employee get(HttpServletRequest request) throws EmployeeBusinessException {
        try {
            long id = Long.parseLong(request.getParameter(EMPLOYEE_ID));
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
