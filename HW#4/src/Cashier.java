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

public class Cashier 
{
	private CustomerQueue cashierQueue;
	private Kiosk kioskToFix;
	private boolean isFixingKiosk;
	private int timeToFix;
	
	/**
	 * Default Constructor for Cashier
	 * Creates a CustomerQueue cashierQueue
	 * Sets kioskToFix to null
	 * Sets isFixingKiosk to false
	 * Sets timeToFix to 0
	 */
	public Cashier()
	{
		cashierQueue = new CustomerQueue();
		kioskToFix = null;
		isFixingKiosk = false;
		timeToFix = 0;
	}
	
	/**
	 * Constructor for Cashier of CAPACITY capacity
	 * Creates a CustomerQueue cashierQueue
	 * Sets kioskToFix to null
	 * Sets isFixingKiosk to false
	 * Sets timeToFix to 0
	 * @param capacity the capacity of the queue
	 */
	public Cashier(int capacity)
	{
		cashierQueue = new CustomerQueue(capacity);
		kioskToFix = null;
		isFixingKiosk = false;
		timeToFix = 0;
	}
	
	/**
	 * If already fixing a Kiosk, throw Exception
	 * Set isFixingKiosk to true, timeToFix to seconds
	 * @param k the Kiosk to fix
	 * @param seconds the time needed to fix a Kiosk
	 */
	public void assignToKiosk(Kiosk k, int seconds)
	{
		if(isFixingKiosk)
			throw new AlreadyAssignedException("The cashier is already fixing a Kiosk");
		k.setStatus(true);
		isFixingKiosk = true;
		kioskToFix = k;
		timeToFix = seconds;
	}
	
	/**
	 * Gets the queue cashierQueue
	 * @return the array holding the queue cashierQueue
	 */
	public CustomerQueue getCashierQueue()
	{
		return cashierQueue;
	}
	
	/**
	 * Gets status of whether cashier is fixing kiosk
	 * @return whether cashier is assigned to kiosk
	 */
	public boolean getStatus()
	{
		return isFixingKiosk;
	}
	
	public int getTimeToFix()
	{
		return timeToFix;
	}
	
	/**
	 * If cashier is fixing a Kiosk, decrease time left to fix
	 * If timeToFix is 0, set isFixingKiosk to false, set Kiosk's isBroken status to false and kioskToFix to null
	 * If queue is not empty, decrement Customer's timeLeft
	 * If timeLeft is 0, dequeue the Customer and return Customer's timeSpent
	 * @return Customer's timeSpent if timeLeft is 0, return -1 otherwise
	 */
	public int act()
	{
		if(isFixingKiosk)
		{
			timeToFix--;
			if(!cashierQueue.isEmpty())
				cashierQueue.increaseAllTimeSpent();
			if(timeToFix == 0)
			{
				isFixingKiosk = false;
				kioskToFix.setStatus(false);
				kioskToFix = null;
				return 0;
			}
		}
		else if(!cashierQueue.isEmpty())
		{
			cashierQueue.peek().decrement();
			cashierQueue.increaseAllTimeSpent();
			if(cashierQueue.peek().getTimeLeft() == 0)
			{
				int timeSpent = cashierQueue.peek().getTimeSpent();
				cashierQueue.dequeue();
				return timeSpent;
			}
		}
		return -1;
	}
	
	/**
	 * Returns the Kiosk that Cashier is fixing
	 * @return the Kiosk being fixed by Cashier
	 */
	public Kiosk getKioskToFix()
	{
		return kioskToFix;
	}
}
