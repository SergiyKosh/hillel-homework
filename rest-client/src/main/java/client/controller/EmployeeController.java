package client.controller;

import client.core.annotation.Controller;
import client.core.annotation.mapping.GetMapping;
import client.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class EmployeeController {
    private final EmployeeService service = new EmployeeService();

    @GetMapping(url = "/employees")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        return service.findAll(request);
    }

    @GetMapping(url = "/employee/edit")
    public String editGetRequest(HttpServletRequest request, HttpServletResponse response) {
        return service.getEditPage(request);
    }

    @GetMapping(url = "/employee/new")
    public String newEmployeeGet(HttpServletRequest request, HttpServletResponse response) {
        return "add";
    }
}
