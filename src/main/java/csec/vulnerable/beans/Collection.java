package csec.vulnerable.beans;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ecom_collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "COLLECTION_SEQ")
    @SequenceGenerator(name = "COLLECTION_SEQ", sequenceName = "ECOM_COLLECTION_SEQ", allocationSize = 1)
    private int id;
    @Column
    @NotEmpty
    private String name;
    @Column
    private String description;

    @ManyToMany
    @JoinTable(
            name = "collection_product",
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    public Collection() {
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.getCollections().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getCollections().remove(this);
    }
    
}
