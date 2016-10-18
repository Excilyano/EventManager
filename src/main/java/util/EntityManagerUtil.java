package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
public class EntityManagerUtil {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManager getEntityManager(String persistenceUnit){
        if (em==null){
            buildEntityManager(persistenceUnit);
        }
        return em;
    }

    private static void buildEntityManager(String persistenceUnit) {
        emf = Persistence.createEntityManagerFactory(persistenceUnit);
        em = emf.createEntityManager();
    }

    public static void close(){
        em.close();
        emf.close();
    }
}
