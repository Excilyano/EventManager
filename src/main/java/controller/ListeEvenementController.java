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
	
	protected void doGet (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		List<Event> evenements = this.evtService.findAll();
		System.out.println(evenements.size());
		request.setAttribute("evenements", evenements);
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
		
		UserService userService = new UserService();
		User john = new User("Jonh","Do","john.do@gmail.com","azerty");
        userService.create(john);
		Event event = new Event("Training JEE",new Date(), new Date(), john);
		evtService.create(event);
	}
}