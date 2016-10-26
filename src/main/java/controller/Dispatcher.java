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
		String[] uriSplitted = request.getRequestURI().split("/");
		String page = uriSplitted[uriSplitted.length -1];
		switch (page) {
		case "connexion.action" :
			this.getServletContext().getNamedDispatcher("logger").forward( request, response );
			break;
		case "inscription.action" :
			this.getServletContext().getNamedDispatcher("register").forward( request, response );
			break;
		case "consultation.action" :
			this.getServletContext().getNamedDispatcher("displayer").forward( request, response );
			break;
		case "ajout.action" :
			this.getServletContext().getNamedDispatcher("manager").forward( request, response );
			break;
			
		default :
			this.getServletContext().getNamedDispatcher("logger").forward( request, response );
			break;
		}
		
	}
	
	protected void doPost (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String[] uriSplitted = request.getRequestURI().split("/");
		String page = uriSplitted[uriSplitted.length -1];
		switch (page) {
		case "connexion.action" :
			// On verifie les identifiants ici
			// On initialise la session, et on redirige en fonction du succès ou de l'échec de l'authentification
			request.setAttribute("idError", "<li>L'adresse mail saisie est incorrecte</li>");
			request.setAttribute("mdpError", "<li>Le mot de passe saisi est incorrect</li>");
			this.getServletContext().getNamedDispatcher("logger").forward( request, response );
			break;
		case "inscription.action" :
			this.getServletContext().getNamedDispatcher("register").forward( request, response );
			break;
		case "consultation.action" :
			this.getServletContext().getNamedDispatcher("displayer").forward( request, response );
			break;
		case "ajout.action" :
			this.getServletContext().getNamedDispatcher("manager").forward( request, response );
			break;
		default :
			this.getServletContext().getNamedDispatcher("logger").forward( request, response );
			break;
		}
	}
	
	public void init() throws ServletException {
		super.init();
	}
}
