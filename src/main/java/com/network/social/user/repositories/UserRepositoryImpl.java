package com.network.social.user.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.network.social.user.models.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private SessionFactory factory;
    
	@Override
	public void register(User user) {
		Session session = factory.getCurrentSession();
		session.save(user);
	}
	
}
