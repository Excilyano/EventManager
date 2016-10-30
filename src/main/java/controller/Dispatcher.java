package controller;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] uriSplitted = request.getRequestURI().split("/");


        String page = uriSplitted[uriSplitted.length - 1];
        System.out.println("page : " + page);
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
                this.getServletContext().getNamedDispatcher("updateEvent").forward(request,response);
                break;
            default:
                this.getServletContext().getNamedDispatcher("logger").forward(request, response);
                break;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] uriSplitted = request.getRequestURI().split("/");
        String page = uriSplitted[uriSplitted.length - 1];
        switch (page) {
            case "connexion.jspa":
                // On verifie les identifiants ici

                request.setAttribute("idError", "<li>L'adresse mail saisie est incorrecte</li>");
                request.setAttribute("mdpError", "<li>Le mot de passe saisi est incorrect</li>");
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
                this.getServletContext().getNamedDispatcher("updateEvent").forward(request, response);
                break;
            default:
                this.getServletContext().getNamedDispatcher("logger").forward(request, response);
                break;
        }
    }


    public void init() throws ServletException {
        super.init();
    }
}
