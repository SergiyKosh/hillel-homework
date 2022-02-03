package client.controller;

import client.core.annotation.GetMapping;
import client.core.controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class TestController {
    @GetMapping(url = "/home")
    public String getHome(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
}
