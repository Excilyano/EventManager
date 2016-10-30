package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
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
	    CriteriaQuery<Event> all = cq.select(rootEntry)
	    								.where(criteriaBuilder.equal(rootEntry.get("creator"), user));
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
}
