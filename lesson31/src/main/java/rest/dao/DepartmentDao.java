package rest.dao;

import rest.entity.Department;
import lombok.Builder;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Builder
public class DepartmentDao implements Dao<Department> {
    private final Session session;

    @Override
    public void save(Department entity) {
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }

    @Override
    public Department get(long id) {
        return session.get(Department.class, id);
    }

    @Override
    public void update(Department entity) {
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
    }

    @Override
    public void delete(Department entity) {
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }
}
