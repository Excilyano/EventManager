import entities.Event;
import entities.User;
import service.EventService;
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
        EventService eventService = new EventService();

        User john = new User("Jonh","Do","john.do@gmail.com","azerty");
        userService.create(john);
        Event event = new Event("Training JEE","4 rue de nantes",new Date(), new Date(), john);
        eventService.create(event);

        for (Event e : eventService.findAll()) {
            System.out.println(e);
        }
        for (User user : userService.findAll()) {
            System.out.println(user);
        }

        EntityManagerUtil.close();

    }

}
