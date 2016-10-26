package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjoutEvenementController extends HttpServlet {
	/**
	 * Auto-generated uid
	 */
	private static final long serialVersionUID = -3765777634990243190L;

	protected void doGet (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/createEvent.jsp" ).forward( request, response );
	}
	
	protected void doPost (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/createEvent.jsp" ).forward( request, response );
	}
	
	public void init() throws ServletException {
		super.init();
	}
}