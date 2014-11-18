/*
 * Entity class for Event
 * Contains: id, name, date, time, location, category, status, ownerid
 * 
 */

package buckevents.EJB.Entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "event_tbl")
public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name, date, time, location, category, status, ownerid;
	
	@Lob
	@Column(name="description", length=999)
	private String description;
	
	// many-to-many relationship established with User
	@ManyToMany(mappedBy = "events", fetch=FetchType.EAGER)
	private List<User> users;
	
	public Event() {
		users = new ArrayList<User>();
	}
	
	public Event(String name, String date, String time, String location, String description, String category, String status, String ownerid) {
		this.users = new ArrayList<User>();
		this.name = name;
		this.date = date;
		this.time = time;
		this.location = location;
		this.description = description;
		this.category = category;
		this.status = status;
		this.ownerid = ownerid;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
