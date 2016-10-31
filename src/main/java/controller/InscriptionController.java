package controller;

import entities.User;
import service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InscriptionController extends AbstractController {
    /**
     * Auto-generated uid
     */
    private static final long serialVersionUID = -3765777634990243190L;
    private static final String msgErreur = "<div class=\"alert alert-danger\">"
            + "<b>Erreur d'authentification</b>"
            + "<ul>"
            + "%s"
            + "</ul>"
            + "</div>";
    UserService userService;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();

        boolean formOk = true;
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String checkpwd = request.getParameter("checkpwd");

        List<String> errorList = new ArrayList<>();


        /* Manage mandatory fields */

        if (firstname.isEmpty()) {
            formOk = false;
            errorList.add("Le prénom est requis");
        }

        if (lastname.isEmpty()) {
            formOk = false;
            errorList.add("Le nom est requis");
        }

        if (mail.isEmpty()) {
            formOk = false;
            errorList.add("Une adresse email est requise");
        } else if (userService.checkEmailExist(mail)) {
            formOk = false;
            errorList.add("L'adresse email est déjà utilisée");
        }
        if (password.isEmpty()) {
            formOk = false;
            errorList.add("Un mot de passe est requis");
        } else {
            if (!password.equals(checkpwd)) {
                formOk = false;
                errorList.add("Les mots de passe ne correspondent pas");
            }
        }

        // Si tous les champs obligatoires sont ok, on construit l'user et on lui ajoute ou non les champs facultatifs

        String company = request.getParameter("company");

        if (formOk) {
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setEmail(mail);
            user.setPassword(password);
//            if (company != null) {
            user.setCompany(company);
//            }
            userService.create(user);
            response.sendRedirect("connexion.jspa");
        } else {
            this.buildAndAttachErrorMessage(request, "Champs incorrect", errorList);
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);
            request.setAttribute("mail", mail);
            request.setAttribute("company", company);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);
        }
    }

    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }
}