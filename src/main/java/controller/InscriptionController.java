package controller;

import entities.User;
import service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InscriptionController extends HttpServlet {
    /**
     * Auto-generated uid
     */
    private static final long serialVersionUID = -3765777634990243190L;

    UserService userService;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);
        System.out.println("do Get inscription");


    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();
        System.out.println(request.getParameter("firstname"));
        System.out.println(request.getParameter("lastname"));
        System.out.println(request.getParameter("mail"));
        System.out.println(request.getParameter("password"));

        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        user.setEmail(request.getParameter("mail"));
        user.setPassword(request.getParameter("password"));

        String company = request.getParameter("company");
        if (company!=null){
            user.setCompany(company);
        }

        userService.create(user);


    }

    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }
}