package csec.vulnerable.controller;

import java.util.ArrayList;
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

import csec.vulnerable.beans.ChangePasswordRequest;
import csec.vulnerable.beans.User;
import csec.vulnerable.dto.UserDTO;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.UserService;

@RestController()
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	public List<UserDTO> getUsers(Authentication authentication) {
		List<User> users = userService.getusers(authentication);
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User user : users) {
			userDTOs.add(new UserDTO(user.getId(), user.getUsername(), user.getUserInfo()));
		}
		return userDTOs;
	}

	@PostMapping
	public Response addUser(@RequestBody User user) {
		return userService.register(user);
	}

	@PutMapping
	public Response changeUser(@RequestBody ChangePasswordRequest request, Authentication authentication) {
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(request.getNewPassword());
		return userService.changePassword(user, authentication, request.getOldPassword());
	}

	
	@DeleteMapping("/{id}")
	public Response deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}
	
	

}

