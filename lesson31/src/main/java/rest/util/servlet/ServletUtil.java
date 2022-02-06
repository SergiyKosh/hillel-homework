package rest.util.servlet;

import jakarta.servlet.http.HttpServletResponse;
import rest.dao.Dao;
import rest.entity.Department;
import rest.entity.Employee;
import rest.repository.Repository;

import java.io.IOException;

public final class ServletUtil {
    public static void writeStatus(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; UTF=8");
        response.getWriter().write(String.valueOf(response.getStatus()));
    }

    public static Dao<Employee> EMPLOYEE_DAO;
    public static Dao<Department> DEPARTMENT_DAO;
    public static Repository<Employee> EMPLOYEE_REPOSITORY;
    public static Repository<Department> DEPARTMENT_REPOSITORY;

    private ServletUtil() {
    }
}
