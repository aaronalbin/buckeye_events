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
	
	// override equals and hashCode so that users do not add multiple copies of the same event
	@Override
	public boolean equals(Object o){
       if (o == null)
		   return false;
			
		if (o == this)
		   return true;
			
		if (o.getClass() != getClass())
			return false;
      
       Event e = (Event)o;
       return (this.id == e.id) &&
              (this.name != null && this.name.equals(e.name)) &&
              (this.date != null && this.date.equals(e.date)) &&
              (this.time != null && this.time.equals(e.time)) &&
              (this.location != null && this.location.equals(e.location)) &&
              (this.category != null && this.category.equals(e.category)) &&
              (this.status != null && this.status.equals(e.status)) &&
              (this.ownerid != null && this.ownerid.equals(e.ownerid));
   }
   
	@Override
    public int hashCode(Object o){
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id;
		result = prime * result + (this.name != null ? this.name.hashCode() : 0);
		result = prime * result + (this.date != null ? this.date.hashCode() : 0);
		result = prime * result + (this.time != null ? this.time.hashCode() : 0);
		result = prime * result + (this.location != null ? this.location.hashCode() : 0);
		result = prime * result + (this.category != null ? this.category.hashCode() : 0);
		result = prime * result + (this.status != null ? this.status.hashCode() : 0);
		result = prime * result + (this.ownerid != null ? this.ownerid.hashCode() : 0);
		return result;
	}
	
}
