package metier.entities;

import java.io.Serializable;
import java.util.List;

//Contient les infos des supers heros
public class SuperHero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String nom;
	private double latitude;
	private double longitude;
	private String phone;
	//La liste des incidents
	private List<String> incidents;

	public SuperHero() {
		super();
	}
	public SuperHero(String nom, List<String> incidents, double latitude, double longitude, String phone) {
		super();
		this.nom = nom;
		this.incidents = incidents;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phone = phone;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<String> getIncidents() {
		return incidents;
	}
	public void setIncidents(List<String> incidents) {
		this.incidents = incidents;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "SuperHero [id=" + id + ", nom=" + nom + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", phone=" + phone + ", incidents=" + incidents + "]";
	}
	
	
	
	

}
