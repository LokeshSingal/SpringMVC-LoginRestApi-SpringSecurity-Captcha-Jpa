package org.loginmodule.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.loginmodule.dao.UserDao;
import org.loginmodule.model.User;
import org.loginmodule.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	UserDao userDao;

	public User createUser(UserVO user) {
		User userObj = user.convertToUser();
		encodePassword(userObj);
		return userDao.create(userObj);
	}

	public User updateUser(UserVO user) {
		User userObj = user.convertToUser();
		encodePassword(userObj);
		return userDao.update(userObj);
	}

	private void encodePassword(User user) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
	}

	public User getUser(Integer id) {
		return userDao.read(id);
	}

	public void deleteUser(Integer id) {
		userDao.delete(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username
					+ " is invalid, Please try again.");
		}
		GrantedAuthority auth = new SimpleGrantedAuthority(user.getRole().getRole());
		return buildUserForAuthentication(user, Arrays.asList(auth));
	}

	private org.springframework.security.core.userdetails.User buildUserForAuthentication(
			User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), true, true, true, true,
				authorities);
	}

	@Override
	public List<User> listUsers() {
		return userDao.list();
	}

}
