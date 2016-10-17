package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
	/**
	 * Auto-generated serial version uid
	 */
	private static final long serialVersionUID = 8380460707993002554L;
	
	private static final String msgErreur = "<div class=\"alert alert-danger\">"
			+ "<b>Erreur d'authentification</b>"
			+ "<ul>"
			+ "%s"
			+ "</ul>"
			+ "</div>";
	ServletConfig config;
	
	protected void doGet (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("!");
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
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/login.jsp" ).forward( request, response );
	}
	
	protected void doPost (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("?");
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
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/login.jsp" ).forward( request, response );
	}
	
	public void init() throws ServletException {
		super.init();
	}
}