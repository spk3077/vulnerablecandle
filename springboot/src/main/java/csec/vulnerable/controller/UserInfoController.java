package csec.vulnerable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@PreAuthorize(("hasAuthority('ROLE_ADMIN')"))
	@GetMapping
	public List<UserInfo> getUserInfos(){
		return userInfoService.getUserInfos();
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@GetMapping("/{id}")
	public UserInfo getUserInfo(@PathVariable int id) {
		return userInfoService.getUserInfo(id);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@PostMapping
	public Response addUserInfo(@RequestBody UserInfo userInfo,Authentication authentication) {
		return userInfoService.addUserInfo(userInfo,authentication);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@PutMapping
	public Response changeUserInfo(@RequestBody UserInfo userInfo, Authentication authentication) {
		return userInfoService.changeUserInfo(userInfo, authentication);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@DeleteMapping("/{id}")
	public Response deleteUserDetail(@PathVariable int id) {
		return userInfoService.deleteUserInfo(id);
	}
}

