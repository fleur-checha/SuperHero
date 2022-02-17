package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import metier.entities.Declaration;
import metier.entities.Incident;
import metier.entities.SuperHero;

public class TestDao {

	public static void main(String[] args) {
		
		//Pour tester les fonctionnalités 
		
		//Liens utils
		/*
		 * https://zestedesavoir.com/tutoriels/1365/des-cartes-sur-votre-site/
		 * https://www.geeksforgeeks.org/program-distance-two-points-earth/#:~:text=For%20this%20divide%20the%20values,is%20the%20radius%20of%20Earth.
		 * https://www.w3schools.com/html/html5_geolocation.asp
		 */

		HeroDaoImpl dao = new HeroDaoImpl();

		// Test ajout de super heros
		SuperHero sh = new SuperHero("Batman",
				new ArrayList<String>(Arrays.asList(Incident.AccidentAerien.name(), Incident.AccidentFluviale.name())),
				47.077766, -0.219043, "06359874455222");
		System.out.println(dao.saveSuperHero(sh));

		SuperHero sh2 = new SuperHero("Superman", new ArrayList<String>(Arrays.asList(Incident.Manifestation.name(),
				Incident.Braquage.name(), Incident.EvasionPrisonnier.name())), 47.077766, -0.643656, "06359874455222");
		System.out.println(dao.saveSuperHero(sh2));

		SuperHero sh3 = new SuperHero("Catwoman",
				new ArrayList<String>(Arrays.asList(Incident.AccidentRoutier.name(), Incident.AccidentFluviale.name())),
				46.778871, -0.643656, "06359874455222");
		System.out.println(dao.saveSuperHero(sh3));
		
		SuperHero sh4 = new SuperHero("Cyborg",
				new ArrayList<String>(Arrays.asList(Incident.Eboulement.name(), Incident.InvasionDeSerpent.name())),
				46.912911, -0.643656, "06359874455222");
		System.out.println(dao.saveSuperHero(sh4));
		
		SuperHero sh5 = new SuperHero("Aquaman",
				new ArrayList<String>(Arrays.asList(Incident.AccidentAerien.name(), Incident.AccidentFluviale.name(), Incident.Incendie.name())),
				46.778871, -0.219043, "06359874455222");
		System.out.println(dao.saveSuperHero(sh5));

		
		System.out.println("----------------------------");
		// Test ajout de declarations et recherche de super superHeros

		Declaration d = new Declaration("Paris", 25.0, 26.2, Incident.AccidentFluviale.name());

		List<SuperHero> superHeros = dao.saveDeclaration(d);
		superHeros.forEach((hero) -> {
			System.out.println(hero.toString());
		});

		
		System.out.println("-----------------------");
		Declaration d2 = new Declaration("Genève", 25.0, 26.2, Incident.Braquage.name());
        
		List<SuperHero> superHeros2 = dao.saveDeclaration(d2);
		superHeros2.forEach((hero) -> {
			System.out.println(hero.toString());
		});

	}

}
