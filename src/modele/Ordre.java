package modele;

public enum Ordre {
		affichage, // Affiche des informations sur la table.
		calcul_distance_aux_tables_occupees,	
		/* Dans la phase d'initialisation, chaque table libre recevant 
		 * cet ordre va calculer ses distances aux tables occupés.
		 */
		calcul_coeff_triche ;
		/* Dans la phase de recuit simulé, chaque table occupé va calculer
		 * sa distance aux autres tables occupés pour déterminer son coefficient de triche.
		 */
}
