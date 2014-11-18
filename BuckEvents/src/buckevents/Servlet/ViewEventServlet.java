/*
 * Servlet to view event
 * 
 */

package buckevents.Servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buckevents.EJB.Entity.Event;
import buckevents.EJB.Service.EventService;


@WebServlet("/viewEvent")
public class ViewEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EventService eventService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get eventid passed in, retrieve event from id, set to be displayed in jsp
		long eventid = new Long(request.getParameter("eventid"));
		String saved = request.getParameter("saved");
		request.setAttribute("saved", saved);
		
		Event ev = eventService.getEventFromId(eventid);
		request.setAttribute("event", ev);
		// forward to view
		request.getRequestDispatcher("view.jsp").forward(request, response);
	}

}
