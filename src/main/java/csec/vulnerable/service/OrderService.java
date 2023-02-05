package csec.vulnerable.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import csec.vulnerable.beans.Order;
import csec.vulnerable.beans.Product;
import csec.vulnerable.beans.ShoppingCart;
import csec.vulnerable.dao.OrderDao;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dao.ShoppingCartDao;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.http.Response;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ShoppingCartDao shoppingCartDao;
	
	@Autowired
	UserDao userDao;
	
	public boolean isAdmin(Collection<? extends GrantedAuthority> profiles) {
		boolean isAdmin = false;
		for(GrantedAuthority profile : profiles) {
			if(profile.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}
		return isAdmin;
	}
	//get
	/**
	 * @param order
	 * @param authentication
	 * @return all the order under authentication
	 */
	public List<Order> getOrders(Authentication authentication){
		if(isAdmin(authentication.getAuthorities())) {
			return orderDao.findAll();
		} else {
			return orderDao.findAllByUser(userDao.findByUsername(authentication.getName()));
		}
	}
	
	public Order getOrder(int id) {
		return orderDao.findById(id).get();
	}
	
	//post
	/**
	 * create a new order
	 * @param order
	 * @param authentication
	 * @return Response
	 */
	public Response addOrder(Order order, Authentication authentication) {
		try {
			List<ShoppingCart> purchaseShoppingCarts = order.getPurchases();
			purchaseShoppingCarts.forEach((ShoppingCart) -> {
				Product product = (Product)productDao.findById(ShoppingCart.getProduct().getId()).get();
				int leftStock = product.getStock() - ShoppingCart.getQuantity();
				if(leftStock >= 0) {
					product.setStock(leftStock);
					ShoppingCart.setProduct(product);
					ShoppingCart.setOrder(order);
				}
			});
			order.setUser(userDao.findByUsername(authentication.getName()));
			orderDao.save(order);
			order.setPurchases(purchaseShoppingCarts);
			return new Response(true);
		}catch (Exception e) {
			return new Response(false,e.getMessage());
		}
	}
	//put
	public Response changeOrder(Order order) {
		Order o = orderDao.findById(order.getId()).get();
		o.setPurchase_date(order.getPurchase_date());
		try {
			List<ShoppingCart> purchaseShoppingCarts = order.getPurchases();
			purchaseShoppingCarts.forEach((ShoppingCart) -> {
				Product product = (Product)productDao.findById(ShoppingCart.getProduct().getId()).get();
				int leftStock = product.getStock() - ShoppingCart.getQuantity();
				if(leftStock >= 0) {
					product.setStock(leftStock);
					ShoppingCart.setProduct(product);
				}
		});
		o.setPurchases(order.getPurchases());
		orderDao.save(o);
		return new Response(true);
		}catch (Exception e) {
			return new Response(false,e.getMessage());
		}
	}
	//delete
	public Response deleteOrder(int id) {
		if(orderDao.findById(id)!=null) {
			orderDao.deleteById(id);
			return new Response(true);
		}else {
			return new Response(false,"order is not found");
		}
	}
}

