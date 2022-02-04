package rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import rest.entity.Department;

import static rest.util.Constants.ID_FIELD;
import static rest.util.Constants.NAME_FIELD;
import static rest.util.servlet.ServletUtil.DEPARTMENT_DAO;
import static rest.util.servlet.ServletUtil.DEPARTMENT_REPOSITORY;

public class DepartmentService {

    public void create(HttpServletRequest request) {
        Department department = Department.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .build();
        DEPARTMENT_DAO.save(department);
    }

    public String read(HttpServletRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        long id = Long.parseLong(request.getParameter(ID_FIELD));
        return mapper.writeValueAsString(DEPARTMENT_DAO.get(id));
    }

    public String readAll() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(DEPARTMENT_REPOSITORY.findAll());
    }

    public void update(HttpServletRequest request) {
        Department department = Department.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .build();
        DEPARTMENT_DAO.update(department);
    }

    public void delete(HttpServletRequest request) {
        Department department = DEPARTMENT_DAO.get(Long.parseLong(request.getParameter(ID_FIELD)));
        DEPARTMENT_DAO.delete(department);
    }
}
