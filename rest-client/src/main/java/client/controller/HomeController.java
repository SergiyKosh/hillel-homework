package client.controller;

import client.core.annotation.Controller;
import client.core.annotation.mapping.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @GetMapping
    public String home(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/departments";
    }
}
