package csec.vulnerable.beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Entity 
@Table(name = "ecom_product")
public class Product {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_SEQ")
	@SequenceGenerator(name = "PRODUCT_SEQ",sequenceName = "ECOM_PRODUCT_SEQ",allocationSize = 1)
	private int id;
	@Column
	@NotEmpty
	private String name;
	@Column
	@NotEmpty
	private String brand;
	@NotNull
	@Positive
	private int price;
	@Column
	@NotNull
	@PositiveOrZero
	private int stock;
	@Column
	private String image;
	@Column
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ProductReview> reviews;
	@ManyToMany(mappedBy = "products")
    private Set<Tag> tags = new HashSet<>();

	public Product(int id) {
		super();
		this.id = id;
	}
	public Product() {
		super();
	}
	public Product(int id, @NotEmpty String name, @NotEmpty String brand, @NotNull @Positive int price,
			@NotNull @PositiveOrZero int stock, String image,String description) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.description = description;
	}
	public Product(@NotEmpty String name, @NotEmpty String brand, @NotNull @Positive int price,
			@NotNull @PositiveOrZero int stock, String image,String description) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.description = description;
	}


	public Product(@NotEmpty String name, @NotEmpty String brand, @NotNull @Positive int price,
			@NotNull @PositiveOrZero int stock, String image, String description, Collection collection,
			List<ProductReview> reviews, Set<Tag> tags) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.description = description;
		this.collection = collection;
		this.reviews = reviews;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
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
	public List<ProductReview> getReviews() {
		return reviews;
	}
	public void setReviews(List<ProductReview> reviews) {
		this.reviews = reviews;
	}
	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + ", stock=" + stock
				+ ", image=" + image + ", description=" + description + ", collection=" + collection + ", reviews="
				+ reviews + ", tags=" + tags + "]";
	}
	
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	
}
