package controller;

import entities.Event;
import entities.User;
import service.EventService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Steeve Sinigaglia on 30/10/2016.
 */
public class ManageEventController extends AbstractController {

    private EventService eventService;
    private UserService userService;

    private final String viewCreateEventJSP = "/WEB-INF/views/user/createEvent.jsp";
    private final String viewDisplayEventJSP = "consultation.jspa";
    private final String PARAM_TITLE = "title";
    private final String PARAM_DESCRIPTION = "description";
    private final String PARAM_LOCATION = "location";
    private final String PARAM_DATEBEG = "datetimepickerfirst";
    private final String PARAM_DATEEND = "datetimepickerend";
    private final String PARAM_VISIBILITY = "visibility";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEvt = Integer.parseInt(req.getParameter("id"));
        Event event = eventService.find(idEvt);

        if (event.getCreator().getId() == (int) (req.getSession().getAttribute("sessionUser"))) {
            req.setAttribute("event", event);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dateBegin = simpleDateFormat.format(event.getStartingDate());
            String dateEnd = simpleDateFormat.format(event.getEndDate());

            req.setAttribute("dateBegin", dateBegin);
            req.setAttribute("dateEnd", dateEnd);
            req.setAttribute("isUpdate", true);

            this.getServletContext().getRequestDispatcher(viewCreateEventJSP).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter(PARAM_TITLE);
        String description = req.getParameter(PARAM_DESCRIPTION);
        String location = req.getParameter(PARAM_LOCATION);
        String dateBeginning = req.getParameter(PARAM_DATEBEG);
        String dateEnding = req.getParameter(PARAM_DATEEND);
        String idUser = req.getSession().getAttribute("sessionUser").toString();


        boolean isHidden = true;
        if ((req.getParameter(PARAM_VISIBILITY) != null))
            isHidden = false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date startingDate = simpleDateFormat.parse(dateBeginning);
            Date endDate = simpleDateFormat.parse(dateEnding);
            int id = Integer.parseInt(idUser);

            int idEvt = Integer.parseInt(req.getParameter("id"));
            Event event = eventService.find(idEvt);

            event.setTitle(title);
            event.setDescription(description);
            event.setLocation(location);
            event.setStartingDate(startingDate);
            event.setEndDate(endDate);
            event.setHidden(isHidden);


            eventService.update(event);
            resp.sendRedirect(viewDisplayEventJSP);
        } catch (ParseException ex) {
            System.out.println("Exception " + ex);
            req.setAttribute("msgError", "Erreur lors du traitement de la création de l'évènement.");
            req.setAttribute(PARAM_TITLE, title);
            req.setAttribute(PARAM_DESCRIPTION, description);
            req.setAttribute(PARAM_LOCATION, location);

            this.getServletContext().getRequestDispatcher(viewCreateEventJSP).forward(req, resp);
        }
    }

    @Override
    public void init() throws ServletException {
        eventService = new EventService();
        userService = new UserService();
    }
}
