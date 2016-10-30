package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
		System.out.println(user);
		CriteriaQuery<Event> cq = criteriaBuilder.createQuery(Event.class);
	    Root<Event> rootEntry = cq.from(Event.class);
	    CriteriaQuery<Event> all = cq.select(rootEntry)
	    								.where(criteriaBuilder.and(
	    										criteriaBuilder.equal(rootEntry.get("creator"), user)),
	    										criteriaBuilder.greaterThanOrEqualTo(rootEntry.get("endDate"), new Date()));
	    TypedQuery<Event> allQuery = em.createQuery(all);
	    List<Event> result = allQuery.getResultList();
	    Collections.sort(result);
	    return result;
	}
	
	public List<Event> getParticipateEvent(User user) {
		List<Event> listEvents = new ArrayList<Event>();
		if (null != user) {
			listEvents.addAll(user.getEvents());
			for (int i=listEvents.size() -1; i > -1; i--) {
				Event current = listEvents.get(i);
				if (current.getEndDate().compareTo(new Date()) < 0) {
					listEvents.remove(i);
				}
			}
		}
		Collections.sort(listEvents);
	    return listEvents;
	}
	
	public List<Event> getAllEvent() {
		List<Event> evenementsAll = this.findAll();
		for (int i=evenementsAll.size() -1; i > -1; i--) {
			Event current = evenementsAll.get(i);
			if (current.getEndDate().compareTo(new Date()) < 0) {
				evenementsAll.remove(i);
			}
		}
		Collections.sort(evenementsAll);
	    return evenementsAll;
	}
}
