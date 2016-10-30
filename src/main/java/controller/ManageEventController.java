package controller;

import entities.Event;
import service.EventService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Steeve Sinigaglia on 30/10/2016.
 */
public class ManageEventController extends AbstractController {

    EventService eventService;
    private final String viewCreateEventJSP = "/WEB-INF/views/user/createEvent.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEvt = Integer.parseInt(req.getParameter("id"));
        Event event = eventService.find(idEvt);

//        if (event.getCreator().equals(req.getSession().getAttribute("sessionUser"))){
            req.setAttribute("event",event);
            this.getServletContext().getRequestDispatcher(viewCreateEventJSP).forward(req, resp);
//        }

        System.out.println(event.isHidden());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        eventService = new EventService();
    }
}
