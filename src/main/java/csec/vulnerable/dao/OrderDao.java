package csec.vulnerable.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.Order;
import csec.vulnerable.beans.User;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
	List<Order> findAllByUser(User user);

}
