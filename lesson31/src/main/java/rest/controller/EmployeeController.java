package rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rest.core.annotation.DeleteMapping;
import rest.core.annotation.GetMapping;
import rest.core.annotation.PostMapping;
import rest.core.annotation.PutMapping;
import rest.entity.Department;
import rest.entity.Employee;

import static rest.util.Constants.*;
import static rest.util.ServletUtil.*;

public class EmployeeController implements Controller {
    @GetMapping(url = "/employees")
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(EMPLOYEE_REPOSITORY.findAll());
    }

    @GetMapping(url = "/employee")
    public String get(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        long id = Long.parseLong(request.getParameter(ID_FIELD));

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(EMPLOYEE_DAO.get(id));
    }

    @PutMapping(url = "/employee/add")
    public void save(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        long departmentId = Long.parseLong(request.getParameter(DEPARTMENT_ID_FIELD));

        Department department = DEPARTMENT_DAO.get(departmentId);

        Employee employee = Employee.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .salary(Integer.parseInt(request.getParameter(SALARY_FIELD)))
                .department(department)
                .chiefId(Long.parseLong(request.getParameter(CHIEF_ID_FIELD)))
                .build();
        EMPLOYEE_DAO.save(employee);
    }

    @PostMapping(url = "/employee/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        long departmentId = Long.parseLong(request.getParameter(DEPARTMENT_ID_FIELD));

        Department department = DEPARTMENT_DAO.get(departmentId);

        Employee employee = Employee.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .salary(Integer.parseInt(request.getParameter(SALARY_FIELD)))
                .department(department)
                .chiefId(Long.parseLong(request.getParameter(CHIEF_ID_FIELD)))
                .build();
        EMPLOYEE_DAO.update(employee);
    }

    @DeleteMapping(url = "/employee/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        long departmentId = Long.parseLong(request.getParameter(DEPARTMENT_ID_FIELD));

        Department department = DEPARTMENT_DAO.get(departmentId);

        Employee employee = Employee.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .salary(Integer.parseInt(request.getParameter(SALARY_FIELD)))
                .department(department)
                .chiefId(Long.parseLong(request.getParameter(CHIEF_ID_FIELD)))
                .build();
        EMPLOYEE_DAO.update(employee);
    }
}
