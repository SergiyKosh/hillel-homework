package rest.service;
import jakarta.servlet.http.HttpServletRequest;
import rest.exceptions.EmployeeBusinessException;
import rest.exceptions.EmployeeDaoException;
import rest.model.dao.EmployeeDao;
import rest.model.dao.EmployeeDatabaseDao;
import rest.model.entity.Employee;

import java.io.IOException;
import java.util.List;

import static rest.util.FieldsConst.*;

public class EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDatabaseDao();

    public Long add(HttpServletRequest request) throws EmployeeBusinessException {
        try {
            Employee employee = Employee.builder()
                    .id(Long.parseLong(request.getParameter(EMPLOYEE_ID)))
                    .name(request.getParameter(NAME))
                    .salary(Integer.parseInt(SALARY))
                    .departmentId(Long.parseLong(request.getParameter("departmentId")))
                    .chiefId(Long.parseLong(request.getParameter("chiefId")))
                    .build();
            return employeeDao.add(employee);
        } catch (EmployeeDaoException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    public void update(HttpServletRequest request) throws EmployeeBusinessException {
        try {
            Employee employee = Employee.builder()
                    .id(Long.parseLong(request.getParameter(EMPLOYEE_ID)))
                    .name(request.getParameter(NAME))
                    .salary(Integer.parseInt(SALARY))
                    .departmentId(Long.parseLong(request.getParameter("departmentId")))
                    .chiefId(Long.parseLong(request.getParameter("chiefId")))
                    .build();
            employeeDao.update(employee);
        } catch (EmployeeDaoException e) {
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
