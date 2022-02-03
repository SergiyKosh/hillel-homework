package rest.dao;

public interface Dao<T> {
    void save(T entity);
    T get(long id);
    void update(T entity);
    void delete(T entity);
}
