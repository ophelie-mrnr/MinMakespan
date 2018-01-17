import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Algo.SelectionFichier;
import Algo.Utils;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class ChoixModeSaisie {

	public static void main(String[] args){
		// Below the same example as Cours3. It should return 15.
        //int machineNumber = 3;
        //int[] tasks = {2,7,1,3,2,6,2,3,6,2,5};
        /*System.out.println("LSA: " + lsa(machineNumber, tasks));
        System.out.println("LPT: " + lpt(machineNumber, tasks));*/
        
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
		System.out.println("3 : Generation aleatoire (Ig)");
		
		mode =scannerGetNextInt(sc);
		}
		while (mode !=1 && mode !=2 && mode !=3);
		
		if(mode==1) {
			System.out.println("Choix 1");
			System.out.println("Creation d'instance If");
			
			mode1();
			 
			
			
		}
		else if(mode==2) {	
			System.out.println("Choix 2");
			System.out.println("Creation d'instance Ic");
		    
			String line;
			  
		    System.out.println("Entrez le nombre de machines m, le nombre de taches n et les durees di de chaque tache sous la forme suivante : "
		    		+ "m:n:d1:d2:...:dn");
		    line=sc.next();
		    
				System.out.println(line);
				String[] s = line.split(":"); // on coupe les durees de part les ":" 
				int[] tabElements = new int[s.length]; //tableau des taches
				tabElements[0] = Integer.parseInt(s[0]);
				tabElements[1] = Integer.parseInt(s[1]);
				System.out.println("nb machines = " +tabElements[0] + "\nnb taches =" +tabElements[1] );
				for(int i =2;i<s.length;i++) { 
					tabElements[i] = Integer.parseInt(s[i]);		//tabElements[i] liste des valeurs des taches 
					System.out.println( "les durees = " +tabElements[i]);
					
				}
                            int[] tab = null;
                                tab=recuperationDurees(tabElements);
                                lsa(tabElements[0],tab);
			affichageResultatsIfIc(tabElements[0],tab);
		}
		else{
			System.out.println("Choix 3");
			System.out.println("Creation d'instance Ig");
			int nbrmachines = -1; 
			int nbrtaches = -1;  
			int nbrinstances = -1;
			int dureesmin = -1;
			int dureesmax = -1;
			 
			System.out.println("Veuillez choisir le nombre d'instances k a produire : ");
			nbrinstances=scannerGetNextInt(sc);
			System.out.println("Choisir le nombre de machines m : ");
			nbrmachines=scannerGetNextInt(sc);
			System.out.println("Choisir le nombre de taches n : ");
			nbrtaches=scannerGetNextInt(sc);
			System.out.println("Choisir la duree minimale : ");
			dureesmin=scannerGetNextInt(sc);			
			System.out.println("Choisir la duree maximale : ");
			dureesmax=scannerGetNextInt(sc);			
			
			
			System.out.println("Pour chaque instance, voici la liste produite de la forme m:n:d1:d2:...:dn ");
			for(int i = 0; i<nbrinstances; i++) { //fonctionne mais pas le nombre taches avec valeurs randoms 
				System.out.println("\nInstance "+ (i+1) +" : ");
				int[] tabElements = new int[nbrtaches+2] ;
				tabElements[0]=nbrmachines; //fonctionne
				tabElements[1]=nbrtaches; //fonctionne 
					
                                for(int j=0; j<nbrtaches; j++) {
                                        int r =(int)( Math.random()*( dureesmax - dureesmin + 1 ) ) + dureesmin;					        
                                        tabElements[j+2]= r;                    
				}

                                     for(int y=0; y<tabElements.length; y++) {   
                                        System.out.print(tabElements[y] + ":");
                                     }
                                     int[] tab = null;
                                tab=recuperationDurees(tabElements);
                                lsa(tabElements[0],tab);
					affichageResultatsIg(nbrinstances,tabElements[0],tab);
		}
	}

}
	private static int scannerGetNextInt(Scanner sc) {
		while(sc.hasNextInt()==false) {
			System.out.println("Entrez un entier s'il vous plait");
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
				String[] s = line.split(":"); // on coupe les durees de part les ":" 
				int[] tabElements = new int[s.length]; //tableau des machines+ taches + duree
				tabElements[0] = Integer.parseInt(s[0]);
				tabElements[1] = Integer.parseInt(s[1]);
				System.out.println("nb machines = " +tabElements[0] + "\nnb taches =" +tabElements[1] );
				for(int i =2;i<s.length;i++) { 

					tabElements[i] = Integer.parseInt(s[i]);		//tabElements[i] liste des valeurs des taches 
					System.out.println( "les durees = " +tabElements[i]);
					
				}
                                int[] newTab = null;
                                newTab=recuperationDurees(tabElements);                                                     
                                lsa(tabElements[0],newTab);
                                affichageResultatsIfIc(tabElements[0], newTab);
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

        
        public static int[] recuperationDurees (int [] instance){
            
            int [] tabDurees = new int[instance.length];
            for (int i = 2; i<instance.length; i++){
                tabDurees[i]=instance[i];
            }
            //System.out.println(tabDurees);int machineNumber
            return tabDurees;
        }
        
	
	/**
     * Computes the max duration using the LSA algorithm.
     *
     * @param   int machineNumber   The number of machines.
     * @param   int tasks           The tasks
     *
     * @return  int The max duration.
     */
public static int lsa(int machineNumber, int [] tasks){
	    int duration = 0;
        /*
         * We'll store the current duration of each machine in an array.
         * If we have 2 machines, the machine 0 will have its tasks at the
         * index 0 of this array and the machine 1 at the index 1. Using
         * this simple logic, we can easily store the current duration of
         * every machine.
         */
        int[] machines = new int[machineNumber];

        //Look over all the tasks.
        //Find the machine the more available (that has the lowest duration)
        //Adds the current task.
        for (int i = 0; i < tasks.length; i++) {
            int availableMachineIndex = Utils.indexMin(machines);
            machines[availableMachineIndex] += tasks[i];
        }
        //Returns the maximum duration.
        return Utils.max(machines);
}

	

    public static int[] triBulleDecroissant(int tableau[]) {
               // int [] tableauTrie=null;
		int longueur = tableau.length;
		int tampon = 0;
		boolean permut;
 
		do {
			// hypothèse : le tableau est trié
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
				// Teste si 2 éléments successifs sont dans le bon ordre ou non
				if (tableau[i] < tableau[i + 1]) {
					// s'ils ne le sont pas, on échange leurs positions
					tampon = tableau[i];
					tableau[i] = tableau[i + 1];
					tableau[i + 1] = tampon;
					permut = true;
				}
                                //tableauTrie= tableau;
			}
		} while (permut);
                return tableau;
}

    
    public static int[] triBulleCroissant(int tableau[]) {
		int longueur = tableau.length;
		int tampon = 0;
		boolean permut;
 
		do {
			// hypothèse : le tableau est trié
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
				// Teste si 2 éléments successifs sont dans le bon ordre ou non
				if (tableau[i] > tableau[i + 1]) {
					// s'ils ne le sont pas, on échange leurs positions
					tampon = tableau[i];
					tableau[i] = tableau[i + 1];
					tableau[i + 1] = tampon;
					permut = true;
				}
			}
		} while (permut);
                return tableau;
	}
    
    
    

	/**
     * Computes the max duration using the LPT algorithm.
     *
     * @param   int machineNumber   The number of machines.
     * @param   int tasks           The tasks
     *
     * @return  int The max duration.
     */
        public static int lpt(int machineNumber, int [] tasks){

                   // order tasks in decreasing order
                    int [] tableauTrie = triBulleDecroissant(tasks);

                   return lsa(machineNumber, tableauTrie);
        }


	
	public static int myAlgo(int [] tab){
		int tempsMyAlgo=0;
                int[] tabCroissant;
                int[] tabDecroissant;
                int[] tabMyAlgo;
            // On part d'un tableau non triée : tab 
            //On la trie par ordre croissant : tabCroissant
            tabCroissant = triBulleCroissant(tab); 
            //On la trie par ordre décroissant : tabDecroissant
            tabDecroissant = triBulleDecroissant(tab); 
            
            //On ajoute à un nouveau tableau : tabCroissant[0] + tabDecroissant[0] + tabCroissant[1] + tabDecroissant[1] +... et on s'arrete à tabCroissant[i/2]=tabDecroissant[i/2]. 
            	
            
            
            
            
		return tempsMyAlgo;
	}
	
        public static int borneInfMaximum (int[] tab){
            int max= 0;
            
            for (int i = 0; i<tab.length; i++){
                if(tab[i]>max){
                    max = tab[i]; 
                }
            }
            return max;
        }
        
        
        public static int borneInfMoyenne (int machineNumber, int[] tab ){
            int moy = 0;
            int sum = 0 ; 
            for(int i = 0; i<tab.length; i++){
                sum += tab[i]; 
            }
            moy = sum/machineNumber; 
            return moy; 
        }
        
	
	public static void affichageResultatsIfIc( int machineNumber, int [] tasks ){
		System.out.println("Voici les resultats des calculs : " );
		System.out.println("Borne inferieure 'maximum' = " + borneInfMaximum(tasks));
		System.out.println("Borne inferieure 'moyenne' = " + borneInfMoyenne(machineNumber, tasks));
		System.out.println("Resultat LSA = " + lsa(machineNumber, tasks));
		System.out.println("Resultat LPT = " + lpt(machineNumber, tasks));
		System.out.println("Resultat myAlgo = ");
	}
	
	public static float ratioApproxMoyenLSA(int n){
            float ratio=0;
            
            
            for (int i =0; i<n; i++){
                
            }
            
            // pour chaque instance I, on prend le maximum entre la borne inférieure “maximum” et la borne inférieure“moyenne”. Appelons ce maximum M I .
            
            
            
            return ratio;
        }
        
        public static float ratioApproxMoyenLPT(){
            float ratio=0;
            
            return ratio;
        }
        
        public static float ratioApproxMoyenMyAlgo(){
            float ratio=0;
            
            return ratio;
        }
        
        
	public static void affichageResultatsIg(int nombreInstances, int machineNumber, int [] tasks ){
            
            File nomFichier = SelectionFichier.choixFichier();
            
            try {
          
            // creer le fichier s'il n'existe pas
            if (!nomFichier.exists()) {
             nomFichier.createNewFile();
            }
            FileWriter fw = new FileWriter(nomFichier.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
             for (int i = 0; i<nombreInstances; i++){
                String contentBorneInfMax = "Borne inferieure 'maximum' = " + borneInfMaximum(tasks);
                bw.write(contentBorneInfMax);
                String contentBorneInfMoy = "Borne inferieure 'moyenne' = " + borneInfMoyenne(machineNumber, tasks);		
                bw.write(contentBorneInfMoy);
                
                String contentResultatLSA = "Resultat LSA = " + lsa(machineNumber, tasks);
		bw.write(contentResultatLSA);
                String contentResultatLPT = "Resultat LPT = " + lpt(machineNumber, tasks);
		String contentResultatMyAlgo = "Resultat myAlgo = ";
            }
		//String contentRatioLSA = "ratio d’approximation moyen LSA = " + ratioApproxMoyenLSA();
                String contentRatioLPT = "ratio d’approximation moyen LPT = " + ratioApproxMoyenLPT();
                String contentRatioMyAlgo = "ratio d’approximation moyen MyAlgo = " + ratioApproxMoyenMyAlgo();
                
             // on ecrit dans le fichier
             //bw.write(contentRatioLSA);
             bw.write(contentRatioLPT);
             bw.write(contentRatioMyAlgo);
               
            bw.close();

            System.out.println("Modification terminee!");

           } catch (IOException e) {
            e.printStackTrace();
           }
 
           
	
	}
	
	
}