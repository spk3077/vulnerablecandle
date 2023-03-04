package csec.vulnerable.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity 
@Table(name = "ecom_collection")
public class Collection {
    @Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "COLLECTION_SEQ")
	@SequenceGenerator(name = "COLLECTION_SEQ",sequenceName = "ECOM_COLLECTION_SEQ",allocationSize = 1)
	private int id;
	@Column
	@NotEmpty
	private String name;
    @Column
	private String description;
    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Product> products;
    
    
    public Collection() {
    }

    public Collection(int id, @NotEmpty String name, String description, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
    }
    
    public Collection(@NotEmpty String name, String description, Set<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Collection [id=" + id + ", name=" + name + ", description=" + description + ", products=" + products
                + "]";
    }
    
    public Set<Product> getProducts() {
        return products;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    
}
