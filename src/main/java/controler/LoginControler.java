package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginControler extends HttpServlet {
	/**
	 * Auto-generated serial version uid
	 */
	private static final long serialVersionUID = 8380460707993002554L;
	ServletConfig config;
	List<String> langues;
	
	protected void doGet (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String langue = request.getParameter("lang");
		if (langues.contains(langue)) {
			// Si la langue est reconnue, on charge la page correspondante.
			this.getServletContext().getRequestDispatcher( "/content/" + langue + "/login.jsp" ).forward( request, response );
		} else {
			// Sinon, on charge la page en anglais par defaut
			this.getServletContext().getRequestDispatcher( "/content/en/login.jsp" ).forward( request, response );
		}
	}
	
	protected void doPost (HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	public void init() throws ServletException {
		super.init();
		this.config = getServletConfig();
		
		this.langues = new ArrayList<String>();
		this.langues.add("fr");
		this.langues.add("en");
	}
}