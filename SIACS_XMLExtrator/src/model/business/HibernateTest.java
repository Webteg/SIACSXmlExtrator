package model.business;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.util.Hibernate;

public class HibernateTest {
	public static void main(String[] args){
		HibernateTest main = new HibernateTest();
		//main.insertTag();
		//main.insertValue();
		//main.getOneTag();
	}

	public void insertTag(){
		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Tag tag = new Tag();
			tag.setRoot_tag("NOME_EVENTO");
			tag.setChildren_tag("DADOS-BASICOS-DE-OUTRAS-PARTICIPACOES-EM-EVENTOS-CONGRESSOS");

			Tag tag2 = new Tag();
			tag2.setRoot_tag("NOME_EVENTO");
			tag2.setChildren_tag("TESTE");
			
			session.save(tag);
			session.save(tag2);
			transaction.commit();
			System.out.println("Records inserted sucessessfully");     
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		

	}

	public void insertValue(){
		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null;

		try{
			transaction = session.beginTransaction();

			Value value = new Value();
			value.setContent("hi");
			Tag tag_ = (Tag) session.createQuery("from Tag where children_tag like '%TESTE%' ").uniqueResult();
			value.setTag(tag_);	

			session.save(value);
			transaction.commit();
			System.out.println("Records inserted sucessessfully");     
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}	
	}

	public void getOneTag() {
		Session session = Hibernate.getSessionFactory().openSession();
		Tag tag_ = (Tag) session.createQuery("from Tag where children_tag like '%TESTE%' ").uniqueResult();
		System.out.println(tag_.getId_tag());
	}

}
