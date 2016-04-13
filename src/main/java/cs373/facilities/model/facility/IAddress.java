package cs373.facilities.model.facility;

public interface IAddress {

    public int getId();
    public void setId(int id);

    public String getStreet();
	public void setStreet(String street);

    public String getCity();
    public void setCity(String city);

    public String getState();
    public void setState(String state);

    public String getZip();
    public void setZip(String zip);

    public String getFullAddress();

}
