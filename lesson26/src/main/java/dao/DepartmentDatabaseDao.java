package dao;

import dbconnect.DatabaseConnection;
import entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.SQLQueries.*;

public class DepartmentDatabaseDao implements DepartmentDao {
    @Override
    public Long add(Department department) {
        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(ADD_DEPARTMENT)) {

            long id = 1 + findAll().stream()
                    .mapToLong(Department::getId)
                    .max()
                    .orElse(0);

            ps.setLong(1, id);
            ps.setString(2, department.getName());
            ps.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findAll().get(findAll().size() - 1).getId();
    }

    @Override
    public void update(Department department) {
        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_DEPARTMENT_WHERE_ID)) {

            ps.setString(1, department.getName());
            ps.setLong(2, department.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(DELETE_DEPARTMENT)) {

            ps.setLong(1, id);
            ps.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department get(Long id) {
        Department department = null;
        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_DEPARTMENT_WHERE_ID)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                department = Department.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return department;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_ALL_FROM_DEPARTMENT);

            while (rs.next()) {
                Department department = Department.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
                departments.add(department);
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return departments;
    }
}
