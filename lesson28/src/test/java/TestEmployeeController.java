import application.controllers.EmployeeController;
import application.dao.EmployeeDatabaseDao;
import application.entities.Employee;
import application.exceptions.DepartmentBusinessException;
import application.exceptions.EmployeeBusinessException;
import application.exceptions.EmployeeDaoException;
import org.junit.jupiter.api.Test;
import application.utils.Menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestEmployeeController {
    @Test
    void testGet() throws EmployeeDaoException, EmployeeBusinessException, DepartmentBusinessException {
        EmployeeController ec = new EmployeeController();
        EmployeeDatabaseDao esd = new EmployeeDatabaseDao();
        Employee employee = Employee.builder()
                .name("naasd")
                .salary(123)
                .departmentId(1L)
                .chiefId(2L)
                .build();

        Long id = esd.add(employee);

        assertNotNull(ec.get(new Menu(), id));
    }
}
