package service;

import entities.Event;
import util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
public abstract class CrudAbstractServiceImpl<T> implements CrudService<T> {
    protected EntityManager em;
    protected Class<T> entityClass;
    protected CriteriaBuilder criteriaBuilder;

    protected CrudAbstractServiceImpl() {
        entityClass = (Class<T>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
        em = EntityManagerUtil.getEntityManager("h2-event");

        criteriaBuilder = em.getCriteriaBuilder();
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

    public List<T> findAll(){
        CriteriaQuery<T> cq = criteriaBuilder.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}