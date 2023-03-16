package csec.vulnerable.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csec.vulnerable.beans.Product;
import csec.vulnerable.beans.ProductReview;
import csec.vulnerable.beans.Tag;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dto.ProductDTO;
import csec.vulnerable.dto.ProductReviewDTO;
import csec.vulnerable.http.Response;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    private ProductDao productDao;

	/* @Autowired
	private UserDao userDao;

	@Autowired
	private TagDao tagDao; */

    @Override
    public ProductDTO findById(int id) {
        Product product = productDao.findById(id).orElse(null);
        return product != null ? productToProductDTO(product) : null;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productDao.findAll().stream().map(this::productToProductDTO).collect(Collectors.toList());
    }

    @Override
    public Response addProduct(Product product) {
        productDao.save(product);
        return new Response(true, "Product added successfully.");
    }

    @Override
    public Response updateProduct(Product product) {
        Product existingProduct = productDao.findById(product.getId()).orElse(null);

        if (existingProduct != null) {
            productDao.save(product);
            return new Response(true, "Product updated successfully.");
        } else {
            return new Response(false, "Product not found.");
        }
    }

    @Override
    public Response deleteProduct(int id) {
        if (productDao.existsById(id)) {
            productDao.deleteById(id);
            return new Response(true, "Product deleted successfully.");
        } else {
            return new Response(false, "Product not found.");
        }
    }

    private ProductDTO productToProductDTO(Product product) {
		List<String> tagNames = product.getTags().stream().map(Tag::getName).collect(Collectors.toList());
		double averageReviewGrade = product.getProductReviews().isEmpty() ? 0.0
				: product.getProductReviews().stream().mapToInt(ProductReview::getGrade).average().orElse(0.0);
	
		return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
				product.getProductReviews().stream().map(this::productReviewToProductReviewDTO).collect(Collectors.toList()),
				averageReviewGrade, tagNames);
	}
	
	private ProductReviewDTO productReviewToProductReviewDTO(ProductReview productReview) {
		return new ProductReviewDTO(productReview.getId(), productReview.getUser().getUsername(), productReview.getTitle(),
				productReview.getGrade(), productReview.getComment(), productReview.getReview_date(), productReview.getProduct().getId());
	}
	
}
