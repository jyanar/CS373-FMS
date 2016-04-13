package cs373.facilities.model.facility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cs373.facilities.model.facility.IAddress;
import cs373.facilities.model.scheduling.Event;
import cs373.facilities.model.scheduling.IEvent;
import cs373.facilities.model.scheduling.ISchedule;

public class Facility implements IFacility {

    private int id;
    private String name;
    private IAddress address;
    private String manager;
    private List<IUnit> units;
    private LocalDateTime beginningOfTime;

    private static int counter = 0;

    public Facility() {
        this.id = counter;
        this.name  = "";
        this.units = new ArrayList<>();
        this.beginningOfTime = LocalDateTime.now(); 
        counter++;
    }

    public Facility(String name) {
        this.id = counter;
    	this.name = name;
        this.units = new ArrayList<>();
        this.beginningOfTime = LocalDateTime.now();
        counter++;
    }

    public int getId() { 
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddressString() {
        return address.getFullAddress();
    }
    public IAddress getAddress() {
        return address;
    }
    public void setAddress(IAddress address) {
        this.address = address;
    }

    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setBeginningOfTime(LocalDateTime beginningOfTime) {
        this.beginningOfTime = beginningOfTime;
    }

    public LocalDateTime getBeginningOfTime() {
        return beginningOfTime;
    }

    public void addUnit(IUnit unit) {
        this.units.add(unit);
    }
    public String listUnits() {
        String output = "";
        for (IUnit u : units)
            output += "Unit: " + u.getId() + " | Capacity: " + u.getCapacity() + "\n";
        return output;
    }
    public List<IUnit> getUnits() {
        return units;
    }
    public void setUnits(List<IUnit> units) {
        this.units = units;
    }

    public String getSchedules() {
        String output = "";
        for (IUnit u : units)
            output += u.getId() + "\n" + u.getSchedule();
        return output;
    }

    public void scheduleMaintenance() {
        for (IUnit u : units)
            u.scheduleMaintenance();
    }

    public String listAvailableUnits(LocalDateTime start, LocalDateTime stop) {
        System.out.println("The following are available during " + start.toString()
                            + " - " + stop.toString());
        String output = "";
        for (IUnit u : units) {
            if (u.getUnitVacancy(start, stop)) {
                output += "Unit: " + u.getId() + " | Capacity: " + u.getCapacity() + "\n";
            }
        }
        return output;
    }

    public double calcMaintCostForFacility() {
        double cost = 0;
        for (IUnit u : units) {
            ISchedule maintenanceSchedule = u.getMaintenanceSchedule();
            for (IEvent e : maintenanceSchedule.getEventList()) {
                cost += e.getCost();
            }
        }
        return cost;
    }

    public int calcProblemRateForFacility() {
        return 0;
    }

    public String listInspectionRequests() {
        String output = "Inspection Requests\n";
        for (IUnit u : units)
            output += u.getInspectionRequests();
        return output;
    }

    public String listScheduledMaintenance() {
        String output = "Maintenance Events\n";
        for (IUnit u : units) {
            ISchedule maintSchedule = u.getMaintenanceSchedule();
            for (IEvent e : maintSchedule.getEventList()) {
            	if (e.getIsMaintenance())
            		output += e.getFullDescription();
            }
        }
        return output;
    }
}
// public class Facility {
//
//     public int requestAvailableCapacity(LocalDateTime start, LocalDateTime stop) {
//         return capacity;
//     }
//
//     public void assignFacilityToUse(Event event){
//         if (facilitySchedule.checkVacancyDuringInterval(event.getStart(), event.getStop())) {
//             facilitySchedule.addEvent(event);
//         } else {
//             System.out.println("Could not schedule event due to conflict!");
//         }
//     }
//
//     public String listActualUsage() {
//         return facilitySchedule.getEvents();
//     }
//
//     public void makeFacilityMaintRequest(String msg, double cost) {
//         maintRequests.add(new MaintenanceRequest(msg, cost));
//     }
//
//     public int calcProblemRateForFacility() {
//         return maintenanceLog.getNumEvents() + maintRequests.size();
//     }
//
//
//     public Schedule getMaintenanceLog() {
//         return maintenanceLog;
//     }
// }
