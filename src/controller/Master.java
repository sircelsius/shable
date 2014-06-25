package controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.Semaphore;
import java.util.Hashtable;

import modele.Classe;
import modele.Createur;
//import modele.Eleve;
import modele.Ordre;
import modele.Salle;
import modele.Table;

public class Master {
	//public static void shable(boolean logactive, int t_min) {
	public static void main(String args[]){
		boolean logactive = false;
		if(logactive){
			System.out.println("Commencement de Master");
		}
		//On commence par créer et remplir une classe d'élèves, puis une salle de tables. 
		Salle salle = Createur.creationSalle();
		Classe classe = Createur.creationClasse();
		salle.startTables();	// On démarre les threads associés à nos tables.
		
		/* On cré un objet Sémaphore qui va contenir nbTables tokens.
		 * 		- lorsqu'une table est déclenché, elle exécute son ordre, puis relache un token ;
		 * 		- lorsque Master recoit nbTables tokens, on sait que toutes les tables ont finis leur tache.  
		 */
		int nbEleves = classe.getEleves().size();
		int nbTables = salle.getTables().size();
		Semaphore sem = new Semaphore(nbTables);
		salle.setSemaphore(sem);
		
		
		/* *************************************
		 * Le Programme est en deux parties :
		 * 		- PHASE D'INITIALISATION
		 * 		- PHASE DE RECUIT SIMULÉ
		 * ************************************/
			
		
		// On commence la PHASE D'INITIALISATION
		ArrayList<Integer> TL = new ArrayList<Integer>(salle.getTables().keySet());	// TL est l'ArrayList des Tables Libres
		ArrayList<Integer> TO = new ArrayList<Integer>() ;								// TO est l'ArrayList des Tables Occupées
		/*On set la table à occupé,
		 *  on ajoute cette table à la liste des tables occupées,
		 *  et je retire cette table de la liste des tables libres.*/
		Enumeration<Integer> enumTable = salle.getTables().keys();
		Table table1 = salle.getTables().get(enumTable.nextElement()) ;
		table1.setOccupee(true);
		TO.add((Integer)table1.getId());
		TL.remove((Integer)table1.getId());
		/*
		 * Juste pour l'exemple, je retire la table choisie TL et l'ajoute à TO.
		 * Je continue de la même manière dans la boucle qui suit et place tout les élèves.
		 * Après ce premier "placage", on commence la vraie initialisation.
		 */
		for(int i = 0 ; i < nbEleves-1 ; i ++){
			// On envoi à toutes les tables la liste des tables occupées.
			salle.envoyerInformation(TO);
			// On dit aux tables libres de commencer à calculer leur distance aux autres tables qui sont occupées.
			try {
				sem.acquire(nbTables);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			salle.declencher(Ordre.calcul_distance_aux_tables_occupees);
			try {
				sem.acquire(nbTables);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sem.release(nbTables);
			
			//On attends que toutes les tables ait terminéee leur tache.
			//while(!salle.getTacheTerminee()){}
			/*
			 * Chacune des tables fait ensuite la moyenne de ses distances aux tables occupées.
			 * On parcoure ensuite toutes ces moyennes, et on renvoi l'id de la table qui a la moyenne la plus élevée.
			 * Cet Id correpsond à la table la plus isolée.
			 */
			int idTableIsolee = salle.getTableIsolee();
			if(logactive){
				System.out.println("Iteration "+(i+1)+" La table la plus isolée est "+idTableIsolee);
			}
			// On ajoute/retire ensuite cette table à la liste des tables occupées (TO)/tables libres (TL).
			TO.add((Integer)idTableIsolee);
			TL.remove((Integer)idTableIsolee);
			// Enfin, on set la table à occupée.
			salle.getTables().get((Integer)idTableIsolee).setOccupee(true);
		}
		
		
		if(logactive){
			System.out.println("PHASE D'INITIALISATION TERMINÉE.");
		}
		/*
		 * On affiche ensuite le résultat.
		 */
		// On commence la PHASE DE RECUIT SIMULÉ
		
		//double temperature = 100;				// Paramètre classique du RC
		//int t_min = 75;							// Condition d'arrêt
		//int rand_libre;							// Permet de selectionner avec une probabilité plus élevé une table qui a un fort coefficient de triche.
		//int compteur = 1;						// Participe au calcul de la proba ci dessus
		//double rand_rs = Math.random();			// Permet de selectionner avec une probabilité une solution dont l'énergie est plus élevée que l'actuel.
		// On renvoi les dernières informations, et on lance un calcul de distances entre tables occupées.
		salle.envoyerInformation(TO);
		try {
			sem.acquire(nbTables);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		salle.declencher(Ordre.calcul_coeff_triche);
		try {
			sem.acquire(nbTables);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sem.release(nbTables);
		// On calcul l'énergie.	
		double energie  = salle.calculerEnergie(TO);
		if(logactive)
		System.out.println("Valeur de l'énergie : "+energie);
		
		Hashtable<Integer, Double> tableauCoefficientTriche ;
		ArrayList<Integer> tableauCoefficientTricheTrie ;
		
		// DEBUT RECUIT
		
		
		salle.envoyerInformation(TO);								// Encore une fois j'envoi à toutes les tables les coordonnées des tables occupées.
		salle.declencher(Ordre.calcul_coeff_triche); 				// Les tables calculent leur coefficient de triche.
		tableauCoefficientTriche = salle.coefficientTriche(TO) ;	// Ici j'ai une ArrayList <IdTable, CoefficientTriche>
		salle.afficher(tableauCoefficientTriche);
		tableauCoefficientTricheTrie = salle.triTables(tableauCoefficientTriche);
		salle.afficher(tableauCoefficientTricheTrie);
		
		
		double temperature = 100;				// Paramètre classique du RC
		double t_min = 75;							// Condition d'arrêt
		int rand_libre;							// Permet de selectionner avec une probabilité plus élevé une table qui a un fort coefficient de triche.
		int compteur = 1;						// Participe au calcul de la proba ci dessus
		double rand_rs;			// Permet de selectionner avec une probabilité une solution dont l'énergie est plus élevée que l'actuel.
//		Table t = salle.tables.get(TO.get(0));	
//		Salle s_temp = salle;
		float efficacite = 0;
		int iteration_recuis = 0;
		int iteration_success = 0;
		
		double e0 = energie;
		double e_temp = e0;
		double e_temp_1 = e0;
		ArrayList<Integer> TO_temp, TL_temp;
		int size = (int)TO.size();
		double res_exp;
		
		double tab_proba[] = new double[size];
		
		
		tab_proba[0] = 0.5;
		
		while (compteur< tab_proba.length) {
			tab_proba[compteur] = tab_proba[compteur - 1 ]/2;
			compteur++;
		}
		
		while(temperature > t_min){
			iteration_recuis++; // pour connaître le nombre d'itérations effectuées
			
			if(logactive){
				System.out.println("Itération " + iteration_recuis + ".");
				System.out.println("Température : "+temperature+" Energie : "+e_temp_1);
			}
		// Selection d'une table de TO au hasard parmi les 20% les plus chères
		rand_rs = Math.random(); // rand_rs in [0,1)
		
		// On travaille sur des copies de TO et TL
		TO_temp = salle.triTables(salle.coefficientTriche(TO)); // tri de TO
		TL_temp = TL;
		
		// reset de l'itérateur sur la sélection de TO
		compteur = 0;
		
		// on cherche la valeur d'index de TO_temp correspondant à rand_rs
		while ((tab_proba[compteur] > rand_rs)&& (tab_proba[compteur +1] < rand_rs)&& (compteur < tab_proba.length-1)) {
			compteur++;
		}
		
		rand_libre = (int) (Math.random()*(TL.size()+1)); // rand_libre in [0;TL.size()+1[
		
		TL_temp.add(TO_temp.get(compteur));
		System.out.println("La table " + TO_temp.get(compteur) + " devient libre.");
		TO_temp.remove(compteur); // on enlève la table sélectionnée dans TO
		TO_temp.add(TL.get((int)rand_libre)); // on rajoute la table sélectionnée dans TL
		System.out.println("La table " + TL.get((int) rand_libre) + " devient occupée.");
		TL_temp.remove((int)rand_libre);
		
		salle.envoyerInformation(TO);
		try {
			sem.acquire(nbTables);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		salle.declencher(Ordre.calcul_coeff_triche);
		try {
			sem.acquire(nbTables);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sem.release(nbTables);
		e_temp_1 = salle.calculerEnergie(TO_temp);
		
		if (e_temp_1 < e_temp) {
			iteration_success++;
			TO = TO_temp;
			TL = TL_temp;
			e_temp = e_temp_1;
			
			salle.envoyerInformation(TO);
			try {
				sem.acquire(nbTables);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			salle.declencher(Ordre.calcul_coeff_triche);
			try {
				sem.acquire(nbTables);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sem.release(nbTables);
		}
		else{
			rand_rs = Math.random();
			res_exp = Math.exp(-(e_temp_1 - e_temp)/temperature);
			if (rand_rs < res_exp) {
				TO = TO_temp;
				TL = TL_temp;
				salle.envoyerInformation(TO);
				try {
					sem.acquire(nbTables);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				salle.declencher(Ordre.calcul_coeff_triche);
				try {
					sem.acquire(nbTables);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sem.release(nbTables);
				
			}
			temperature -= 0.1;
		}
		
		}
		
		System.out.println("\n_______________________________");
		System.out.println("\n\nALGORITHME TERMINE AVEC SUCCES\n\n");
		System.out.println("_______________________________\n");
		
		double optimisation = 0;
		
		optimisation =(float) (e0 - e_temp)/(e0);
		
		efficacite = (float) (iteration_success)/(iteration_recuis);
		
		
		
		System.out.println("Iterations effectuées: " + iteration_recuis + "\nItérations réussies : " + iteration_success);
		System.out.println("Efficacité des itérations :" + efficacite);
		System.out.println("\nEfficacité de l'algorithme : " + optimisation);
		
		salle.afficher(TO);
		
		
		
		
	}
	
}
