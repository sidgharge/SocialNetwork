package com.network.social.user.repositories;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.network.social.user.models.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

//	@PersistenceUnit
//    private EntityManagerFactory emf;

	@Autowired
	private SessionFactory factory;
    
	@Override
	public void register(User user) {
		Session session = factory.getCurrentSession();
		session.save(user);
	}
	

	//@PostConstruct
//    private void setSessionFactory() {
//        if (emf.unwrap(SessionFactory.class) == null) {
//            throw new NullPointerException("factory is not a hibernate factory");
//        }
//        factory = emf.unwrap(SessionFactory.class);
//    }

}
