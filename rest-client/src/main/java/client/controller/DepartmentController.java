package client.controller;

import client.core.annotation.Controller;
import client.core.annotation.mapping.GetMapping;
import client.entity.Department;
import client.service.DepartmentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DepartmentController {
    private final DepartmentService service = new DepartmentService();

    @GetMapping(url = "/departments")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        return service.findAll(request);
    }

    @GetMapping(url = "/department/edit")
    public String getUpdateMapping(HttpServletRequest request, HttpServletResponse response) {
        return service.getUpdateMapping(request);
    }

    @GetMapping(url = "/department/new")
    public String getAddMapping(HttpServletRequest request, HttpServletResponse response) {
        return "add";
    }

}
