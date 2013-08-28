package model.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.business.Value;
import model.util.Hibernate;

public class ValueHibernateDAO implements IValueDAO {

	@Override
	public void saveValue(Value value_) {
		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();		
			session.save(value_);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}			
	}

}
