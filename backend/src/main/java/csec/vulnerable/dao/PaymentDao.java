package csec.vulnerable.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.Payment;
import csec.vulnerable.beans.User;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {

    List<Payment> findByUser(User user);
	
}