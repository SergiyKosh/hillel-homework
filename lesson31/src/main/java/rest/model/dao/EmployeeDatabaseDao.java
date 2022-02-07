package rest.model.dao;

import rest.exceptions.EmployeeDaoException;
import rest.model.dbconnect.ConnectionFactory;
import rest.model.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static rest.util.SQLQueries.*;
import static rest.util.FieldsConst.*;

public class EmployeeDatabaseDao implements EmployeeDao {
    @Override
    public void add(Employee employee) throws EmployeeDaoException {
        try (
                Connection connection = ConnectionFactory.build().getConnection();
                PreparedStatement ps = connection.prepareStatement(ADD_EMPLOYEE)
        ) {

            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setLong(3, employee.getDepartmentId());

            if (Objects.isNull(employee.getChiefId())) {
                ps.setNull(4, Types.NULL);
            } else {
                ps.setLong(4, employee.getChiefId());
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new EmployeeDaoException(e);
        }
    }

    @Override
    public void update(Employee employee) throws EmployeeDaoException {
        try (
                Connection connection = ConnectionFactory.build().getConnection();
                PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_WHERE_ID)
        ) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setLong(3, employee.getDepartmentId());
            if (Objects.isNull(employee.getChiefId())) {
                ps.setLong(4, Types.NULL);
            } else {
                ps.setLong(4, employee.getChiefId());
            }
            ps.setLong(5, employee.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new EmployeeDaoException(e);
        }
    }

    @Override
    public void delete(Long id) throws EmployeeDaoException {
        try (
                Connection connection = ConnectionFactory.build().getConnection();
                PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE)
        ) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new EmployeeDaoException(e);
        }
    }

    @Override
    public Employee get(Long id) throws EmployeeDaoException {
        Employee employee = null;

        try (
                Connection connection = ConnectionFactory.build().getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_FROM_EMPLOYEE_WHERE_ID)
        ) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                employee = Employee.builder()
                        .id(rs.getLong(EMPLOYEE_ID))
                        .name(rs.getString(NAME))
                        .salary(rs.getInt(SALARY))
                        .departmentId(rs.getLong(DEPARTMENT_ID))
                        .chiefId(rs.getLong(CHIEF_ID))
                        .build();
            }

        } catch (SQLException e) {
            throw new EmployeeDaoException(e);
        }

        return employee;
    }

    @Override
    public List<Employee> findAll() throws EmployeeDaoException {
        List<Employee> employees = new ArrayList<>();

        try (
                Connection connection = ConnectionFactory.build().getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(SELECT_FROM_EMPLOYEE);

            while (rs.next()) {
                Employee employee = Employee.builder()
                        .id(rs.getLong(EMPLOYEE_ID))
                        .name(rs.getString(NAME))
                        .salary(rs.getInt(SALARY))
                        .departmentId(rs.getLong(DEPARTMENT_ID))
                        .chiefId(rs.getLong(CHIEF_ID))
                        .build();
                employees.add(employee);
            }

        } catch (SQLException e) {
            throw new EmployeeDaoException(e);
        }

        return employees;
    }
}
