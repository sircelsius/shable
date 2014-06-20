package modele;

import java.util.Hashtable ;

public class Classe {
	private final String nom ;
	private Hashtable<String, Eleve> eleves ;
	
	public Classe(String nom, Hashtable<String, Eleve> eleves){
		this.nom = nom ;
		this.eleves = eleves ;
	}

	public Hashtable<String, Eleve> getEleves() {
		return eleves;
	}
	
	public String getNom(){
		return this.nom ;
	}
	
}
