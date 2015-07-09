
/**
 * Homework Four
 * @author shakeeb saleh
 * ID: 109239204
 * RANDOM NUMBER GENERATION BETWEEN GIVEN RANGES
 */
public class RandomNumber {
	/**
	 * minimum tiem for repairs
	 * maximum time for reparis
	 */
	private static int repairMin;
	private static int repairMax;
	/**
	 * mimumm time it takes for a cashier to finish an order
	 * an maximum of teh same
	 */
	private static int cashierMin;
	private static int cashierMax;
	/**
	 * minimum time it takes for a kiosk to serve you
	 * and maximum time for teh same
	 */
	private static int kioskMin;
	private static int kioskMax;
	/**
	 * repeated msicellanous numbers needing to be generated
	 * max and min
	 */
	private static int rMiscMin;// repeated miscellaneous
	private static int rMiscMax;
	/**
	 *setters for everyhting
	 */
	public static void setRepairMinMax(int min, int max){ // time it takes on avg to repair a kiosk
		repairMin = min;
		repairMax = max;
	}
	
	public static void setCashierMinMax(int min, int max){ // time it takes on avg for a cashier to serve a customer
		cashierMin = min;
		cashierMax = max;
	}
	
	public static void setKioskMinMax(int min, int max){ // time it takes for a kiosk to serve a customer
		kioskMin = min;
		kioskMax = max;
	}
	
	public static void setRMiscMinMax(int min, int max){ // repeated misc with certain values
		rMiscMin = min;
		rMiscMax = max;
	}
	/**
	 * Generators for everyhting
	 * rturns a random value within the given range
	 * @return
	 */
	public static int generateForRepair(){
		return (int)(repairMin + (Math.random()*(repairMax - repairMin)));
	}
	
	public static int generateForCashier(){
		return (int)(cashierMin + (Math.random()*(cashierMax - cashierMin)));
	}
	
	public static int generateForKiosk(){
		return (int)(kioskMin + (Math.random()*(kioskMax - kioskMin)));
	}
	
	public static int generateForRMisc(){
		return (int)(rMiscMin + (Math.random()*(rMiscMax - rMiscMin)));
	}
	/**
	 * includes random generator to create whatever neccessary ranges
	 * @param min
	 * @param max
	 * @return
	 */
	public static int generate(int min, int max){
		return (int)(Math.random()*(max+1) + min);
	}

}
