package dao;

import model.Department;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.SQLQueries.*;

public class DepartmentSimpleDao implements DepartmentDao {
    private List<Department> departments;
    private Department department;

    public DepartmentSimpleDao() {
        this.departments = new ArrayList<>();
    }

    @Override
    public Long add(Department department) {
        departments = new ArrayList<>();

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(ADD_DEPARTMENT)) {

            long id = 1 + findAll().stream()
                    .mapToLong(Department::getId)
                    .max()
                    .orElse(0);

            ps.setLong(1, id);
            ps.setString(2, department.getName());
            ps.executeUpdate();
            departments = findAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findAll().get(findAll().size() - 1).getId();
    }

    @Override
    public void update(Department department) {
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(UPDATE_DEPARTMENT_WHERE_ID)) {

            ps.setString(1, department.getName());
            ps.setLong(2, department.getId());
            ps.executeUpdate();
            departments = findAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(DELETE_DEPARTMENT)) {

            ps.setLong(1, id);
            ps.executeUpdate();
            departments = findAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department get(Long id) {
        Department department = null;

        try (
                PreparedStatement ps = DatabaseConnection
                        .getConnection()
                        .prepareStatement(SELECT_FROM_DEPARTMENT_WHERE_ID)
        ) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                department = Department.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return department;
    }

    @Override
    public List<Department> findAll() {
        departments = new ArrayList<>();

        try (Statement statement = DatabaseConnection.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_ALL_FROM_DEPARTMENT);

            while (rs.next()) {
                department = Department.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return departments;
    }
}
