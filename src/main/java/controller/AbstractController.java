package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Steeve Sinigaglia on 27/10/2016.
 */
public abstract class AbstractController extends HttpServlet {

    private String errorMessage = "<div class=\"alert alert-danger\">"
            + "<b>Erreur d'authentification</b>"
            + "<ul>"
            + "%s"
            + "</ul>"
            + "</div>";
    @Override
    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    @Override
    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    /**
     * Prepare et attache à la requete un message d'erreur formaté
     * @param req requete de la servlet
     * @param bodyMessage coeur du message (body)
     * @param errors enumération d'erreur sous forme de liste
     */
    protected void buildAndAttachErrorMessage(HttpServletRequest req, String bodyMessage, List<String> errors){
        String errorMessage = "<div class=\"alert alert-danger\">"
                + "<b>" + bodyMessage + "</b>";

        for (String e : errors) {
            errorMessage += "<ul> - " + e + "</ul>";
        }

        errorMessage += "</div>";
        req.setAttribute("msgErreur", errorMessage);
    }

    protected void buildAndAttachErrorMessage(HttpServletRequest req, String bodyMessage, String... errors){
        String errorMessage = "<div class=\"alert alert-danger\">"
                + "<b>" + bodyMessage + "</b>";

        for (String e : errors) {
            errorMessage += "<ul> - " + e + "</ul>";
        }

        errorMessage += "</div>";
        req.setAttribute("msgErreur", errorMessage);
    }
}
