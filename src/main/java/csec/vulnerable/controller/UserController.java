package csec.vulnerable.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		User user = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/csec", "root", "csec77499981");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM ecom_user WHERE id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@PostMapping
	public Response addUser(@RequestBody User user) {
		return userService.register(user);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PutMapping
	public Response changeUser(@RequestBody ChangePasswordRequest request, Authentication authentication) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/csec?useTimezone=true&serverTimezone=UTC", "root", "csec77499981")) {
			String sqlGetPassword = "SELECT password FROM ecom_user WHERE username = '" + request.getUsername() + "'";
			try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlGetPassword)) {

				if (rs.next()) {
					String oldPasswordHash = rs.getString("password");

					if (passwordEncoder.matches(request.getOldPassword(), oldPasswordHash)) {
						String newPasswordHash = passwordEncoder.encode(request.getNewPassword());
						String sqlUpdatePassword = "UPDATE ecom_user SET password = '" + newPasswordHash + "' WHERE username = '" + request.getUsername() + "'";

						try (Statement stmtUpdate = conn.createStatement()) {
							stmtUpdate.executeUpdate(sqlUpdatePassword);
							return new Response(true, "Password updated successfully.");
						}
					} else {
						return new Response(false, "Old password does not match.");
					}
				} else {
					return new Response(false, "User not found.");
				}
			}
		} catch (SQLException e) {
			return new Response(false, "Error updating password.");
		}
	}

	
	@DeleteMapping("/{id}")
	public Response deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}
	
	

}
