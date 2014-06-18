package controller;

import java.util.ArrayList;
import java.util.Enumeration;



//import modele.Classe;
import modele.Createur;
import modele.Ordre;
import modele.Salle;

public class Master {
	public static void main(String[] args) {
		System.out.println("Commencement de Master");
		Salle salle = Createur.creationSalle();
		//Classe classe = Createur.creationClasse();
		salle.setChaine("Helloooo");
		salle.startTables();
		
		// On affiche la chaine commune
//		Enumeration<Integer> e = salle.getTables().keys();
//		while(e.hasMoreElements()){
//			salle.getTables().get(e.nextElement()).printChaine();
//		}
//		salle.setChaine("Yo");
//		e = salle.getTables().keys();
//		while(e.hasMoreElements()){
//			salle.getTables().get(e.nextElement()).printChaine();
//		}
		salle.setChaine("Yo");
		salle.startTables();
		
		/* On commence la phase d'initialisation */
		salle.setOrdre(Ordre.calcul_distance_aux_tables_occupes);
		ArrayList<Integer> TL = salle.getTables();
		ArrayList<Integer> TO = new ArrayList<Integer>() ;
		
		
	}
}
