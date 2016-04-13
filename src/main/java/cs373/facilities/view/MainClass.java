package cs373.facilities.view;

import cs373.facilities.dao.HibernatePGSQLHelper;
import cs373.facilities.model.facility.IAddress;
import cs373.facilities.model.facility.IFacility;
import cs373.facilities.model.facility.IUnit;
import cs373.facilities.model.scheduling.IEvent;
import cs373.facilities.model.scheduling.ISchedule;
import cs373.facilities.model.maintenance.InspectionRequest;
import cs373.facilities.model.maintenance.IInspectionRequest;

import java.util.List;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/META-INF/spring/app-context.xml");
        System.out.println("****************** Application Context instantiated! ******************");

        // Demonstration of Spring's (setter) dependency injection...

        IFacility argonneLab = (IFacility) context.getBean("ifacility");
        argonneLab.setName("Argonne Lab");
        argonneLab.setManager("Peter Littlewood");

        IAddress argonneAddress = argonneLab.getAddress();
        argonneAddress.setStreet("123 Sheridan Avenue");
        argonneAddress.setState("IL");
        argonneAddress.setZip("60660");
        argonneAddress.setCity("Chicago");

        IUnit particleAccelerator = argonneLab.getUnits().get(0);
        particleAccelerator.setCapacity(300);

        // Add some events to Unit 1 of the facility:

        IEvent event1 = (IEvent) context.getBean("ievent");
        event1.setDescription(padRight("Accelerate protons", 25));
        event1.setStart(argonneLab.getBeginningOfTime());
        event1.setStop(argonneLab.getBeginningOfTime().plusDays(2));

        IEvent event2 = (IEvent) context.getBean("ievent");
        event2.setDescription(padRight("National Computing Conference", 25));
        event2.setStart(event1.getStop().plusDays(7));
        event2.setStop(event2.getStart().plusDays(4));

        IEvent event3 = (IEvent) context.getBean("ievent");
        event3.setDescription(padRight("Fire up SLAC", 25));
        event3.setStart(event2.getStop().plusDays(4));
        event3.setStop(event3.getStart().plusDays(2));

        IEvent event4 = (IEvent) context.getBean("ievent");
        event4.setDescription(padRight("Run weather simulations", 25));
        event4.setStart(event3.getStop().plusDays(3));
        event4.setStop(event4.getStart().plusDays(1));

        ISchedule particleAccSchedule = particleAccelerator.getSchedule();
        particleAccSchedule.addEvent(event1);
        particleAccSchedule.addEvent(event2);
        particleAccSchedule.addEvent(event3);
        particleAccSchedule.addEvent(event4);

        // Add some inspection requests:

        particleAccelerator.addInspectionRequest(new InspectionRequest(padRight("Can't get hot water", 27), "John Smith"));
        particleAccelerator.addInspectionRequest(new InspectionRequest(padRight("Magnets in need of replacement", 27), "Jack Smarts"));
        particleAccelerator.addInspectionRequest(new InspectionRequest(padRight("Helium tanks have ruptured!", 27), "Jacqueline Doe"));

        System.out.println("\n" + particleAccelerator.getScheduleString());

        particleAccelerator.scheduleMaintenance();

        System.out.println("\nCurrent inspection requests: ");
        System.out.println(particleAccelerator.getInspectionRequestsString());

        System.out.println("Scheduling maintenance...\n\n" + particleAccelerator.getScheduleString());

        // Now let's test Hibernate:

        System.out.println("*************** INSERT EXAMPLE ****************************");
        
        System.out.println("Adding Facility with the ID..." + argonneLab.getId());

        Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // session.save(argonneLab.getAddress());
        // session.save(event1);
        // session.save(particleAccSchedule);
        session.save(argonneLab);
        session.getTransaction().commit();

        System.out.println("*************** Facility Inserted *************************");

    }

    public static String padRight(String s, int n) {
        while (s.length() < n)
        s += " ";
        return s;
    }
}
