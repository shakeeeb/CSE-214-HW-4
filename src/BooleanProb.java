
/**
 * HomeWork Four
 * @author Shakeeb saleh
 * ID: 109239204
 * basically a static boolean source program
 */
public class BooleanProb {
	/**
	 * arrivalprobability probability of a customer arriving
	 * kioskProbability probability of a kiosk being chosen over a cashier
	 * malfunciton probability probability of a kiosk malfunction
	 */
	private static double arrivalProbability;
	private static double kioskProbability;
	private static double malfunctionProbability;
	
	// the arrival of a customer
	/**
	 * setarrivalprob
	 * @param p - arrival probability
	 * @throws IllegalArgumentException
	 */
	public static void setArrivalProb(double p) throws IllegalArgumentException {
		if (p<0.0 || p>1.0)
			throw new IllegalArgumentException();
		arrivalProbability = p;
	}
	/**
	 * customerarrives
	 * Booleansource of whether based on probability, a customer arrives
	 * @return either true or false
	 */
	public static boolean customerArrives(){
		return (Math.random() < arrivalProbability);
	}
	
	// the probability that the customer will choose a kiosk
	/**
	 * setkioskprob
	 * probability that a customer will choose a kiosk
	 * @param p - probability
	 * @throws IllegalArgumentException
	 */
	public static void setKioskProb(double p) throws IllegalArgumentException {
		if (p<0.0 || p>1.0)
			throw new IllegalArgumentException();
		kioskProbability = p;
	}
	/**
	 * chooseskiosk
	 * booleansource for choice
	 * @return either ture or false
	 */
	public static boolean choosesKiosk(){
		return (Math.random() < kioskProbability);
	}
	
	// the probability that a kiosk will malfunction
	/**
	 * setmalfuncitonprob
	 * probability that kiosk malfuncitons
	 * @param p - probability
	 * @throws IllegalArgumentException
	 */
	public static void setMalfunctionProb(double p) throws IllegalArgumentException {
		if (p<0.0 || p>1.0)
			throw new IllegalArgumentException();
		kioskProbability = p;
	}
	/**
	 * malfucntion
	 * booleansource for malfucntion
	 * @return either true or false
	 */
	public static boolean malfunction(){
		return (Math.random() < kioskProbability);
	}

}
