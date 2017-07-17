import java.util.List;

public class weather {

	public String location;
	public String country;
	public List<time> listTime;
	
	public String getLocation(){
		return location;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	public String getCountry(){
		return country;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public List<time> getListTime(){
		return listTime;
	}
	
	public void setListTime(){
		this.listTime = listTime;
	}
}
