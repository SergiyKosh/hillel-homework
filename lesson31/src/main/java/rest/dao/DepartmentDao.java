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
        Transaction transaction = session.getTransaction();
        session.save(entity);
        transaction.commit();
        session.clear();
    }

    @Override
    public Department get(long id) {
        session.clear();
        return session.get(Department.class, id);
    }

    @Override
    public void update(Department entity) {
        Transaction transaction = session.getTransaction();
        session.merge(entity);
        transaction.commit();
        session.clear();
    }

    @Override
    public void delete(Department entity) {
        Transaction transaction = session.getTransaction();
        session.delete(entity);
        transaction.commit();
        session.clear();
    }
}
