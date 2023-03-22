package csec.vulnerable.controller;

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
import org.springframework.web.bind.annotation.RestController;

import csec.vulnerable.beans.UserInfo;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.UserInfoService;

@RestController()
@RequestMapping("/userinfos")
public class UserInfoController {
	@Autowired
	UserInfoService userInfoService;
	
	@GetMapping
	public List<UserInfo> getUserInfos(Authentication authentication){
		return userInfoService.getUserInfos(authentication);
	}

	@PostMapping
	public Response addUserInfo(@RequestBody UserInfo userInfo,Authentication authentication) {
		return userInfoService.addUserInfo(userInfo,authentication);
	}
	
	@PutMapping
	public Response changeUserInfo(@RequestBody UserInfo userInfo, Authentication authentication) {
		return userInfoService.changeUserInfo(userInfo, authentication);
	}
	
	@DeleteMapping("/{id}")
	public Response deleteUserDetail(@PathVariable int id) {
		return userInfoService.deleteUserInfo(id);
	}
}

