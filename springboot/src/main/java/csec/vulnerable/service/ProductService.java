package csec.vulnerable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csec.vulnerable.beans.Product;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.http.Response;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	
	//get
	public Product getProduct(int id) {
		return productDao.findById(id).get();
	}
	
	public List<Product> getProducts() {
		return productDao.findAll();
		
	}
	//post
	public Response addProduct(Product product) {
		productDao.save(product);
		return new Response(true);
	}
	//put
	public Response changeProduct(Product product) {
		Product p = productDao.findById(product.getId()).get();
		p.setBrand(product.getBrand());
		p.setImage(p.getImage());
		p.setPrice(product.getPrice());
		p.setName(product.getName());
		p.setStock(product.getStock());
		p.setDescription(product.getDescription());
		p.setReviews(product.getReviews());
		productDao.save(p);
		return new Response(true);
	}
	
	//delete
	public Response deleteProduct(int id) {
		if(productDao.findById(id)!=null) {
			productDao.deleteById(id);
			return new Response(true);
		}else {
			return new Response(false,"product is not found");
		}
	}
	
}

