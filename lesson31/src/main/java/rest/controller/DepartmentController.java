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

import static rest.util.Constants.ID_FIELD;
import static rest.util.Constants.NAME_FIELD;
import static rest.util.servlet.ServletUtil.DEPARTMENT_DAO;
import static rest.util.servlet.ServletUtil.DEPARTMENT_REPOSITORY;

public class DepartmentController implements Controller {
    @GetMapping(url = "/departments")
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(DEPARTMENT_REPOSITORY.findAll());
    }

    @GetMapping(url = "/department")
    public String get(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        long id = Long.parseLong(request.getParameter(ID_FIELD));
        return mapper.writeValueAsString(DEPARTMENT_DAO.get(id));
    }

    @PutMapping(url = "/department/add")
    public void add(HttpServletRequest request, HttpServletResponse response) {
        Department department = Department.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .build();
        DEPARTMENT_DAO.save(department);
    }

    @PostMapping(url = "/department/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Department department = Department.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .build();
        DEPARTMENT_DAO.update(department);
    }

    @DeleteMapping(url = "/department/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        Department department = DEPARTMENT_DAO.get(Long.parseLong(request.getParameter(ID_FIELD)));
        DEPARTMENT_DAO.delete(department);
    }
}
