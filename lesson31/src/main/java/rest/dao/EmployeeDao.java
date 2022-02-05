package rest.dao;

import lombok.Builder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rest.entity.Employee;

@Builder
public class EmployeeDao implements Dao<Employee> {
    private final Session session;

    @Override
    public void save(Employee entity) {
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }

    @Override
    public Employee get(long id) {
        return session.get(Employee.class, id);
    }

    @Override
    public void update(Employee entity) {
        Transaction transaction = session.beginTransaction();
        session.merge("Employee", entity);
        transaction.commit();
    }

    @Override
    public void delete(Employee entity) {
        Transaction transaction = session.getTransaction();
        session.delete(entity);
        transaction.commit();
    }
}
