package dao;

import java.util.List;

import metier.entities.Declaration;
import metier.entities.SuperHero;

//Interface de base pour l'acc�s aux donn�es
public interface IHeroDao {
	
	//M�thode pour l'enregistrement d'un super hero
	public SuperHero saveSuperHero(SuperHero superHero);
    //M�thode pour la soumission d'une d�claration d'incendie
	public List<SuperHero> saveDeclaration(Declaration declaration);
	/*M�thode pour avoir une liste de Super H�ros � contacter selon une g�olocalisation pouvant resoudre un type d'incendie 
	et se trouvant dans un perimetre de 50km 
	*/
	public List<SuperHero> searchSuperHeros(double latitude, double longitude, String typeIncident);
	

}
