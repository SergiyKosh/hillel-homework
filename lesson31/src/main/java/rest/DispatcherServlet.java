package rest;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;
import rest.annotation.GetMapping;
import rest.annotation.PostMapping;
import rest.controller.Controller;

import java.io.IOException;
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
                .filter(clazz -> Arrays.stream(clazz.getDeclaredMethods())
                        .anyMatch(method -> method.isAnnotationPresent(GetMapping.class))
                ).map(clazz -> {
                    Method mapping = Arrays.stream(clazz.getDeclaredMethods())
                            .filter(method -> method.isAnnotationPresent(GetMapping.class)
                                    && (method.getAnnotation(GetMapping.class).url()).equals(uri)
                                    && request.getMethod().equals("GET"))
                            .findFirst()
                            .orElseThrow(RuntimeException::new);
                    return new AbstractMap.SimpleEntry<>(clazz, mapping);
                })
                .findFirst()
                .orElseThrow(RuntimeException::new);

        Object result = entry.getValue().invoke(entry.getKey().getConstructor().newInstance(), request, response);
        String url = entry.getValue().getAnnotation(GetMapping.class).url();
        if (entry.getValue().isAnnotationPresent(GetMapping.class)) {
            response.setContentType("application/json; UTF-8");
            response.getWriter().write(result.toString());
        } else if (entry.getValue().isAnnotationPresent(PostMapping.class)) {

        }
    }
}
