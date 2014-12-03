/*
 * Entity class for User
 * Contains: id, username, password, email, type
 * 
 */

package buckevents.EJB.Entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "user_tbl")
public class User implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username, password, email, type;
	
	// many-to-many relationship established with Event
	// User is 'owner' (stores saved events for the user), so join column set here
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_event_tbl", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="event_id"))
	private List<Event> events;
	
	public User() {
		this.events = new ArrayList<Event>();
	}
	
	public User(String username, String password, String email, String type) {
		this.events = new ArrayList<Event>();
		setUsername(username);
		setPassword(password);
		setEmail(email);
		setType(type);
	}
	
	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public void addEvent(Event event) {
		// TODO: this might be better implemented using a set of events
		if (!this.events.contains(event)) {
			this.events.add(event);
		}
	}
	
	public void removeEvent(Event event) {
		this.events.remove(event);
	}
	
}
