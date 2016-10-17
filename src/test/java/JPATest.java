import entities.Event;
import entities.User;

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
        EntityManager em = emf.createEntityManager();

        User john = new User("Jonh","Do","john.do@gmail.com","azerty");

        System.out.println("Entity manager open ? " + em.isOpen());

        em.getTransaction().begin();
        em.persist(john);
        em.getTransaction().commit();

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


        em.getTransaction().begin();
        List<User> users = em.createQuery("from User").getResultList();
        em.getTransaction().commit();
        for (User u : users) {
            System.out.println(u);
        }
        em.close();
        emf.close();


    }

}
