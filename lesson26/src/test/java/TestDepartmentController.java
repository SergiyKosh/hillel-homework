import controllers.DepartmentController;
import dao.DepartmentSimpleDao;
import model.Department;
import org.junit.jupiter.api.Test;
import utils.Menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDepartmentController {
    @Test
    void testGet() {
        DepartmentController dc = new DepartmentController();
        DepartmentSimpleDao dsd = new DepartmentSimpleDao();
        Department department = Department.builder()
                .name("asdasd")
                .build();

        Long id = dsd.add(department);

        assertNotNull(dc.get(new Menu(), id));
    }
}
