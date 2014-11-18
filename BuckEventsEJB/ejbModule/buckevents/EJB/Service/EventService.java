/*
 * Session Bean for Event
 * 
 */


package buckevents.EJB.Service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import buckevents.EJB.Entity.Event;

@Stateless
public class EventService {
	
	@PersistenceContext(unitName="buckevents-EJB")
	EntityManager em;
	
	public void addEvent(Event event) {
		this.em.persist(event);
	}
	
	// returns size of events from all categories
	public int getEventSize() {
		Query query = em.createQuery("SELECT e FROM Event e");
		List<Event> events = query.getResultList();
		return events.size();
	}
	
	// returns size of events in a specific category
	public int getEventSizeFromCategory(String cat) {
		Query query = em.createQuery("SELECT e FROM Event e WHERE e.category = :x");
		query.setParameter("x", cat);
		List<Event> events = query.getResultList();
		return events.size();
	}
	
	// returns list of events from all categories based on starting position
	public List<Event> getEvents(int start) {
		Query query = em.createQuery("SELECT e FROM Event e");
		query.setFirstResult(start);
		query.setMaxResults(5);
		List<Event> events = query.getResultList();
		return events;
	}
	
	// returns list of events based on category and starting position
	public List<Event> getEventFromCategory(String cat, int start) {
		Query query = em.createQuery("SELECT e FROM Event e WHERE e.category = :x");
		query.setParameter("x", cat);
		query.setFirstResult(start);
		query.setMaxResults(5);
		List<Event> events = query.getResultList();
		return events;
	}
	
	// returns Event based on id
	public Event getEventFromId(long id) {
		Query query = em.createQuery("SELECT e FROM Event e WHERE e.id = :x");
		query.setParameter("x", id);
		return (Event)query.getSingleResult();
	}
}
