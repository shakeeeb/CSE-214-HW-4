
/**
 * HOMEWORK FOUR
 * @author Shakeeb Saleh
 * ID: 109239204
 * CUSTOMER OBJECT
 */
public class Customer {
	/**
	 * tmeleft is the amoun tof time remaining fot the customer to be dequeued
	 * time arrived is the arrival time of the customer
	 */
	private int timeLeft;
	private int timeArrived;
	
	// first, there has to be a chance that a customer will arrive
	// if so, then they are delegated to either a kiosk or a cashier
	// once delegated, they are enqueued
	/**
	 * default cosntructor
	 */
	public Customer(){}
	/**
	 * overloaded constructor
	 * @param arrival - time custoemr has arrived
	 */
	public Customer(int arrival){ // have to pass in currentTime
		this.timeArrived = arrival;
	}
	/**
	 * Oerloaded cosntructor
	 * @param arrival time customer arrived
	 * @param toBeServed time the cusotmer is to be served
	 */
	public Customer(int arrival, int toBeServed){// have to pass in currentTime and time tht comes from delegation
		this.timeArrived = arrival;
		this.timeLeft = toBeServed;
	}
	/**gettimeleft
	 *Returns the time left
	 */
	public int getTimeLeft(){
		return timeLeft;
	}
	/**getTimearrived
	 * returns the time arrived
	 */
	public int getTimeArrived(){
		return timeArrived;
	}
	/**
	 * service
	 * reduces the time left for teh customer to wait by one second
	 */
	public void service(){
		timeLeft--;
	}
	/**
	 * Tostring
	 * returns the object as a string
	 */
	public String toString(){
		String result = "";
		result = "(" + timeArrived + "," + timeLeft + ")";
		return result;
	}

}
