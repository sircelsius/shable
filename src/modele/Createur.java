package modele;

import java.util.Hashtable ;

import controller.Declencheur;

public class Createur {
	public static Classe creationClasse(){
		// On crée la classe que l'on va retourner.
		Hashtable<String, Eleve> eleves = new Hashtable<String, Eleve>() ;
		Classe classe = new Classe("GIC15", eleves) ;

		// On crée nos élèves que l'on ajoute ensuite à notre Hashtable
		Eleve marc = new Eleve("Marc", classe) ;
		Eleve christophe = new Eleve("Christophe", classe) ;
		Eleve jean = new Eleve("Jean", classe) ;
		Eleve thomas = new Eleve("Thomas", classe) ;
		Eleve hugues = new Eleve("Hugues", classe) ;
		Eleve bastien = new Eleve("Bastien", classe) ;
		Eleve gael = new Eleve("Gael", classe) ;
		Eleve william = new Eleve("William", classe) ;
		Eleve louis = new Eleve("Louis", classe) ;
		Eleve thibaut = new Eleve("Thibaut", classe) ;

		eleves.put(marc.getNom(), marc) ;
		eleves.put(christophe.getNom(), christophe) ;
		eleves.put(jean.getNom(), jean) ;
		eleves.put(thomas.getNom(), thomas) ;
		eleves.put(hugues.getNom(), hugues) ;
		eleves.put(bastien.getNom(), bastien) ;
		eleves.put(gael.getNom(), gael) ;
		eleves.put(william.getNom(), william) ;
		eleves.put(louis.getNom(), louis) ;
		eleves.put(thibaut.getNom(), thibaut) ;

		return classe ;
	}
	
	public static Salle creationSalle(){
		Declencheur declencheur = new Declencheur();
		
		// On crée la salle que l'on va retourner
		Hashtable<Integer, Table> tables = new Hashtable<Integer, Table>() ;
		Salle salle = new Salle("TG-215", tables, declencheur) ;

		// On crée nos tables que l'on va ajouter à notre salle.
		Table table1 = new Table(5, 5, 1, salle, declencheur);
		Table table2 = new Table(5, 10, 2, salle, declencheur);
		Table table3 = new Table(5, 15, 3, salle, declencheur);
		Table table4 = new Table(5, 20, 4, salle, declencheur);
		Table table5 = new Table(5, 25, 5, salle, declencheur);
		Table table6 = new Table(5, 30, 6, salle, declencheur);
		Table table7 = new Table(10, 5, 7, salle, declencheur);
		Table table8 = new Table(10, 10, 8, salle, declencheur);
		Table table9 = new Table(10, 15, 9, salle, declencheur);
		Table table10 = new Table(10, 20, 10, salle, declencheur);
		Table table11 = new Table(10, 25, 11, salle, declencheur);
		Table table12 = new Table(10, 30, 12, salle, declencheur);
		Table table13 = new Table(15, 5, 13, salle, declencheur);
		Table table14 = new Table(15, 10, 14, salle, declencheur);
		Table table15 = new Table(15, 15, 15, salle, declencheur);
		Table table16 = new Table(15, 20, 16, salle, declencheur);
		Table table17 = new Table(15, 25, 17, salle, declencheur);
		Table table18 = new Table(15, 30, 18, salle, declencheur);
		Table table19 = new Table(20, 5, 19, salle, declencheur);
		Table table20 = new Table(20, 10, 20, salle, declencheur);
		Table table21 = new Table(20, 15, 21, salle, declencheur);
		Table table22 = new Table(20, 20, 22, salle, declencheur);
		Table table23 = new Table(20, 25, 23, salle, declencheur);
		Table table24 = new Table(20, 30, 24, salle, declencheur);

		tables.put(table1.getId(), table1);
		tables.put(table2.getId(), table2);
		tables.put(table3.getId(), table3);
		tables.put(table4.getId(), table4);
		tables.put(table5.getId(), table5);
		tables.put(table6.getId(), table6);
		tables.put(table7.getId(), table7);
		tables.put(table8.getId(), table8);
		tables.put(table9.getId(), table9);
		tables.put(table10.getId(), table10);
		tables.put(table11.getId(), table11);
		tables.put(table12.getId(), table12);
		tables.put(table13.getId(), table13);
		tables.put(table14.getId(), table14);
		tables.put(table15.getId(), table15);
		tables.put(table16.getId(), table16);
		tables.put(table17.getId(), table17);
		tables.put(table18.getId(), table18);
		tables.put(table19.getId(), table19);
		tables.put(table20.getId(), table20);
		tables.put(table21.getId(), table21);
		tables.put(table22.getId(), table22);
		tables.put(table23.getId(), table23);
		tables.put(table24.getId(), table24);
		
		return salle ;


	}
}
