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

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Set;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
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
                    if (request.getMethod().equals("GET")) {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(m -> m.isAnnotationPresent(GetMapping.class));
                    } else if (request.getMethod().equals("POST")) {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(m -> m.isAnnotationPresent(PostMapping.class));
                    } else if (request.getMethod().equals("PUT")) {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(method -> method.isAnnotationPresent(PutMapping.class));
                    } else {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(method -> method.isAnnotationPresent(DeleteMapping.class));
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


        Object result = entry.getValue().invoke(entry.getKey().getConstructor().newInstance(), request, response);

        if (entry.getValue().isAnnotationPresent(GetMapping.class)) {
            String url = entry.getValue().getAnnotation(GetMapping.class).url();
            response.setContentType("application/json; UTF-8");
            response.getWriter().write(result.toString());
        } else if (entry.getValue().isAnnotationPresent(PostMapping.class)) {
            String url = entry.getValue().getAnnotation(PostMapping.class).url();
            response.setContentType("text/html; UTF=8");
            response.getWriter().write(result.toString());
        }
    }
}

