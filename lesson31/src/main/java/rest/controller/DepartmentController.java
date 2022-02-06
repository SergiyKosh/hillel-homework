package rest.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rest.core.annotation.*;
import rest.exceptions.DepartmentBusinessException;
import rest.model.entity.Department;
import rest.service.DepartmentService;

import java.io.IOException;
import java.util.List;

@Controller
public class DepartmentController {
    private final DepartmentService service = new DepartmentService();

    @GetMapping(url = "/departments")
    public List<Department> findAll(HttpServletRequest request, HttpServletResponse response) throws DepartmentBusinessException {
        return service.findAll();
    }

    @GetMapping(url = "/department")
    public Department get(HttpServletRequest request, HttpServletResponse response) throws DepartmentBusinessException {
        return service.get(request);
    }

    @PutMapping(url = "/department/add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws DepartmentBusinessException {
        service.add(request);
        return "redirect:/http://localhost:8081/departments";
    }

    @PostMapping(url = "/department/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws DepartmentBusinessException {
        service.update(request);
        return "redirect:/http://localhost:8081/departments";
    }

    @DeleteMapping(url = "/department/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, DepartmentBusinessException {
        service.delete(request);
    }
}
