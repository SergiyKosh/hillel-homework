package rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rest.core.annotation.*;
import rest.entity.Department;
import rest.service.DepartmentService;

import java.util.List;

@Controller
public class DepartmentController {
    private final DepartmentService service = new DepartmentService();

    @GetMapping(url = "/departments")
    public List<Department> findAll(HttpServletRequest request, HttpServletResponse response) {
        return service.readAll();
    }

    @GetMapping(url = "/department")
    public Department get(HttpServletRequest request, HttpServletResponse response) {
        return service.read(request);
    }

    @PutMapping(url = "/department/add")
    public void add(HttpServletRequest request, HttpServletResponse response) {
        service.create(request);
    }

    @PostMapping(url = "/department/update")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        service.update(request);
        return "redirect:/http://localhost:8081/departments";
    }

    @DeleteMapping(url = "/department/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        service.delete(request);
    }
}
