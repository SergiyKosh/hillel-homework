package rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {
    String handleRequest(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException;
}
