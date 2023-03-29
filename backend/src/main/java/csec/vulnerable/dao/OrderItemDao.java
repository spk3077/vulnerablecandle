package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import csec.vulnerable.beans.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {

}