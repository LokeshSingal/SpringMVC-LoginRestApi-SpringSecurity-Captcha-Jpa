package org.loginmodule.controller;

import org.loginmodule.model.User;
import org.loginmodule.service.IUserService;
import org.loginmodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@ResponseBody
public class UserController {

	@Autowired
	IUserService userService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public User createUser(@RequestBody User userReq) {
		return userService.createUser(userReq);
		
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "application/json")
	public User updateUser(@PathVariable("userId") Integer userId,
			@RequestBody User user) {
		user.setId(userId);
		return userService.updateUser(user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
	public User getUser(@PathVariable("userId") Integer userId) {
		return userService.getUser(userId);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = "application/json")
	public void deleteUser(@PathVariable("userId") Integer userId) {
		userService.deleteUser(userId);
	}

}
