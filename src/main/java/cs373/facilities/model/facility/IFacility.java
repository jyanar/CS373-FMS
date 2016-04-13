package cs373.facilities.model.facility;

import cs373.facilities.model.facility.IAddress;
import cs373.facilities.model.facility.IUnit;

import java.util.List;
import java.time.LocalDateTime;

public interface IFacility {

    public int getId();
    public void setId(int id);

    public String getName();
    public void setName(String name);

    public String getAddressString();
    public IAddress getAddress();
    public void setAddress(IAddress address);

    public String getManager();
    public void setManager(String manager);

    public LocalDateTime getBeginningOfTime();

    public void addUnit(IUnit unit);
    public String listUnits();
    public List<IUnit> getUnits();
    public void setUnits(List<IUnit> units);

    public String getSchedules();

    public void scheduleMaintenance();
    public String listAvailableUnits(LocalDateTime start, LocalDateTime stop);

    public double calcMaintCostForFacility();

    public int calcProblemRateForFacility();

    public String listInspectionRequests();

    public String listScheduledMaintenance();

}
