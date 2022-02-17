package dao;

import java.util.List;

import metier.entities.Declaration;
import metier.entities.SuperHero;

//Interface de base pour l'accés aux données
public interface IHeroDao {
	
	//Méthode pour l'enregistrement d'un super hero
	public SuperHero saveSuperHero(SuperHero superHero);
    //Méthode pour la soumission d'une déclaration d'incendie
	public List<SuperHero> saveDeclaration(Declaration declaration);
	/*Méthode pour avoir une liste de Super Héros à contacter selon une géolocalisation pouvant resoudre un type d'incendie 
	et se trouvant dans un perimetre de 50km 
	*/
	public List<SuperHero> searchSuperHeros(double latitude, double longitude, String typeIncident);
	

}
