package csec.vulnerable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csec.vulnerable.beans.ProductReview;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dao.ProductReviewDao;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.http.Response;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductReviewService {
	@Autowired
	ProductDao productDao;

    @Autowired
    UserDao userDao;
	
	@Autowired
	ProductReviewDao productReviewDao;
	
	public ProductReview getProductReview(int id) {
		return productReviewDao.findById(id).get();
	}
	
	public List<ProductReview> getProductReviews() {
		return productReviewDao.findAll();
	}

    //post
	public Response addProductReview(ProductReview productReview,org.springframework.security.core.Authentication authentication) {
        productReview.setUser(userDao.findByUsername(authentication.getName()));
		productReviewDao.save(productReview);
		return new Response(true);
	}
	//put
	public Response changeProductReview(ProductReview productReview,org.springframework.security.core.Authentication authentication) {
        ProductReview pr = productReviewDao.findById(productReview.getId()).get();
        if(pr.getUser().equals(userDao.findByUsername(authentication.getName()) )){
            pr.setComment(productReview.getComment());
            pr.setGrade(productReview.getGrade());
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
