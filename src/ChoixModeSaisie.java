import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Algo.SelectionFichier;

public class ChoixModeSaisie {

	public static void main(String[] args){
		menu(); 
	}
	
	@SuppressWarnings("null")
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		int mode = -1; 	
		do {
		System.out.println("Veuillez choisir votre mode de saisie : ");
		System.out.println("1 : Depuis un fichier (If)" );
		System.out.println("2 : Au clavier (Ic)");
		System.out.println("3 : Génération aléatoire (Ig)");
		
		mode =scannerGetNextInt(sc);
		}
		while (mode !=1 && mode !=2 && mode !=3);
		
		if(mode==1) {
			System.out.println("Choix 1");
			System.out.println("Création d'instance If");
			
			mode1();
			 
			affichageResultatsIfIc();
			
		}
		else if(mode==2) {	
			System.out.println("Choix 2");
			System.out.println("Création d'instance Ic");
		    
			String line;
			  
		    System.out.println("Entrez le nombre de machines m, le nombre de taches n et les durées di de chaque tâche sous la forme suivante : "
		    		+ "m:n:d1:d2:...:dn");
		    line=sc.next();
		    
				System.out.println(line);
				String[] s = line.split(":"); // on coupe les durées de part les ":" 
				int[] taches = new int[s.length]; //tableau des taches
				taches[0] = Integer.parseInt(s[0]);
				taches[1] = Integer.parseInt(s[1]);
				System.out.println("nb machines = " +taches[0] + "/nnb taches =" +taches[1] );
				for(int i =2;i<s.length;i++) { 
					taches[i] = Integer.parseInt(s[i]);		//taches[i] liste des valeurs des tâches 
					System.out.println( "les durées = " +taches[i]);
					
				}
		    
			affichageResultatsIfIc();
		}
		else{
			System.out.println("Choix 3");
			System.out.println("Création d'instance Ig");
			int nbrmachines = -1; 
			int nbrtaches = -1;  
			int nbrinstances = -1;
			int dureesmin = -1;
			int dureesmax = -1;
			 
			System.out.println("Veuillez choisir le nombre d'instances à produire : ");
			nbrinstances=scannerGetNextInt(sc);
			System.out.println("Choisir le nombre de machines m : ");
			nbrmachines=scannerGetNextInt(sc);
			System.out.println(nbrmachines);
			System.out.println("Choisir le nombre de tâches n : ");
			nbrtaches=scannerGetNextInt(sc);
			System.out.println("Choisir la durée minimale : ");
			dureesmin=scannerGetNextInt(sc);
			System.out.println(dureesmin);
			System.out.println("Choisir la durée maximale : ");
			dureesmax=scannerGetNextInt(sc);
			System.out.println(dureesmax);
			
			
			System.out.println("Pour chaque instance, voici la liste produite de la forme m:n:d1:d2:...:dn ");
			for(int i = 0; i<nbrinstances; i++) { //fonctionne mais pas le nombre taches avec valeurs randoms 
				System.out.println("instance "+i+" : \n");
				int[] tabElements = new int[nbrtaches+2] ;
				tabElements[0]=nbrmachines; //fonctionne
				tabElements[1]=nbrtaches; //fonctionne 

				
					for(int j=2; j<nbrtaches; j++) {						
						Random rand = null;
						int r = rand.nextInt((dureesmax + 1) - dureesmin) + dureesmin;
						tabElements[j]= r;
						
						
					//	Random ra = new Random();
					//	int valeur = dureesmin + ra.nextInt(dureesmax - dureesmin); 
					//	tabElements[j]= valeur;
				}
					System.out.println(tabElements);
					affichageResultatsIg();
		}
	}

}
	private static int scannerGetNextInt(Scanner sc) {
		while(sc.hasNextInt()==false) {
			System.out.println("tapez un entier s'il vous plait");
			sc.nextLine();
		}
		
		return sc.nextInt();
	}
	
	public static void mode1(){
		File nomFichier = SelectionFichier.choixFichier();		
		
		BufferedReader r = null;
		try {
			r = new BufferedReader(new FileReader(nomFichier));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = null;
		try {
			while ((line=r.readLine()) != null){
				System.out.println(line);
				String[] s = line.split(":"); // on coupe les durées de part les ":" 
				int[] taches = new int[s.length]; //tableau des taches
				taches[0] = Integer.parseInt(s[0]);
				taches[1] = Integer.parseInt(s[1]);
				System.out.println("nb machines = " +taches[0] + "/nnb taches =" +taches[1] );
				for(int i =2;i<s.length;i++) { 

					taches[i] = Integer.parseInt(s[i]);		//taches[i] liste des valeurs des tâches 
					System.out.println( "les durées = " +taches[i]);
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			r.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	
	
	public static int lsa(int [] taches){
		int tempsAlgoLSA=0;
		ArrayList < ArrayList<Integer> > listeMachines = new ArrayList < ArrayList<Integer> >(taches[0]);
                int nombreMachines= taches[0];
                
                
                for (int i =0; i<nombreMachines; i++){ 
			for(int j=2;j<taches.length; j++ ){
                             listeMachines.get(i).get(j); // avec i =0 et j = 0 : premiere taches de la première machine
                             if(listeMachines.get(i).get(j)==null){
                                 listeMachines.get(i).add(taches[j]);
                             }
                             else if(listeMachines.get(i).get(j)!=null){
                                 listeMachines.get(i+1).add(taches[j]); 
                             } 
                        
                        }
			
			
		}
		
 	
		return tempsAlgoLSA;
	}
	
	public static int lpt(int [] tab){
		
		int tempsAlgoLTP=0;
		
		ArrayList < ArrayList<Integer> > listeMachines = new ArrayList(tab[0]);
		int nbTaches = tab[1];
		// faire pareil mais en ayant trié les durées avant
		for (int i = 2; i<tab.length ; i++){
			
		}
		
		/*Algorithme à implémenter dans un langage
		Entrée : I : Ensemble contenant mes tâches à ordonnancés
		n : nombre de tâches
		ri, pi : respectivement date de disponibilité et durée de la tâche
		Variables intermédiaires : S est un sous ensemble de I
		k, i, Min, Max : entiers
		Sorties : C*(I)
		Début :
		C*(∅)=0 
		Pour k=1 à n 
		Pour tout sous ensemble S de I contenant k éléments de I
		C*(S)=∞
		Pour tout élément i de S
		Si C*(S-{i})<= ri alors Max=ri 
		Sinon Max = C*(S-{i})	
		Min=Max + pi
		Si Min<C*(S)alors C*(S)=Min
		Fin pour
		Fin pour
		Fin pour
		Fin*/
		
		return tempsAlgoLTP;
	}
	
	public static int myAlgo(){
		
		int tempsMyAlgo=0;
						
		
		
		return tempsMyAlgo;
	}
	
	
	public static void affichageResultatsIfIc(){
		System.out.println("Voici les résultats des calculs : ");
		System.out.println("Borne inférieure 'maximum' = ");
		System.out.println("Borne inférieure 'moyenne' = ");
		System.out.println("Résultat LSA = ");
		System.out.println("Résultat LPT = ");
		System.out.println("Résultat myAlgo = ");
	}
	
	public static void affichageResultatsIg(){
		
	
	}
	
}