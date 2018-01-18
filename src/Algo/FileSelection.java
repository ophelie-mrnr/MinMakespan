package Algo;


import java.io.File;

import javax.swing.JFileChooser;
 
 
public class FileSelection {
	
    public static File fileChoice() {
    	
        // Boite de selection de fichier a partir du repertoire
        // "home" de l'utilisateur
        {
            // creation de la boite de dialogue
            JFileChooser dialogue = new JFileChooser();
             
            // affichage
            dialogue.showOpenDialog(null);
             
            // recuperation du fichier selectionne
            System.out.println("\nLe fichier choisi est : " + dialogue.getSelectedFile());
            //return dialogue.getSelectedFile();
            File file =dialogue.getSelectedFile();
            return file;
        }
      
    }

   
}

