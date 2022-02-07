package client.service;

import client.entity.Department;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static client.util.rest.Constants.ID_FIELD;

public class DepartmentService {
    public String getUpdateMapping(HttpServletRequest request) {
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

    public String findAll(HttpServletRequest request) {
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
}
