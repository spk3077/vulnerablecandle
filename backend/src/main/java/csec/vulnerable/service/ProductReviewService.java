package csec.vulnerable.service;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.ProductReview;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dao.ProductReviewDao;
import csec.vulnerable.dao.UserDao;
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
	
	public HashMap<Integer,String> getProductReview(int id) {
		HashMap<Integer,String> review = new HashMap<>();
		review.put(productReviewDao.findById(id).get().getGrade(), productReviewDao.findById(id).get().getComment());
		return review;
	}
	
	public HashMap<Integer,HashMap<Integer,String>> getProductReviews() {
		HashMap<Integer,HashMap<Integer,String>> reviews = new HashMap<>();
		for(ProductReview pr : productReviewDao.findAll()){
			HashMap<Integer,String> review = new HashMap<>();
			review.put(pr.getGrade(), pr.getComment());
			reviews.put(pr.getId(), review);
		}
		return reviews;
	}
    //post
	public Response addProductReview(ProductReview productReview,org.springframework.security.core.Authentication authentication) {
        productReview.setUser(userDao.findByUsername(authentication.getName()));
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Date currentDate = new java.sql.Date(now.getTime());
		productReview.setReview_date(currentDate);
		productReviewDao.save(productReview);
		return new Response(true);
	}
	//put
	public Response changeProductReview(ProductReview productReview,org.springframework.security.core.Authentication authentication) {
        ProductReview pr = productReviewDao.findById(productReview.getId()).get();
        if(pr.getUser().equals(userDao.findByUsername(authentication.getName()) )){
            pr.setComment(productReview.getComment());
            pr.setGrade(productReview.getGrade());
			Calendar calendar = Calendar.getInstance();
        	java.util.Date now = calendar.getTime();
        	java.sql.Date currentDate = new java.sql.Date(now.getTime());
			pr.setReview_date(currentDate);
            productReviewDao.save(pr);
            return new Response(true);
        }else{
            return new Response(false,"Autentication error");
        }
		
	}
	
	//delete
	public Response deleteProductReview(int id,org.springframework.security.core.Authentication authentication) {
		if(productReviewDao.findById(id)!=null) {
            if(productReviewDao.findById(id).get().getUser().equals(userDao.findByUsername(authentication.getName()))){
                productReviewDao.deleteById(id);
			    return new Response(true);
            }else{
                return new Response(false,"Autentication error");
            }
		}else {
			return new Response(false,"ProductReview is not found");
		}
	}

}
