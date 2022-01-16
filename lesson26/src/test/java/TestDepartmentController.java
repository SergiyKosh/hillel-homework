import controllers.DepartmentController;
import dao.DepartmentDatabaseDao;
import entities.Department;
import org.junit.jupiter.api.Test;
import utils.Menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDepartmentController {
    @Test
    void testGet() {
        DepartmentController dc = new DepartmentController();
        DepartmentDatabaseDao dsd = new DepartmentDatabaseDao();
        Department department = Department.builder()
                .name("asdasd")
                .build();

        Long id = dsd.add(department);

        assertNotNull(dc.get(new Menu(), id));
    }
}
