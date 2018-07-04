package org.loginmodule.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.loginmodule.model.User;
import org.loginmodule.model.UserVO;
import org.loginmodule.service.ICaptchaService;
import org.loginmodule.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
@ResponseBody
public class UserController {

	@Autowired
	IUserService userService;

	@Autowired
	ICaptchaService captchaService;

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("SignUp", "user", new UserVO());
	}

	@RequestMapping(value = "/captchaSignUp", method = RequestMethod.POST, produces = "application/json")
	public User signUpUsingCaptcha(@ModelAttribute UserVO user,
			HttpServletRequest req) {
		String response = req.getParameter("g-recaptcha-response");
		String ip = req.getRemoteAddr();
		captchaService.processResponse(response, ip);
		// TODO - Need to fix UI, as of now default user is admin
		user.setRoleId(1);
		return userService.createUser(user);

	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST, produces = "application/json")
	public User signUp(@RequestBody UserVO user, HttpServletRequest req) {
		return userService.createUser(user);

	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "application/json")
	public User updateUser(@PathVariable("userId") Integer userId,
			@RequestBody UserVO user) {
		user.setUserId(userId);
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
	
	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUserList() {
        return userService.listUsers();
    }


}
