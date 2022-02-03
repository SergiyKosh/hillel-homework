package rest.controller;

import rest.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    @GetMapping(url = "home")
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "home";
    }
}
