package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.ProductReview;

@Repository
public interface ProductReviewDao extends JpaRepository<ProductReview, Integer> {
	
}
