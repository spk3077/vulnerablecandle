package csec.vulnerable.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import csec.vulnerable.beans.User;
import csec.vulnerable.beans.UserInfo;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.dao.UserInfoDao;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.UserInfoService;

@RestController()
@RequestMapping("/userinfos")
public class UserInfoController {

	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserInfoService userInfoService;
	
	@GetMapping
	public List<UserInfo> getUserInfos(Authentication authentication){
		return userInfoService.getUserInfos(authentication);
	}

	@PutMapping
	public Response changeUserInfo(@RequestBody UserInfo userInfo, Authentication authentication) {
		return userInfoService.changeUserInfo(userInfo, authentication);
	}
	
	@PostMapping("/uploadimage")
	public Response uploadUserImage(@RequestParam("file") MultipartFile file, Authentication authentication) {
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get("frontend/src/assets/userinfo/" + file.getOriginalFilename());
			Files.write(path, bytes);

			User user = userDao.findByUsername(authentication.getName());
			UserInfo userInfo = userInfoDao.findByUser(user);
			if (userInfo != null) {
				userInfo.setPicture(path);
				userInfoDao.save(userInfo);
				return new Response(true);
			} else {
				return new Response(false, "User is not found!");
			}
		} catch (IOException e) {
			return new Response(false, "Failed to save file: " + e.getMessage());
		}
	}

	
	@DeleteMapping("/{id}")
	public Response deleteUserDetail(@PathVariable int id) {
		return userInfoService.deleteUserInfo(id);
	}
}

