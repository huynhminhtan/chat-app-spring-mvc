package main.java.entities;

//import stdio.utilities.StdioHelper;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="ProductCategories")
public class ProductCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	private String description;
	
	private String friendlyUrl;
	private Long position;
	private Boolean isPublished;
	private Boolean isFeatured;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@OneToMany(fetch=FetchType.LAZY,  mappedBy="productCategory")
	private List<Product> products;

	public ProductCategory() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFriendlyUrl() {
		return friendlyUrl;
	}

	public void setFriendlyUrl(String friendlyUrl) {
		this.friendlyUrl = friendlyUrl;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public Boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Boolean update(Map<String, Object> map) {
//
//		ProductCategory srcObj = StdioHelper.getObjectFromMap(map, ProductCategory.class);
//		if (srcObj == null)
//			return false;
//
//		Field field;
//
//		for (String key: map.keySet()) {
//			try {
//				field = ProductCategory.class.getDeclaredField(key);
//				if (field.getName().equals("id") == false &&
//					field.getName().equals("createdDate") == false) {
//					boolean accessible = field.isAccessible();
//					field.setAccessible(true);
//					field.set(this, field.get(srcObj));
//					field.setAccessible(accessible);
//				}
//			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
		return true;
	}
}