package org.loginmodule.service;

import java.util.List;

import org.loginmodule.model.User;
import org.loginmodule.model.UserVO;

public interface IUserService {

	
	public User createUser(UserVO user);
	
	public User updateUser(UserVO user);
	
	public User getUser(Integer id);
	
	public void deleteUser(Integer id);
	
	public List<User> listUsers();
		
}
