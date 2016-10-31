package service;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Event;
import entities.User;

public class UserServiceTest {
	public static EventService serviceEvent = new EventService();
	public static UserService serviceUser = new UserService();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// On vide la base
		TestsUtil.viderDatabase();
		User user1 = new User("Etienne", "CASSIN", "et.cassin@gmail.com", "abcde");
		new Event("Test1", "Location1", new Date(), new Date(), user1);
		new Event("Test2", "Location2", new Date(), new Date(), user1);
		UserServiceTest.serviceUser.create(user1);
	}

	@Test
	public void testerCheckMailExist() {
		Assert.assertTrue(UserServiceTest.serviceUser.checkEmailExist("et.cassin@gmail.com"));
		Assert.assertFalse(UserServiceTest.serviceUser.checkEmailExist("et.cassin"));
		Assert.assertFalse(UserServiceTest.serviceUser.checkEmailExist("@gmail.com"));
	}
	
	@Test
	public void testerCheckLogin() {
		Assert.assertNotNull(UserServiceTest.serviceUser.checkLogin("et.cassin@gmail.com", "abcde"));
		Assert.assertNull(UserServiceTest.serviceUser.checkLogin("et.cassin@gmail.com", "abc"));
		Assert.assertNull(UserServiceTest.serviceUser.checkLogin("et.cassin@gmail.com", "cde"));
	}
	
	@Test
	public void testerRemoveUserEvent() {
		User user = UserServiceTest.serviceUser.find(1);
		boolean result = UserServiceTest.serviceUser.removeUserEvent(
							UserServiceTest.serviceEvent.find(1),
							user);
		user = UserServiceTest.serviceUser.find(1);
		Assert.assertTrue(result);
		Assert.assertEquals(1, user.getEvents().size());
		Assert.assertTrue(user.getEvents().contains(UserServiceTest.serviceEvent.find(2)));
		new Event("Test1", "Location1", new Date(), new Date(), user);
		UserServiceTest.serviceUser.update(user);
	}
	
	@Test
	public void testerAddUserEvent() {
		User user = UserServiceTest.serviceUser.find(1);
		Event evt = new Event("Test3", "Location3", new Date(System.currentTimeMillis() + 200_000), new Date(), user);
		UserServiceTest.serviceUser.addUserEvent(evt, user);
		user = UserServiceTest.serviceUser.find(1);
		Assert.assertEquals(3, user.getEvents().size());
		Assert.assertTrue(user.getEvents().contains(UserServiceTest.serviceEvent.find(2)));
	}
}