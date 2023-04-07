package csec.vulnerable.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.ShoppingCart;
import csec.vulnerable.beans.User;


@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Integer> {
	List<ShoppingCart> findAllByUser(User user);

    ShoppingCart findByUser(User findByUsername);
}
