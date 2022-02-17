package metier.entities;

//Contient les informations sur la déclaration d'une ville/commune d'un incident
public class Declaration {
	private long id;
	private String ville;
	private double latitude;
	private double longitude;
	//Type d'incident
	private String typeIncident;

	public Declaration() {
		super();
	}

	public Declaration( String ville, double latitude, double longitude, String typeIncident) {
		super();
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
		this.typeIncident = typeIncident;
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

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTypeIncident() {
		return typeIncident;
	}

	public void setTypeIncident(String typeIncident) {
		this.typeIncident = typeIncident;
	}

	@Override
	public String toString() {
		return "Declaration [id=" + id + ", ville=" + ville + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", typeIncident=" + typeIncident + "]";
	}

	
}
