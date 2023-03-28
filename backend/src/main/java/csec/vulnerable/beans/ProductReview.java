package csec.vulnerable.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "ecom_product_review")
public class ProductReview {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_REVIEW_SEQ")
	@SequenceGenerator(name = "PRODUCT_REVIEW_SEQ",sequenceName = "ECOM_PRODUCT_REVIEW_SEQ",allocationSize = 121)
	private int id;

    @Column
    private String title;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    @Min(1)
    @Max(5)
	private int grade;

    @Column(length = 2000)
    private String comment;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date review_date;
    
    
    
    public ProductReview() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public ProductReview(Product product, User user, int grade, String comment, Date review_date) {
        this.product = product;
        this.user = user;
        this.grade = grade;
        this.comment = comment;
        this.review_date = review_date;
    }
    
    public ProductReview(Product product, int grade, String comment) {
        this.product = product;
        this.grade = grade;
        this.comment = comment;
    }

    public ProductReview(int grade, String comment) {
        this.grade = grade;
        this.comment = comment;
    }

    @PrePersist
    public void onPrePersist() {
        if (this.review_date == null) {
            this.review_date = new java.sql.Date(System.currentTimeMillis());
        }
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
