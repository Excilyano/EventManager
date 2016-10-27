package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Event;
import entities.User;
import service.EventService;
import service.UserService;

public class ListeEvenementController extends HttpServlet {
	/**
	 * Auto-generated uid
	 */
	private static final long serialVersionUID = -3765777634990243190L;

	private EventService evtService;
	private UserService userService;
	
	protected void doGet (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("sessionUser", 2);
		User user = userService.find(request.getSession().getAttribute("sessionUser"));
		List<Event> evenementsPerso = this.evtService.getPersonalEvent(user);
		request.setAttribute("evenementsPerso", evenementsPerso);
		List<Event> evenementsParticipate = this.evtService.getParticipateEvent(user);
		request.setAttribute("evenementsParticipate", evenementsParticipate);
		List<Event> evenementsAll = this.evtService.findAll();
		request.setAttribute("evenementsAll", evenementsAll);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/displayEvent.jsp" ).forward( request, response );
	}
	
	protected void doPost (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// TODO
	}
	
	public void init() throws ServletException {
		super.init();
		evtService = new EventService();
		userService = new UserService();
		
		User john = new User("Jonh","Do","john.do@gmail.com","azerty");
        userService.create(john);
		Event event = new Event("Training JEE",new Date(), new Date(), john);
		evtService.create(event);
	}
}