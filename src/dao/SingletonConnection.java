package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Class de connection à la base de données mysql
public class SingletonConnection {
	private static Connection connection;
	
	static {
		try {
			//Chargement du driver jdbc de mysql
			Class.forName("com.mysql.jdbc.Driver");
			//Connexion à la base de données 'DB_SUPER_HERO' avec l'utilisateur 'root' sans mot de passe 
			connection = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/DB_SUPER_HERO","root","");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
     //Méthode de recuperation de la connection
	public static Connection getConnection() {
		return connection;
	}
	
	

}
