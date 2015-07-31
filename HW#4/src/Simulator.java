/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 4
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

import java.util.Scanner;

public class Simulator
{
	//Static variables for simulator class
	static int num_cashiers = 5;
	static int num_kiosks = 5;
	static int min_cashier_time = 3;
	static int max_cashier_time = 5;
	static int min_kiosk_time = 1;
	static int max_kiosk_time = 3;
	static int min_repair_time = 5;
	static int max_repair_time = 9;
	static int simulationTime = 100;
	static double arrival_prob = 1.0;
	static double kiosk_prob = .5;
	static double malfunction_prob = .01;
	static Cashier[] cashiers;
	static Kiosk[] kiosks;
	static int orders;
	static int totalTimeWaited;
	
	public static void main(String[] args)
	{	
		Scanner scan = new Scanner(System.in); //Creates scanner for input
		
		/**
		 * Prompts input for static variables.
		 * If input is invalid, catch Exception e.
		 * Prompt for input again.
		 */
		boolean validData = false;
		while(validData == false)
		{
			try
			{
				System.out.print("Enter the number of cashiers: ");
				num_cashiers = scan.nextInt();
				scan.nextLine();
				System.out.print("Enter the number of kiosks: ");
				num_kiosks = scan.nextInt();
				scan.nextLine();
				System.out.print("Enter the minimum cashier service time: ");
				min_cashier_time = scan.nextInt();
				scan.nextLine();
				System.out.print("Enter the maximum cashier service time: ");
				max_cashier_time = scan.nextInt();
				scan.nextLine();
				System.out.print("Enter the minimum kiosk service time: ");
				min_kiosk_time = scan.nextInt();
				scan.nextLine();
				System.out.print("nter the maximum kiosk service time: ");
				max_kiosk_time = scan.nextInt();
				scan.nextLine();
				System.out.print("Enter the minimum repair time: ");
				min_repair_time = scan.nextInt();
				scan.nextLine();
				System.out.print("Enter the maximum repair time: ");
				max_repair_time = scan.nextInt();
				scan.nextLine();
				System.out.print("Enter the arrival probability: ");
				arrival_prob = scan.nextDouble();
				scan.nextLine();
				System.out.print("Enter the probability for customers to use a kiosk: ");
				kiosk_prob = scan.nextDouble();
				scan.nextLine();
				System.out.print("Enter the probability of kiosk malfunction: ");
				malfunction_prob = scan.nextDouble();
				scan.nextLine();
				System.out.print("Enter the duration of the simulation: ");
				simulationTime = scan.nextInt();
				scan.nextLine();
				validData = true;
			}
			catch(Exception e)
			{
				System.out.println("One or more of the variables are invalid. Please input data again.");
				validData = false;
				scan.nextLine();
			}
		}
		System.out.print("\n");
		
		/**
		 * Creates Cashier[] and Kiosk[] with length num_cashiers and num_kiosks with each element's queue having CAPACITY = simulationTime
		 */
		cashiers = new Cashier[num_cashiers];
		kiosks = new Kiosk[num_kiosks];
		for(int i = 0; i < num_cashiers; i++)
		{
			cashiers[i] = new Cashier(simulationTime);
		}
		
		for(int i = 0; i < num_kiosks; i++)
		{
			kiosks[i] = new Kiosk(simulationTime);
		}
		
		
		double avrgWaitTime = simulate(simulationTime);
		System.out.print(orders + " customers served. Average waiting time was " + avrgWaitTime + " seconds per customer.");
		
		
		scan.close(); //Closes scanner
	}
	
	/**
	 * 
	 * @param simulationTime
	 * @return
	 */
	public static double simulate(int simulationTime)
	{
		orders = 0;
		totalTimeWaited = 0;
		int timeCounter = 1;
		int timeToFix = 0;
		int kioskToFix = 0;
		double probability;
		int timeToServe = 0;
		boolean printNowFixed = false;
		
		while(timeCounter <= simulationTime)//Runs simulation for given time
		{
			String printString = "";//Keeps track of what to print
			System.out.println("Time = " + timeCounter);//Prints current time
			
			/**
			 * Checks to see if customer arrives.
			 * If customer arrives, checks to see if customer picks a kiosk, assign random kiosk.
			 * Else if customer picks a cashier, assign random cashier
			 * Otherwise print no customers arrive.
			 */
			probability = Math.random();
			if(probability < arrival_prob)
			{
				probability = (Math.random());
				{
					if(probability < kiosk_prob)
					{
						probability = (int)(Math.random()*kiosks.length);
						timeToServe = (int)(Math.random()*(max_kiosk_time - min_kiosk_time +1) + min_kiosk_time);
						if(kiosks[(int)probability]!=null)
							kiosks[(int)probability].getKioskQueue().enqueue(new Customer(timeCounter, timeToServe));
						System.out.print("A customer arrives and chooses kiosk " + (int)(probability+1) + ". Time to serve = " + timeToServe + "\n");
					}
					
					else
					{
						probability = (int)(Math.random()*cashiers.length);
						timeToServe = (int)(Math.random()*(max_cashier_time - min_cashier_time +1) + min_cashier_time);
						if(cashiers[(int)probability]!=null)
						cashiers[(int)probability].getCashierQueue().enqueue(new Customer(timeCounter, timeToServe));
						System.out.print("A customer arrives and chooses cashier " + (int)(probability+1) + ". Time to serve = " + timeToServe + "\n");
					}
				}
			}
			else
			{
				System.out.println("No customers arrive.");
			}
			
			/**
			 * If all kiosks broken, probability to break = 0
			 * Check to see if kiosk breaks
			 * If probability says kiosk breaks, find a non-broken kiosk to break
			 * Assign cashier to broken kiosk
			 */
			int numKiosksBroken = 0;
			probability = Math.random();
			for(int i = 0; i < kiosks.length; i++)
			{
				if(kiosks[i] != null && kiosks[i].getStatus())
					numKiosksBroken++;
			}
			if(numKiosksBroken == num_kiosks)
				probability = probability+1;
			if(probability < malfunction_prob)
			{
				boolean kioskHasBroken = false;
				while(kioskHasBroken == false)
				{
					kioskToFix = (int)(Math.random()*kiosks.length);
					timeToFix = (int)(Math.random()*(max_repair_time - min_repair_time) + min_repair_time);
					if(!kiosks[kioskToFix].getStatus())
						kioskHasBroken = true;
				}
				boolean assigned = false;
				while(assigned == false)
				{
					try
					{
						probability = (int)(Math.random()*cashiers.length);
						cashiers[(int)probability].assignToKiosk(kiosks[kioskToFix], timeToFix);
						assigned = true;
						kiosks[kioskToFix].setTimeBroken(timeCounter);
					}
					catch(Exception e)
					{
						//Do nothing
					}
				}
				System.out.println("Kiosk " + (kioskToFix + 1) + " malfunctions. Cashier " + (int)(probability + 1) + " is selected to fix it. Time to fix = " + timeToFix);
			}
			
			/**
			 * Cashier acts if his queue is not empty, or cashier is fixing a machine.
			 * Precondition: The customer or machine did not enter or break at the same time cashier acts.
			 * If customer has been served, increment orders and add to totalTimeWaited.
			 */
			for(int i = 0; i < cashiers.length; i++)
			{
				int timeSpent = -1;
				if((!cashiers[i].getCashierQueue().isEmpty() && cashiers[i].getCashierQueue().peek().getTimeEntered() != timeCounter) || ((cashiers[i].getKioskToFix() != null) && (cashiers[i].getKioskToFix().getTimeBroken() != timeCounter)))
					timeSpent = cashiers[i].act();
				
				if(timeSpent != -1)
				{
					if(timeSpent == 0)
					{
						printString += "Cashier " + (i+1) + ": " + cashiers[i].getCashierQueue().toString() + " Done Fixing." + "\n";
						printNowFixed = true;
					}
					else
					{
						printString += "Cashier " + (i+1) + ": " + cashiers[i].getCashierQueue().toString();
						if(cashiers[i].getCashierQueue().toString().equals(""))
							printString += "One customer served this time unit" + "\n";
						else
							printString += " One customer served this time unit" + "\n";
						totalTimeWaited += timeSpent;
						orders++;
					}
				}
				else
				{
					if(!cashiers[i].getStatus())
						printString += "Cashier " + (i+1) + ": " + cashiers[i].getCashierQueue().toString() + "\n";
					else
						printString += "Cashier " + (i+1) + ": " + cashiers[i].getCashierQueue().toString() + " Busy for " + cashiers[i].getTimeToFix() + " more seconds." + "\n";
				}
			}
			
			/**
			 * Kiosk acts if queue is not empty and customer did not enter at same time kiosk acts.
			 * If customer has been served, increment orders and add to totalTimeWaited.
			 */
			for(int i = 0; i < kiosks.length; i++)
			{
				int timeSpent = -1;
				if(!kiosks[i].getKioskQueue().isEmpty() && kiosks[i].getKioskQueue().peek().getTimeEntered() != timeCounter)
					timeSpent = kiosks[i].act();
				
				if(timeSpent != -1)
				{
					printString += "Kiosk " + (i+1) + ": " + kiosks[i].getKioskQueue().toString();
					if(kiosks[i].getKioskQueue().toString().equals(""))
						printString += "One customer served this time unit" + "\n";
					else
						printString += " One customer served this time unit" + "\n";
					totalTimeWaited += timeSpent;
					orders++;
				}
				else
				{
					if(!kiosks[i].getStatus())
					{
						if(printNowFixed)
						{
							printString += "Kiosk " + (i+1) + ": " + kiosks[i].getKioskQueue().toString() + " Now Fixed." + "\n";
							printNowFixed = false;
						}
						else
							printString += "Kiosk " + (i+1) + ": " + kiosks[i].getKioskQueue().toString() + "\n";
					}
					else
						printString += "Kiosk " + (i+1) + ": " + kiosks[i].getKioskQueue().toString() + " Broken" + "\n";
				}
			}
			
			
			/**
			 * Prints record of this instance of simulation, and go to next instance.
			 */
			printString += "Customers served: " + orders + "\n";
			printString += "Total time waited: " + totalTimeWaited + "\n";
			System.out.println(printString);
			System.out.print("\n");
			timeCounter++;
		}
		
		/**
		 * Try to return average wait time, catch error if orders is 0, no customers have been served, so return 0
		 */
		try
		{
			return totalTimeWaited / orders;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
}
