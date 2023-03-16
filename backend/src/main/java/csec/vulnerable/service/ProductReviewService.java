package csec.vulnerable.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Response addProductReview(ProductReview productReview, org.springframework.security.core.Authentication authentication) {
        validateGrade(productReview.getGrade());

        productReview.setUser(userDao.findByUsername(authentication.getName()));
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Date currentDate = new java.sql.Date(now.getTime());
        productReview.setReview_date(currentDate);
        productReviewDao.save(productReview);
        return new Response(true);
    }

    /* // put
    public Response changeProductReview(int id, ProductReviewDTO productReviewDTO, org.springframework.security.core.Authentication authentication) {
		ProductReview pr = productReviewDao.findById(id).orElse(null);
		if (pr == null) {
			return new Response(false, "ProductReview is not found");
		}
	
		if (pr.getUser().equals(userDao.findByUsername(authentication.getName()))) {
			validateGrade(productReviewDTO.getGrade());
	
			pr.setComment(productReviewDTO.getComment());
			pr.setGrade(productReviewDTO.getGrade());
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Date currentDate = new java.sql.Date(now.getTime());
			pr.setReview_date(currentDate);
			productReviewDao.save(pr);
			return new Response(true);
		} else {
			return new Response(false, "Authentication error");
		}
	} */

    // delete
    public Response deleteProductReview(int id, org.springframework.security.core.Authentication authentication) {
        if (productReviewDao.findById(id) != null) {
            if (productReviewDao.findById(id).get().getUser().equals(userDao.findByUsername(authentication.getName()))) {
                productReviewDao.deleteById(id);
                return new Response(true);
            } else {
                return new Response(false, "Authentication error");
            }
        } else {
            return new Response(false, "ProductReview is not found");
        }
    }
}