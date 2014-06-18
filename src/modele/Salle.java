package modele ;

import java.util.Enumeration;
import java.util.Hashtable ;

public class Salle {
	private String nom ;
	private Hashtable<Integer, Table> tables ;

	public String getNom(){
		return this.nom ;
	}

	public Hashtable<Integer, Table> getTables(){
		return this.tables ;
	}
	
	public Salle(String nom, Hashtable<Integer, Table> tables){
		this.nom = nom ;
		this.tables = tables ;
	}
	
	public void setChaine(String chaine){
		Enumeration<Integer> e = tables.keys();
		while(e.hasMoreElements()){
			tables.get(e.nextElement()).setChaine(chaine);
		}
		
	}

	public void startTables(){
		Enumeration<Integer> e = tables.keys();
		while(e.hasMoreElements()){
			Table table = tables.get(e.nextElement());
			Thread t = new Thread(table) ;
			t.start() ;
		}
	}

	public void setOrdre(Ordre ordre){
		Enumeration<Integer> e = tables.keys();
		while(e.hasMoreElements()){
			tables.get(e.nextElement()).setOrdre(ordre);
		}
	}
}