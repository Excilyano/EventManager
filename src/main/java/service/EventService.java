package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import entities.Event;
import entities.User;

/**
 * Created by Steeve Sinigaglia on 18/10/2016.
 */
public class EventService extends CrudAbstractServiceImpl<Event> {
	public List<Event> getPersonalEvent(User user) {
		CriteriaQuery<Event> cq = criteriaBuilder.createQuery(Event.class);
		Root<Event> rootEntry = cq.from(Event.class);
		CriteriaQuery<Event> all = cq.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("creator"), user));
		TypedQuery<Event> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	public List<Event> getParticipateEvent(User user) {
		List<Event> listEvents = new ArrayList<Event>();
		if (null != user) {
			listEvents.addAll(user.getEvents());
		}
		return listEvents;
	}

	/**
	 * Return true if the user is removed, if the user is not initially in the
	 * event, will return false;
	 * 
	 * @param idEvent
	 *            the id of the event
	 * @param user
	 *            the user to remove from the event
	 * @return
	 */
	public boolean removeUserEvent(int idEvent, User user) {
		boolean bool = false;
		Event event = this.find(idEvent);
		Set<User> setUser = event.getParticipants();
		bool = setUser.remove(user);
		event.setParticipants(setUser);
		this.update(event);
		return bool;
	}

	/**
	 * Return true if the user is add into the event if the user is already in
	 * the event, will return false;
	 * 
	 * @param idEvent
	 *            the id of the event
	 * @param user
	 *            the user to remove from the event
	 * @return
	 */
	public boolean addUserEvent(int idEvent, User user) {
		boolean bool = false;
		Event event = this.find(idEvent);
		Set<User> setUser = event.getParticipants();
		bool = setUser.add(user);
		event.setParticipants(setUser);
		this.update(event);
		return bool;
	}
}
