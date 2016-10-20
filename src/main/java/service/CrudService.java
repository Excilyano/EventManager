package service;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
public interface CrudService<T> {

    T find(Object id);

    void delete(int id);

    void delete(T entity);

    void create(T entity);

    T update(T entity);

}
