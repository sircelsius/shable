package modele;

public class Eleve {
	private final String nom ;
	private Classe classe ;
	private Table table = null ;
	private int coeffTriche ;
	
	public Eleve(String nom, Classe classe){
		this.nom = nom ;
		this.classe = classe ;
	}
	
	public String getNom(){
		return this.nom ;
	}
	
	public Classe getClasse(){
		return this.classe ;
	}
	
	public Table getTable(){
		return this.table ;
	}
	
	public void setTable(Table table){
		this.table = table ;
	}
	
	public int getCoeffTriche(){
		return this.coeffTriche ;
	}
}
