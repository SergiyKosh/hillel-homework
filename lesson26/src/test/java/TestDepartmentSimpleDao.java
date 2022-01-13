import dao.DepartmentSimpleDao;
import model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDepartmentSimpleDao {
    private DepartmentSimpleDao departmentSimpleDao;

    @BeforeEach
    void setup() {
        departmentSimpleDao = new DepartmentSimpleDao();
    }

    @Test
    void testAdd() {
        Department department = Department.builder()
                .name("Name")
                .build();

        Long id = departmentSimpleDao.add(department);

        assertNotNull(departmentSimpleDao.get(id));
    }

    @Test
    void testUpdate() {
        Long id = 3L;
        Department department = Department.builder()
                .id(id)
                .name("Name")
                .build();

        departmentSimpleDao.update(department);

        assertEquals(department, departmentSimpleDao.get(3L));
    }

    @Test
    void testDelete() {
        Long id = 4L;

        departmentSimpleDao.delete(id);

        assertNull(departmentSimpleDao.get(id));
    }

    @Test
    void testGet() {
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
    void testFindAll() {
        Department department = Department.builder()
                .name("Name")
                .build();

        departmentSimpleDao.add(department);

        assertFalse(departmentSimpleDao.findAll().isEmpty());
    }
}
