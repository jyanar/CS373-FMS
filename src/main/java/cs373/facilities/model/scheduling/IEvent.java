package cs373.facilities.model.scheduling;

import java.time.LocalDateTime;

public interface IEvent {

    public void setId(int id);
    public int getId();

    public void setDescription(String description);
    public String getDescription();

    public void setStart(LocalDateTime start);
    public LocalDateTime getStart();

    public void setStop(LocalDateTime stop);
    public LocalDateTime getStop();

    public void setIsMaintenance(Boolean set);
    public Boolean getIsMaintenance();

    public void setTechnician(String technician);
    public String getTechnician();

    public void setCost(double cost);
    public double getCost();

    public String getFullDescription();

}
