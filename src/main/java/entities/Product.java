package stdio.entities;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import stdio.utilities.StdioHelper;

@Entity
@Table(name="Products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	private String description;
	private String friendlyUrl;
	
	private String code;
	
	@Lob
	private String accessories;
	
	@Lob
	private String content;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@OrderColumn
	private List<String> thumbnails;
	
	public List<String> getThumbnails() {
		return thumbnails;
	}
	
	public void setThumbnails(List<String> thumbnails) {
		this.thumbnails = thumbnails;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	@OrderColumn
	private List<String> screenshots;
	
	public List<String> getScreenshots() {
		return screenshots;
	}
	
	public void setScreenshots(List<String> screenshots) {
		this.screenshots = screenshots;
	}

	private String video;
	
	private Long price;
	
	private Boolean isPublished;
	private Boolean isFeatured;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private ProductCategory productCategory;

	public Long getId() {
		return id;
	}
	public String getAccessories() {
		return accessories;
	}
	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
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
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getPrice() {
		return price;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public String getVideo() {
		return video;
	}
	
	public void setVideo(String video) {
		this.video = video;
	}
	
	public String getFriendlyUrl() {
		return friendlyUrl;
	}
	
	public void setFriendlyUrl(String friendlyUrl) {
		this.friendlyUrl = friendlyUrl;
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
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public static String[] jsonToStringArray(String jsonStr) {
		
		String[] strArray = null;
		
		if (jsonStr != null) {
			try {
				if (jsonStr != null)
					strArray = new ObjectMapper().readValue(jsonStr, String[].class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strArray;
	}
	
	public static String stringArrayToJson(String[] stringArray) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(stringArray);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	public Boolean update(Map<String, Object> map) {

		Product srcObj = StdioHelper.getObjectFromMap(map, Product.class);
		if (srcObj == null)
			return false;
		
		Field field;

		for (String key: map.keySet()) {
			try {
				field = Product.class.getDeclaredField(key);
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