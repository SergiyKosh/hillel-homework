package rest.util.hibernate;

import rest.entity.Department;
import rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import rest.util.PropertiesUtil;

public class HibernateConfiguration {
    private static final Configuration CONFIGURATION = new Configuration();
    private static volatile Session SESSION;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        CONFIGURATION.addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class);
    }

    public static Session getSession() {
        if (SESSION != null) {
            return SESSION;
        }

        SESSION = CONFIGURATION.buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(PropertiesUtil.getAllProperties())
                                .build()
                ).openSession();
        return SESSION;
    }
}
