package hw.annotated;

import hw.annotations.Start;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class Annotated {
    public Set<Class<?>> getAnnotatedClasses() {
        return new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forJavaClassPath())
        ).getTypesAnnotatedWith(Start.class);
    }
}
