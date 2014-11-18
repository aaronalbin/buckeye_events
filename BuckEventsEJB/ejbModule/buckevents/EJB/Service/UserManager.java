/*
 * Session Bean for User
 * 
 */


package buckevents.EJB.Service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import buckevents.EJB.Entity.Event;
import buckevents.EJB.Entity.User;

@Stateless
public class UserManager {

	@PersistenceContext(unitName="buckevents-EJB")
	EntityManager em;
	
	public void addUser(User user) {
		this.em.persist(user);
	}
	
	// returns list of events saved for a single user based on starting position
	// list has a max size of 5
	public List<Event> getEventsForUser(long userid, int start) {
		User u = getUserFromId(userid);
		if (u.getEvents().size() < 5) {
			return u.getEvents().subList(start, u.getEvents().size());
		}
		else {
			return u.getEvents().subList(start, start+5);
		}
	}
	
	// returns number of events saved by user
	public int getEventSizeForUser(long userid) {
		User u = getUserFromId(userid);
		return u.getEvents().size();
	}
	
	// returns user based on id
	public User getUserFromId(long userid) {
		return this.em.find(User.class, userid);
	}
	
	// returns user based on username
	public User getUserFromName(String username) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :x");
		query.setParameter("x", username);
		try {
			User u = (User)query.getSingleResult();
			return u;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	// saves event to user based on their id
	public void addUserEvent(long userid, long eventid) {
		User user = em.find(User.class, userid);
		Event event = em.find(Event.class, eventid);
		user.addEvent(event);
	}
	
	// removes event from user based on their id
	public void removeUserEvent(long userid, long eventid) {
		User user = em.find(User.class, userid);
		Event event = em.find(Event.class, eventid);
		user.removeEvent(event);
	}
	
	// returns true if user exists, false otherwise based on username and password
	public boolean userExists(String username, String password) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :x AND u.password = :y");
		query.setParameter("x", username);
		query.setParameter("y", password);
		try {
			User u = (User)query.getSingleResult();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}
