package service;

import java.util.List;

import entities.Event;
import entities.User;

public abstract class TestsUtil {
	private static EventService serviceEvent = new EventService();
	private static UserService serviceUser = new UserService();
	
	public static void viderDatabase() {
		List<Event> events = TestsUtil.serviceEvent.findAll();
		for (Event event : events) {
			TestsUtil.serviceEvent.delete(event);
		}
		List<User> users = TestsUtil.serviceUser.findAll();
		for (User user : users) {
			TestsUtil.serviceUser.delete(user);
		}
	}
}
