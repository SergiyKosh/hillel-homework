package client.controller;

import client.core.annotation.Controller;
import client.core.annotation.DeleteMapping;
import client.core.annotation.GetMapping;
import client.core.annotation.PostMapping;
import client.entity.Department;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
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

//    @PostMapping(url = "/department/delete")
//    public String delete(HttpServletRequest request, HttpServletResponse response) {
//        ObjectMapper om = new ObjectMapper();
//        Department department = null;
//        try {
//            department = om.readValue(new URL("http://127.0.0.1:8080/department?id=" + request.getParameter(ID_FIELD)), Department.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            URL url = new URL("http://127.0.0.1:8080/department/delete");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestProperty("Content-Type", "application/json; utf-8");
//            con.setRequestProperty("Accept", "application/json");
//            con.setRequestMethod("DELETE");
//            con.setDoOutput(true);
//            con.setDoInput(true);
//            String jsonInputString = new ObjectMapper().writeValueAsString(department);
//            byte[] input = jsonInputString.getBytes("utf-8");
//            try (OutputStream os = con.getOutputStream()) {
//                os.write(input, 0, input.length);
//            }
//        } catch(IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return "redirect:/departments";
//    }

    @DeleteMapping(url = "http://127.0.0.1:8080/department/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/departments";
    }

}
