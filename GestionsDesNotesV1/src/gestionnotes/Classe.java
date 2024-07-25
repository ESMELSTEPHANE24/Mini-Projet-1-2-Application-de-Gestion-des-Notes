package gestionnotes;
import java.util.*;



//La class Classe contient les Etudiants et les matieres

public class Classe {

	//Declaration des variables 
	
	private String nom; 				//Nom de la classe 
	private List<Etudiant> etudiants;	//Liste des etudiants
	private List<Matiere> matieres;		//Liste des matieres
	
	
	//constructeur initialisant le nom de la classe et la liste des etudiants et des matieres
	
	public Classe(String nom) {
		this.nom = nom;
		this.etudiants = new ArrayList<>();
		this.matieres = new ArrayList<>();
		
	}
	
	//retourne le nom de la classe  
	
	public String getNom() {
		return nom; 
	}
	
	//Methode pour ajouter un Etudiant
	
	public void ajouterEtudiant(Etudiant etudiant) {
		etudiants.add(etudiant);
	}
	
	//Methode pour ajouter une matiere a un etudiant 
	
	public void ajouterMatiere(Matiere matiere) {
		matieres.add(matiere);
	}
	
	//retourne la liste des etudiants
	
	public List<Etudiant> getEtudiants(){
		return etudiants; 
	}
	
	//retourne la liste des matieres
	
	public List<Matiere> getMatieres(){
		return matieres; 
	}
}
