package csec.vulnerable.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.User;
import csec.vulnerable.beans.UserInfo;
import csec.vulnerable.beans.UserProfile;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.dao.UserInfoDao;
import csec.vulnerable.http.Response;


@Service
@Transactional
public class UserService {
	@Autowired
	UserDao userDao;

	@Autowired
	UserInfoDao userInfoDao ;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<User> getusers(Authentication authentication){
		if(isAdmin(authentication.getAuthorities())){
			return userDao.findAll();
		}else{
			User user = userDao.findByUsername(authentication.getName());
			List<User> list = new ArrayList<>();
			list.add(user);
			return list;
		}
	}
	
	public Response register(User user) {
		if (user.getPassword() == null) {
			return new Response(false, "Password cannot be null");
		}
		// check if username already exists
		User existingUser = userDao.findByUsername(user.getUsername());
		if (existingUser != null) {
			return new Response(false, "Username already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		List<UserProfile> profiles = new ArrayList<UserProfile>();
		profiles.add(new UserProfile(2));
		user.setProfiles(profiles);
		System.out.println(user);
		userDao.save(user);
		UserInfo userInfo = new UserInfo();
		userInfo.setUser(user);
		userInfo.setId(user);
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Date currentDate = new java.sql.Date(now.getTime());
		userInfo.setCreate_date(currentDate);
		userInfoDao.save(userInfo);
		return new Response(true);
	}
	public Response registerAdm(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		List<UserProfile> profiles = new ArrayList<UserProfile>();
		profiles.add(new UserProfile(1));
		user.setProfiles(profiles);
		System.out.println(user);
		userDao.save(user);
		return new Response(true);
	}
	
	public Response changePassword(User user, Authentication authentication, String oldPassword) {
		if (user.getUsername().equals(authentication.getName()) || isAdmin(authentication.getAuthorities())) {
			User u = userDao.findByUsername(user.getUsername());
			if (passwordEncoder.matches(oldPassword, u.getPassword())) {
				u.setPassword(passwordEncoder.encode(user.getPassword()));
				userDao.save(u);
				return new Response(true);
			} else {
				return new Response(false, "Old password is incorrect");
			}
		} else {
			return new Response(false);
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
	
	public Response deleteUser(int id) {
		if(userDao.findById(id)!=null) {
			userDao.deleteById(id);
			return new Response(true);
		}else {
			return new Response(false,"user is not found");
		}
	}

}

