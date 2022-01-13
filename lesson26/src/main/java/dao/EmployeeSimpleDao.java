package dao;

import model.Employee;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.SQLQueries.*;

public class EmployeeSimpleDao implements EmployeeDao {
    private List<Employee> employees;
    private Employee employee;

    public EmployeeSimpleDao() {
        employees = new ArrayList<>();
    }

    @Override
    public Long add(Employee employee) {
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(ADD_EMPLOYEE)) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setLong(3, employee.getDepartmentId());
            ps.setLong(4, employee.getChiefId());
            ps.executeUpdate();
            employees.add(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee.getId();
    }

    @Override
    public void update(Employee employee) {
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(UPDATE_EMPLOYEE_WHERE_ID)) {

            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setLong(3, employee.getDepartmentId());
            ps.setLong(4, employee.getChiefId());
            ps.setLong(5, employee.getId());
            ps.executeUpdate();
            employees = findAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(DELETE_EMPLOYEE)) {

            ps.setLong(1, id);
            ps.executeUpdate();
            employees = findAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee get(Long id) {
        try (
                PreparedStatement ps = DatabaseConnection
                        .getConnection()
                        .prepareStatement(SELECT_FROM_EMPLOYEE_WHERE_ID)
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                employee = Employee.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .salary(rs.getInt("salary"))
                        .departmentId(rs.getLong("department_id"))
                        .chiefId(rs.getLong("chief_id"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    @Override
    public List<Employee> findAll() {
        employees = new ArrayList<>();
        try (Statement statement = DatabaseConnection.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_FROM_EMPLOYEE);

            while (rs.next()) {
                Employee employee = Employee.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .salary(rs.getInt("salary"))
                        .departmentId(rs.getLong("department_id"))
                        .chiefId(rs.getLong("chief_id"))
                        .build();
                employees.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }
}
