package csec.vulnerable.dto;

import java.sql.Date;

public class ProductReviewDTO {
    private int id;
    private String username;
    private String title;
    private int grade;
    private String comment;
    private Date review_date;
    private int productId;

    public ProductReviewDTO(int id, String username, String title, int grade, String comment, Date review_date, int productId) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.grade = grade;
        this.comment = comment;
        this.review_date = review_date;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGrade() {
        return grade;
    }

    public String getComment() {
        return comment;
    }

    public Date getReview_date() {
        return review_date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
