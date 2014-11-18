/*
 * Servlet to inject test data into application
 * 
 */


package buckevents.Servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buckevents.EJB.Entity.Event;
import buckevents.EJB.Entity.User;
import buckevents.EJB.Service.EventService;
import buckevents.EJB.Service.UserManager;



@WebServlet("/inject")
public class InjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EventService eventService;
	
	@EJB
	private UserManager userManager;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// create and add events, users
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//------- Social --------//
    	
    	Event event = new Event("OUAB Coffee House", "06/25/12", "9:00 PM", "Drake Performing Arts Center Lounge", 
    			"Studying for new classes got you stressed? Need a break but don't think you have the time? Well OUAB Grad/Prof has " +
    			"the perfect event for you! On April 25th we are having a coffeehouse event featuring the music of Beau Bristo " +
    			"http://www.beaubristow.com at The Drake Center. There will be free food and plenty of caffeine provided to prepare " +
    			"you for the long nights ahead. It will be a relaxed environment with great music for people to hang out with friends or " +
    			"study. Hope to see you all there!", "social", "approved", "11");
    	
    	eventService.addEvent(event);
    	
    	event = new Event("Happy Hour featuring Live Jazz", "06/04/12", "5:30 PM", "Woody's Tavern - Ohio Union", 
    			"Join fellow students and their guests for a night celebrating the beginning of Spring with free food, fun and raffle prizes. " +
    			"Entertainment for the event features fantastic live jazz. This week’s happy hour is a great way to relax and unwind. Vegetarian options " +
    			"will be provided. To purchase Ohio beer or wine, please remember to bring proper identification. Non-student guests are welcome! ", "social", "approved", "11");
    	eventService.addEvent(event);

    	//------- Fitness --------//
    	
    	event = new Event("OUABe Fit: Shake It!", "06/21/12", "5:00 PM", "Ohio Union - Dance Room 1", 
    			"OUAB is sponsoring free weekly fitness classes during spring quarter. Every Tuesday we will be offering " +
    			"Shake It! Spice up your workout routines in a fun and exciting way! Shake it! gives you one hour of choreographed dances " +
    			"to the latest hip hop and pop songs. You will literally dance and shake it in ways you never thought were possible! This class " +
    			"is a great way to let loose and dance your heart out while working out at the same time. You will work your upper body, lower body, " +
    			"and core without even thinking about it. No dancing experience required. Come try it and find that hidden dancer in you! RSVP preferred " +
    			"to ouab.grad.prof@gmail.com. Come on out.", "fitness", "approved", "11");
    	eventService.addEvent(event);
    	
    	event = new Event("OUABe Fit: Power Yoga", "06/25/12", "7:00 PM", "Ohio Union - Dance Room 1", 
    			"OUAB is sponsoring free weekly fitness classes during spring quarter. Every Wednesday we will be offering " +
    			"power yoga. Looking for a yoga class that incorporates strength, stamina, and flexibility? Power Yoga utilizes " +
    			"dynamic breathing and powerful yoga-based poses to heat up the entire body. A powerful combination of movement, " +
    			"breath, and strength allows the body to generate heat increasing the muscles' ability to lengthen and release. Sun " +
    			"salutations, standing poses, and floor work will be incorporated into the class. End each class with an integrative " +
    			"relaxation period and additional breath work, time permitting. Mats will be provided." +
    			"Must RSVP to ouab.grad.prof@gmail.com. Come on out.", "fitness", "approved", "11");
    	eventService.addEvent(event);
 
    	//------- Food --------//
    	
    	event = new Event("Baking 101", "06/26/12", "6:00 PM", "Ohio Union- Lower Level- Instructional Kitchen", 
    			"Never had success with baking a cake? Don't know the difference between different types of pasty? Join OUAB for an exploration " +
    			"of basic baking techniques you can transfer to your kitchen at home. Space is limited. RSVP to " +
    			"ouab.grad.prof@gmail.com.", "food", "approved", "11");
    	eventService.addEvent(event);
    	
    	event = new Event("Kitchen Basics Boot Camp", "06/28/12", "6:00 PM", "Ohio Union- Lower Level- Instructional Kitchen", 
    			"Unsure of where to start when preparing food at home? Join your fellow students to learn basic cooking techniques.", "food", "approved", "11");
    	eventService.addEvent(event);

    	//------- Lecture --------//
    	
    	event = new Event("Science Rules! with Bill Nye the Science Guy", "06/21/12", "7:00 PM", "Ohio Union - Archie M. Griffin Grand Ballroom", 
    			"Ohio Union Activities Board, Engineers Without Borders, Engineers for Community Service, and The Chemistry Club of The Ohio State " +
    			"University present an evening with Bill Nye the Science Guy! Bill Nye, scientist, engineer, comedian, author, and inventor, is a man " +
    			"with a mission: to help foster a scientifically literate society and to help people everywhere understand and appreciate the science " +
    			"that makes our world work. Making science entertaining and accessible is something Bill has been doing most of his life and now he is " +
    			"doing it at Ohio State! Don't miss this event!", "lecture", "approved", "11");
    	eventService.addEvent(event);
    	
    	event = new Event("An Evening with Ross Mathews", "06/22/12", "7:00 PM", "Ohio Union - Performance Hall", 
    			"Ross Mathews started his career as an intern on The Tonight Show with Jay Leno. Ross would go on to be a key correspondent on the " +
    			"Tonight Show, interviewing celebrities around the world and making a name for himself. Most recently Ross has been a regular on the E! " +
    			"talk-show Chelsea Lately. Come join OUAB as Ross shares with us his rise to success, as well as his take on pop culture.", "lecture", "approved", "11");
    	eventService.addEvent(event);

    	//------- Arts --------//
    	
    	event = new Event("Choral Collage Concert", "06/20/12", "2:30 PM", "Weigel Auditorium", 
    			"$6 general public / $4 senior citizens and students. Advance ticket sales available from the OSU Theatre Ticket Office. Call 292-2295 (all major credit cards) or " +
    			"at the door (cash or check only)", "arts", "approved", "11");
    	eventService.addEvent(event);
    	
    	event = new Event("Flute Studio Concert", "06/30/12", "6:30 PM", "Weigel Auditorium", 
    			"$6 general public / $4 senior citizens and students, at the door (cash or check only)", "arts", "approved", "11");
    	eventService.addEvent(event);
 
    	// create users
    	User user1 = new User("Aaron", "123", "blah@blah.com", "user");
    	userManager.addUser(user1);
    	
    	User user2 = new User("Hitoe", "123", "blah@blah.com", "screener");
    	userManager.addUser(user2);
    	
    	// create message to send response
    	 Map<String, String> messages = new HashMap<String, String>();
         request.setAttribute("messages", messages);

         if (messages.isEmpty()) {
             messages.put("success", "Done.");
         }
         
         request.getRequestDispatcher("inject.jsp").forward(request, response);
	}

}
