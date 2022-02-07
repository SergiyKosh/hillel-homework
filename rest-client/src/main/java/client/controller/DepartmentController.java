package client.controller;

import client.core.annotation.Controller;
import client.core.annotation.mapping.DeleteMapping;
import client.core.annotation.mapping.GetMapping;
import client.entity.Department;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static client.util.rest.Constants.ID_FIELD;

@Controller
public class DepartmentController {
    @GetMapping(url = "/departments")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper om = new ObjectMapper();
        List<Department> departments;
        try {
            departments = om.readValue(new URL("http://127.0.0.1:8080/departments"), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("departments", Optional.ofNullable(departments).orElse(new ArrayList<>()));
        return "departments";
    }

    @GetMapping(url = "/department/edit")
    public String getUpdateMapping(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ID_FIELD);
        ObjectMapper om = new ObjectMapper();
        Department department = null;
        try {
            department = om.readValue(new URL("http://127.0.0.1:8080/department?id=" + id), Department.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("department", Optional.ofNullable(department).orElse(new Department()));
        return "edit";
    }

    @GetMapping(url = "/department/new")
    public String getAddMapping(HttpServletRequest request, HttpServletResponse response) {
        return "add";
    }

}
