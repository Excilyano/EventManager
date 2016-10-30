package service;

import entities.Event;
import entities.User;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
public class UserService extends CrudAbstractServiceImpl<User> {

    //implements all custom request for this entity here

    /**
     * Vérifie si l'adresse email est déjà présente en base
     * @param email email à vérifier
     * @return true si l'email existe, false sinon
     */
    public boolean checkEmailExist(String email){
        TypedQuery<User> query = em.createQuery("select u from User u where u.email like :mEmail", User.class);
        query.setParameter("mEmail",email);
        return query.getResultList().isEmpty() ? false : true;
    }

    public User checkLogin(String email, String password){
        TypedQuery<User> query = em.createQuery("select u from User u where u.email like :mEmail and u.password like :mPassword",User.class);
        query.setParameter("mEmail",email);
        query.setParameter("mPassword",password);
        List<User> users = query.getResultList();
        if (users.isEmpty()){
            return null;
        }
        return users.get(0);
    }
    
    public boolean removeUserEvent(Event event, User user) {
		boolean bool = false;
		Set<Event> setEvent = user.getEvents();
		bool = setEvent.remove(event);
		user.setEvents(setEvent);
		this.update(user);
		return bool;
	}

	public boolean addUserEvent(Event event, User user) {
		boolean bool = false;
		Set<Event> setEvent = user.getEvents();
		bool = setEvent.add(event);
		user.setEvents(setEvent);
		this.update(user);
		return bool;
	}
}
