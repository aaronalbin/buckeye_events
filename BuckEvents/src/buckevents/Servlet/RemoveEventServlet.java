/*
 * Servlet to remove saved event
 * 
 */

package buckevents.Servlet;

import java.io.IOException;

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


@WebServlet("/removeEvent")
public class RemoveEventServlet extends HttpServlet {
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
			// get event from id
			long eventid = new Long(request.getParameter("eventid"));
			Event ev = eventService.getEventFromId(eventid);
			// set to be displayed in jsp
			request.setAttribute("event", ev);
			
			// get users name
			User user = userManager.getUserFromName(cookieName.getValue());
			// remove event from users saved list
			userManager.removeUserEvent(user.getId(), eventid);
			
			String saved = "false";
	        request.setAttribute("saved", saved);
	        // send back to view
	        request.getRequestDispatcher("view.jsp").forward(request, response);
		}
	}

}
