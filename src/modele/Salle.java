package modele ;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable ;
import java.util.concurrent.Semaphore;

import controller.Declencheur;

public class Salle {
	private Declencheur declencheur ;
	private String nom ;
	private Hashtable<Integer, Table> tables ;

	public String getNom(){
		return this.nom ;
	}

	public Hashtable<Integer, Table> getTables(){
		return this.tables ;
	}
	
	public Salle(String nom, Hashtable<Integer, Table> tables, Declencheur declencheur){
		this.nom = nom ;
		this.tables = tables ;
		this.declencheur = declencheur ;
	}
	
	public void startTables(){
		Enumeration<Integer> e = tables.keys();
		while(e.hasMoreElements()){
			Table table = tables.get(e.nextElement());
			Thread t = new Thread(table) ;
			t.start() ;
		}
	}

	public void declencher(Ordre ordre){
		this.setOrdre(ordre);
            synchronized (declencheur) {
                declencheur.notifyAll();
                declencheur.notifyAll();
            }
	}
	
	private void setOrdre(Ordre ordre){
		Enumeration<Integer> e = tables.keys();
		while(e.hasMoreElements()){
			tables.get(e.nextElement()).setOrdre(ordre);
		}
	}

	public int getTableIsolee() {
		Hashtable<Integer, Double> tableauMoyenneDistanceTableOccupee = new Hashtable<Integer, Double>(); 
		
		// On remplit cette Hashtable avec les résultats des autres tables.
		Enumeration<Integer> e = getTables().keys();
		int id ;
		while(e.hasMoreElements()){
			id = e.nextElement();
			// Seul les tables libres ajoutent leur moyenne à ce tableau.
			if(!getTables().get(id).getOccupee())
				tableauMoyenneDistanceTableOccupee.put(id, getTables().get(id).getMoyennedistanceAuxTablesOccupees());
		}
		
		// On parcoure cette Hashtable pour trouver la table qui a les distances les plus élevées (la plus isolée en fait.
		Enumeration<Integer> f = tableauMoyenneDistanceTableOccupee.keys() ;
		int maxId = f.nextElement();
		double max = tableauMoyenneDistanceTableOccupee.get(maxId) ;
		while(f.hasMoreElements()){
			id = f.nextElement();
			if(tableauMoyenneDistanceTableOccupee.get(id) > max){
				max = tableauMoyenneDistanceTableOccupee.get(id);
				maxId = id ;
			}	
		}
		return maxId;
	}

	/*
	 * Envoi les informations nécessaires au calcul des distances entre une table et ses voisines occupees.
	 * On envoi donc :
	 * 		- une ArrayList T pour les Id des tables occupées ;
	 * 		- une ArrayList x contenant les abscisses des tables correspondantes ;
	 * 		- une ArrayList x contenant les ordonnées des tables correspondantes ;
	 * Ces informations sont evnoyées à TOUTES les tables.
	 */
	public void envoyerInformation(ArrayList<Integer> TO){
		// On crée nos 3 ArrayList que l'on va envoyer aux tables.
		ArrayList<Integer> T = new ArrayList<Integer>() ;	// Va contenir les Ids des tables occupées.
		ArrayList<Integer> x = new ArrayList<Integer>() ;	// Va contenir les abscisses des tables occupées
		ArrayList<Integer> y = new ArrayList<Integer>() ;	// Va contenir les ordonnées des tables occupées
		int taille = TO.size();
		for(int i = 0 ; i < taille ; i ++){
			T.add(i, TO.get(i));
			x.add(i, getTables().get(T.get(i)).getX()) ;
			y.add(i, getTables().get(T.get(i)).getY()) ;
		}
		
		// On envoi ensuite ces informations à TOUTES les tables.
		Enumeration<Integer> e = tables.keys();
		while(e.hasMoreElements()){
			getTables().get(e.nextElement()).recupererInformation(T, x, y);
		}
	}
	
//	/**
//	 * Permet de setter la valeur de l'attribut "tacheTerminee" pour toutes les tables de la salle.
//	 * @param b "true" pour "tache terminee" ; "false" pour "tache non terminee"
//	 */
//	public void setTacheTerminee(boolean b){
//		Enumeration<Integer> e = this.tables.keys();
//		// On parcoure toutes les tables de la salle.
//		while(e.hasMoreElements())
//			this.tables.get(e.nextElement()).setTacheTerminee(b);
//	}
//	
//	/**
//	 * Renvoi la multiplication (au sens &&) de l'attribut "tacheTerminee" de toutes les tables.
//	 * 		- à "true", toutes les tables ont terminées leur tache.
//	 * 		- à "false", une/des table(s) n'ont pas terminée(s) leur tache.
//	 * @return Renvoi l'état générale de la salle.
//	 */
//	public boolean getTacheTerminee(){
//		Enumeration<Integer> e = this.tables.keys();
//		boolean res = true ;
//		// On parcoure toutes les tables de la salle.
//		while(e.hasMoreElements())
//			res = res && this.tables.get(e.nextElement()).getTacheTerminee();
//			// On "multiplie" les attributs "tacheTerminee" des tables de la salle.
//		return res ;
//	}
	
	public void setSemaphore(Semaphore sem){
		Enumeration<Integer> e =  this.tables.keys();
		while(e.hasMoreElements()){
			this.tables.get(e.nextElement()).setSemaphore(sem);
		}
	}
	
}