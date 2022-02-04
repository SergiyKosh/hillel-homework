package client.controller;

import client.core.annotation.Controller;
import client.core.annotation.GetMapping;
import client.core.annotation.PostMapping;
import client.entity.Department;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goebl.david.Webb;
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

    @PostMapping(url = "/department/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        Webb updReq = Webb.create();

        updReq.delete("http://127.0.0.1:8080/department/delete")
                .param(ID_FIELD, request.getParameter(ID_FIELD))
                .ensureSuccess()
                .asString();
        return "redirect:/departments";
    }
}
