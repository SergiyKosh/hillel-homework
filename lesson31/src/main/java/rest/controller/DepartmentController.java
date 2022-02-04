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
import rest.service.DepartmentService;

import static rest.util.Constants.ID_FIELD;
import static rest.util.Constants.NAME_FIELD;
import static rest.util.servlet.ServletUtil.DEPARTMENT_DAO;
import static rest.util.servlet.ServletUtil.DEPARTMENT_REPOSITORY;

public class DepartmentController implements Controller {
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
    public void update(HttpServletRequest request, HttpServletResponse response) {
        service.update(request);
    }

    @DeleteMapping(url = "/department/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        service.delete(request);
    }
}
