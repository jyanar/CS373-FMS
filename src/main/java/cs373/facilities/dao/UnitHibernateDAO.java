package cs373.facilities.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cs373.facilities.model.facility.Unit;
import cs373.facilities.model.facility.IUnit;

public class UnitHibernateDAO {
	
	// Create
	public void insertUnit(IUnit unit){
		System.out.println("*************** Adding Unit to DB with Id ...  " + unit.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(unit);
		session.getTransaction().commit();
	}
	
	// Delete
	public void deleteUnit(IUnit unit) {
		System.out.println("*************** Deleteing unit from DB with Id..." + unit.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(unit);
		session.getTransaction().commit();
	}

	public Unit getUnitById(int id) {
		System.out.println("*************** Retrieving unit with Id..." + id);
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query getUnitQuery = session.createQuery("From Unit id=:id");
		getUnitQuery.setLong("id", id);

		List<?> unites = getUnitQuery.list();

		session.getTransaction().commit();
		return (Unit) unites.get(0);
	}
	
	// Update
	public void updateUnit(IUnit unit){
		System.out.println("********** Updating unit with Id..." + unit.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(unit);
		session.getTransaction().commit();
	}
	
	// Delete All unites
	public void deleteAllUnits(){
				
		System.out.println("************* Deleting ALL units from DB ");
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllUnits = "DELETE FROM Unit";
		Query deleteQuery = session.createQuery(deleteAllUnits);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
	
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}
