package br.com.app.zup.xyinc.generic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GegnericFactory {
	
	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	protected final Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
