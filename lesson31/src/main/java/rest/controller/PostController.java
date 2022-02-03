package rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rest.core.annotation.PostMapping;

public class PostController implements Controller {
    @PostMapping(url = "/123")
    public String postR(HttpServletRequest request, HttpServletResponse response) {
        return "1234";
    }
}
