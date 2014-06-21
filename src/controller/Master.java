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
		
		// DEBUT DE LA PHASE DE RECUIT SIMULE
		
		//Définition des paramètres du recuit
		int temp = 100; // Température. On commence par 100 (à régler empiriquement)
		int proba_solution_accept = 0; // Probabilité d'accepter une solution invalide. Initialisée à 0
		int proba_table[] = new int[(int) (0.2*TO.size())]; // Probabilité permettant de sélectionner la table à bouger.
		int proba_table_accept = 0;
		int proba_temp= 50;
		int compteur =0;
		int table_current_number = 0;
		int rand_tl = 0;
		Table table_current;
		boolean test = false;
		
		proba_table[0] = proba_temp;
		
		// initialiser le tableau permettant de sélectionner la table à bouger
		for (int i = 1; i < proba_table.length; i++) {
			proba_temp = proba_temp/2;
			proba_table[i] = proba_temp;
		}
		
		
		
		
		while(temp>75){
			compteur=0;
			
			// Prendre une table aléatoirement dans les 20% au potentiel le plus élevé
			proba_temp =(int)(Math.random()*100);
			
			while((test == false)&&(compteur<proba_table.length)){
				if (proba_temp > proba_table[compteur]) {
					table_current_number = compteur; 
				}
			}
			
			//table_current = TO.get(table_current_number);
			// TODO: récupérer la table occuppée correspondante
			
			// on récupère une table libre au hasard.
			rand_tl = (int)((Math.random()*TL.size()+1)-1);
			
			// table_new = TL.get(rand_tl);
			// TODO: récupérer la table libre correspondante
			
			// TODO: mettre la TO à libre et la TL a occuppée
			// TO.remove(rand_tl);
			//salle.getTables().get(table_current).setOccupee(false);
			//table_new.occuppee = true
			//
			//Envoyer les informations aux threads et recalculer les distances
			// salle.envoyerInformation(TO);
			// On dit aux tables de commencer à calculer leur distance aux autres tables qui sont occupées.
			// salle.declencher(Ordre.calcul_distance_aux_tables_occupees);
			
			// TODO: calculer l'énergie
			
			// TODO: case Ei+1 >= Ei: valider avec proba, baisser la valeur de temp
			
			// TODO: case Ei+1 < Ei: valider
		}
		
	}
}
