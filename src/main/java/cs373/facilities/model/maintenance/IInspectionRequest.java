package cs373.facilities.model.maintenance;

public interface IInspectionRequest {

    public void setId(int id);
    public int getId();
    public void setDescription(String description);
    public void setTechnician(String technician);
    public String getTechnician();
    public String getFullDescription();

}
