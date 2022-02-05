package rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rest.core.annotation.Controller;
import rest.core.annotation.DeleteMapping;
import rest.core.annotation.GetMapping;
import rest.core.annotation.PostMapping;
import rest.core.annotation.PutMapping;
import rest.service.DepartmentService;

@Controller
public class DepartmentController {
    private final DepartmentService service = new DepartmentService();

    @GetMapping(url = "/departments")
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        return service.readAll();
    }

    @GetMapping(url = "/department")
    public String get(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
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
