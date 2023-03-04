package csec.vulnerable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.User;
import csec.vulnerable.beans.UserInfo;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
	UserInfo findByUser(User user);
}
