package controller;

import entities.User;
import service.UserService;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends AbstractController {
    /**
     * Auto-generated serial version uid
     */
    private static final long serialVersionUID = 8380460707993002554L;

    private UserService userService;

    private static final String msgErreur = "<div class=\"alert alert-danger\">"
            + "<b>Erreur d'authentification</b>"
            + "<ul>"
            + "%s"
            + "</ul>"
            + "</div>";
    ServletConfig config;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        String msgIdError = (String) request.getAttribute("idError");
        String msgMdpError = (String) request.getAttribute("mdpError");
        String msgErreurFormat = null;
        String erreur = "";
        if (null != msgIdError || null != msgMdpError) {
            erreur += msgIdError + msgMdpError;
        }
        if (!erreur.isEmpty()) {
            msgErreurFormat = String.format(LoginController.msgErreur, erreur);
        }
        request.setAttribute("msgErreur", msgErreurFormat);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User authentificatedUser = userService.checkLogin(email, password);
        if (authentificatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser", authentificatedUser.getId());
            session.setAttribute("userName", authentificatedUser.getFirstName()+" "+authentificatedUser.getLastName());
            response.sendRedirect("consultation.jspa");
        } else {
            this.buildAndAttachErrorMessage(request, "Authentification impossible", "L'adresse email ou le mot de passe sont incorrects");
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

        }
    }

    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }
}