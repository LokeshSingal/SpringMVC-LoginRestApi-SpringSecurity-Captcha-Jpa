package org.loginmodule.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.loginmodule.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends CrudRepository<User> {

	@Override
	public Class<User> getClassName() {
		return User.class;
	}

	public User getUserByUserName(String email) {
		 List<User> users = new ArrayList<>();
	        CriteriaBuilder cb = getCriteriaBuilder();
	        CriteriaQuery<User> emailFilterQuery = cb.createQuery(User.class);
	        Root<User> root = emailFilterQuery.from(User.class);
	        Predicate emailPredicate = cb.equal(root.get("email"), email);
	        emailFilterQuery.where(emailPredicate);
	        users = executeQuery(emailFilterQuery);
	        if (users.size() > 0) {
	            return users.get(0);
	        } else {
	            return null;
	        }
		
	}

}
