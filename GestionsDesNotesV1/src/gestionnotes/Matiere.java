package gestionnotes;
import java.util.*;


/**
 * La class Matiere contient les matieres
 */

public class Matiere { 

	private String nom; // nom de la matiere 
	
	//constucteur initialisant le nom de la matiere 
	
	public Matiere(String nom) {
        this.nom = nom;
    }
	
	//retourne le nom de la matiere 
	
	 public String getNom() {
	        return nom;
	    }
	
}
