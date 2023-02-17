package csec.vulnerable.beans;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "ecom_product_review")
public class ProductReview {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_REVIEW_SEQ")
	@SequenceGenerator(name = "PRODUCT_REVIEW_SEQ",sequenceName = "ECOM_PRODUCT_REVIEW_SEQ",allocationSize = 1)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	@JsonIgnoreProperties("reviews")
	Product product;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("myreviews")
	User user;
	@Column
	private int grade;
    @Column
    private String comment;
    @Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date review_date;
    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductReview(int grade, String comment) {
        this.grade = grade;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReview_date() {
        return review_date;
    }

    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }

    @Override
    public String toString() {
        return "ProductReview [id=" + id + ", product=" + product + ", user=" + user + ", grade=" + grade + ", comment="
                + comment + ", review_date=" + review_date + "]";
    }

}
