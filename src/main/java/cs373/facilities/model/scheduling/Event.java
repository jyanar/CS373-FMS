package cs373.facilities.model.scheduling;

import java.time.LocalDateTime;

public class Event implements IEvent {

    private int id;
    private String description;
    private LocalDateTime start;
    private LocalDateTime stop;
    private boolean isMaintenance;
    private String technician;
    private double cost;

    private static int counter = 0;

    public Event() {
        this.id = counter;
        counter++;
    }

    // Constructor for regular events
    public Event(String description,
                 LocalDateTime start,
                 LocalDateTime stop) {
        this.id = counter;
        this.description = description;
        this.start = start;
        this.stop = stop;
        this.isMaintenance = false;
        this.technician = "N/A";
        this.cost = 0;
        counter++;
    }

    // Constructor for Maintenance events
    public Event(String description,
                 LocalDateTime start, LocalDateTime stop,
                 Boolean isMaintenance, String technician,
                 double cost) {
        this.id = counter;
        this.description = description;
        this.start = start;
        this.stop = stop;
        this.isMaintenance = true;
        this.technician = technician;
        this.cost = cost;
        counter++;
    }

    public int getId() { 
        return id;
    }
    public void setId(int id) {
        this.id = id; 
    }

    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getStop() { 
        return stop;
    }
    public void setStop(LocalDateTime stop) {
        this.stop = stop;
    }

    public Boolean getIsMaintenance() {
        return isMaintenance;
    }
    public void setIsMaintenance(Boolean set) {
        this.isMaintenance = set;
    }

    public String getTechnician() {
        return technician;
    }
    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getFullDescription() {
        return "Event: \t" + getDescription() + "\t\t" + getStart() +
               " - " + getStop() + " Cost: $" + getCost();
    }
}
