package modele ;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable ;
import java.util.Random;
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
	
	public void setSemaphore(Semaphore sem){
		Enumeration<Integer> e =  this.tables.keys();
		while(e.hasMoreElements()){
			this.tables.get(e.nextElement()).setSemaphore(sem);
		}
	}
	
	/**
	 * Renvoi une hashtable <IdTable, coefficientTriche>
	 * @param  a Va contenir la liste des tables en entrées;
	 * @return   resultat.
	 */
	public Hashtable<Integer, Double> coefficientTriche(ArrayList<Integer> a){
		Hashtable<Integer, Double> res = new Hashtable<Integer, Double>();
		for(int j = 0 ; j < a.size(); j++){
			res.put(a.get(j), this.tables.get(a.get(j)).getMoyennedistanceAuxTablesOccupees());
		}
		return res;
	}

	/**
	 * Renvoi une ArrayList d'Id de tables triée par Coefficient de triche décroissant.
	 * @param  Hashtable<Integer, Double> h [description]
	 * @return                     			[description]
	 */
	public ArrayList<Integer> triTables(Hashtable<Integer, Double> h){
		int size = h.size() ;
		Enumeration<Integer> e ;
		double maxCoefficient ;
		int maxId, i,k ;
		ArrayList<Integer> res = new ArrayList<Integer>() ;
		
		// On parcoure la Hashtable autant de fois qu'il y a d'éléments dedans.
		for(int j = 0 ; j < size ; j++){
			e = h.keys();
			i = e.nextElement();
			k=i;
			maxId = i ;
			maxCoefficient = h.get(i);
			// Ici on trouve le maximum de la Hashtable.
			while(e.hasMoreElements()){
				i = e.nextElement();
				if((maxCoefficient < h.get(i))&&(h.get(i)!=null)){
					k=i;
					maxCoefficient = h.get(i);
					maxId = i ;
					
				}
			}
			res.add(maxId);
			h.remove(k);
		}
		return res ;
	}
	
	public void afficher(ArrayList<Integer> a){
		System.out.println("Affichage des tables triés : \n");
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i) + " : "+this.tables.get(a.get(i)).getMoyennedistanceAuxTablesOccupees());
		}
	}
	
	public void afficher(Hashtable<Integer, Double> h){
		Enumeration<Integer> e = h.keys();
		int i ;
		System.out.println("Affichage du tableau des coefficients\n");
		while(e.hasMoreElements()){
			i = e.nextElement();
			System.out.println(i+" : "+h.get(i));
		}
	}
	
	public double calculerEnergie(ArrayList<Integer> a){
		double res = 0;
		Hashtable<Integer, Double> coeff = coefficientTriche(a);
		Enumeration<Integer> e = coeff.keys();
		while (e.hasMoreElements())
			res+= coeff.get(e.nextElement());
		return res;
	}
	
}