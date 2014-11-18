/*
 * Servlet to save event
 * 
 */

package buckevents.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buckevents.EJB.Entity.Event;
import buckevents.EJB.Entity.User;
import buckevents.EJB.Service.EventService;
import buckevents.EJB.Service.UserManager;


@WebServlet("/saveEvent")
public class SaveEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserManager userManager;
	
	@EJB
	private EventService eventService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookieName = HelperServlet.getCookie(request, "buckevents-username");
		
		// if no username cookie (ie not logged in)
		if (cookieName == null) {
			response.sendRedirect("home");
		}
		else {
			// get username, event from event id
			String username = cookieName.getValue();
			long eventid = new Long(request.getParameter("eventid"));
			
			Event ev = eventService.getEventFromId(eventid);
			// set to be displayed in jsp
			request.setAttribute("event", ev);
			
			// get user from username, add event to user using ids
			User user = userManager.getUserFromName(username);
			userManager.addUserEvent(user.getId(), eventid);
			
			String saved = "true";
	        request.setAttribute("saved", saved);
			// send back to view
			request.getRequestDispatcher("view.jsp").forward(request, response);
		}
	}
}
