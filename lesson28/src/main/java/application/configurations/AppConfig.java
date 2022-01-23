package application.configurations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    public static Properties properties;

    public static void init() {
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties = new Properties();

            if (input == null) {
                System.out.println("Unable to find config.properties");
                return;
            }

            properties.load(input);

            properties.entrySet().stream().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }
}
