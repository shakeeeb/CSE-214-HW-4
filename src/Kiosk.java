

/**
 * HomeWork Four
 * @author Shakeeb saleh
 * ID: 109239204
 * KIOSK OBJECT
 */
public class Kiosk {
	/**
	 * line is the customerQueue used to hold customers
	 * borken is the status of the kiosk, if tis working or broken
	 * served is the amount of customers served
	 * current time is the current time
	 * positioninarray is the position in array of kiosks
	 * gotserved tellls whether this kiosk has completed an order this time unit
	 */
	private CustomerQueue line = new CustomerQueue();
	private boolean broken;
	private int served = 0;
	private int currentTime = 0;
	private int positionInArray = 0;
	private boolean gotServed;
	/**
	 * default osntructor
	 */
	public Kiosk(){
	}
	/**
	 * break
	 * breaks the kiosk
	 */
	public void Break(){
		broken = true;
	}
	/**
	 * fix
	 * fixes the kiosk
	 */
	public void Fix(){
		broken = false;
	}
	/**
	 * act
	 * performs an action
	 * services or serves a customer
	 * @return the wait time for service
	 */
	public int act(){ // count how many served
		if (!broken){ 
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
	} return 0;
	}
	/**
	 * get served
	 * @return served
	 */
	public int getServed(){
		return served;
	}
	/**
	 * isBroken
	 * @return broken or not broken
	 */
	public boolean isBroken(){
		return broken;
	}
	/**
	 * returns postion in array
	 * @return
	 */
	public int getPosition(){
		return positionInArray;
	}
	/**
	 * addcustomer
	 * adds a customer to the line
	 * @param c -customer to eb added to line
	 */
	public void addCustomer(Customer c){
		line.enqueue(c);
	}
	/**
	 * sets current time
	 * @param currentTime
	 */
	public void setCurrentTime(int currentTime){
		this.currentTime = currentTime;
	}
	/**
	 * makes the kiosk aware of its position
	 * @param pos
	 */
	public void setPosition(int pos){
		this.positionInArray = pos;
	}
	/**
	 * returns the kiosk as a string value
	 */
	public String toString(){
		String result = "";
		result = "Kiosk " + positionInArray + " :" + line.toString();
		if (gotServed){
			result += " One Customer Served this time unit";
			gotServed = false;
		}
		return result;
	}

}
