import hw.annotated.Annotated;
import hw.annotations.Start;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Annotated annotated = new Annotated();

        Set<Class<?>> names = annotated.getAnnotatedClasses();

        names.stream()
                .sorted((a, b) -> {
                    int first = a.getAnnotation(Start.class).priority();
                    int second = b.getAnnotation(Start.class).priority();
                    return Integer.compare(first, second);
                }).forEach(clazz -> {
                    try {
                        Method method = clazz.getDeclaredMethod(clazz.getAnnotation(Start.class).method());
                        method.invoke(clazz.getConstructor().newInstance());
                    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                        System.out.println(e.getMessage());
                    }
                });
    }
}
