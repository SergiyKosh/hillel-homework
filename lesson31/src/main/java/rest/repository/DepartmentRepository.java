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
        List<Department> departments = session.createQuery(SELECT_ALL_DEPARTMENTS, Department.class).getResultList();
        session.clear();
        return departments;
    }
}
