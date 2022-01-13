import controllers.EmployeeController;
import dao.EmployeeSimpleDao;
import model.Employee;
import org.junit.jupiter.api.Test;
import utils.Menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestEmployeeController {
    @Test
    void testGet() {
        EmployeeController ec = new EmployeeController();
        EmployeeSimpleDao esd = new EmployeeSimpleDao();
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
