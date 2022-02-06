package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;
import rest.core.annotation.*;
import rest.core.annotationimpl.AnnotationImpl;
import rest.dao.DepartmentDao;
import rest.dao.EmployeeDao;
import rest.repository.DepartmentRepository;
import rest.repository.EmployeeRepository;
import rest.util.hibernate.HibernateConfiguration;
import rest.util.servlet.HttpMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static rest.util.servlet.ServletUtil.*;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();

        EMPLOYEE_DAO = EmployeeDao.builder()
                .session(HibernateConfiguration.getSession())
                .build();

        DEPARTMENT_DAO = DepartmentDao.builder()
                .session(HibernateConfiguration.getSession())
                .build();

        EMPLOYEE_REPOSITORY = EmployeeRepository.builder()
                .session(HibernateConfiguration.getSession())
                .build();

        DEPARTMENT_REPOSITORY = DepartmentRepository
                .builder()
                .session(HibernateConfiguration.getSession())
                .build();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            process(request, response);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ServletException {
        String uri = request.getRequestURI();

        AnnotationImpl annotationImpl = new AnnotationImpl();

        Map.Entry<? extends Class<?>, Method> mappingMethods = annotationImpl.getMappingMethods(uri, request);

        Method mappingMethod = mappingMethods.getValue();
        Class<?> mappingClass = mappingMethods.getKey();
        Object result = mappingMethod.invoke(mappingClass.getConstructor().newInstance(), request, response);

        if (result.toString().startsWith("redirect:/")) {
            result = result.toString()
                    .replaceAll("redirect:/", "")
                    .replaceAll(".jsp", "");
        }

        if (mappingMethod.isAnnotationPresent(GetMapping.class)) {
            if (mappingClass.getAnnotation(Controller.class).isMicroservice()) {
                response.setContentType("application/json; UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                String returned = mapper.writeValueAsString(result);
                response.getWriter().write(returned);
            } else {
                if (result.toString().startsWith("redirect:")) {
                    result = result.toString()
                            .replaceAll("redirect:", "")
                            .replaceAll(".jsp", "");
                } else {
                    if (!result.toString().endsWith(".jsp")) {
                        result += ".jsp";
                    }
                }
                request.getRequestDispatcher(result.toString()).forward(request, response);
            }
        } else if (mappingMethod.isAnnotationPresent(PostMapping.class)) {
            writeStatus(response);
            response.sendRedirect(result.toString());
        } else if (mappingMethod.isAnnotationPresent(PutMapping.class)) {
            writeStatus(response);
        } else if (mappingMethod.isAnnotationPresent(DeleteMapping.class)) {
            writeStatus(response);
        }
    }

    @Override
    public void destroy() {
        HibernateConfiguration.getSession().close();
    }
}

