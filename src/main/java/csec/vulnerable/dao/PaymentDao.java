package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
	
}