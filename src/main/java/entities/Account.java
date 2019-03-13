package stdio.entities;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import stdio.utilities.StdioHelper;

@Entity
@Table(name="Accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String email;
	
	@JsonIgnore
	private String password;

	private String firstName;
	private String lastName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	private Boolean isActivated;
	
	@ManyToMany
	private Set<Role> roles;
	
	public Account(Long id) {
		this.id = id;
	}
	
	public Account() {
		createdDate = new Date();
	}
	
	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean update(Map<String, Object> map) {

		Account srcObj = StdioHelper.getObjectFromMap(map, Account.class);
		if (srcObj == null)
			return false;
		
		Field field;

		for (String key: map.keySet()) {
			try {
				field = Account.class.getDeclaredField(key);
				if (field.getName().equals("id") == false && 
					field.getName().equals("createdDate") == false) {
					boolean accessible = field.isAccessible();			
					field.setAccessible(true);
					field.set(this, field.get(srcObj));
					field.setAccessible(accessible);
				}	
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}