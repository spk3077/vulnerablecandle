package csec.vulnerable.dto;

import java.util.List;

import csec.vulnerable.beans.ProductReview;

public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private List<ProductReviewDTO> productReviews;
    private double averageReviewGrade;
    private List<String> tagNames;

    public ProductDTO(int id, String name, String description, double price, List<ProductReviewDTO> productReviews, double averageReviewGrade, List<String> tagNames) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productReviews = productReviews;
        this.averageReviewGrade = averageReviewGrade;
        this.tagNames = tagNames;
    }

    public ProductDTO(int id2, String name2, String description2, int price2, List<ProductReview> productReviews2,
            double averageReviewGrade2, List<String> tagNames2) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<ProductReviewDTO> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewDTO> productReviews) {
        this.productReviews = productReviews;
    }

    public double getAverageReviewGrade() {
        return averageReviewGrade;
    }

    public void setAverageReviewGrade(double averageReviewGrade) {
        this.averageReviewGrade = averageReviewGrade;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    
}
