package stdio.entities;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import stdio.utilities.StdioHelper;

@Entity
@Table(name="Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	private String lastName;
	
	private String email;
	private String phone;
	private String promotionCode;
	
	@Lob
	private String address;
	
	@Lob
	private String note;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	private Long status;
	
	@OneToMany(mappedBy="order")
	private Set<OrderProduct> ordersProducts;
	
	public Set<OrderProduct> getOrdersProducts() {
		return ordersProducts;
	}

	public void setOrdersProducts(Set<OrderProduct> ordersProducts) {
		this.ordersProducts = ordersProducts;
	}

	public Order() {
		createdDate = new Date();
	}
	
	public Order(String firstName, String lastName, String email, String phone, String promotionCode, String address,
			String note) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.promotionCode = promotionCode;
		this.address = address;
		this.note = note;
		
		createdDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public Boolean update(Map<String, Object> map) {

		Order srcObj = StdioHelper.getObjectFromMap(map, Order.class);
		if (srcObj == null)
			return false;
		
		Field field;

		for (String key: map.keySet()) {
			try {
				field = Order.class.getDeclaredField(key);
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