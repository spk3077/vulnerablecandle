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

