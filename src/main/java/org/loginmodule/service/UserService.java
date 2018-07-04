package org.loginmodule.service;

import javax.transaction.Transactional;

import org.loginmodule.dao.UserDao;
import org.loginmodule.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements IUserService , UserDetailsService{
	
	@Autowired
	UserDao userDao;
	
	
	public User createUser(User user){
		encodePassword(user);
		return userDao.create(user);
	}
	
	public User updateUser(User user){
		encodePassword(user);
		return userDao.update(user);
	}

	private void encodePassword(User user) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
	}
	
	public User getUser(Integer id){
		return userDao.read(id);
	}
	
	public void deleteUser(Integer id){
		userDao.delete(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.getUserByUserName(username);
		if(user == null){
			throw new UsernameNotFoundException(username + " is invalid, Please try again.");
		}
		return new org.springframework.security.core.userdetails.User(username, user.getEmail(), null);
	}


}
