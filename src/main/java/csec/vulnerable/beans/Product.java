package csec.vulnerable.beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity 
@Table(name = "ecom_product")
public class Product {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_SEQ")
	@SequenceGenerator(name = "PRODUCT_SEQ",sequenceName = "ECOM_PRODUCT_SEQ",allocationSize = 1, initialValue = 44)
	private int id;
	@Column
	@NotEmpty
	private String name;
	@Column
	@NotEmpty
	private String brand;
	@NotNull
	@Positive
	private Double price;
	@Column
	@NotNull
	@PositiveOrZero
	private Integer stock;
	@Column
	private String image;
	@Column(length = 2000)
	private String description;

	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<ProductReview> productReviews;

	@ManyToMany
    @JoinTable(
        name = "product_tag",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

	@ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<Collection> collections = new HashSet<>();

	public Product(int id) {
		super();
		this.id = id;
	}
	public Product() {
		super();
	}
	
	public Product(@NotEmpty String name, @NotEmpty String brand, @NotNull @Positive double price,
			@NotNull @PositiveOrZero int stock, String image, String description, List<ProductReview> productReviews,
			Set<Tag> tags) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.description = description;
		this.productReviews = productReviews;
		this.tags = tags;
	}
	/**
	 * Get and Set 	
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + ", stock=" + stock
				+ ", image=" + image + ", description=" + description + ", productReviews=" + productReviews + ", tags="
				+ tags + "]";
	}
	public List<ProductReview> getProductReviews() {
		return productReviews;
	}
	public void setProductReviews(List<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}
	public Set<Collection> getCollections() {
		return collections;
	}
	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}
	
}
