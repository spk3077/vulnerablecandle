package csec.vulnerable.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.CartItem;
import csec.vulnerable.beans.User;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer> {

    List<CartItem> findAllByShoppingCart(User user); 
    
}