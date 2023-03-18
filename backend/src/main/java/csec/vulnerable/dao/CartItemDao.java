package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.CartItem;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer> { 
    
}