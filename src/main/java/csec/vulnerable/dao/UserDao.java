package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.User;

@Repository
public interface UserDao extends JpaRepository<User,Integer>{
	User findByUsername(String username);
}
