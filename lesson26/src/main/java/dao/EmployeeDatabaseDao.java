package dao;

import entities.Employee;
import dbconnect.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static utils.SQLQueries.*;

public class EmployeeDatabaseDao implements EmployeeDao {
    @Override
    public Long add(Employee employee) {
        Connection connection = DatabaseConnection.getConnection();
        Long employeeId = null;

        try (PreparedStatement ps = connection.prepareStatement(ADD_EMPLOYEE)) {

            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setLong(3, employee.getDepartmentId());

            if (Objects.isNull(employee.getChiefId())) {
                ps.setNull(4, Types.NULL);
            } else {
                ps.setLong(4, employee.getChiefId());
            }

            ps.executeUpdate();

            ResultSet gk = ps.getGeneratedKeys();

            if (gk.next()) {
                employeeId = gk.getLong("id");
            }

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employeeId;
    }

    @Override
    public void update(Employee employee) {
        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_WHERE_ID)) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setLong(3, employee.getDepartmentId());
            ps.setLong(4, employee.getChiefId());
            ps.setLong(5, employee.getId());
            ps.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE)) {

            ps.setLong(1, id);
            ps.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee get(Long id) {
        Employee employee = null;
        Connection connection = DatabaseConnection.getConnection();

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

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();

        try (Statement statement = connection.createStatement()) {
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

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }
}
