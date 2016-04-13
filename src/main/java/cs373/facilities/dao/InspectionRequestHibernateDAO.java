package cs373.facilities.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cs373.facilities.model.maintenance.InspectionRequest;
import cs373.facilities.model.maintenance.IInspectionRequest;

public class InspectionRequestHibernateDAO {
	
	// Create
	public void insertInspectionRequest(IInspectionRequest inspectionRequest){
		System.out.println("*************** Adding InspectionRequest to DB with Id ...  " + inspectionRequest.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(inspectionRequest);
		session.getTransaction().commit();
	}
	
	// Delete
	public void deleteInspectionRequest(IInspectionRequest inspectionRequest) {
		System.out.println("*************** Deleteing inspectionRequest from DB with Id..." + inspectionRequest.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(inspectionRequest);
		session.getTransaction().commit();
	}

	public InspectionRequest getInspectionRequestById(int id) {
		System.out.println("*************** Retrieving inspectionRequest with Id..." + id);
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query getInspectionRequestQuery = session.createQuery("From InspectionRequest id=:id");
		getInspectionRequestQuery.setLong("id", id);

		List<?> inspectionRequests = getInspectionRequestQuery.list();

		session.getTransaction().commit();
		return (InspectionRequest) inspectionRequests.get(0);
	}
	
	// Update
	public void updateInspectionRequest(IInspectionRequest inspectionRequest){
		System.out.println("********** Updating inspectionRequest with Id..." + inspectionRequest.getId());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(inspectionRequest);
		session.getTransaction().commit();
	}
	
	// Delete All inspectionRequests
	public void deleteAllInspectionRequests(){
				
		System.out.println("************* Deleting ALL inspectionRequests from DB ");
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllUnits = "DELETE FROM InspectionRequest";
		Query deleteQuery = session.createQuery(deleteAllUnits);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
	
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}
