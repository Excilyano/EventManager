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
	public static Date dateAnterieure = new Date(System.currentTimeMillis() - 100_000);
	public static Date datePosterieure1 = new Date(System.currentTimeMillis() + 100_000);
	public static Date datePosterieure2 = new Date(System.currentTimeMillis() + 200_000);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// On vide la base
		TestsUtil.viderDatabase();
		// On cree les donnees generiques de test
		// L'utilisateur 1 est le createur des evenements 1,2&3
		// L'utilisateur 2 participe a l'evenement 2
		// L'evenement 2 est cree comme plus recent que l'evenement 1 pour les tests de tri
		// L'evenement 3 ne doit jamais ressortir car il est deja termine
		User user1 = new User("Etienne", "CASSIN", "et.cassin@gmail.com", "a");
		User user2 = new User("Kévin", "KEOVILAY", "b@b.com", "b");
		
		Event event1 = new Event("Test1", "Location1", EventServiceTest.datePosterieure2, EventServiceTest.datePosterieure2, user1);
		Event event2 = new Event("Test2", "Location2", EventServiceTest.datePosterieure1, EventServiceTest.datePosterieure1, user1);
		Event event3 = new Event("Test3", "Location3", new Date(), EventServiceTest.dateAnterieure, user1);

		user2.addEvent(event2);
		
		EventServiceTest.serviceUser.create(user1);
		EventServiceTest.serviceUser.create(user2);
		EventServiceTest.serviceEvent.create(event1);
		EventServiceTest.serviceEvent.create(event2);
		EventServiceTest.serviceEvent.create(event3);
		
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
		Assert.assertEquals("Test2", events.get(0).getTitle());
		Assert.assertEquals("Test1", events.get(1).getTitle());
		
		user = EventServiceTest.serviceUser.find(2);
		events = EventServiceTest.serviceEvent.getPersonalEvent(user);
		Assert.assertEquals(0, events.size());
	}
	
	@Test
	public void testerParticipateEvent() {
		User user = EventServiceTest.serviceUser.find(1);
		List<Event> events = EventServiceTest.serviceEvent.getParticipateEvent(user);
		Assert.assertEquals(2, events.size());
		Assert.assertEquals("Test2", events.get(0).getTitle());
		Assert.assertEquals("Test1", events.get(1).getTitle());
		
		user = EventServiceTest.serviceUser.find(2);
		events = EventServiceTest.serviceEvent.getParticipateEvent(user);
		Assert.assertEquals(1, events.size());
		Assert.assertEquals("Test2", events.get(0).getTitle());
	}
	
	@Test
	public void testerAllEvent() {
		List<Event> events = EventServiceTest.serviceEvent.getAllEvent();
		Assert.assertEquals(2, events.size());
		Assert.assertEquals("Test2", events.get(0).getTitle());
		Assert.assertEquals("Test1", events.get(1).getTitle());
	}
	
	@Test
	public void testerRemoveUserEvent() {
		User user2 = EventServiceTest.serviceUser.find(2);
		Event evt2 = EventServiceTest.serviceEvent.find(2);
		
		EventServiceTest.serviceEvent.removeUserEvent(evt2.getId(), user2);
		evt2 = EventServiceTest.serviceEvent.find(2);
		Assert.assertEquals(1, evt2.getParticipants().size());
		Assert.assertTrue(evt2.getParticipants().
				contains(EventServiceTest.serviceUser.find(1)));
		
		user2.addEvent(evt2);
		EventServiceTest.serviceEvent.create(evt2);
	}
	
	@Test
	public void testerAddUserEvent() {
		User user3 = new User("Steeve", "Sinigaglia", "steevy@gmail.com", "s");
		EventServiceTest.serviceUser.create(user3);
		user3 = EventServiceTest.serviceUser.find(3);
		Event evt2 = EventServiceTest.serviceEvent.find(2);
		EventServiceTest.serviceEvent.addUserEvent(evt2.getId(), user3);
		Assert.assertTrue(evt2.getParticipants().contains(user3));
	}
}
