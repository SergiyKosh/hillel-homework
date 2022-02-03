package rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rest.core.annotation.DeleteMapping;
import rest.core.annotation.GetMapping;
import rest.core.annotation.PostMapping;
import rest.core.annotation.PutMapping;
import rest.dao.DepartmentDao;
import rest.entity.Department;
import rest.repository.DepartmentRepository;
import rest.util.hibernate.HibernateConfiguration;

import static rest.util.Constants.ID_FIELD;
import static rest.util.Constants.NAME_FIELD;

public class DepartmentController implements Controller {
    private final DepartmentRepository repository = DepartmentRepository
            .builder()
            .session(HibernateConfiguration.getSession())
            .build();

    private final DepartmentDao dao = DepartmentDao.builder()
            .session(HibernateConfiguration.getSession())
            .build();

    @GetMapping(url = "/departments")
    public String findAllDepartments(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(repository.findAll());
    }

    @GetMapping(url = "/department")
    public String get(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        long id = Long.parseLong(request.getParameter(ID_FIELD));
        return mapper.writeValueAsString(dao.get(id));
    }

    @PutMapping(url = "/department/add")
    public void add(HttpServletRequest request, HttpServletResponse response) {
        Department department = Department.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .build();
        dao.save(department);
    }

    @PostMapping(url = "/department/update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Department department = Department.builder()
                .id(Long.parseLong(request.getParameter(ID_FIELD)))
                .name(request.getParameter(NAME_FIELD))
                .build();
        dao.update(department);
    }

    @DeleteMapping(url = "/department/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        Department department = dao.get(Long.parseLong(request.getParameter(ID_FIELD)));
        dao.delete(department);
    }
}
