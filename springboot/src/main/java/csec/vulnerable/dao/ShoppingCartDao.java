package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.ShoppingCart;


@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Integer> {
	
}
