package Algo;


import java.io.File;

import javax.swing.JFileChooser;
 
 
public class SelectionFichier {
	
    public static File choixFichier() {
    	
        // Bo�te de s�lection de fichier � partir du r�pertoire
        // "home" de l'utilisateur
        {
            // cr�ation de la bo�te de dialogue
            JFileChooser dialogue = new JFileChooser();
             
            // affichage
            dialogue.showOpenDialog(null);
             
            // r�cup�ration du fichier s�lectionn�
            System.out.println("Fichier choisi : " + dialogue.getSelectedFile());
            //return dialogue.getSelectedFile();
            File file =dialogue.getSelectedFile();
            return file;
        }
      
    }

   
}

