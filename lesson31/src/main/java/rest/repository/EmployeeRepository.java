package rest.repository;

import rest.entity.Employee;
import lombok.Builder;
import org.hibernate.Session;

import java.util.List;

import static rest.util.hibernate.HQLQuery.SELECT_ALL_EMPLOYEES;

@Builder
public class EmployeeRepository implements Repository<Employee> {
    private final Session session;
    @Override
    public List<Employee> findAll() {
        return session.createQuery(SELECT_ALL_EMPLOYEES, Employee.class).getResultList();
    }
}
