package rest.core.annotationimpl;

import jakarta.servlet.http.HttpServletRequest;
import org.reflections.Reflections;
import rest.core.annotation.*;
import rest.util.servlet.HttpMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationImpl {
    private Set<Class<?>> getControllers() {
        return new Reflections("rest.controller").getTypesAnnotatedWith(Controller.class);
    }

    public Map.Entry<? extends Class<?>, Method> getMappingMethods(String uri, HttpServletRequest request) {
        return getControllers().stream()
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
                    } else if (request.getMethod().equals(HttpMethod.DELETE.name())) {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(method -> method.isAnnotationPresent(DeleteMapping.class)
                                        && method.getAnnotation(DeleteMapping.class).url().equals(uri));
                    } else if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
                        return Arrays.stream(clazz.getDeclaredMethods())
                                .anyMatch(method -> method.isAnnotationPresent(OptionsMapping.class)
                                        && method.getAnnotation(OptionsMapping.class).url().equals(uri));
                    } else {
                        throw new RuntimeException("Unknown request type");
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
                                } else if (method.isAnnotationPresent(DeleteMapping.class)) {
                                    return method.isAnnotationPresent(DeleteMapping.class)
                                            && method.getAnnotation(DeleteMapping.class).url().equals(uri);
                                } else {
                                    return method.isAnnotationPresent(OptionsMapping.class)
                                            && method.getAnnotation(OptionsMapping.class).url().equals(uri);
                                }
                            })
                            .filter(mappingM -> {
                                if (request.getMethod().equals(HttpMethod.GET.name())) {
                                    return mappingM.isAnnotationPresent(GetMapping.class);
                                } else if (request.getMethod().equals(HttpMethod.POST.name())) {
                                    return mappingM.isAnnotationPresent(PostMapping.class);
                                } else if (request.getMethod().equals(HttpMethod.PUT.name())) {
                                    return mappingM.isAnnotationPresent(PutMapping.class);
                                } else if (request.getMethod().equals(HttpMethod.DELETE.name())) {
                                    return mappingM.isAnnotationPresent(DeleteMapping.class);
                                } else {
                                    return mappingM.isAnnotationPresent(OptionsMapping.class);
                                }
                            })
                            .findFirst()
                            .orElseThrow(RuntimeException::new);
                    return new HashMap.SimpleEntry<>(clazz, mapping);
                })
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
