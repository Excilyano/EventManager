package service;

import util.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
public abstract class CrudAbstractServiceImpl<T> implements CrudService<T> {
    protected EntityManager em;
    protected Class<T> entityClass;

    public CrudAbstractServiceImpl() {
        System.out.println(getClass());
        entityClass = (Class<T>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
        em = EntityManagerUtil.getEntityManager("h2-event");
    }

    @Override
    public T find(Object id) {
        return em.find(entityClass, id);
    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        em.remove(find(id));
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public void create(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public T update(T entity) {
        em.getTransaction().begin();
        T ent = em.merge(entity);
        em.getTransaction().commit();
        return ent;
    }
}