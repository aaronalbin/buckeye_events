/*
 * Servlet to handle display to home.jsp
 * 
 */

package buckevents.Servlet;

import java.io.IOException;
import java.util.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buckevents.EJB.Entity.Event;
import buckevents.EJB.Service.EventService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EventService eventService;

	// fetch list of events to be displayed to home.jsp
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// delete previous cookie storing index for list
		Cookie cookieindex = HelperServlet.getCookie(request, "buckevents-viewindex");
		if (cookieindex != null) {
		    cookieindex.setMaxAge(0);
		    cookieindex.setValue(null);
		    response.addCookie(cookieindex);
		}
		// if cookie storing categories exists, set to default "all"
		Cookie cookiecat = HelperServlet.getCookie(request, "buckevents-viewcat");
		if (cookiecat != null) {
			cookiecat.setValue("all");
			response.addCookie(cookiecat);
		}
		
		// get list of events from range [0 - 4]
		List<Event> events = eventService.getEvents(0);
		
		// set description of events to max length 100 for list display
		Iterator itr = events.iterator(); 
		while(itr.hasNext()) {
			Event e = (Event)itr.next();
			if (e.getDescription().length() > 100) {
				e.setDescription(e.getDescription().substring(0, 100));
			}
		}
		
		// add list to request, forward to home.jsp
		request.setAttribute("events", events);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}


	// category is selected, posted here
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get category type
		String cat = request.getParameter("cat");
		
		// delete previous cookie storing index for list
		Cookie cookieindex = HelperServlet.getCookie(request, "buckevents-viewindex");
		if (cookieindex != null) {
		    cookieindex.setMaxAge(0);
		    cookieindex.setValue(null);
		    response.addCookie(cookieindex);
		}
		
		// if cookie exists, set the category type; otherwise create new cookie with category type
		Cookie cookiecat = HelperServlet.getCookie(request, "buckevents-viewcat");
		if (cookiecat != null) {
			cookiecat.setValue(cat);
			response.addCookie(cookiecat);
		}
		else {
			// create new cookie
			response.setContentType("text/html");
			Cookie c = new Cookie("buckevents-viewcat", cat);
			c.setMaxAge(60*60);
			response.addCookie(c);
		}
		
		List<Event> events;
		
		// retrieve first 5 events for category type
		if (cat.equals("all")) {
			events = eventService.getEvents(0);
		}
		else {
			events = eventService.getEventFromCategory(cat, 0);
		}
		
		// set description of events to max length 100 for list display
		Iterator itr = events.iterator(); 
		while(itr.hasNext()) {
			Event e = (Event)itr.next();
			if (e.getDescription().length() > 100) {
				e.setDescription(e.getDescription().substring(0, 100));
			}
		}
		
		// add list to request, forward to home.jsp
		request.setAttribute("events", events);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
