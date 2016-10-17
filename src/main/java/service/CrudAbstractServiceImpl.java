package service;

import util.EntityManagerUtil;

import javax.persistence.EntityManager;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
public class CrudAbstractServiceImpl<T> implements CrudService<T> {
    protected EntityManager em;
    protected Class<T> entityClass;

    protected CrudAbstractServiceImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        em = EntityManagerUtil.getEntityManager("td");
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