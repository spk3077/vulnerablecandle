package csec.vulnerable.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity 
@Table(name = "ecom_tag")
public class Tag {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TAG_SEQ")
	@SequenceGenerator(name = "TAG_SEQ",sequenceName = "ECOM_TAG_SEQ",allocationSize = 1)
	private int id;
	@Column
	private String name;
	@ManyToMany
    @JoinTable(
        name = "product_tag",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();
	
	public Tag(int id, String name, Set<Product> products) {
		this.id = id;
		this.name = name;
		this.products = products;
	}
	public Tag(int id,  String name) {
		this.id = id;
		this.name = name;
	}
	
	public Tag( String name) {
		this.name = name;
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
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", products=" + products + "]";
	}
    

}
