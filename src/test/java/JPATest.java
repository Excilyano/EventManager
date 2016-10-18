import entities.Event;
import entities.User;
import service.UserService;
import util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
public class JPATest {

    public static void main(String[] args) {
        EntityManager em = EntityManagerUtil.getEntityManager("h2-event");

        UserService userService = new UserService();

        User john = new User("Jonh","Do","john.do@gmail.com","azerty");

        System.out.println("Entity manager open ? " + em.isOpen());

//        em.getTransaction().begin();
//        em.persist(john);
//        em.getTransaction().commit();
        // REPLACE BY
        userService.create(john);

        Event event = new Event("Training JEE",new Date(), new Date(), john);
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();

        em.getTransaction().begin();
        List<Event> events = em.createQuery("from Event").getResultList();
        em.getTransaction().commit();
        for (Event e : events) {
            System.out.println(e);
        }


        System.out.println("liste des utilisateurs");
        for (User user : userService.findAll()) {
            System.out.println(user);
        }

        System.out.println("blabla");
        EntityManagerUtil.close();
        System.out.println("close");

    }

}
