
/**
 * HomeWork Four
 * @author Shakeeb Saleh
 * ID: 109239204
 * CASHIER OBJECT
 */

public class Cashier {
	/**
	 * tobeFixed is the kiosk the cashier is currently working on
	 * fixing kiosk shows whether the cashier is int eh act of fixing or not
	 * fixing time is the time it takes for a kiosk to be fixed
	 * gotserved shows whether the cashier has served a customer this time unit
	 */
	private Kiosk toBeFixed = null;
	private boolean fixingKiosk = false;
	private int fixingTime = 0;
	private boolean gotServed;
	/**
	 * line is the customerQueue that holds the customers
	 * served is teh amount of customers served
	 * currenttime is the time at this time unit
	 * positioninarray is the position of this cashier in teh cashier array
	 */
	private CustomerQueue line = new CustomerQueue();
	private int served = 0;
	private int currentTime = 0;
	private int positionInArray = 0;
	/**
	 * default constructor
	 */
	public Cashier(){
	}
	/**assignToKiosk
	 * assigns a cashier to a kiosk
	 * @param k - kiosk in need of fixing
	 * @param seconds - how many seconds are needed to fix a kiosk
	 */
	public void assignToKiosk(Kiosk k, int seconds){
		fixingKiosk = true;
		fixingTime = seconds;
		toBeFixed = k;
	}
	/**
	 * isfixingkiosk
	 * shows wheter or not this cashier is busy fixing a kiosk
	 * @return
	 */
	public boolean isFixingKiosk(){
		return fixingKiosk;
	}
	/**
	 * getposition
	 * @return position in array
	 */
	public int getPosition(){
		return positionInArray;
	}
	/**
	 * act
	 * performs an action
	 * either servicing a customer or fixing a kiosk
	 * if neither, it idles
	 * @return the amount of time the customer has waited
	 */
	public int act(){
		if (fixingKiosk){
			if (fixingTime == 0){
				toBeFixed.Fix();
				fixingKiosk = false;
				return 0;
			} else {
				fixingTime--;
				return 0;
			}} else {
				if (!(line.isEmpty())){
					if (line.peek().getTimeLeft() != 0){
						line.peek().service();
						return 0;
					} else {
						int result = currentTime - line.peek().getTimeArrived();
						line.dequeue(); gotServed = true;
						served++; return result;
					}
				} return 0;
			} 
	}
	/**
	 * getServed
	 * @return served
	 */
	public int getServed(){
		return served;
	}
	/**
	 * addcustomer
	 * adds a customer onto the line
	 * @param c - custoemr to be added
	 */
	public void addCustomer(Customer c){
		line.enqueue(c);
	}
	/**
	 * set current time
	 * @param currentTime
	 */
	public void setCurrentTime(int currentTime){
		this.currentTime = currentTime;
	}
	/**
	 * setposition
	 * @param pos - sets position in the array
	 */
	public void setPosition(int pos){
		this.positionInArray = pos;
	}
	/**
	 * toString
	 * Returns the object as a string
	 */
	public String toString(){
		String result = "";
		result = "Cashier " + positionInArray + " :" + line.toString();
		if(gotServed){
			result += " One Customer Served this time unit";
			gotServed = false;
		}
		return result;
	}
	
}


