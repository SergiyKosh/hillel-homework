package client.controller;

import client.core.annotation.Controller;
import client.core.annotation.mapping.GetMapping;
import client.core.annotation.mapping.PostMapping;
import client.entity.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goebl.david.Webb;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static client.util.rest.Constants.*;

@Controller
public class EmployeeController {
    @GetMapping(url = "/employees")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
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

    @GetMapping(url = "/employee")
    public String get(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ID_FIELD);
        Employee employee = null;
        ObjectMapper om = new ObjectMapper();
        try {
            employee = om.readValue(new URL("http://127.0.0.1:8080/employee?id=" + id), Employee.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "employee";
    }

    @GetMapping(url = "/employee/edit")
    public String editGetRequest(HttpServletRequest request, HttpServletResponse response) {
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

    @PostMapping(url = "/employee/edit/apply")
    public String editPostRequest(HttpServletRequest request, HttpServletResponse response) {
        Webb updReq = Webb.create();

        updReq.post("http://127.0.0.1:8080/employee/update")
                .param(DEPARTMENT_ID_FIELD, request.getParameter(DEPARTMENT_ID_FIELD))
                .param(ID_FIELD, request.getParameter(ID_FIELD))
                .param(NAME_FIELD, request.getParameter(NAME_FIELD))
                .param(SALARY_FIELD, request.getParameter(SALARY_FIELD))
                .param(CHIEF_ID_FIELD, request.getParameter(CHIEF_ID_FIELD))
                .ensureSuccess()
                .asStream();

        return "redirect:/employees";
    }

    @PostMapping(url = "/employee/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        Webb updReq = Webb.create();

        updReq.delete("http://127.0.0.1:8080/employee/delete")
                .param(DEPARTMENT_ID_FIELD, request.getParameter(DEPARTMENT_ID_FIELD))
                .param(ID_FIELD, request.getParameter(ID_FIELD))
                .param(NAME_FIELD, request.getParameter(NAME_FIELD))
                .param(SALARY_FIELD, request.getParameter(SALARY_FIELD))
                .param(CHIEF_ID_FIELD, request.getParameter(CHIEF_ID_FIELD))
                .ensureSuccess()
                .asStream();

        return "redirect:/employees";
    }
}
