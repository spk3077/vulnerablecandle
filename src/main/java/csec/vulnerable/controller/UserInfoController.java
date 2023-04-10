package csec.vulnerable.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadDirectory;

	@PostMapping("/uploadimage")
	public Response uploadUserImage(@RequestParam("file") MultipartFile file, Authentication authentication) {
		try {
			byte[] bytes = file.getBytes();
			User user = userDao.findByUsername(authentication.getName());
			UserInfo userInfo = userInfoDao.findByUser(user);
			if (userInfo != null) {
				String fileName = UUID.randomUUID().toString() + "." + getFileExtension(file.getOriginalFilename());
				Path path = Paths.get(uploadDirectory, fileName);
				Files.write(path, bytes);
				userInfo.setPicture("../../assets/profiles/" + fileName);
				userInfoDao.save(userInfo);
				return new Response(true);
			} else {
				return new Response(false, "User is not found!");
			}
		} catch (IOException e) {
			return new Response(false, "Failed to save file: " + e.getMessage());
		}
	}

	private String getFileExtension(String fileName) {
		if (fileName == null) {
			return null;
		}
		int extensionIndex = fileName.lastIndexOf('.');
		if (extensionIndex == -1) {
			return null;
		}
		return fileName.substring(extensionIndex + 1);
	}

	
	@DeleteMapping("/{id}")
	public Response deleteUserDetail(@PathVariable int id) {
		return userInfoService.deleteUserInfo(id);
	}
}