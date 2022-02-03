package rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rest.annotation.GetMapping;
import rest.dao.DepartmentDao;
import rest.util.hibernate.HibernateConfiguration;

public class HomeController implements Controller {
    @GetMapping(url = "/home")
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        DepartmentDao dao = DepartmentDao.builder()
                .session(HibernateConfiguration.getSession())
                .build();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dao.get(2));
    }
}
