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

	protected void doGet (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getRequestURI().split("/")[0];
		switch (page) {
		case "connexion" :
			System.out.println(1);
			this.getServletContext().getNamedDispatcher("logger").forward( request, response );
			break;
			
			
		default :
			System.out.println(2);
			this.getServletContext().getNamedDispatcher("logger").forward( request, response );
			break;
		}
		
	}
	
	protected void doPost (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getRequestURI().split("/")[0];
		switch (page) {
		case "connexion" :
			System.out.println(3);
			// On verifie les identifiants ici
			// On initialise la session, et on redirige en fonction du succès ou de l'échec de l'authentification
			request.setAttribute("idError", "<li>L'adresse mail saisie est incorrecte</li>");
			request.setAttribute("mdpError", "<li>Le mot de passe saisi est incorrect</li>");
			this.getServletContext().getNamedDispatcher("logger").forward( request, response );
			break;
			
			
		default :
			System.out.println(4);
			this.getServletContext().getNamedDispatcher("logger").forward( request, response );
			break;
		}
	}
	
	public void init() throws ServletException {
		super.init();
	}
}
