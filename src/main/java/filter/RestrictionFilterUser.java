 package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFilterUser implements Filter{
	private static final String PAGE_CONNEXION = "connexion.jspa";
	private static final String ATT_SESSION_USER = "sessionUser";
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		// for push 	
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
		
        HttpSession session = request.getSession();
        if(session.getAttribute(ATT_SESSION_USER) == null){
            response.sendRedirect(PAGE_CONNEXION);
        }
        else {
        	chain.doFilter(request, response);
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
