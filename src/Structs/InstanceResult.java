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
	public InstanceResult(int maximumLowerEdge, int averageLowerEdge,
			int algoResult) {
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

}
