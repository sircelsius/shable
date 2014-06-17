package modele ;

import modele.* ;

public class Table {
	
	// Coordonnées de la table
	final int x ;
	final int y ;
	final int id ;
	//boolean utilisable = true ; Eventuellement pour faire préciser les tables réservés au professeurs.
	Salle salle ;

	public int getX(){
		return this.x ;
	}

	public int getY(){
		return this.y;
	}

	public int getId(){
		return this.id;
	}

	public Salle getSalle(){
		return this.salle;
	}

}