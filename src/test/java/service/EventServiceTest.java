package service;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Event;
import entities.User;

public class EventServiceTest {
	public static EventService serviceEvent = new EventService();
	public static UserService serviceUser = new UserService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// On cree les donnees generiques de test
		// L'utilisateur 1 est le createur des evenements 1&2
		// L'utilisateur 2 participe a l'evenement 2
		User user1 = new User("Etienne", "CASSIN", "et.cassin@gmail.com", "a");
		User user2 = new User("Kévin", "KEOVILAY", "b@b.com", "b");
		
		Event event1 = new Event("Test1", "Location1", new Date(), new Date(), user1);
		Event event2 = new Event("Test2", "Location2", new Date(), new Date(), user1);

		user2.addEvent(event2);
		
		EventServiceTest.serviceUser.create(user1);
		EventServiceTest.serviceUser.create(user2);
		EventServiceTest.serviceEvent.create(event1);
		EventServiceTest.serviceEvent.create(event2);
		
		List<User> verifUser = EventServiceTest.serviceUser.findAll();
		List<Event> verifEvent = EventServiceTest.serviceEvent.findAll();
		
		if (verifUser == null || verifEvent == null)
			Assert.fail("Les listes d'utilisateurs et d'evenements ne devraient pas etre null.");
		else if (verifUser.size() < 1 || verifEvent.size() < 1)
			Assert.fail("Les listes d'utilisateurs et d'evenements ne devraient pas etre vides.");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testerPersonalEvent() {
		User user = EventServiceTest.serviceUser.find(1);
		List<Event> events = EventServiceTest.serviceEvent.getPersonalEvent(user);
		Assert.assertEquals(2, events.size());
		Assert.assertEquals("Test1", events.get(0).getTitle());
		Assert.assertEquals("Test2", events.get(1).getTitle());
		
		user = EventServiceTest.serviceUser.find(2);
		events = EventServiceTest.serviceEvent.getPersonalEvent(user);
		Assert.assertEquals(0, events.size());
	}
	
	@Test
	public void testerParticipateEvent() {
		User user = EventServiceTest.serviceUser.find(1);
		List<Event> events = EventServiceTest.serviceEvent.getParticipateEvent(user);
		Assert.assertEquals(2, events.size());
		Assert.assertEquals("Test1", events.get(0).getTitle());
		Assert.assertEquals("Test2", events.get(1).getTitle());
		
		user = EventServiceTest.serviceUser.find(2);
		events = EventServiceTest.serviceEvent.getParticipateEvent(user);
		Assert.assertEquals(1, events.size());
		Assert.assertEquals("Test2", events.get(0).getTitle());
	}
}
