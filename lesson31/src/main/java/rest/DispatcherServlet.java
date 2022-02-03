package rest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;
import rest.core.annotation.DeleteMapping;
import rest.core.annotation.GetMapping;
import rest.core.annotation.PostMapping;
import rest.core.annotation.PutMapping;
import rest.controller.Controller;
import rest.dao.DepartmentDao;
import rest.dao.EmployeeDao;
import rest.repository.DepartmentRepository;
import rest.repository.EmployeeRepository;
import rest.util.hibernate.HibernateConfiguration;
import rest.util.servlet.HttpMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Set;

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

        Set<Class<? extends Controller>> entries = new Reflections("rest.controller")
                .getSubTypesOf(Controller.class);

        AbstractMap.SimpleEntry<? extends Class<? extends Controller>, Method> entry = entries.stream()
                .filter(clazz -> {
                    if (request.getMethod().equals(HttpMethod.GET.name())) {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(method -> method.isAnnotationPresent(GetMapping.class)
                                && method.getAnnotation(GetMapping.class).url().equals(uri));
                    } else if (request.getMethod().equals(HttpMethod.POST.name())) {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(method -> method.isAnnotationPresent(PostMapping.class)
                                        && method.getAnnotation(PostMapping.class).url().equals(uri));
                    } else if (request.getMethod().equals(HttpMethod.PUT.name())) {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(method -> method.isAnnotationPresent(PutMapping.class)
                                        && method.getAnnotation(PutMapping.class).url().equals(uri));
                    } else {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(method -> method.isAnnotationPresent(DeleteMapping.class)
                                        && method.getAnnotation(DeleteMapping.class).url().equals(uri));
                    }
                })
                .map(clazz -> {
                    Method mapping = Arrays.stream(clazz.getDeclaredMethods())
                            .filter(method -> {
                                if (method.isAnnotationPresent(GetMapping.class)) {
                                    return method.isAnnotationPresent(GetMapping.class)
                                            && method.getAnnotation(GetMapping.class).url().equals(uri);
                                } else if (method.isAnnotationPresent(PostMapping.class)) {
                                    return method.isAnnotationPresent(PostMapping.class)
                                            && method.getAnnotation(PostMapping.class).url().equals(uri);
                                } else if (method.isAnnotationPresent(PutMapping.class)) {
                                    return method.isAnnotationPresent(PutMapping.class)
                                            && method.getAnnotation(PutMapping.class).url().equals(uri);
                                } else {
                                    return method.isAnnotationPresent(DeleteMapping.class)
                                            && method.getAnnotation(DeleteMapping.class).url().equals(uri);
                                }
                            })
                            .findFirst()
                            .orElseThrow(RuntimeException::new);
                    return new AbstractMap.SimpleEntry<>(clazz, mapping);
                })
                .findFirst()
                .orElseThrow(RuntimeException::new);


        Method mappingMethod = entry.getValue();
        Class<?> mappingClass = entry.getKey();
        Object result = mappingMethod.invoke(mappingClass.getConstructor().newInstance(), request, response);

        if (mappingMethod.isAnnotationPresent(GetMapping.class)) {
            response.setContentType("application/json; UTF-8");
            response.getWriter().write(result.toString());
        } else if (mappingMethod.isAnnotationPresent(PostMapping.class)) {
            writeStatus(response);
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

