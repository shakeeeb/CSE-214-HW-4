import javax.swing.JOptionPane;
/**
 * HomeWork Four
 * @author Shakeeb Saleh
 * ID: 109239204
 * SIMULATOR OBJECT
 */

public class Simulator {
	/**
	 * Time is the global time variable that keeps track of time
	 * allKiosksAreBroken signifies when all kiosks are broken
	 */
	private int time = 0;
	private boolean allKiosksAreBroken = false;
	/**
	 * Number of cashiers and number of kiosks respectively
	 */
	private int num_cashiers;
	private int num_kiosks;
	/**
	 * the minimum and maximum times that a cashier will take for service
	 */
	private int min_cashier_time;
	private int max_cashier_time;
	/**
	 * minimum and maximum times a kiosk will take for service
	 */
	private int min_kiosk_time;
	private int max_kiosk_time;
	/**
	 * minimum and maximum times a kiosk will take for repair
	 */
	private int min_repair_time;
	private int max_repair_time;
	/**
	 * the probability that a customer will arrive
	 * the probbility that they will choose a kiosk
	 * and the probability that a kiosk will malfunction
	 */
	private double arrival_prob;
	private double kiosk_prob;
	private double malfunction_prob;
	/**
	 * Array of cashiers to hold the cashiers
	 * Arra for Kiosks to hold the kiosks
	 */
	private Cashier[] cashiers;
	private Kiosk[] kiosks;
	/**
	 * served holds the amount the staff serves by the end of the simulation
	 * orders holds the amount they are currently serving
	 * totaltimewaited it the time waitied in total
	 */
	private static int served;// will be calculated at the end
	private int orders;//will be used throughout
	private int totalTimeWaited;
	/**
	 * Default Constructor
	 */
	public Simulator(){
	}
	/**
	 * Simulate simulates what a normal day in the restaurant is like
	 * @param duration - how many seconds are within the simulation
	 * @return - the average wait for an order to be completed 
	 * (for soem reason this does not return as it's true answer, but rounded down. i have no idea why.)
	 */
	public double simulate(int duration){
		num_cashiers = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of cashiers"));
		num_kiosks = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of kiosks"));
		min_cashier_time = Integer.parseInt(JOptionPane.showInputDialog("What is the Minimum amount of time, in seconds,\n it should take for a Cashier to serve a customer?"));
		max_cashier_time = Integer.parseInt(JOptionPane.showInputDialog("What about the Maximum?"));
		RandomNumber.setCashierMinMax(min_cashier_time, max_cashier_time);
		min_kiosk_time = Integer.parseInt(JOptionPane.showInputDialog("What is the Minimum amount of time, in seconds,\n it should take for a Kiosk to serve a customer?"));
		max_kiosk_time = Integer.parseInt(JOptionPane.showInputDialog("What about the Maximum?"));
		RandomNumber.setKioskMinMax(min_kiosk_time, max_kiosk_time);
		min_repair_time = Integer.parseInt(JOptionPane.showInputDialog("What is the Minimum amount of time, in seconds,\n for a Cashier to fix a Kiosk?"));
		max_repair_time = Integer.parseInt(JOptionPane.showInputDialog("What about the Maximum?"));
		RandomNumber.setRepairMinMax(min_repair_time, max_repair_time);
		
		arrival_prob = Double.parseDouble(JOptionPane.showInputDialog("What is the chance that a customer will appear\n(between 1 and 0)"));
		kiosk_prob = Double.parseDouble(JOptionPane.showInputDialog("What is the chance that a customer will select a Kiosk over a Cashier?\n(between 1 and 0)"));
		malfunction_prob = Double.parseDouble(JOptionPane.showInputDialog("What is the chance that a Kiosk will malfunction?\n(between 1 and 0, should be low)"));
		
		BooleanProb.setArrivalProb(arrival_prob); 
		BooleanProb.setKioskProb(kiosk_prob);
		BooleanProb.setMalfunctionProb(malfunction_prob);		
		
		cashiers = new Cashier[num_cashiers];
		for(int i=0; i<num_cashiers; i++){
			cashiers[i] = new Cashier();
		}
		for(int i=0; i<num_cashiers-1; i++){
			cashiers[i].setPosition(i+1);
		}
		kiosks = new Kiosk[num_kiosks];
		for(int i=0; i<num_kiosks; i++){
			kiosks[i] = new Kiosk();
		}
		for(int i=0;i<num_kiosks-1; i++){
			kiosks[i].setPosition(i+1);
		}
		
		String timeUnitReport = "";
		while(time<=duration){
			timeUnitReport = "Time: "+ time+"\n"; 
			for(int i=0; i<num_cashiers; i++){
				cashiers[i].setCurrentTime(time);
			}
			for(int i=0; i<num_kiosks;i++){
				kiosks[i].setCurrentTime(time);
			}
			if(BooleanProb.customerArrives()){ // if a customer arrives
				if(BooleanProb.choosesKiosk()){// the customer has chosen a kiosk
					int num = RandomNumber.generateForKiosk();
					Customer tom = new Customer(time, num);
					int knum = RandomNumber.generate(0,(num_kiosks-1));
					Kiosk optimus = kiosks[knum];
					optimus.addCustomer(tom);
					timeUnitReport += "A customer has arrived and chooses Kiosk "+ knum +". Time to serve: " + num + "\n";
				} else { // the customer has chosen a cashier
					int wnum = RandomNumber.generateForCashier();
					Customer jane = new Customer(time, wnum);
					int cnum = RandomNumber.generate(0, num_cashiers-1);
					Cashier jesse = cashiers[cnum];
					if (jesse.isFixingKiosk()){ // if the cashier is busy, change cashiers
						if (jesse.getPosition() == 1){
							jesse = cashiers[jesse.getPosition()];
						} else if (jesse.getPosition()==num_cashiers){
							jesse = cashiers[jesse.getPosition()-1];
						} else {
							cnum = RandomNumber.generate(0, num_cashiers-1);
							jesse = cashiers[cnum];
						}
					}
					jesse.addCustomer(jane);
					timeUnitReport += "A customer has arrived and chooses Cashier "+ cnum +". Time to serve: " + wnum + "\n";
				}
				if(BooleanProb.malfunction()){ // if a customer arrives and a malfunction happens
					for(int i=0; i<num_kiosks; i++){
						if (!(kiosks[i].isBroken())){ // if there is a working kiosk
							allKiosksAreBroken = false;
							break; // then break
						}else{
							allKiosksAreBroken = true;
						}
					}
					int dnum = RandomNumber.generate(0, num_kiosks-1);
					Kiosk megaman = kiosks[dnum];
					if(megaman.isBroken()){ // assuming there are more than two kiosks and one is already broken
						if(megaman.getPosition()==1){
							dnum = megaman.getPosition();
							megaman = kiosks[dnum];
						} else if (megaman.getPosition() == num_kiosks){
							dnum = megaman.getPosition()-1;
							megaman = kiosks[dnum];
						} else {
							dnum = RandomNumber.generate(0, num_kiosks-1);
							megaman = kiosks[dnum];
						}
					}
					int mnum = RandomNumber.generate(0, num_cashiers-1);
					int rtime = RandomNumber.generateForRepair();
					Cashier casey = cashiers[mnum];
					megaman.Break();
					casey.assignToKiosk(megaman, rtime);
					timeUnitReport += "Kiosk "+ dnum + " has malfunctioned. Cashier "+ mnum +" is selected to fix it. Time to Fix:" + rtime + "\n";
				}
				
				for (int i=0;i<num_cashiers;i++){
					orders += cashiers[i].getServed();
					totalTimeWaited += cashiers[i].act();
					timeUnitReport += cashiers[i].toString()+" \n";
				}
				for (int i=0;i<num_kiosks;i++){
					orders += kiosks[i].getServed();
					totalTimeWaited += kiosks[i].act();
					timeUnitReport += kiosks[i].toString()+" \n";
				}
				timeUnitReport += "Customers served: "+ orders+ "\n";
				timeUnitReport += "Total Time waited: " + totalTimeWaited + "\n\n";
				
			} else {  // the case of a customer not arriving
				if(BooleanProb.malfunction()){ // a customer does not arrive and a kiosk malfunctions
					for(int i=0; i<num_kiosks; i++){
						if (!(kiosks[i].isBroken())){ // if there is a working kiosk
							allKiosksAreBroken = false;
							break; // then break
						}else{
							allKiosksAreBroken = true;
						}
					}
					if(allKiosksAreBroken) // if all kiosks are broken, then no point in a malfunction happening
						break;
					int dnum = RandomNumber.generate(0, num_kiosks-1);
					Kiosk megaman = kiosks[dnum];
					if(megaman.isBroken()){ // assuming there are more than two kiosks and one is already broken
						if(megaman.getPosition()==1){
							dnum = megaman.getPosition();
							megaman = kiosks[dnum];
						} else if (megaman.getPosition() == num_kiosks){
							dnum = megaman.getPosition()-1;
							megaman = kiosks[dnum];
						} else {
							dnum = RandomNumber.generate(0, num_kiosks-1);
							megaman = kiosks[dnum];
						}
					}
					int mnum = RandomNumber.generate(0, num_cashiers-1);
					int rtime = RandomNumber.generateForRepair();
					Cashier casey = cashiers[mnum];
					megaman.Break();
					casey.assignToKiosk(megaman, rtime);
					timeUnitReport += "Kiosk "+ dnum + " has malfunctioned. Cashier "+ mnum +" is selected to fix it. Time to Fix:" + rtime + "\n";
				}
				for (int i=0;i<num_cashiers;i++){
					orders += cashiers[i].getServed();
					totalTimeWaited += cashiers[i].act();
					timeUnitReport += cashiers[i].toString()+" \n";
				}
				for (int i=0;i<num_kiosks;i++){
					orders += kiosks[i].getServed();
					totalTimeWaited += kiosks[i].act();
					timeUnitReport += kiosks[i].toString()+" \n";
				}
				timeUnitReport += "Customers served: "+ orders+ "\n";
				timeUnitReport += "Total Time waited: " + totalTimeWaited + "\n\n";
			}
			// reset variables that need to be reset
			System.out.print(timeUnitReport);
			timeUnitReport = "";
			orders = 0;	
			time++;
		}// end of while loop
		

		for (int i=0;i<num_cashiers;i++){
			served += cashiers[i].getServed();
		}
		for (int i=0;i<num_kiosks;i++){
			served += kiosks[i].getServed();
		} if (served == 0){
			return 0;
		}
		double res = totalTimeWaited/served;
		return res;
	}
	/**
	 * Main Method, where a simulaiton takes place
	 * @param args
	 */
	public static void main(String[] args){
		int run_it = Integer.parseInt(JOptionPane.showInputDialog("How long would you like your simulation to run for?"));
		Simulator s = new Simulator();
		double average = s.simulate(run_it);
		String result = "" + served + " customers served. Average wait time: "+ average ;
		System.out.print(result);
		
	}
	

	

}
