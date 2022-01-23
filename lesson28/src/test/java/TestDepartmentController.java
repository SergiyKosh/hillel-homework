import application.controllers.DepartmentController;
import application.dao.DepartmentDatabaseDao;
import application.entities.Department;
import application.exceptions.DepartmentBusinessException;
import application.exceptions.DepartmentDaoException;
import application.exceptions.EmployeeBusinessException;
import org.junit.jupiter.api.Test;
import application.utils.Menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDepartmentController {
    @Test
    void testGet() throws DepartmentBusinessException, DepartmentDaoException, EmployeeBusinessException {
        DepartmentController dc = new DepartmentController();
        DepartmentDatabaseDao dsd = new DepartmentDatabaseDao();
        Department department = Department.builder()
                .name("asdasd")
                .build();

        Long id = dsd.add(department);

        assertNotNull(dc.get(new Menu(), id));
    }
}
