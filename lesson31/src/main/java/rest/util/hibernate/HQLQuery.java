package rest.util.hibernate;

public final class HQLQuery {
    public static final String SELECT_ALL_EMPLOYEES = "select e from Employee e inner join e.department order by e.department.id";
    public static final String SELECT_ALL_DEPARTMENTS = "FROM Department ORDER BY id";
}
