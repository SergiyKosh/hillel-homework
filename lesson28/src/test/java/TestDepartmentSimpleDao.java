import application.dao.DepartmentDatabaseDao;
import application.entities.Department;
import application.exceptions.DepartmentDaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDepartmentSimpleDao {
    private DepartmentDatabaseDao departmentSimpleDao;

    @BeforeEach
    void setup() {
        departmentSimpleDao = new DepartmentDatabaseDao();
    }

    @Test
    void testAdd() throws DepartmentDaoException {
        Department department = Department.builder()
                .name("Name")
                .build();

        Long id = departmentSimpleDao.add(department);

        assertNotNull(departmentSimpleDao.get(id));
    }

    @Test
    void testUpdate() throws DepartmentDaoException {
        Long id = 3L;
        Department department = Department.builder()
                .id(id)
                .name("Name")
                .build();

        departmentSimpleDao.update(department);

        assertEquals(department, departmentSimpleDao.get(3L));
    }

    @Test
    void testDelete() throws DepartmentDaoException {
        Long id = 4L;

        departmentSimpleDao.delete(id);

        assertNull(departmentSimpleDao.get(id));
    }

    @Test
    void testGet() throws DepartmentDaoException {
        Long id = 4L;

        boolean isPresent = departmentSimpleDao.findAll().stream()
                .anyMatch(department -> department.getId().equals(id));

        if (isPresent) {
            assertNotNull(departmentSimpleDao.get(id));
        } else {
            assertNull(departmentSimpleDao.get(id));
        }
    }

    @Test
    void testFindAll() throws DepartmentDaoException {
        Department department = Department.builder()
                .name("Name")
                .build();

        departmentSimpleDao.add(department);

        assertFalse(departmentSimpleDao.findAll().isEmpty());
    }
}
