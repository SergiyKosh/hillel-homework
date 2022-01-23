import application.dao.EmployeeDatabaseDao;
import application.entities.Employee;
import application.exceptions.EmployeeDaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEmployeeSimpleDao {
    private EmployeeDatabaseDao employeeSimpleDao;

    @BeforeEach
    void setup() {
        employeeSimpleDao = new EmployeeDatabaseDao();
    }

    @Test
    void testAdd() throws EmployeeDaoException {
        Employee employee = Employee.builder()
                .name("Name")
                .salary(6000)
                .departmentId(1L)
                .chiefId(1L)
                .build();

        Long id = employeeSimpleDao.add(employee);

        assertNotNull(employeeSimpleDao.get(id));
    }

    @Test
    void testUpdate() throws EmployeeDaoException {
        Long id = 4L;
        Employee employee = Employee.builder()
                .id(id)
                .name("Name")
                .salary(6000)
                .departmentId(1L)
                .chiefId(1L)
                .build();

        employeeSimpleDao.update(employee);

        assertEquals(employee, employeeSimpleDao.get(4L));
    }

    @Test
    void testDelete() throws EmployeeDaoException {
        Long id = 5L;

        employeeSimpleDao.delete(id);

        assertNull(employeeSimpleDao.get(id));
    }

    @Test
    void testGet() throws EmployeeDaoException {
        Long id = 4L;

        boolean isPresent = employeeSimpleDao.findAll().stream()
                .anyMatch(employee -> employee.getId().equals(id));

        if (isPresent) {
            assertNotNull(employeeSimpleDao.get(id));
        } else {
            assertNotNull(employeeSimpleDao.get(id));
        }
    }

    @Test
    void testFindAll() throws EmployeeDaoException {
        Employee employee = Employee.builder()
                .name("Name")
                .salary(6000)
                .departmentId(1L)
                .chiefId(1L)
                .build();

        employeeSimpleDao.add(employee);

        assertFalse(employeeSimpleDao.findAll().isEmpty());
    }
}
