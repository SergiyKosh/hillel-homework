package rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rest.core.annotation.*;
import rest.core.annotation.Controller;
import rest.entity.Employee;
import rest.service.EmployeeService;

import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService service = new EmployeeService();

    @GetMapping(url = "/employees")
    public List<Employee> findAll(HttpServletRequest request, HttpServletResponse response) {
        return service.readAll();
    }

    @GetMapping(url = "/employee")
    public Employee get(HttpServletRequest request, HttpServletResponse response) {
        return service.read(request);
    }

    @PutMapping(url = "/employees")
    public String save(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        service.create(request);
        return "redirect:/http://localhost:8081/employees";
    }

    @PostMapping(url = "/employee")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        service.update(request);
        return "redirect:/http://localhost:8081/employees";
    }

    @DeleteMapping(url = "/employee")
    public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.delete(request);
        return "redirect:/http://localhost:8081/employees";
    }
}
