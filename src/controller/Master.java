package controller;

import java.util.ArrayList;
import java.util.Enumeration;
import modele.Classe;
import modele.Createur;
import modele.Eleve;
import modele.Ordre;
import modele.Salle;
import modele.Table;

public class Master {
	public static void main(String[] args) {
		System.out.println("Commencement de Master");
		
		//On commence par créer et remplir une classe d'élèves, puis une salle de tables. 
		Salle salle = Createur.creationSalle();
		Classe classe = Createur.creationClasse();
		salle.startTables();	// On démarre les threads associés à nos tables.

		
		Enumeration<String> g = classe.getEleves().keys();
		String l = "" ;
		while(g.hasMoreElements()){
			l = classe.getEleves().get(g.nextElement()).getNom();
			System.out.println(l+"\n");
		}
		
		
		
		/* *************************************
		 * Le Programme est en deux parties :
		 * 		- PHASE D'INITIALISATION
		 * 		- PHASE DE RECUIT SIMULÉ
		 * ************************************/
		
		
		
		 // On commence la PHASE D'INITIALISATION
		ArrayList<Integer> TL = new ArrayList<Integer>(salle.getTables().keySet());	// TL est l'ArrayList des Tables Libres
		ArrayList<Integer> TO = new ArrayList<Integer>() ;							// TO est l'ArrayList des Tables Occupées
		/*
		 *  On place le 1er élève à sa table, on set la table à occupé,
		 *  et on ajoute cette table à la liste des tables occupées. 
		 */
		Enumeration<String> enumEleve = classe.getEleves().keys() ; 
		Eleve eleve1 = classe.getEleves().get(enumEleve.nextElement()) ;
		Enumeration<Integer> enumTable = salle.getTables().keys();
		Table table1 = salle.getTables().get(enumTable.nextElement()) ;
		eleve1.setTable(table1);
		table1.setOccupee(true);
		TO.add(table1.getId());
		/*
		 * Juste pour l'exemple, je retire la table choisie TL et l'ajoute à TO.
		 * Je continue de la même manière dans la boucle qui suit et place tout les élèves.
		 * Après ce premier "placage", on commence la vraie initialisation.
		 */
		while(enumEleve.hasMoreElements()){
			// On envoi à toutes les tables la liste des tables occupées.
			salle.envoyerInformation(TO);
			// On dit aux tables de commencer à calculer leur distance aux autres tables qui sont occupées.
			salle.declencher(Ordre.calcul_distance_aux_tables_occupees);
			/*
			 * Chacune des tables fait ensuite la moyenne de ses distances aux tables occupées.
			 * On parcoure ensuite toutes ces moyennes, et on renvoi l'id de la table qui a la moyenne la plus élevée.
			 * Cet Id correpsond à la table la plus isolée.
			 */
			int idTableIsolee = salle.getTableIsolee();
			// On attribue la table la plus isolée à l'élève courant.
			classe.getEleves().get(enumEleve.nextElement()).setTable(salle.getTables().get(idTableIsolee));
			// On ajoute ensuite cette table à la liste des tables occupées (TO).
			TO.add(idTableIsolee);
			// Enfin, on set la table à occupée.
			salle.getTables().get(idTableIsolee).setOccupee(true);
		}
		
		
		
		/*
		 * On affiche ensuite le résultat.
		 */
		salle.declencher(Ordre.affichage);
		
	}
}
