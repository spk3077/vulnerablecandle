package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.Tag;

@Repository
public interface TagDao extends JpaRepository<Tag, Integer> {
	
}
