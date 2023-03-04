package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.Collection;

@Repository
public interface CollectionDao extends JpaRepository<Collection, Integer> {
	
}
