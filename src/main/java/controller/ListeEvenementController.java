package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = userService.find(request.getSession().getAttribute("sessionUser"));

		List<Event> evenementsPerso = this.evtService.getPersonalEvent(user);
		request.setAttribute("evenementsPerso", evenementsPerso);
		List<Event> evenementsParticipate = this.evtService.getParticipateEvent(user);
		request.setAttribute("evenementsParticipate", evenementsParticipate);
		List<Event> evenementsAll = this.evtService.getAllEvent();
		request.setAttribute("evenementsAll", evenementsAll);
		Map<Integer, Boolean> isRegister = new HashMap<>();
		for (Event e : evenementsAll) {
			if (e.getParticipants().contains(user))
				isRegister.put(e.getId(), true);
			else
				isRegister.put(e.getId(), false);
		}
		Map<Integer, Boolean> isCreator = new HashMap<>();
		for (Event e : evenementsPerso) {
			isCreator.put(e.getId(), true);
		}
		request.setAttribute("isRegister", isRegister);
		request.setAttribute("isCreator", isCreator);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/displayEvent.jsp").forward(request, response);
	}

	/**
	 * Traitement de l'inscription, désinscription de l'utilisateur à
	 * l'événement, et de la suppresion d'un event puis on redirige vers la page
	 * consultation empêchant ainsi des problème lié au rafraichissement de la
	 * page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("sessionUser") == null)
			response.sendRedirect("connexion.jspa");
		else {
			if (request.getParameter("button") != null) {
				if (request.getParameter("button").equals("registerAction"))
					this.registerAction(request, response);
				else if (request.getParameter("button").equals("deleteAction"))
					this.deleteAction(request, response);
				else if (request.getParameter("button").equals("updateAction"))
					this.updateAction(request, response);
			}
		}
	}

	private void updateAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String eventId = request.getParameter("eventId");
		response.sendRedirect("updateEvent.jspa?id=" + eventId);

	}

	public void init() throws ServletException {
		super.init();
		evtService = new EventService();
		userService = new UserService();
	}

	/**
	 * L'utiliser est ajouté ou supprimer à l'event
	 * 
	 * @param req
	 */
	private void registerAction(HttpServletRequest req, HttpServletResponse response) throws IOException {
		User user = userService.find(req.getSession().getAttribute("sessionUser"));
		if (req.getParameter("eventId") != null) {
			int idEvent = Integer.parseInt(req.getParameter("eventId"));
			Event event = evtService.find(idEvent);
			if (this.evtService.addUserEvent(idEvent, user)) {
				this.userService.addUserEvent(event, user);
			} else {
				this.evtService.removeUserEvent(idEvent, user);
				this.userService.removeUserEvent(event, user);
			}
		}
		response.sendRedirect("consultation.jspa");
	}

	/**
	 * Supprimer l'évenement
	 * 
	 * @param req
	 */
	private void deleteAction(HttpServletRequest req, HttpServletResponse response) throws IOException {
		if (req.getParameter("eventId") != null) {
			int idEvent = Integer.parseInt(req.getParameter("eventId"));
			Event event = evtService.find(idEvent);
			for (User u : event.getParticipants())
				this.userService.removeUserEvent(event, u);
			Set<User> set = new HashSet<>();
			event.setParticipants(set);
			this.evtService.update(event);
			this.evtService.delete(event);
		}
		response.sendRedirect("consultation.jspa");
	}
}