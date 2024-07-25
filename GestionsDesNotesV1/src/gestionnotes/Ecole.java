package gestionnotes;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

//la class Ecole contient les classes

public class Ecole {
    private List<Classe> classes; 

    //Creation du constructeur pour initialiser l'objet Ecole
    
    public Ecole() {
        this.classes = new ArrayList<>();
    }

    //Methode pour ajouter une classe 
    
    public void ajouterClasse(Classe classe) {
        classes.add(classe);
    }

    //Methode pour trouver une classe 
    
    public Classe trouverClasse(String nomClasse) {
        for (Classe c : classes) {
            if (c.getNom().equalsIgnoreCase(nomClasse)) {
                return c;
            }
        }
        return null;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    
}