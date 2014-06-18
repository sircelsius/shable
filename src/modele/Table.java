package modele ;

import java.util.ArrayList;

public class Table implements Runnable{
	
	// Coordonnées de la table
	private final int x ;
	private final int y ;
	private final int id ;
	//private boolean utilisable = true ; Eventuellement pour faire préciser les tables réservés au professeurs.
	private boolean occupe = false ;
	private Salle salle ;
	private String chaineCommune = "" ;
	private Ordre ordre = Ordre.affichage;
	
	// Pour la phase d'initialisation
	private ArrayList<Integer> TL ; // Les tables libres
	private ArrayList<Integer> TO ; // Les tables libres

	public int getX(){
		return this.x ;
	}

	public int getY(){
		return this.y;
	}

	public int getId(){
		return this.id;
	}
	
	public ArrayList<Integer> getT(boolean libre){
		/* Si libre est à true, on retourne l'ArrayList des Tables Libres (TL)
		 * Sinon on retourne l'ArrayList des Tables Occupees (TO).
		 */
		ArrayList<Integer> res = (libre) ? this.TL : this.TO ;
		return res ;
	}
	
	
	public void setT(boolean libre, ArrayList<Integer> T){
		/* Si libre est à true, on set l'ArrayList des Tables Libres (TL)
		 * Sinon on set l'ArrayList des Tables Occupees (TO).
		 */
		if(libre)
			this.TL = T ;
		else
			this.TO = T ;
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

	public Table(int x, int y, int id, Salle salle){
		this.x = x ;
		this.y = y ;
		this.id = id ;
		this.salle = salle ;
	}

	@Override
	public void run() {
		switch(this.ordre){
		case affichage:
//			String statut = this.occupe ? "Occupé" : "Libre" ;
//			System.out.print("Je suis la table "+this.id+" et je suis "+statut+".");
			this.printChaine();
			break;
		default:
			System.out.println("Cet ordre n'exste pas !\n");
			break;
		}
		
	}

	public void setOrdre(Ordre ordre) {
		this.ordre = ordre ;
	}
}