/*
 * Servlet to login user
 * 
 */

package buckevents.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buckevents.EJB.Entity.Event;
import buckevents.EJB.Service.EventService;
import buckevents.EJB.Service.UserManager;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EventService eventService;
	
	@EJB
	private UserManager userManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// delete cookie storing index
		Cookie cookieindex = HelperServlet.getCookie(request, "buckevents-viewindex");
		if (cookieindex != null) {
			cookieindex.setMaxAge(0);
			cookieindex.setValue(null);
			response.addCookie(cookieindex);
		}
		// delete cookie storing category
		Cookie cookiecat = HelperServlet.getCookie(request, "buckevents-viewcat");
		if (cookiecat != null) {
			cookiecat.setValue("all");
			response.addCookie(cookiecat);
		}
				
		// get list of events to send back to jsp for display
		List<Event> events = eventService.getEvents(0);
		request.setAttribute("events", events);
				
		// set event description to max 100 for display
		Iterator itr = events.iterator(); 
		while(itr.hasNext()) {
			Event e = (Event)itr.next();
			if (e.getDescription().length() > 100) {
				e.setDescription(e.getDescription().substring(0, 100));
			}
		}
		
		// validate
		boolean isValid = true;
		// Prepare messages.
        Map<String, String> loginmessages = new HashMap<String, String>();
        request.setAttribute("loginmessages", loginmessages);
		
		String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	// check that username and password are not empty or plain whitespace
    	if (username == null || username.trim().isEmpty()) {
            loginmessages.put("error", "Error logging in");
            isValid = false;
    	}
    	if (password == null || password.trim().isEmpty()) {
    		loginmessages.put("error", "Error logging in");
    		isValid = false;
    	}
    	
    	if (isValid) {
    		// check if username and password match database entry
    		boolean userExists = userManager.userExists(username, password);
    		if (userExists) {		
    			// add cookie with username to be used for saving/removing/viewing events
				response.setContentType("text/html");
	    		Cookie c = new Cookie("buckevents-username", username);
	    		c.setMaxAge(60*60);
	    		response.addCookie(c);
	    		response.sendRedirect("home");
			}
    		else {
    			// login doesn't match, send error message back
    			loginmessages.put("error", "Error logging in");
    			request.getRequestDispatcher("home.jsp").forward(request, response);
    		}
			
    	}
    	else {
    		// not valid input, send error message back
	    	request.getRequestDispatcher("home.jsp").forward(request, response);
    	}
	}

}
