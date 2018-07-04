package org.loginmodule.service;

import org.loginmodule.model.User;

public interface IUserService {

	
	public User createUser(User user);
	
	public User updateUser(User user);
	
	public User getUser(Integer id);
	
	public void deleteUser(Integer id);
		
}
