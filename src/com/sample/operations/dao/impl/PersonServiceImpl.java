package com.sample.operations.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;

import com.sample.operations.Client;
import com.sample.operations.dao.PersonServiceI;
import com.sample.operations.entities.PersonEntity;
import com.sample.operations.entities.ReporteeEntity;
import com.sample.operations.exceptions.CustomException;
import com.sample.operations.utils.HibernateUtils;

public class PersonServiceImpl implements PersonServiceI {

	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;

	@Override
	public void createPerson(PersonEntity person) throws CustomException {
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			List<ReporteeEntity> reporteeList = Client.addReportees();
			for (ReporteeEntity reportee : reporteeList) {
				person.getReporteeList().add(reportee);
				session.save(reportee);
			}

			session.save(person);
			// session.persist(person);
			transaction.commit();

			session.refresh(person);
		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
		} finally {
			destroy();
		}
	}

	public PersonEntity fetchPerson(int personId) throws CustomException {
		PersonEntity person = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			// person = (PersonEntity) session.load(PersonEntity.class, personId);
			person = (PersonEntity) session.get(PersonEntity.class, personId);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
		} finally {
			destroy();
		}

		return person;
	}

	public PersonEntity updatePerson(int personId) throws CustomException {
		PersonEntity person = null;

		Savepoint savepoint = null;

		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			person = (PersonEntity) session.get(PersonEntity.class, personId);

			if (person == null) {

				System.out.println("There is no record found with the provided Id, Creating a new record");
				PersonEntity prs = new PersonEntity();
				prs.setPersonName("Mahesh Babu");
				prs.setEmailAddress("mahesh.babu@xyz.com");
				prs.setDateOfBirth(getMyBirthday());

				session.saveOrUpdate(prs);
			} else {

				person.setPersonName("Mahesh krishna");
				session.update(person);
				session.flush();
				savepoint = setSavepoint("savepoint_1");
				// session.merge(person);

			}

			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			System.out.println("Exception occured rolling back to save point");
			rollbackSavepoint(savepoint);
		} finally {
			destroy();
		}

		return person;
	}

	public void deletePerson(int personId) throws CustomException {
		PersonEntity person = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			person = (PersonEntity) session.get(PersonEntity.class, personId);
			session.delete(person);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
		} finally {
			destroy();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void customQuery() throws CustomException {

		int PersonId = 1;
		String tmp = "";

		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			// Query<PersonEntity> query = session.createQuery("from
			// com.sample.operations.entities.PersonEntity where personId = ?");

			// Query query = session.getNamedQuery("PersonDetails.byID");// [Old depreciated
			// way]
			// query.setInteger(1, 1);

			Criteria personquery = session.createCriteria(PersonEntity.class);
			personquery.add(Restrictions.eq("PersonId", PersonId));

			List<PersonEntity> personRS = personquery.list();

			transaction.commit();

			for (PersonEntity person : personRS) {
				for (int num : person.getContactNumbers()) {
					tmp = tmp + ", " + String.valueOf(num);
				}

				System.out.println("*************** PERSON DETAILS ***************");
				System.out.println("Person ID    	    :: " + person.getPersonId());
				System.out.println("Person Name  	    :: " + person.getPersonName());
				System.out.println("Person Email  	    :: " + person.getEmailAddress());
				System.out.println("Person Address	    :: " + person.getAddress().getStreet() + ", " + ""
						+ person.getAddress().getCity() + ", " + person.getAddress().getCity() + ",  "
						+ person.getAddress().getCountry());

				System.out.println("Person ContactInfo 	:: " + tmp);
				System.out.println("Person Date of Birth :: " + person.getDateOfBirth());

				System.out.println("Person Organisation :: " + person.getCompany().getCompanyName());
				System.out.println("Person Job Title :: " + person.getCompany().getJobTitle());
				System.out.println("Person Work Location :: " + person.getCompany().getLocation());

				System.out.print("Person Reportees  :: ");
				for (ReporteeEntity reportees : person.getReporteeList()) {
					System.out.print(" " + reportees.getRepoteeName());

				}
				System.out.println("");
				System.out.println("**********************************************");

			}

		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
		} finally {
			destroy();
		}
	}

	public void destroy() {
		if (session.isOpen()) {
			System.out.println("Closing session");
			session.close();
		}
		if (!sessionFactory.isClosed()) {
			System.out.println("Closing SessionFactory");
			sessionFactory.close();
		}
	}

	private Savepoint savepoint;

	public Savepoint setSavepoint(final String savePoint) {
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				savepoint = connection.setSavepoint(savePoint);
			}
		});
		return savepoint;
	}

	public void rollbackSavepoint(final Savepoint savepoint) {
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				connection.rollback(savepoint);
			}
		});
	}

	@SuppressWarnings("static-access")
	private static Date getMyBirthday() {
		Calendar c = Calendar.getInstance();
		c.set(c.YEAR, 1984);
		c.set(c.MONTH, 6);
		c.set(c.DATE, 23);
		return c.getTime();
	}
}
