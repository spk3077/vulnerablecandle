package csec.vulnerable.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			userDTOs.add(new UserDTO(user.getId(), user.getUsername(), user.getUserInfo(),user.getAuthorities()));
		}
		return userDTOs;
	}

	@PostMapping
	public Response addUser(@RequestBody User user) {
		return userService.register(user);
	}

	@PutMapping
	public Response changeUser(@RequestBody ChangePasswordRequest request, Authentication authentication) {
		String sql = "UPDATE users SET password = ? WHERE username = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/csec", "username", "password");
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, request.getNewPassword());
			stmt.setString(2, request.getUsername());
			stmt.executeUpdate();
			return new Response(true, "Password updated successfully.");
		} catch (SQLException e) {
			return new Response(false, "Error updating password.");
		}
	}

	
	@DeleteMapping("/{id}")
	public Response deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}
	
	

}

