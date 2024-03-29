package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.BillingInfo;

@Repository
public interface BillingInfoDao extends JpaRepository<BillingInfo, Integer> {

}