package client.core.annotated;

import client.core.annotation.mapping.GetMapping;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.Set;

public class SearchAnnotatedClass {
    public static Set<Method> getAnnotatedMethods() {
        return new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forJavaClassPath())
        ).getMethodsAnnotatedWith(GetMapping.class);
    }
}
