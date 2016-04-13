package cs373.facilities.model.facility;

import cs373.facilities.model.maintenance.IInspectionRequest;
import cs373.facilities.model.scheduling.IEvent;
import cs373.facilities.model.scheduling.Event;
import cs373.facilities.model.scheduling.ISchedule;

import java.util.Random;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Unit implements IUnit {

    private int id;
    private int capacity;

    private ISchedule schedule;
    private ISchedule maintenanceSchedule;
    private List<IInspectionRequest> inspectionRequests;
    private List<IInspectionRequest> pastInspectionsLog;

    private static int counter = 0;

    public Unit() {
        this.id = counter;
        counter++;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setSchedule(ISchedule schedule) {
        this.schedule = schedule;
    }
    public ISchedule getSchedule() {
        return schedule;
    }
    public String getScheduleString() {
        String output = "";
        for (IEvent e : schedule.getEventList())
        	output += e.getFullDescription() + "\n";
        return output;
    }
    public ISchedule getMaintenanceSchedule() {
        return maintenanceSchedule;
    }
    public void setMaintenanceSchedule(ISchedule maintSchedule) {
        this.maintenanceSchedule = maintSchedule;
    }

    public void scheduleUse(IEvent e) {
    	if (schedule.checkVacancyDuringInterval(e.getStart(), e.getStop())) {
    		// System.out.println("Unit " + unitID + " | Adding:\n" + e.getFullDescription());
            schedule.addEvent(e);
    	} else {
    		System.out.println("Could not add event: " + e.getDescription() + " to Unit " + id + ".");
    	}
    }

    public boolean getUnitVacancy(LocalDateTime start, LocalDateTime stop) {
        return schedule.checkVacancyDuringInterval(start, stop);
    }

    public String getMaintScheduleString() {
        String output = "";
        for (IEvent e : maintenanceSchedule.getEventList())
            output += e.getFullDescription();
        return output;
    }

    public void addInspectionRequest(IInspectionRequest request) {
        inspectionRequests.add(request);
        pastInspectionsLog.add(request);
    }
    public void setInspectionRequests(List<IInspectionRequest> requests) {
        this.inspectionRequests = requests;
    }
    public List<IInspectionRequest> getInspectionRequests() {
        return inspectionRequests;
    }
    public String getInspectionRequestsString() {
        String output = "";
        for (IInspectionRequest r : inspectionRequests)
            output += r.getFullDescription() + "\n";
        return output;
    }

    public String getPastInspectionsLogString() {
        String output = "";
        for (IInspectionRequest r : pastInspectionsLog)
            output += r.getFullDescription() + "\n";
        return output;
    }
    public List<IInspectionRequest> getPastInspectionsLog() {
        return pastInspectionsLog;
    }
    public void setPastInspectionsLog(List<IInspectionRequest> requests) {
        this.pastInspectionsLog = requests;
    }

    public static Random r = new Random();

    /**
     * Runs through current inspection requests and schedules each one for
     * maintenance, randomly determining the cost and duration of the event,
     * and scheduling the event such that it does not conflict with other
     * events.
     */
    public void scheduleMaintenance() {
        System.out.println(inspectionRequests.size());
        for (IInspectionRequest request : inspectionRequests) {
            int duration = r.nextInt(5) + 1;
            double cost  = r.nextInt(10000) + 500;
            String technician = "Billy Bob";
            LocalDateTime startMaint = schedule.findVacantInterval(duration, schedule.getBeginningOfTime());

            schedule.addEvent(
                new Event(request.getFullDescription(),
                          startMaint,
                          startMaint.plusHours(duration),
                          true,
                          technician,
                          cost
                )
            );
        }
    }
}
