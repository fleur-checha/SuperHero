package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import metier.entities.Declaration;
import metier.entities.SuperHero;
//Class d'implemention de notre interface de base avec les 3 méthodes
public class HeroDaoImpl implements IHeroDao {
    //Enregistrement d'un super hero
	@Override
	public SuperHero saveSuperHero(SuperHero superHero) {
		//recupere de la connection
		Connection conn = SingletonConnection.getConnection();
		try {
			//Insertion des infos du super hero dans la table SUPERHEROS
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO SUPERHEROS(NOM,LATITUDE, LONGITUDE, PHONE, INCIDENTS) VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, superHero.getNom());
			ps.setDouble(2, superHero.getLatitude());
			ps.setDouble(3, superHero.getLongitude());
			ps.setString(4, superHero.getPhone());

			//Conversion de la liste d'incidents en un string
			//On recupere l'ensemble des elements de la liste pour former un string à enregistrer dans la colonne INCIDENTS 
			//Ce n'est pas possible de stocker une liste directement
			String incidents = "";
			for (String incident : superHero.getIncidents()) {
				incidents += incident.toString() + " ";
			}
          //Le string formé contient l'ensemble des elements de la liste d'incidents separés par des espaces
			ps.setString(5, incidents.trim());
			ps.executeUpdate();
			
			//On recupere ID du super hero ajouter
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID) as MAX_ID FROM SUPERHEROS");
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				superHero.setId(rs.getLong("MAX_ID"));
			}
			//Fermeture de la connection
			ps.close();
			ps2.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	//On retourne le super hero ajouter
		return superHero;
	}

	//Ajout d'une déclaration
	@Override
	public List<SuperHero> saveDeclaration(Declaration declaration) {
		Connection conn = SingletonConnection.getConnection();
		try {
			// Sauvegarde de la Declaration
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO DECLARATION(VILLE, LATITUDE, LONGITUDE, TYPE_INCIDENT) VALUES(?, ?, ?, ?)");
			ps.setString(1, declaration.getVille());
			ps.setDouble(2, declaration.getLatitude());
			ps.setDouble(3, declaration.getLongitude());
			ps.setString(4, declaration.getTypeIncident());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Recherher et retourner la liste des super heros capable de resoudre le type d'incendie et se trouvant dans un perimetre de 50km 
		return searchSuperHeros(declaration.getLatitude(), declaration.getLongitude(), declaration.getTypeIncident());
	}

	
	// Recherher la liste des super heros capable de resoudre le type d'incendie et se trouvant dans un perimetre de 50km 

	@Override
	public List<SuperHero> searchSuperHeros(double latitude, double longitude, String typeIncident) {
		List<SuperHero> superHeros = new ArrayList<SuperHero>();
		Connection conn = SingletonConnection.getConnection();
		try {
			// Recherche des super heros capable de resoudre l'incendie
			PreparedStatement ps = conn.prepareStatement("select * from SUPERHEROS where INCIDENTS LIKE ?");
			ps.setString(1, "%" + typeIncident + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SuperHero sh = new SuperHero();
				sh.setId(rs.getLong("ID"));
				sh.setNom(rs.getString("NOM"));
				sh.setPhone(rs.getString("PHONE"));
				sh.setLatitude(rs.getDouble("LATITUDE"));
				sh.setLongitude(rs.getDouble("LONGITUDE"));
				//Reconstituer la liste des incendies à partir de l'string 
				sh.setIncidents(Arrays.asList(rs.getString("INCIDENTS").split(" ")));
				//Chercher parmis ces super heros, ceux se trouvant dans un perimetre de 50km
				if(distanceInKM(latitude, sh.getLatitude(), longitude, sh.getLongitude()) <= 50) {
					superHeros.add(sh);
				}
			}

			ps.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return superHeros;

	}

	
//calcul de la distance en km
public double distanceInKM(double lat1, double lat2, double lon1, double lon2) {

// The math module contains a function
// named toRadians which converts from
// degrees to radians.
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

// Haversine formula
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

// Radius of earth in kilometers. 
		double r = 6371;
		
// calculate the result
		return (c * r);
	}

}
