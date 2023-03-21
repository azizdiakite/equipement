/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 */
package org.itech.equipment.controller;

import java.util.List;

import javax.validation.Valid;

import org.itech.equipment.config.UserValidator;
import org.itech.equipment.dto.UserDTO;
import org.itech.equipment.model.AppUser;
import org.itech.equipment.service.AppUserService;
import org.itech.equipment.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private AppUserService userService;

	@Autowired
	private LoginService loginService;
	@Autowired
	private UserValidator userValidator;
	
	@GetMapping(value = "")
	public String index() {
		return "redirect:/dashboard";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/403")
	public String error403() {
		return "error";
	}

	// user management
	@PostMapping(value = "/user")
	public String createUser(UserDTO user, BindingResult bindingResult, Model model) {
		try {
			userValidator.validate(user, bindingResult);
			model.addAttribute("user", new UserDTO());
			if (bindingResult.hasErrors()) {
				return "user/new";
			}

			AppUser u = new AppUser();
			u.setPassword(user.getPassword());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setPhoneContact(user.getPhoneContact());
			u.setEmail(user.getEmail());
			u.setPasswordExpireAt(user.getPasswordExpireAt());
			u.setCreatedAt(new java.util.Date());
			u.setActive(user.isActive());
			u.setLocked(user.isLocked());
			u.setRole(user.getRole());
			userService.create(u, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/new";
	}

	@GetMapping(value = "/user")
	public String registration(Model model) {
		model.addAttribute("user", new UserDTO());
		return "user/new";
	}

	@GetMapping(value = "/user/list")
	public String getAllUser(Model model) {
		List<AppUser> users = userService.getAll();
		model.addAttribute("users", users);
		return "user/list";
	}

	@GetMapping(value = "/{id}")
	public String getOneUser(@PathVariable("id") Integer id, Model model) {
		AppUser e = userService.getOne(id);
		if (e == null) {
			e = new AppUser();
		}
		model.addAttribute("user", e);
		return "user/edit";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {
		userService.delete(id);
		return "redirect:/user";
	}

}
