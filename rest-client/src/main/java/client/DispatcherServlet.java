package client;

import client.core.annotation.DeleteMapping;
import client.core.annotation.GetMapping;
import client.core.annotation.PostMapping;
import client.core.annotation.PutMapping;
import client.core.controller.Controller;
import client.util.servlet.HttpMethod;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
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

        Set<Class<?>> entries = new Reflections("client.controller")
                .getTypesAnnotatedWith(Controller.class);

        HashMap.SimpleEntry<? extends Class<?>, Method> entry = entries.stream()
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
                    return new HashMap.SimpleEntry<>(clazz, mapping);
                })
                .findFirst()
                .orElseThrow(RuntimeException::new);


        Method mappingMethod = entry.getValue();
        Class<?> mappingClass = entry.getKey();
        Object result = mappingMethod.invoke(mappingClass.getConstructor().newInstance(), request, response);

        if (!result.toString().endsWith(".jsp")) {
            result += ".jsp";
        }

        if (mappingMethod.isAnnotationPresent(GetMapping.class)) {
            request.getRequestDispatcher(result.toString()).forward(request, response);
        } else if (mappingMethod.isAnnotationPresent(PostMapping.class)) {
        } else if (mappingMethod.isAnnotationPresent(PutMapping.class)) {
        } else if (mappingMethod.isAnnotationPresent(DeleteMapping.class)) {
        }
    }
}
