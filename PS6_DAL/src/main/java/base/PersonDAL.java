package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import domain.StudentDomainModel;
import util.HibernateUtil;

public class PersonDAL {

	public static PersonDomainModel addPerson(PersonDomainModel per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		int eID = 0;
		try {
			trans = session.beginTransaction();
			session.save(per);
			trans.commit();
		} catch (HibernateException e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return per;
	}
	

	public static ArrayList<PersonDomainModel> getPersons() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		StudentDomainModel studentmodel = null;
		ArrayList<PersonDomainModel> person = new ArrayList<PersonDomainModel>();

		try {
			trans = session.beginTransaction();

			List students = session.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = students.iterator(); iterator.hasNext();) {
				PersonDomainModel per = (PersonDomainModel) iterator.next();
				person.add(per);

			}

			trans.commit();
		} catch (HibernateException e) {
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return person;

	}

	public static PersonDomainModel getPerson(UUID perID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		PersonDomainModel person = null;

		try {
			trans = session.beginTransaction();

			Query query = session.createQuery("from PersonDomainModel where PersonID = :id ");
			query.setParameter("id", perID);

			person = (PersonDomainModel) query.list().get(0);

			trans.commit();

		} catch (IndexOutOfBoundsException ex) {
			person = null;
		} catch (HibernateException e) {
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return person;
	}

	public static void deletePerson(UUID perID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		PersonDomainModel person = null;

		try {
			trans = session.beginTransaction();

			PersonDomainModel per = (PersonDomainModel) session.get(PersonDomainModel.class, perID);
			if (per==null)
			{
				// Nothing to delete, not found
				return;
			}
			
			session.delete(per);

			trans.commit();
		} catch (HibernateException e) {
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static PersonDomainModel updatePerson(PersonDomainModel per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		PersonDomainModel person = null;

		try {
			trans = session.beginTransaction();

			session.update(per);

			trans.commit();
		} catch (HibernateException e) {
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return per;
	}
}