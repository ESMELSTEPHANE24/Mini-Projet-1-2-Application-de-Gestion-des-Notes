package gestionnotes;
import java.util.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;



/**
 * la class etudiant contient les notes pour chaque matiere
 */

public class Etudiant {

	private String nom;		//nom de l'etudiant
	private Map<Matiere,List<Double>> notes; //Map associe chaque matiere a une note
	
	//constructeur initialisant le nom de l'etudiant et le Map qui associe chaque matiere a une note
	
	public Etudiant(String nom) {
		this.nom = nom;
		this.notes = new HashMap<>(); 
	}
	
	//retourne le nom de l'etudiant
	
	public String getNom() {
		return nom; 
	}
	
	//Ajout d'une note a une matiere 
	
	public void ajouterNote(Matiere matiere, double note) {
		notes.computeIfAbsent(matiere, k -> new ArrayList<>()).add(note);
	}
	
	//retourne la note
	
	public Map<Matiere, List<Double>> getNotes() { 
        return notes;
    }
	
	//calcul la moyenne pour une matiere
	
	public double calculerMoyenne(Matiere matiere) {
        List<Double> notesMatiere = notes.get(matiere);
        if (notesMatiere == null || notesMatiere.isEmpty()) {
            return 0;
        }
        double somme = 0;
        for (double note : notesMatiere) {
            somme += note;
        }
        return somme / notesMatiere.size(); 
    }
	
	//calcul la moyenne pour les notes obtenue  dans une matiere par un etudiant
	
	 public double calculerMoyenneGenerale() {
	        double somme = 0;
	        int count = 0;
	        for (List<Double> notesMatiere : notes.values()) {
	            for (double note : notesMatiere) {
	                somme += note;
	                count++;
	            }
	        }
	        return (count == 0) ? 0 : somme / count;
	    }
	
}
