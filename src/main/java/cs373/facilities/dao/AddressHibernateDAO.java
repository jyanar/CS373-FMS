package cs373.facilities.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import cs373.facilities.model.facility.IAddress;
import cs373.facilities.model.facility.Address;

public class AddressHibernateDAO {

	public void addAddress(Address addr) {
		System.out.println("*************** Adding address information in DB with ID ...  " + addr.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(addr);
		session.getTransaction().commit();
	}

	public void deleteAddress(Address addr) {
		System.out.println("*************** Deleting address information in DB with ID ...  " + addr.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(addr);
		session.getTransaction().commit();
	}

	public Address retrieveCustomer(long i) {
		try {
			System.out.println("*************** Searcing for address information with ID ...  " + i);
			Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			//System.out.println("*************** Hibernate session is created ..................\n" + session.toString());

			org.hibernate.Query getCustQuery = session.createQuery("From Address where id=:id");		
			getCustQuery.setLong("id", i);

			System.out.println("*************** Retrieve Query is ....>>\n" + getCustQuery.toString()); 

			List<?> addresses = getCustQuery.list();
			System.out.println("Getting Address Details using HQL. \n" + addresses);

			session.getTransaction().commit();
			return (Address)addresses.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
