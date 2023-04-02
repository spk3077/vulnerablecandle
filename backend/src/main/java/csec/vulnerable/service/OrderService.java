package csec.vulnerable.service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.CartItem;
import csec.vulnerable.beans.Order;
import csec.vulnerable.beans.OrderItem;
import csec.vulnerable.beans.Payment;
import csec.vulnerable.beans.ShoppingCart;
import csec.vulnerable.beans.User;
import csec.vulnerable.beans.UserInfo;
import csec.vulnerable.dao.OrderDao;
import csec.vulnerable.dao.OrderItemDao;
import csec.vulnerable.dao.PaymentDao;
import csec.vulnerable.dao.ShoppingCartDao;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.dto.PaymentDTO;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    PaymentDao paymentDao;

    public List<Order> getOrdersByUser(User user) {
        return orderDao.findAllByUser(user);
    }

    public Order getOrderById(int id) {
        return orderDao.findById(id).orElse(null);
    }

    public Order createOrder(Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(authentication);
        if (shoppingCart == null || shoppingCart.getCartItems().isEmpty()) {
            throw new RuntimeException("Shopping cart is empty");
        }
    
        Order order = new Order();
        order.setUser(user);
    
        List<Payment> thePayments = paymentDao.findByUser(user);
        List<PaymentDTO> payments = thePayments.stream().map(Payment::toPaymentDTO).collect(Collectors.toList());
        if (payments == null || payments.isEmpty()) {
            throw new RuntimeException("The payment method missing.");
        }
        PaymentDTO payment = payments.get(0);
        
        UserInfo userInfo = user.getUserInfo();

        order.setName(userInfo.getName());
        order.setEmail(userInfo.getEmail());
        order.setAddress(userInfo.getAddress());
        order.setCity(userInfo.getCity());
        order.setState(userInfo.getState());
        order.setZip(userInfo.getZip());
        order.setCardNumber(payment.getAnonymousPayment().getCardNumber());
        order.setPaymentOwnerName(payment.getAnonymousPayment().getOwnerName());
        
        if (order.getName() == null || order.getAddress() == null
                || order.getCity() == null || order.getState() == null || order.getZip() == null) {
            throw new RuntimeException("Billing information is incomplete");
        }

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity());
            orderItem.setOrder(order); // Set the saved Order object to the OrderItem object
            order.addOrderItem(orderItem);
            orderItemDao.save(orderItem);
        }
    
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Date currentDate = new java.sql.Date(now.getTime());
        order.setPurchase_date(currentDate);
        orderDao.save(order);
        shoppingCartService.clearShoppingCart(authentication);
        return order;
    }
    
    
}
