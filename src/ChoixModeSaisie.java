import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Algo.FileSelection;
import Algo.Utils;
import Structs.InstanceResult;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class ChoixModeSaisie {

	public static void main(String[] args){
		// Below the same example as Cours3. It should return 15.
		//int machineNumber = 3;
		//int[] tasksImpair = {2,7,1,3,6,2,3,2,5};
                //int[] tasksPair = {2,7,1,3,2,6,2,3};
		/*System.out.println("LSA: " + lsa(machineNumber, tasks));
        System.out.println("LPT: " + lpt(machineNumber, tasks));*/

		menu();
                
                //System.out.println("My aglo pair = " +myAlgo(tasksPair));
                //System.out.println("My aglo impair = " +myAlgo(tasksImpair));


	}

        /**
         *Method which manages 3 modes of seizure of the user
         * 
         */
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
			String[] s = line.split(":"); //We cut durations with the ":" 
			int[] tabElements = new int[s.length]; //table of tasks 
			tabElements[0] = Integer.parseInt(s[0]);
			tabElements[1] = Integer.parseInt(s[1]);
			System.out.println("Nombre de machines = " +tabElements[0] + "\nNombre de taches =" +tabElements[1] );
			for(int i =2;i<s.length;i++) { 
				tabElements[i] = Integer.parseInt(s[i]); //tabElements[i] is the list of values of tasks   
				System.out.println( "Duree de la tache " +i+ " = " +tabElements[i]);

			}
			int[] tab = null;
			tab=recuperationDurations(tabElements);
			lsa(tabElements[0],tab);
			displayResultsIfIc(tabElements[0],tab);
		}
		else{
			System.out.println("Choix 3");
			System.out.println("Creation d'instance Ig");
			int machinesNumber = -1; 
			int tasksNumber = -1;  
			int instancesNumber = -1;
			int minDuration = -1;
			int maxDuration = -1;

			System.out.println("Veuillez choisir le nombre d'instances k a produire : ");
			instancesNumber=scannerGetNextInt(sc);
			System.out.println("Choisir le nombre de machines m : ");
			machinesNumber=scannerGetNextInt(sc);
			System.out.println("Choisir le nombre de taches n : ");
			tasksNumber=scannerGetNextInt(sc);
			System.out.println("Choisir la duree minimale : ");
			minDuration=scannerGetNextInt(sc);			
			System.out.println("Choisir la duree maximale : ");
			maxDuration=scannerGetNextInt(sc);			
			System.out.println("Choisir le fichier ou ecrire : ");
			File fileName = FileSelection.fileChoice();	

			System.out.println("Pour chaque instance, voici la liste produite dans le fichier souhaite sous la forme m:n:d1:d2:...:dn ");


			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(fileName, true);
				bw = new BufferedWriter(fw);
			} catch (IOException e) {				
				e.printStackTrace();
				System.out.println("Erreur d'ecriture");
				System.exit(1);
			}

			InstanceResult [] myInstancesTabResult = new InstanceResult [instancesNumber];
                        int[] tabElements = new int[tasksNumber] ;

			for(int i = 0; i<instancesNumber; i++) { 
				System.out.println("\nInstance "+ (i+1) +" : ");
		
				System.out.print(machinesNumber + ":" + tasksNumber + ":");

				for(int j=0; j<tasksNumber; j++) {
					int r =(int)( Math.random()*( maxDuration - minDuration + 1 ) ) + minDuration;					        
					tabElements[j]= r; 
					if (j<tasksNumber-1){
						System.out.print(r + ":");
					}
					else
						System.out.println(r);
				}

				int [] tab = tabElements;

				int algoResultLSA = lsa(machinesNumber,tab);
	
                             
                                    InstanceResult instanceResult = new InstanceResult(maxLowerBorder(tab), averageLowerBorder(machinesNumber, tab), algoResultLSA);
                                    myInstancesTabResult[i] = instanceResult;
                                    
                                    
                                
			}
			
                        float ratio = InstanceResult.rIAverage(myInstancesTabResult);
                        displayResultsIg(instancesNumber,machinesNumber, tabElements, bw, ratio) ;
                        
                        try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                        
                        
		}

	}
        
        /**
         * Method which sends back an error if the user enter something else than an integer
         * 
         * @param sc scanner who permetted to keep the value enter by the user  
         * @return 
         */
	private static int scannerGetNextInt(Scanner sc) {
		while(sc.hasNextInt()==false) {
			System.out.println("Veuillez entrer un entier s'il vous plait.");
			sc.nextLine();
		}

		return sc.nextInt();
	}

        /**
         * Method who define the first mode of seizure who permitted the user to choose a file who contain all the values 
         */
	public static void mode1(){
		File fileName = FileSelection.fileChoice();		

		BufferedReader r = null;
		try {
			r = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = null;
		try {
			while ((line=r.readLine()) != null){
				System.out.println(line);
				String[] s = line.split(":"); //We cut durations with the ":" 
				int[] tabElements = new int[s.length]; //Table containing machines number + number of tasks  + durations
				tabElements[0] = Integer.parseInt(s[0]);
				tabElements[1] = Integer.parseInt(s[1]);
				System.out.println("Nombre de machines = " +tabElements[0] + "\nNombre de taches =" +tabElements[1] );
				for(int i =2;i<s.length;i++) { 

					tabElements[i] = Integer.parseInt(s[i]); //tabElements[i] list of values of tasks  
					System.out.println( "Duree de la tache " +i+ " = " +tabElements[i]);

				}
				int[] newTab = null;
				newTab=recuperationDurations(tabElements);                                                     
				lsa(tabElements[0],newTab);
				displayResultsIfIc(tabElements[0], newTab);
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


        
        /**
         * Method that removes from the table the number of machines and the number of tasks to keep only the duration of the tasks
         * 
         * @param instance
         * @return int[]
         */
	public static int[] recuperationDurations (int [] instance){
		int [] tabDurations = new int[instance.length];
		for (int i = 2; i<instance.length; i++){
			tabDurations[i]=instance[i];
		}
		return tabDurations;
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

        /**
         * Method that returns the values of a table in decreasing order
         * 
         * @param table
         * @return int[] a table with decreasing values
         */
	public static int[] sortingDecreasingBubble(int table[]) {
		// int [] tableauTrie=null;
		int longueur = table.length;
		int buffer = 0;
		boolean permut;
		do {
			// Hypothesis: the table is sorted out
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
                                    //Verify if 2 successive elements are in the good order or not
				if (table[i] < table[i + 1]) {
					// If they aren't in the good order we change their positions
					buffer = table[i];
					table[i] = table[i + 1];
					table[i + 1] = buffer;
					permut = true;
				}
			}
		} while (permut);
		return table;
	}

        
        /**
         * Method that returns the values of a table in increasing order
         * 
         * @param table
         * @return int[] a table with decreasing values
         */
	public static int[] sortingIncreasingBubble(int table[]) {
		int longueur = table.length;
		int buffer = 0;
		boolean permut;
		do {
			// Hypothesis: the table is sorted out
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
                                    //Verify if 2 successive elements are in the good order or not
                                    if (table[i] > table[i + 1]) {
					// If they aren't in the good order we change their positions
					buffer = table[i];
					table[i] = table[i + 1];
					table[i + 1] = buffer;
					permut = true;
				}
			}
		} while (permut);
		return table;
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
		int [] sortedTable = sortingDecreasingBubble(tasks);
		return lsa(machineNumber, sortedTable);
	}


        /**
         * Computes the max duration using the myAlgo algorithm that we have created
         * 
         * @param machineNumber
         * @param tab
         * @return int
         */
	public static int myAlgo(int machineNumber, int [] tab){
		int[] increasingTable;
		int[] decreasingTable;
		int[] resultTabMyAlgo = new int[tab.length];
		// We start from a unsorted table : tab 
		//We sort with increasing order : increasingTable
		increasingTable = sortingIncreasingBubble(tab); 
		//We sort with decreasing order: decreasingTable
		decreasingTable = sortingDecreasingBubble(tab); 

		//We add to a new table : increasingTable[0] + decreasingTable[0] + increasingTable[1] + decreasingTable[1] +... and we stop at the middle of the two sorted tables. 
		for (int i = 0; i < resultTabMyAlgo.length ; i++) {
                    if(tab.length % 2 == 0) {
			for(int j=0; j<increasingTable.length/2; j++){
                            for(int y=0; y<decreasingTable.length/2; y++){
                             //  int availableMachineIndex = Utils.indexMin(tab);
                            resultTabMyAlgo[i] += increasingTable[j];
                            resultTabMyAlgo[i+1] += decreasingTable[y];
                            }
                        }
                        System.out.println(resultTabMyAlgo[i]);
                    }   
                    else {
			for(int j=0; j<increasingTable.length/2 +1; j++){
                            for(int y=0; y<decreasingTable.length/2-1; y++){
                              //  int availableMachineIndex = Utils.indexMin(tab);
                            resultTabMyAlgo[i] += increasingTable[j];
                            resultTabMyAlgo[i+1] += decreasingTable[y];
                           
                            }
                        }
                         System.out.println(resultTabMyAlgo[i]);
		}
			
		}
		
		//return Utils.max(resultTabMyAlgo);  
                return lsa(machineNumber, resultTabMyAlgo);
	}


        /**
         * Computes the maximum lower border of a table
         * 
         * @param tab
         * @return int
         */
	public static int maxLowerBorder (int[] tab){
		int max= 0;
		for (int i = 0; i<tab.length; i++){
			if(tab[i]>max){
				max = tab[i]; 
			}
		}
		return max;
	}


        /**
         * Computes the average lower border of a table
         * 
         * @param machineNumber
         * @param tab
         * @return int
         */
	public static int averageLowerBorder (int machineNumber, int[] tab ){
		int moy = 0;
		int sum = 0 ; 
		for(int i = 0; i<tab.length; i++){
			sum += tab[i]; 
		}
		moy = sum/machineNumber; 
		return moy; 
	}


        /**
         * Display results of the mode 1 and 2 
         * 
         * @param machineNumber
         * @param tasks 
         */
	public static void displayResultsIfIc( int machineNumber, int [] tasks ){
		System.out.println("Voici les resultats des calculs : " );
		System.out.println("Borne inferieure 'maximum' = " + maxLowerBorder(tasks));
		System.out.println("Borne inferieure 'moyenne' = " + averageLowerBorder(machineNumber, tasks));
		System.out.println("Resultat LSA = " + lsa(machineNumber, tasks));
		System.out.println("Resultat LPT = " + lpt(machineNumber, tasks));
		System.out.println("Resultat myAlgo = " + myAlgo( machineNumber, tasks));
	}


        /**
         * Display results of the mode 3
         * 
         * @param instancesNumber
         * @param machineNumber
         * @param tasks
         * @param bw
         * @param ratio 
         */
	public static void displayResultsIg(int instancesNumber, int machineNumber, int [] tasks , BufferedWriter bw, float ratio){
		try {
			String content = "";
                        String separator = "==================================================";
			for (int i = 0; i<instancesNumber; i++){
                                content += "Instance numéro "+ i +" :\n";
				content += "Borne inferieure 'maximum' = " + maxLowerBorder(tasks) +"\n"+ "\n";
				content += "Borne inferieure 'moyenne' = " + averageLowerBorder(machineNumber, tasks)+"\n"+"\n";
				content += "Resultat LSA = " + lsa(machineNumber, tasks)+"\n"+ "\n";
				content += "Resultat LPT = " + lpt(machineNumber, tasks)+"\n"+ "\n";
				content += "Resultat myAlgo = " + myAlgo( machineNumber, tasks) +"\n"+ separator +"\n";				
			}
                        
			String contentRatioLSA = "ratio d’approximation moyen LSA = " +  ratio +"\n";
			String contentRatioLPT = "ratio d’approximation moyen LPT = " + ratio +"\n";
			String contentRatioMyAlgo = "ratio d’approximation moyen MyAlgo = " +  ratio +"\n";

			// We write in the file
			bw.write(content);
			bw.write(contentRatioLSA);
			bw.write(contentRatioLPT);
			bw.write(contentRatioMyAlgo);
                        
			System.out.println("Modification du fichier terminee !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}