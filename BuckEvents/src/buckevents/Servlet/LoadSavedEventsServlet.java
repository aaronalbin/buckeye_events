/*
 * Servlet called by jquery to re-populate list in saved.jsp 
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

import com.google.gson.Gson;

import buckevents.EJB.Entity.Event;
import buckevents.EJB.Entity.User;
import buckevents.EJB.Service.UserManager;


@WebServlet("/loadSavedEvents")
public class LoadSavedEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UserManager userManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie cookieName = HelperServlet.getCookie(request, "buckevents-username");
		
		// if no username cookie (ie not logged in)
		if (cookieName == null) {
			response.sendRedirect("home");
		}
		else {
			// action is either 'next' or 'prev'
			String action = request.getParameter("q");
			// get position index for list
			Cookie cookiesavedindex = HelperServlet.getCookie(request, "buckevents-viewsavedindex");
			User user = userManager.getUserFromName(cookieName.getValue());
			int size = userManager.getEventSizeForUser(user.getId());
			
			List<Event> userevents;
			
			// if no index cookie
			if (cookiesavedindex == null) {
				if (5 < size) {
					// create new cookie
					response.setContentType("text/html");
					Cookie c = new Cookie("buckevents-viewsavedindex", "5");  // move index position up 5
					c.setMaxAge(60*60);
					response.addCookie(c);
					// get next 5 events from category
					userevents = userManager.getEventsForUser(user.getId(), 5);
				}
				// if list <= 5
				else {
					// get first max 5 events
					userevents = userManager.getEventsForUser(user.getId(), 0);
				}
			}
			else {
				// get current index from cookie
				int index = new Integer(cookiesavedindex.getValue());
				
				if (action.equals("next")) {
					// check if another 5 events to list, otherwise keep index same
					if ((index+5) < size) {
						index += 5;  // update index
						cookiesavedindex.setValue(Integer.toString(index));
						response.addCookie(cookiesavedindex);
					}
					// get events from user id
					userevents = userManager.getEventsForUser(user.getId(), index);
				}
				else {
					// check if another 5 events to list (reverse direction), otherwise keep index same
					if ((index-5) >= 0) {
						index -= 5;  // update index
						cookiesavedindex.setValue(Integer.toString(index));
						response.addCookie(cookiesavedindex);
					}
					// get events from user id
					userevents = userManager.getEventsForUser(user.getId(), index);
				}
			}
			
			// set description to max 100 for list display
			Iterator itr = userevents.iterator();
			while(itr.hasNext()) {
				Event e = (Event)itr.next();
				e.setUsers(null);  // hack to avoid recursion with relationship when casting to json
				if (e.getDescription().length() > 100) {
					e.setDescription(e.getDescription().substring(0, 100));
				}
				e.setDescription(e.getDescription()+"...");
			}
			
			// cast java object to JSON using google's gson library, add to response
		    String json = new Gson().toJson(userevents);
	
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
