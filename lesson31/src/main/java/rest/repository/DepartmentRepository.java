package rest.repository;

import rest.entity.Department;
import lombok.Builder;
import org.hibernate.Session;

import java.util.List;

import static rest.util.hibernate.HQLQuery.SELECT_ALL_DEPARTMENTS;

@Builder
public class DepartmentRepository implements Repository<Department> {
    private final Session session;

    @Override
    public List<Department> findAll() {
        return session.createQuery(SELECT_ALL_DEPARTMENTS, Department.class).getResultList();
    }
}
