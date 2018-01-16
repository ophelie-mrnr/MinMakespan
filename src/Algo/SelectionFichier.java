package Algo;


import java.io.File;

import javax.swing.JFileChooser;
 
 
public class SelectionFichier {
	
    public static File choixFichier() {
    	
        // Boîte de sélection de fichier à partir du répertoire
        // "home" de l'utilisateur
        {
            // création de la boîte de dialogue
            JFileChooser dialogue = new JFileChooser();
             
            // affichage
            dialogue.showOpenDialog(null);
             
            // récupération du fichier sélectionné
            System.out.println("Fichier choisi : " + dialogue.getSelectedFile());
            //return dialogue.getSelectedFile();
            File file =dialogue.getSelectedFile();
            return file;
        }
      
    }

   
}

