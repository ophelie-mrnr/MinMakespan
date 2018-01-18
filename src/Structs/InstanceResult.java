package Structs;

public class InstanceResult {
	/**
	 * The maximum lower edge
	 */
	private int maximumLowerEdge;
	
	/**
	 * The average lower edge
	 */
	private int averageLowerEdge;
	/**
	 * The algo result
	 */
	private int algoResult;
	
	
	/**
	 * Instantiates a new InstanceResult
	 * 
	 * @param maximumLowerEdge
	 * @param averageLowerEdge
	 * @param algoResult
	 */
	public InstanceResult(int maximumLowerEdge, int averageLowerEdge, int algoResult) {
		this.maximumLowerEdge = maximumLowerEdge;
		this.averageLowerEdge = averageLowerEdge;
		this.algoResult = algoResult;
	}
	
	private int mI()
	{
		return Math.max(this.averageLowerEdge, this.maximumLowerEdge);
	}
	
	public float rI(){
		return algoResult/this.mI();
	}

        /**
	 * Computes the average of all rI using an array of instance results; 
	 * 
	 * @param InstanceResult [] instances   An array contening all the instances used to compute the rI average 
         * 
         * @return float   The average of rI
	 */
        public static float rIAverage(InstanceResult [] instances){
            float average = 0; 
            for(int i=0; i<instances.length; i++){
                average +=instances[i].rI(); //computes the rI and adds it to average
            }
            average /=instances.length; 
            return average; 
        } 
}
