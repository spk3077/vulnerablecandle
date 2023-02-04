package csec.vulnerable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import csec.vulnerable.beans.UserInfo;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.dao.UserInfoDao;
import csec.vulnerable.http.Response;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserInfoService {
	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	UserDao userDao;
	
	public List<UserInfo> getUserInfos() {
		return userInfoDao.findAll();
	}
	
	public UserInfo getUserInfo(int id) {
		return userInfoDao.findById(id).get();
	}
	
	public Response addUserInfo(UserInfo userInfo,Authentication authentication) {
		userInfo.setUser(userDao.findByUsername(authentication.getName()));
		userInfo.setId(userDao.findByUsername(authentication.getName()));
		userInfoDao.save(userInfo);
		return new Response(true);
	}
	
	public Response changeUserInfo(UserInfo userInfo, Authentication authentication) {
		UserInfo ui = userInfoDao.findByUser(userDao.findByUsername(authentication.getName()));
		ui.setAddress(userInfo.getAddress());
		ui.setCity(userInfo.getCity());
		ui.setEmail(userInfo.getEmail());
		ui.setName(userInfo.getName());
		ui.setPhone(userInfo.getPhone());
		ui.setState(userInfo.getState());
		ui.setZip(userInfo.getZip());
		userInfoDao.save(ui);
		return new Response(true);
	}
	
	public Response deleteUserInfo(int id) {
		if (userInfoDao.findById(id) != null) {
			userInfoDao.deleteById(id);
			return new Response(true);
		} else {
			return new Response(false, "User is not found!");
		}
	}
	
}

