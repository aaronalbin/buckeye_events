/*
 * Servlet to view saved event
 * 
 */


package buckevents.Servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buckevents.EJB.Entity.Event;
import buckevents.EJB.Entity.User;
import buckevents.EJB.Service.UserManager;

/**
 * Servlet implementation class ViewSavedServlet
 */
@WebServlet("/viewSaved")
public class ViewSavedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UserManager userManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookieName = HelperServlet.getCookie(request, "buckevents-username");
		
		// check if user is logged in, otherwise redirect to home
		if (cookieName == null) {
			response.sendRedirect("home");
		}
		else {
			// delete cookie storing saved index
			Cookie cookiesavedindex = HelperServlet.getCookie(request, "buckevents-viewsavedindex");
			if (cookiesavedindex != null) {
				cookiesavedindex.setValue("all");
				response.addCookie(cookiesavedindex);
			}
	
			// get user from cookiename
			User user = userManager.getUserFromName(cookieName.getValue());
			if (user != null) {
				// get first 5 saved events
				List<Event> userevents = userManager.getEventsForUser(user.getId(), 0);
				
				// set description max 100 characters
				Iterator itr = userevents.iterator(); 
				while(itr.hasNext()) {
					Event e = (Event)itr.next();
					if (e.getDescription().length() > 100) {
						e.setDescription(e.getDescription().substring(0, 100));
					}
				}
				
				// set list to be displayed in jsp
				request.setAttribute("userevents", userevents);
			}
			// forward to saved.jsp
			request.getRequestDispatcher("saved.jsp").forward(request, response);
		}
	}

}
