package rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import rest.entity.Department;
import rest.entity.Employee;

import javax.swing.text.html.Option;
import java.sql.Types;
import java.util.Optional;

import static rest.util.Constants.*;
import static rest.util.servlet.ServletUtil.*;

public class EmployeeService {
    public Employee getEmployee(HttpServletRequest request) {
        long departmentId = Long.parseLong(request.getParameter(DEPARTMENT_ID_FIELD));

        Department department = DEPARTMENT_DAO.get(departmentId);

        long chiefId;

        if (request.getParameter(CHIEF_ID_FIELD) == null
                || request.getParameter(CHIEF_ID_FIELD).equals("null")) {
            chiefId = 0;
        } else {
            chiefId = Long.parseLong(request.getParameter(CHIEF_ID_FIELD));
        }

        return Employee.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .salary(Integer.parseInt(request.getParameter(SALARY_FIELD)))
                .department(department)
                .chiefId(chiefId)
                .build();
    }

    public void create(HttpServletRequest request) {
        EMPLOYEE_DAO.save(getEmployee(request));
    }

    public String read(HttpServletRequest request) throws JsonProcessingException {
        long id = Long.parseLong(request.getParameter(ID_FIELD));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(EMPLOYEE_DAO.get(id));
    }

    public String readAll() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(EMPLOYEE_REPOSITORY.findAll());
    }

    public void update(HttpServletRequest request) {
        EMPLOYEE_DAO.update(getEmployee(request));
    }

    public void delete(HttpServletRequest request) {
        EMPLOYEE_DAO.delete(getEmployee(request));
    }
}
