package client.service;

import client.entity.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static client.util.rest.Constants.ID_FIELD;

public class EmployeeService {
    public String getEditPage(HttpServletRequest request) {
        String id = request.getParameter(ID_FIELD);

        Employee employee = null;
        ObjectMapper om = new ObjectMapper();

        try {
            employee = om.readValue(new URL("http://127.0.0.1:8080/employee?id=" + id), Employee.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("employee", employee);

        return "edit";
    }

    public String findAll(HttpServletRequest request) {
        ObjectMapper om = new ObjectMapper();
        List<Employee> employees;
        try {
            employees = om.readValue(new URL("http://127.0.0.1:8080/employees"), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("employees", Optional.ofNullable(employees).orElse(new ArrayList<>()));
        return "employees";
    }
}
