package util;

import entities.Event;
import entities.User;
import service.EventService;
import service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
public class EntityManagerUtil {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManager getEntityManager(String persistenceUnit){
        if (em==null){
            buildEntityManager(persistenceUnit);
            buildDataTest();
        }
        return em;
    }

    private static void buildDataTest() {
        User user = new User("John","Do","a@a.com","a");
        UserService userService = new UserService();
        userService.create(user);

        Event event = new Event("J2ee","B117",new Date(),new Date(), user);
        EventService eventService = new EventService();
        eventService.create(event);
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
