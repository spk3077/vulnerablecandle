package csec.vulnerable.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.User;
import csec.vulnerable.beans.UserInfo;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.dao.UserInfoDao;
import csec.vulnerable.http.Response;


@Service
@Transactional
public class UserInfoService {
	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	UserDao userDao;
	
	public List<UserInfo> getUserInfos(Authentication authentication) {
		if(isAdmin(authentication.getAuthorities())){
			return userInfoDao.findAll();
		}else{
			User user = userDao.findByUsername(authentication.getName());
			UserInfo userInfo = userInfoDao.findByUser(user);
			List<UserInfo> list = new ArrayList<>();
			list.add(userInfo);
			return list;
		}

	}
	
	public boolean isAdmin(Collection<? extends GrantedAuthority> profiles) {
		boolean isAdmin = false;
		for(GrantedAuthority profile : profiles) {
			if(profile.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}
		return isAdmin;
	}
	
	public UserInfo getUserInfo(int id,Authentication authentication) {
		if(userInfoDao.findById(id).get().getUser().getUsername().equals(authentication.getName()) || isAdmin(authentication.getAuthorities())){
			return userInfoDao.findById(id).get();
		}
		return null;
		
	}
	
	public Response addUserInfo(UserInfo userInfo,Authentication authentication) {
		if(userInfo.getUser().getUsername().equals(authentication.getName())){
			UserInfo ui = userInfoDao.findByUser(userDao.findByUsername(authentication.getName()));
			ui.setAddress(userInfo.getAddress());
			ui.setCity(userInfo.getCity());
			ui.setEmail(userInfo.getEmail());
			ui.setName(userInfo.getName());
			ui.setPhone(userInfo.getPhone());
			ui.setState(userInfo.getState());
			ui.setZip(userInfo.getZip());
			ui.setPicture(userInfo.getPicture());
			userInfoDao.save(ui);
		}else{
			return new Response(false);
		}
		return new Response(true);
	}

	public Response changeUserInfo(UserInfo userInfo, Authentication authentication) {
		// Check if user information being passed in is null or empty
		if(userInfo == null || userInfo.getName() == null || userInfo.getName().isEmpty() || 
			userInfo.getEmail() == null || userInfo.getEmail().isEmpty() || 
			userInfo.getPhone() == null || userInfo.getPhone().isEmpty()) {
			return new Response(false, "Invalid user information.");
		}
		
		User user = userDao.findByUsername(authentication.getName());
		
		if(user == null) {
			return new Response(false, "User not found.");
		}
		
		UserInfo ui = userInfoDao.findByUser(user);
		
		if(ui == null) {
			return new Response(false, "User information not found.");
		}
		
		// Update user information with validated data
		if(userInfo.getAddress() != null) {
			ui.setAddress(userInfo.getAddress());
		}
		if(userInfo.getCity() != null) {
			ui.setCity(userInfo.getCity());
		}
		if(userInfo.getEmail() != null) {
			ui.setEmail(userInfo.getEmail());
		}
		if(userInfo.getName() != null) {
			ui.setName(userInfo.getName());
		}
		if(userInfo.getPhone() != null) {
			ui.setPhone(userInfo.getPhone());
		}
		if(userInfo.getState() != null) {
			ui.setState(userInfo.getState());
		}
		if(userInfo.getZip() != null) {
			ui.setZip(userInfo.getZip());
		}
		if(userInfo.getPicture() != null) {
			ui.setPicture(userInfo.getPicture());
		}
		
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

