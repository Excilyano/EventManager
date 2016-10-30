package controller;

import entities.Event;
import entities.User;
import service.EventService;
import service.UserService;

import java.io.IOException;

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
                System.out.println("update here");
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
            default:
                this.getServletContext().getNamedDispatcher("logger").forward(request, response);
                break;
        }
    }

    public void init() throws ServletException {
        super.init();
        eventService = new EventService();
        userService = new UserService();
    }
}
