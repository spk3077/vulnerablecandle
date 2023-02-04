package csec.vulnerable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csec.vulnerable.beans.ShoppingCart;
import csec.vulnerable.dao.OrderDao;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dao.ShoppingCartDao;
import csec.vulnerable.http.Response;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartService {
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ShoppingCartDao shoppingCartDao;
	
	public ShoppingCart getShoppingCart(int id) {
		return shoppingCartDao.findById(id).get();
	}
	
	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCartDao.findAll();
	}
	
	//post
	public Response addShoppingCart(ShoppingCart shoppingCart) {
		int leftStock = productDao.findById(shoppingCart.getProduct().getId()).get().getStock() - shoppingCart.getQuantity();
		if(leftStock >= 0) {
			productDao.findById(shoppingCart.getProduct().getId()).get().setStock(leftStock);
			productDao.save(productDao.findById(shoppingCart.getProduct().getId()).get());
			shoppingCart.setProduct(productDao.findById(shoppingCart.getProduct().getId()).get());
			shoppingCartDao.save(shoppingCart);
			return new Response(true);
		}else {
			return new Response(false, "Out the stock :" + leftStock);
		}
		
	}
	//put
	public Response changeShoppingCart(ShoppingCart shoppingCart) {
		int leftStock = productDao.findById(shoppingCart.getProduct().getId()).get().getStock() - (shoppingCart.getQuantity() - shoppingCartDao.findById(shoppingCart.getId()).get().getQuantity());
		if(leftStock >= 0) {
			productDao.findById(shoppingCart.getProduct().getId()).get().setStock(leftStock);
			ShoppingCart op = shoppingCartDao.findById(shoppingCart.getId()).get();
			op.setOrder(shoppingCart.getOrder());
			productDao.save(productDao.findById(shoppingCart.getProduct().getId()).get());
			op.setProduct(shoppingCart.getProduct());
			op.setQuantity(shoppingCart.getQuantity());
			shoppingCartDao.save(op);												
			return new Response(true);
		}else {
			return new Response(false, "Out the stock :" + leftStock);
		}
	}
	
	//delete
	public Response deleteShoppingCart(int id) {
		if(shoppingCartDao.findById(id)!=null) {
			shoppingCartDao.deleteById(id);
			return new Response(true);
		}else {
			return new Response(false,"ShoppingCart is not found");
		}
	}
	
	
}

