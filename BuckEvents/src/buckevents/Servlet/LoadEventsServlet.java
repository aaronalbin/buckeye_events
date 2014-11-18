/*
 * Servlet called by jquery to re-populate list in home.jsp 
 * 
 */

package buckevents.Servlet;

import java.io.IOException;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import buckevents.EJB.Entity.Event;
import buckevents.EJB.Service.EventService;
import javax.servlet.http.Cookie;


@WebServlet("/loadEvents")
public class LoadEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EventService eventService;
	    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// action is either 'next' or 'prev'
		String action = request.getParameter("q");
		
		// use cookies to get list index position and category type 
		Cookie cookieindex = HelperServlet.getCookie(request, "buckevents-viewindex");
		Cookie cookiecat = HelperServlet.getCookie(request, "buckevents-viewcat");
		String cat = "";
		
		// if category cookie is null, assume "all" as default
		if (cookiecat != null) {
			cat = cookiecat.getValue();
		}
		else {
			cat = "all";
		}
		
		List<Event> events;
		int size = 0;
		
		// get size of event list based on category
		if (cat.equals("all")) {
			size = eventService.getEventSize();
		}
		else {
			size = eventService.getEventSizeFromCategory(cat);
		}
		
		// if no index cookie
		if (cookieindex == null) {
			if (5 < size) {
				// create new cookie
				response.setContentType("text/html");
				Cookie c = new Cookie("buckevents-viewindex", "5");  // move index position up 5
				c.setMaxAge(60*60);
				response.addCookie(c);
				// get next 5 events from category
				if (cat.equals("all")) {
					events = eventService.getEvents(5);
				}
				else {
					events = eventService.getEventFromCategory(cat, 5);
				}
			}
			// list is <= 5
			else {
				// get first max 5 events from category
				if (cat.equals("all")) {
					events = eventService.getEvents(0);
				}
				else {
					events = eventService.getEventFromCategory(cat, 0);
				}
			}
		}
		else {
			// get current index
			int index = new Integer(cookieindex.getValue());
			
			if (action.equals("next")) {
				// check if another 5 events to list, otherwise keep index same
				if ((index+5) < size) {
					index += 5;  // update index
					cookieindex.setValue(Integer.toString(index));
					response.addCookie(cookieindex);
				}
				// get events from category
				if (cat.equals("all")) {
					events = eventService.getEvents(index);
				}
				else {
					events = eventService.getEventFromCategory(cat, index);
				}
			}
			// action is 'prev'
			else {
				// check if another 5 events to list (reverse direction), otherwise keep index same
				if ((index-5) >= 0) {
					index -= 5;  // update index
					cookieindex.setValue(Integer.toString(index));
					response.addCookie(cookieindex);
				}
				// get events from category
				if (cat.equals("all")) {
					events = eventService.getEvents(index);
				}
				else {
					events = eventService.getEventFromCategory(cat, index);
				}
			}
		}
		
		// set description to max 100 for list display
		Iterator itr = events.iterator();
		while(itr.hasNext()) {
			Event e = (Event)itr.next();
			e.setUsers(null);
			if (e.getDescription().length() > 100) {
				e.setDescription(e.getDescription().substring(0, 100));
			}
			e.setDescription(e.getDescription()+"...");
		}
		
		// cast java object to JSON using google's gson library, add to response
	    String json = new Gson().toJson(events);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
