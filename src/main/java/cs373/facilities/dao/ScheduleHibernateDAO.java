package cs373.facilities.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cs373.facilities.model.scheduling.Schedule;
import cs373.facilities.model.scheduling.ISchedule;

public class ScheduleHibernateDAO {
	
	// Create
	public void insertSchedule(ISchedule schedule){
		System.out.println("*************** Adding Schedule to DB with Id ...  " + schedule.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(schedule);
		session.getTransaction().commit();
	}
	
	// Delete
	public void deleteSchedule(ISchedule schedule) {
		System.out.println("*************** Deleteing schedule from DB with Id..." + schedule.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(schedule);
		session.getTransaction().commit();
	}

	public Schedule getScheduleById(int id) {
		System.out.println("*************** Retrieving schedule with Id..." + id);
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query getScheduleQuery = session.createQuery("From Schedule id=:id");
		getScheduleQuery.setLong("id", id);

		List<?> schedules = getScheduleQuery.list();

		session.getTransaction().commit();
		return (Schedule) schedules.get(0);
	}
		
	// Update
	public void updateSchedule(ISchedule schedule){
		System.out.println("********** Updating schedule with Id..." + schedule.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(schedule);
		session.getTransaction().commit();
	}
	
	// Delete All schedules
	public void deleteAllSchedules(){
				
		System.out.println("************* Deleting ALL schedules from DB ");
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllUnits = "DELETE FROM Schedule";
		Query deleteQuery = session.createQuery(deleteAllUnits);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
	
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}
