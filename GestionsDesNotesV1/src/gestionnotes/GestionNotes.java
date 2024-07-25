package gestionnotes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.ParseException;


// classe principale de l'application

public class GestionNotes {
    private static Ecole ecole = new Ecole();  // creation de l'objet Ecole
    private static Scanner scanner = new Scanner(System.in); // creation d'un scanner pour la lecture des entrees utilisateur
    private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##"); //format decimal pour la note
    
    // Identifiant de l'administrateur par defaut
    
    private static final String ADMIN_USERNAME = "Esmel"; 
    private static final String ADMIN_PASSWORD = "P@ssword";

    public static void main(String[] args) {
    	
    	// Authentification de l'administrateur
    	
        if (!connexion()) {
            System.out.println("Accès refusé.");
            return;
        }

        // Attendre l'appui sur une touche pour continuer
        
        attendreEtEffacer();

        // Affichage du menu principal
        
        System.out.println("------------------------------------------------------------------");
        System.out.println("*** BIENVENUE DANS VOTRE ESPACE DE GESTION DES NOTES ETUDIANTS ***");
        System.out.println("------------------------------------------------------------------");

        while (true) {
            afficherMenu();
            int choix = scanner.nextInt(); // lecture du choix de l'utilisateur
            scanner.nextLine();  // consommer la nouvelle ligne
            switch (choix) {
                case 1:
                    ajouterClasse(); // appel la méthode pour ajouter une classe
                    break;
                case 2:
                    ajouterEtudiant(); // appel la méthode pour ajouter un étudiant
                    break;
                case 3:
                    ajouterMatiere(); // appel la méthode pour ajouter une matière
                    break;
                case 4:
                    enregistrerNotes(); // appel la méthode pour enregistrer une note
                    break;
                case 5:
                    listerClasses(); // appel la méthode pour lister les classes creer avec les etudiants, les matieres et les notes
                    break;
                case 6:
                    calculerMoyennes(); // appel la méthode pour calculer les moyennes
                    break;
                
                case 7:
                    etablirClassement(); // appel la méthode pour afficher le classement
                    break;
                case 8:
                    genererBulletins(); // appel la méthode pour générer les bulletins
                    break;
                case 9:
                    System.out.println("Fin de session !"); // quitter l'application
                    return;
                default:
                    System.out.println("Choix invalide."); // gestion de choix non valide
            }
        }
    }

    // Connexion de l'administrateur
    
    private static boolean connexion() {
        System.out.println("------------------------------------------------------------- ");
        System.out.println("*                        Connexion                         * ");
        System.out.println("------------------------------------------------------------- ");
        System.out.print("Nom d'utilisateur : ");
        String username = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("Connexion réussie : " + username);
            return true;
        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
            return false;
        }
    }

    // Méthode pour attendre et effacer l'écran
    
    private static void attendreEtEffacer() {
        System.out.println("Appuyez sur Entrée pour continuer...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Effacer l'écran 
        
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // lignes vides pour simuler le nettoyage de l'écran
        
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }
    
    //Affichage des options
    
    private static void afficherMenu() {
        System.out.println("1. Ajouter une classe");
        System.out.println("2. Ajouter un étudiant");
        System.out.println("3. Ajouter une matière");
        System.out.println("4. Enregistrer des notes");
        System.out.println("5. Lister les classes");
        System.out.println("6. Calculer les moyennes");
        System.out.println("7. Établir le classement");
        System.out.println("8. Générer les bulletins");
        System.out.println("9. Quitter");
        System.out.print("Choisissez une option : ");
    }

    
    //methode 1 : ajout d'une nouvelle classe 
    
    private static void ajouterClasse() {
        System.out.print("Nom de la nouvelle classe : ");
        String nom = scanner.nextLine();
        Classe classe = new Classe(nom);
        ecole.ajouterClasse(classe);
    
    }

  //methode 2 : ajout d'un nouvel etudiant 
    
    private static void ajouterEtudiant() {
        System.out.print("Nom de la classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = ecole.trouverClasse(nomClasse);
        if (classe == null) {
            System.out.println("Classe non trouvée.");
      
            return;
        }

      //ajout d'un nouvel etudiant a une classe existante
        
        System.out.print("Nom du nouvel étudiant : ");
        String nomEtudiant = scanner.nextLine();
        Etudiant etudiant = new Etudiant(nomEtudiant);
        classe.ajouterEtudiant(etudiant);
     
    }

  
  //methode 3 : ajouter une matiere 
    
    private static void ajouterMatiere() {
        System.out.print("Nom de la nouvelle matière : ");
        String nom = scanner.nextLine();
        Matiere matiere = new Matiere(nom);
        System.out.print("Classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = ecole.trouverClasse(nomClasse);
        if (classe == null) {
            System.out.println("Classe non trouvée.");
        
            return;
        }
        classe.ajouterMatiere(matiere);
    }

  //methode 4 : enregistrer une note pour une matiere et une classe specifique
    
    private static void enregistrerNotes() {
        System.out.print("Nom de la classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = ecole.trouverClasse(nomClasse);
        if (classe == null) {
            System.out.println("Classe non trouvée.");
         
            return;
        }
        System.out.print("Matière : ");
        String nomMatiere = scanner.nextLine();
        Matiere matiere = null;
        for (Matiere m : classe.getMatieres()) {
            if (m.getNom().equalsIgnoreCase(nomMatiere)) {
                matiere = m;
                break;
            }
        }
        if (matiere == null) {
        
            System.out.println("Matière non trouvée.");
            return;
        }
        for (Etudiant etudiant : classe.getEtudiants()) {
            System.out.print("Note pour " + etudiant.getNom() + " : ");
            String noteInput = scanner.nextLine();
            try {
                double note = Double.parseDouble(noteInput);
                
                // Formatter la note avec deux décimales
                
                String noteFormatted = DECIMAL_FORMAT.format(note);
                
                // Convertir la chaîne formatée en double pour le stockage
                
                note = DECIMAL_FORMAT.parse(noteFormatted).doubleValue();
                etudiant.ajouterNote(matiere, note);
            } catch (NumberFormatException | ParseException e) {
                System.out.println("Erreur de format de note. Veuillez entrer un nombre valide.");
            }
        }
    }
    
    
    
 // Méthode 5: pour lister les classes avec les étudiants, matières et notes
    
    private static void listerClasses() {
        List<Classe> classes = ecole.getClasses();
        if (classes.isEmpty()) {
            System.out.println("Aucune classe n'a été créée.");
            return;
        }

        for (Classe classe : classes) {
            System.out.println("Classe : " + classe.getNom());

            // Afficher les étudiants
            for (Etudiant etudiant : classe.getEtudiants()) {
                System.out.println("  Étudiant : " + etudiant.getNom());

                // Afficher les matières et les notes de l'étudiant
                for (Matiere matiere : classe.getMatieres()) {
                    // Récupérer les notes pour la matière actuelle
                    List<Double> notes = etudiant.getNotes().get(matiere);
                    if (notes != null && !notes.isEmpty()) {
                        // Afficher chaque note pour la matière
                        for (Double note : notes) {
                            System.out.println("    Matière : " + matiere.getNom() + " - Note : " + note);
                        }
                    } else {
                        System.out.println("    Matière : " + matiere.getNom() + " - Note : Aucune note enregistrée");
                    }
                }
            }
        }
    }
    
    
 // methode 6 : calcule et affichage des moyennes des etudiants
    
    private static void calculerMoyennes() {
        System.out.print("Nom de la classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = ecole.trouverClasse(nomClasse);
        if (classe == null) {
            System.out.println("Classe non trouvée.");
            return;
        }
        for (Etudiant etudiant : classe.getEtudiants()) {
            System.out.println("Étudiant: " + etudiant.getNom());
            for (Matiere matiere : classe.getMatieres()) {
                double moyenne = etudiant.calculerMoyenne(matiere);
                System.out.println(String.format("  Moyenne en %s: %.2f", matiere.getNom(), moyenne));
            }
            double moyenneGenerale = etudiant.calculerMoyenneGenerale();
            System.out.println(String.format("  Moyenne générale: %.2f", moyenneGenerale));
        }
    }

  //methode 7: afficher le classement des etudiants pour chaque matiere
    
    private static void etablirClassement() {
        System.out.print("Nom de la classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = ecole.trouverClasse(nomClasse);
        if (classe == null) {
       
            System.out.println("Classe non trouvée.");
            return;
        }
        System.out.print("Nom de la matière : ");
        String nomMatiere = scanner.nextLine();

     // Utilisation d'un tableau avec une seule case pour stocker la variable matiere
        
        final Matiere[] matiere = {null};
        for (Matiere m : classe.getMatieres()) {
            if (m.getNom().equalsIgnoreCase(nomMatiere)) {
                matiere[0] = m;
                break;
            }
        }
        if (matiere[0] == null) {
        	 
            System.out.println("Matière non trouvée.");
            return;
        }
        List<Etudiant> etudiants = classe.getEtudiants();
        etudiants.sort((e1, e2) -> Double.compare(e2.calculerMoyenne(matiere[0]), e1.calculerMoyenne(matiere[0])));

        System.out.println("Classement pour la matière " + matiere[0].getNom() + ":");
        for (int i = 0; i < etudiants.size(); i++) {
            Etudiant etudiant = etudiants.get(i);
            System.out.println(String.format("%d. %s - Moyenne: %.2f", (i + 1), etudiant.getNom(), etudiant.calculerMoyenne(matiere[0])));
        }
    }

    //Methode 8: génération des bulletins des etudiants
    
    private static void genererBulletins() {
        System.out.print("Nom de la classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = ecole.trouverClasse(nomClasse);
        if (classe == null) {
            System.out.println("Classe non trouvée.");
            return;
        }

        for (Etudiant etudiant : classe.getEtudiants()) {
            String nomFichier = etudiant.getNom().replaceAll("\\s+", "_") + "_Bulletin.txt";
            try (FileWriter writer = new FileWriter(nomFichier)) {
                writer.write("Bulletin de notes pour " + etudiant.getNom() + "\n");
                writer.write("Classe : " + classe.getNom() + "\n\n");

                for (Matiere matiere : classe.getMatieres()) {
                    double moyenne = etudiant.calculerMoyenne(matiere);
                    writer.write(String.format("Matière : %s - Moyenne : %.2f\n", matiere.getNom(), moyenne));
                }

                double moyenneGenerale = etudiant.calculerMoyenneGenerale();
                writer.write(String.format("\nMoyenne générale : %.2f\n", moyenneGenerale));

                System.out.println("Bulletin généré pour " + etudiant.getNom() + " : " + nomFichier);
            } catch (IOException e) {
                System.out.println("Erreur lors de la génération du bulletin pour " + etudiant.getNom());
                e.printStackTrace();
            }
        }
    }

  
}