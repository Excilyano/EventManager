package controller;

import entities.Event;
import entities.User;
import service.EventService;
import service.UserService;
import util.EntityManagerUtil;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher extends HttpServlet {
    /**
     * Auto-generated serial version uid
     */
    private static final long serialVersionUID = 1392340765071245696L;

    private EventService eventService;
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch(request, response);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] uriSplitted = request.getRequestURI().split("/");


        String page = uriSplitted[uriSplitted.length - 1];

        switch (page) {
            case "connexion.jspa":
                this.getServletContext().getNamedDispatcher("logger").forward(request, response);
                break;
            case "inscription.jspa":
                this.getServletContext().getNamedDispatcher("register").forward(request, response);
                break;
            case "consultation.jspa":
                this.getServletContext().getNamedDispatcher("displayer").forward(request, response);
                break;
            case "ajout.jspa":
                this.getServletContext().getNamedDispatcher("manager").forward(request, response);
                break;
            case "creationEvenement.jspa":
                this.getServletContext().getNamedDispatcher("createEvent").forward(request, response);
                break;
            case "updateEvent.jspa":
                int idEvt = Integer.parseInt(request.getParameter("id"));
                Event event = eventService.find(idEvt);
                if (request.getSession().getAttribute("sessionUser") == null) {
                    response.sendRedirect("connexion.jspa");
                } else if (event.getCreator().equals(userService.find(Integer.parseInt(request.getSession().getAttribute("sessionUser").toString())))) {
                    this.getServletContext().getNamedDispatcher("updateEvent").forward(request, response);
                } else {
                    response.sendRedirect("consultation.jspa");
                }
                break;
            case "logout.jspa":
                request.getSession().invalidate();
                response.sendRedirect("connexion.jspa");
                break;
            default:
                this.getServletContext().getNamedDispatcher("logger").forward(request, response);
                break;
        }
    }

    public void init() throws ServletException {
        super.init();

        eventService = new EventService();
        userService = new UserService();

        User user = new User("John","Do","a@a.com","a");
        userService.create(user);

        userService.create(new User("a","a","azerty@gmail.com","a"));

        Event eventA = new Event("J2eeA","B117",new Date(2016-1900,10,02),new Date(2016-1900,11,02), user);
        eventA.setHidden(false);

        Event eventB = new Event("J2eeB","B117",new Date(2016-1900,10,03),new Date(2016-1900,11,03), user);
        eventB.setHidden(true);
        eventService.create(eventA);
    }

    @Override
    public void destroy() {
        super.destroy();
        EntityManagerUtil.close();
    }
}
