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
	private boolean occupee = false ;	// true = occupe ; false = libre.
	private Salle salle ;
	private String chaineCommune = "" ;
	private Ordre ordre = Ordre.affichage;

	// Pour la phase d'initialisation
	private ArrayList<Integer> TL = new ArrayList<Integer>() ; // Les tables libres
	private ArrayList<Integer> TO = new ArrayList<Integer>() ; // Les tables libres
	private ArrayList<Integer> tx = new ArrayList<Integer>() ;
	private ArrayList<Integer> ty = new ArrayList<Integer>() ;
	
	// Pour connaitre sa distance aux autres tables occupées : Hashtable<Clef = IdTable, Valeur = DistanceALaTable>
	private Hashtable<Integer, Double> distanceAuxTablesOccupees;
	private double moyennedistanceAuxTablesOccupees ;
	
//	// Pour la synchronisation des threads.
//	private CyclicBarrier barrier ;
	
	public int getX(){
		return this.x ;
	}

	public int getY(){
		return this.y;
	}

	public int getId(){
		return this.id;
	}
	
	public double getMoyennedistanceAuxTablesOccupees(){
		return this.moyennedistanceAuxTablesOccupees ;
	}
	
	public void setOccupee(boolean occupee){
		this.occupee = occupee;
	}
	
	public boolean getOccupee(){
		return this.occupee;
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
	
	public void printChaine(){
		System.out.println(this.chaineCommune);
	}
	
	public void setChaine(String chaine){
		this.chaineCommune = chaine ;
	}
	
	public Salle getSalle(){
		return this.salle;
	}

	public Table(int x, int y, int id, Salle salle, Declencheur declencheur){
		this.x = x ;
		this.y = y ;
		this.id = id ;
		this.salle = salle ;
		//int n = salle.getTables().size();
		this.declencheur = declencheur ;
	}

	@Override
	public void run() {
		//CyclicBarrier barrier = new CyclicBarrier(24);
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
//			case initialisation:
//				this.barrier = new CyclicBarrier(this.salle.getTables().size());
//				break;
			case affichage:
				String statut = this.occupee ? "occupé" : "libre" ;
				System.out.print("Je suis la table "+this.id+" et je suis "+statut+".\n");
	//			this.printChaine();
	//			try {
	//				barrier.await();
	//			} catch (InterruptedException | BrokenBarrierException e) {
	//				e.printStackTrace();
	//			}
				break;
			case calcul_distance_aux_tables_occupees:
				if(!this.occupee)
					this.calculDistanceTablesOccupees();
					this.moyennedistanceAuxTablesOccupees();
//					try {
//						barrier.await();
//					} catch (InterruptedException | BrokenBarrierException e) {
//						e.printStackTrace();
//					}
					break;
			default:
				System.out.println("Cet ordre n'existe pas !\n");
				break;
			}
		}
	}

	public void setOrdre(Ordre ordre) {
		this.ordre = ordre ;
	}

	/*
	 * Cette méthode rentre dans le tableau "distanceAuxTablesOccupees" les
	 * distances entre "this" table et les autres tables occupees.
	 */
	public void calculDistanceTablesOccupees(){
		Iterator<Integer> i = this.TO.iterator() ;
		int id ;
		while(i.hasNext()){
			id = i.next();
			distanceAuxTablesOccupees.put(id, distance(salle.getTables().get(id)));
		}
	}
	
	public double distance(Table table){
		return hypot((double)(table.getY() - this.y), (double)(table.getX() - this.x));
	}
	
	/*
	 * Cette méthode calcule la moyenne des distances entre "this" table
	 * et les autres tables occupees.
	 */
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