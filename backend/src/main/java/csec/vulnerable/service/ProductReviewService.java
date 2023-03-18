package csec.vulnerable.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.Product;
import csec.vulnerable.beans.ProductReview;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dao.ProductReviewDao;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.dto.ProductReviewDTO;
import csec.vulnerable.http.Response;

@Service
@Transactional
public class ProductReviewService {
	@Autowired
	ProductDao productDao;

    @Autowired
    UserDao userDao;
	
	@Autowired
	ProductReviewDao productReviewDao;
	
	public ProductReviewDTO getProductReview(int id) {
        ProductReview pr = productReviewDao.findById(id).orElse(null);
        if (pr == null) {
            return null;
        }

        return new ProductReviewDTO(pr.getId(), pr.getUser().getUsername(), pr.getTitle(), pr.getGrade(), pr.getComment(), pr.getReview_date(), pr.getProduct().getId());
    }

    public List<ProductReviewDTO> getProductReviews() {
        List<ProductReviewDTO> reviews = new ArrayList<>();

        for (ProductReview pr : productReviewDao.findAll()) {
            ProductReviewDTO reviewDTO = new ProductReviewDTO(pr.getId(), pr.getUser().getUsername(), pr.getTitle(), pr.getGrade(), pr.getComment(), pr.getReview_date(), pr.getProduct().getId());
            reviews.add(reviewDTO);
        }

        return reviews;
    }
    // Validate the grade
    private void validateGrade(int grade) {
        if (grade < 1 || grade > 5) {
            throw new IllegalArgumentException("Grade must be between 1 and 5");
        }
    }

    // post
    public Response addProductReview(ProductReview productReview, Authentication authentication) {
        validateGrade(productReview.getGrade());
        productReview.setUser(userDao.findByUsername(authentication.getName()));
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Date currentDate = new java.sql.Date(now.getTime());
        productReview.setReview_date(currentDate);
        // Retrieve the product from the database using the productId
        Product product = productDao.findById(productReview.getProduct().getId()).orElse(null);
        if (product == null) {
            return new Response(false, "Product not found");
        }
        // Set the product for the review
        productReview.setProduct(product);
        productReviewDao.save(productReview);
        return new Response(true);
    }
}