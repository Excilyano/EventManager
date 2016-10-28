package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Event;
import entities.User;
import service.EventService;
import service.UserService;

public class CreateEventController extends HttpServlet {
	/**
	 * Auto-generated uid
	 */
	private static final long serialVersionUID = -3765777634990243190L;

	private static final String viewCreateEventJSP = "/WEB-INF/views/user/createEvent.jsp";
	private static final String viewDisplayEventJSP = "/WEB-INF/views/user/createEvent.jsp";
	private static final String PARAM_TITLE = "title";
	private static final String PARAM_DESCRIPTION = "description";
	private static final String PARAM_LOCATION = "location";
	private static final String PARAM_DATEBEG = "datetimepickerfirst";
	private static final String PARAM_DATEEND = "datetimepickerend";
	private static final String PARAM_VISIBILITY = "visibility";
	private static final UserService userService = new UserService();
	private static final EventService eventService = new EventService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO
		this.getServletContext().getRequestDispatcher(viewCreateEventJSP).forward(request, response);
		System.out.println("passed here");
	}

	/**
	 * Method doPost Treatment of event's creation by searching the user and
	 * creating an event into the db After creating the event, the user will be
	 * redirect into the displayEvent view If a problem occurs during the
	 * parsing, the viewer will be redirect to the createEvent view with a
	 * errorMsg
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO

		String title = request.getParameter(PARAM_TITLE);
		String description = request.getParameter(PARAM_DESCRIPTION);
		String location = request.getParameter(PARAM_LOCATION);
		String dateBeginning = request.getParameter(PARAM_DATEBEG);
		String dateEnding = request.getParameter(PARAM_DATEEND);
		String idUser = request.getSession().getAttribute("sessionUser").toString();

		boolean isHidden = false;
		if ((request.getParameter(PARAM_VISIBILITY) != null))
			isHidden = true;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			Date startingDate = simpleDateFormat.parse(dateBeginning);
			Date endDate = simpleDateFormat.parse(dateEnding);
			int id = Integer.parseInt(idUser);

			User creator = userService.find(id);
			Event event = new Event(title, location, startingDate, endDate, creator);

			if (description != null)
				event.setDescription(description);
			event.setHidden(isHidden);

			request.setAttribute(PARAM_TITLE, title);
			request.setAttribute(PARAM_DESCRIPTION, description);
			request.setAttribute(PARAM_LOCATION, location);

			eventService.create(event);
			request.setAttribute("msgSucess",
					"Succés de la création de l'événement qui peut être consulté via le lien : ");
			this.getServletContext().getRequestDispatcher(viewDisplayEventJSP).forward(request, response);

		} catch (ParseException ex) {
			System.out.println("Exception " + ex);
			request.setAttribute("msgError", "Erreur lors du traitement de la création de l'évènement.");
			request.setAttribute(PARAM_TITLE, title);
			request.setAttribute(PARAM_DESCRIPTION, description);
			request.setAttribute(PARAM_LOCATION, location);

			this.getServletContext().getRequestDispatcher(viewCreateEventJSP).forward(request, response);
		}
	}

	public void init() throws ServletException {
		super.init();
	}

}