package model;

public class Station {

	private String name;
	private String adress;
	private String[] buses;

	public Station(String n, String a, String[] b) {
		name = n;
		adress = a;
		buses = b;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}



	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}



	/**
	 * @return the buses
	 */
	public String[] getBuses() {
		return buses;
	}



	/**
	 * @param buses the buses to set
	 */
	public void setBuses(String[] buses) {
		this.buses = buses;
	}



	@Override
	public String toString() {
		return name;
	}
}