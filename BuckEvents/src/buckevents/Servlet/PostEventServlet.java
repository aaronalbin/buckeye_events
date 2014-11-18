/*
 * Servlet to save event to database
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
import buckevents.EJB.Service.EventService;


@WebServlet("/postEvent")
public class PostEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EventService eventService;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// if username cookie does not exist, user is not logged in
		Cookie cookieName = HelperServlet.getCookie(request, "buckevents-username");
		if (cookieName == null) {
			// send back to home
			response.sendRedirect("home");
		}
		else {
			// get username
			String username = cookieName.getValue();
		
			Map<String, String> messages = new HashMap<String, String>();
		    request.setAttribute("messages", messages);
		    boolean isValid = true;
			
			String name = request.getParameter("name");
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			String location = request.getParameter("location");
			String description = request.getParameter("description");
			String category = request.getParameter("category");
			
			// validate input, check if null or empty whitespace
			if (name == null || name.trim().isEmpty()) {
	            messages.put("errorname", "Please enter an event name");
	            isValid = false;
	    	}
	    	if (date == null || date.trim().isEmpty()) {
	    		messages.put("errordate", "Please enter a date");
	    		isValid = false;
	    	}
	    	if (time == null || time.trim().isEmpty()) {
	    		messages.put("errortime", "Please enter a time");
	    		isValid = false;
	    	}
	    	if (location == null || location.trim().isEmpty()) {
	    		messages.put("errorlocation", "Please enter a location");
	    		isValid = false;
	    	}
	    	if (description == null || description.trim().isEmpty()) {
	    		messages.put("errordescription", "Please enter a description");
	    		isValid = false;
	    	}
	    	if (category == null || category.trim().isEmpty()) {
	    		messages.put("errorcategory", "Please choose a category");
	    		isValid = false;
	    	}
			
			if (isValid) {
				// create event and persist it using the service bean
				Event e = new Event(name, date, time, location, description, category, "posted", username);
				eventService.addEvent(e);
				// send to confirmation page
				response.sendRedirect("postconfirm.jsp");
			}
			else {
				// error with validation, send message back
				request.getRequestDispatcher("post.jsp").forward(request, response);
			}
		}
	}

}
