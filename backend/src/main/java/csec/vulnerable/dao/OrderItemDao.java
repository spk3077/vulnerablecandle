package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.OrderItem;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
     
}