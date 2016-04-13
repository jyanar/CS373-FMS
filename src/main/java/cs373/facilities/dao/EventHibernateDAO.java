package cs373.facilities.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cs373.facilities.model.scheduling.Event;
import cs373.facilities.model.scheduling.IEvent;

public class EventHibernateDAO {
	
	// Create
	public void insertEvent(IEvent event){
		System.out.println("*************** Adding Event to DB with Id ...  " + event.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(event);
		session.getTransaction().commit();
	}
	
	// Delete
	public void deleteEvent(IEvent event) {
		System.out.println("*************** Deleteing event from DB with Id..." + event.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(event);
		session.getTransaction().commit();
	}

	public Event getEventById(int id) {
		System.out.println("*************** Retrieving event with Id..." + id);
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query getEventQuery = session.createQuery("From Event id=:id");
		getEventQuery.setLong("id", id);

		List<?> events = getEventQuery.list();

		session.getTransaction().commit();
		return (Event) events.get(0);
	}
	
	// Update
	public void updateEvent(IEvent event){
		System.out.println("********** Updating event with Id..." + event.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(event);
		session.getTransaction().commit();
	}
	
	// Delete All events
	public void deleteAllEvents(){
				
		System.out.println("************* Deleting ALL events from DB ");
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllUnits = "DELETE FROM Event";
		Query deleteQuery = session.createQuery(deleteAllUnits);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
	
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}
