package model.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.business.Tag;
import model.util.Hibernate;

public class TagHibernateDAO implements ITagDAO{

	@Override
	public void saveTag(Tag tag_) {
		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();		
			session.save(tag_);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}				
	}

	@Override
	public Tag getOneTag(String query) {
		Session session = Hibernate.getSessionFactory().openSession();
		Tag tag_ = (Tag) session.createQuery(query).uniqueResult();
		return tag_;
	}

}
