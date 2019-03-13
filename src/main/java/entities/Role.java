package stdio.entities;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import stdio.utilities.StdioHelper;

@Entity
@Table(name="Roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String title;
	
	@Column(unique=true)
	private String idName;
	
	@ManyToMany(mappedBy="roles")
	private Set<Account> accounts;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	static public Role findRoleByIdName(List<Role> roles, String idName) {
		for (Role role : roles) {
			if (role.getIdName().equals(idName)) {
				return role;
			}
		}
		return null;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Boolean update(Map<String, Object> map) {

		Role srcObj = StdioHelper.getObjectFromMap(map, Role.class);
		if (srcObj == null)
			return false;
		
		Field field;

		for (String key: map.keySet()) {
			try {
				field = Role.class.getDeclaredField(key);
				if (field.getName().equals("id") == false && 
					field.getName().equals("createdDate") == false &&
					field.getName().equals("accounts") == false) {
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