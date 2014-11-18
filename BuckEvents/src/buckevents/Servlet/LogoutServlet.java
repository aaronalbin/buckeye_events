/*
 * Servlet to logout
 * 
 */

package buckevents.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// delete cookie storing username, logged-in features will not be displayed in home.jsp
		response.setContentType("text/html");
		String cookieName = "buckevents-username";
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					cookie.setValue("");
					response.addCookie(cookie);
					break;
				}
			}
		}
		
		// send back to home servlet
		response.sendRedirect("home");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
