package modele ;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import controller.Declencheur;
import static java.lang.Math.hypot;

public class Table implements Runnable{
	// Cet objet va être le déclencheur d'action de nos tables
	private Declencheur declencheur ;
	
	// Coordonnées de la table
	private final int x ;
	private final int y ;
	private final int id ;
	private boolean occupee = false ;	// true = occupee ; false = libre.
	private Ordre ordre = Ordre.affichage;

	// Pour la phase d'initialisation
	private ArrayList<Integer> TL = new ArrayList<Integer>() ; // Les tables libres
	private ArrayList<Integer> TO = new ArrayList<Integer>() ; // Les tables libres
	private ArrayList<Integer> tx = new ArrayList<Integer>() ;
	private ArrayList<Integer> ty = new ArrayList<Integer>() ;
	
	// Pour connaitre sa distance aux autres tables occupées : Hashtable<Clef = IdTable, Valeur = DistanceALaTable>
	private Hashtable<Integer, Double> distanceAuxTablesOccupees;
	private double moyennedistanceAuxTablesOccupees ;
	
	public Table(int x, int y, int id, Declencheur declencheur){
		this.x = x ;
		this.y = y ;
		this.id = id ;
		this.declencheur = declencheur ;
	}
	
	public int getX(){
		return this.x ;
	}

	public int getY(){
		return this.y;
	}

	public int getId(){
		return this.id;
	}

	public void setOccupee(boolean occupee){
		this.occupee = occupee;
	}
	
	public boolean getOccupee(){
		return this.occupee;
	}
	
	public String toString(){
		String statut = (occupee) ? "occupée" : "libre" ;
		String res = "Je suis la table " + this.id + " de coordonnees (" + this.x + "," + this.y + ") et je suis " + statut ;
		return res ;
	}
	
	public double getMoyennedistanceAuxTablesOccupees(){
		return this.moyennedistanceAuxTablesOccupees ;
	}
	
	public void setOrdre(Ordre ordre) {
		this.ordre = ordre ;
	}
	
	
	public ArrayList<Integer> getT(boolean libre){
		/* Si libre est à true, on retourne l'ArrayList des Tables Libres (TL)
		 * Sinon on retourne l'ArrayList des Tables Occupees (TO).
		 */
		ArrayList<Integer> res = (libre) ? this.TL : this.TO ;
		return res ;
	}
	
	public void setT(boolean occupee, ArrayList<Integer> T){
		/* Si "occupee" est à true, on set l'ArrayList des Tables Occupees (TO)
		 * Sinon on set l'ArrayList des Tables Libres (TL).
		 */
		if(occupee)
			this.TO = T ;
		else
			this.TL = T ;
	}
	
	public void setTO(ArrayList<Integer> TO){
		this.TO = TO ;
	}
	
	@Override
	public void run() {
		while(true){
			synchronized (declencheur) {
				try{
					System.out.println("je suis en attente "+this.id+"\n");
					declencheur.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			System.out.println("Je me réveille"+this.id+"\n");
			switch(this.ordre){
				case affichage:
					this.toString();
					break;
				case calcul_distance_aux_tables_occupees:
					if(!this.occupee)
						this.calculDistanceTablesOccupees();
						this.moyennedistanceAuxTablesOccupees();
						break;
				default:
					System.out.println("Cet ordre n'existe pas !\n");
					break;
			}
		}
	}


	/*
	 * Cette méthode rentre dans le tableau "distanceAuxTablesOccupees" les
	 * distances entre "this" table et les autres tables occupees.
	 */
	public void calculDistanceTablesOccupees(){
		int taille = this.TO.size();
		double a = 0 ;
		for(int i = 0 ; i < taille ; i++ ){
			a = hypot((double)(this.tx.get(i) - this.x), ((double)(this.tx.get(i) - this.x)));
			distanceAuxTablesOccupees.put(this.TO.get(i), a);
			System.out.println("Ma distance à la table :"+a);
		}
	}
	
	
	//Fais la moyenne des distances entre "this" table et les tables occupees.
	public double moyennedistanceAuxTablesOccupees(){
		Enumeration<Integer> e = distanceAuxTablesOccupees.keys();
		double res = 0 ;
		// On somme l'ensemble des distances entre cette tables et les tables occuppées, on divise ensuite.
		while(e.hasMoreElements()){ res += distanceAuxTablesOccupees.get(e.nextElement()) ; }
		res /= distanceAuxTablesOccupees.size() ;
		return res ;
	}

	
	/*
	 * Cette méthode permet de récupérer les infos nécessaires au calcul des distances pour la phase d'initialisation.
	 * Je fais une copie pour éviter que mes tables essayent d'accéder aux même variables.
	 * Je ne voulais pas utiliser le bloc "synchronised" pour ne pas perdre l'intérêt du multi-thread.
	 */
	public void recupererInformation(ArrayList<Integer> t, ArrayList<Integer> ptx, ArrayList<Integer> pty) {
		int taille = t.size() ;
		for(int i = 0 ; i < taille; i++){
			/*
			 * En faisant ce qui suit, je m'assure de faire une "deep copy" des objets en paramètres de la méthode.
			 * Ainsi, lorsque toutes les tables commenceront à faire leur calcul, je n'aurai pas de problèmes d'accès concurrents à la mémoire.
			 */
			this.TO.add(i, t.get(i)) ;
			this.tx.add(i, ptx.get(i));
			this.ty.add(i, pty.get(i));
		}
		
	}
}