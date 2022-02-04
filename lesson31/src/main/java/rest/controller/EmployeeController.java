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
import rest.service.EmployeeService;

import static rest.util.Constants.*;
import static rest.util.servlet.ServletUtil.*;

public class EmployeeController implements Controller {
    private final EmployeeService service = new EmployeeService();

    @GetMapping(url = "/employees")
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        return service.readAll();
    }

    @GetMapping(url = "/employee")
    public String get(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        return service.read(request);
    }

    @PutMapping(url = "/employee/add")
    public void save(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        service.create(request);
    }

    @PostMapping(url = "/employee/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        service.update(request);
    }

    @DeleteMapping(url = "/employee/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        service.delete(request);
    }
}
