package cs373.facilities.model.facility;

import cs373.facilities.model.maintenance.IInspectionRequest;
import cs373.facilities.model.scheduling.IEvent;
import cs373.facilities.model.scheduling.ISchedule;

import java.util.List;
import java.time.LocalDateTime;

public interface IUnit {

    public int getId();
    public void setId(int id);

    public int getCapacity();
    public void setCapacity(int capacity);

    public ISchedule getSchedule();
    public void setSchedule(ISchedule schedule);
    public String getScheduleString();

    public void scheduleUse(IEvent e);

    public boolean getUnitVacancy(LocalDateTime start, LocalDateTime stop);

    public String getMaintScheduleString();
    public ISchedule getMaintenanceSchedule();
    public void setMaintenanceSchedule(ISchedule maintSchedule);

    public String getInspectionRequestsString();
    public List<IInspectionRequest> getInspectionRequests();
    public void setInspectionRequests(List<IInspectionRequest> requests);
    public void addInspectionRequest(IInspectionRequest request);

    public String getPastInspectionsLogString();
    public List<IInspectionRequest> getPastInspectionsLog();
    public void setPastInspectionsLog(List<IInspectionRequest> requests);

    public void scheduleMaintenance();

}
