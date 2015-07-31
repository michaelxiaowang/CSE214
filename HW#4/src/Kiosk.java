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

public class Kiosk 
{
	private CustomerQueue kioskQueue;
	private boolean isBroken;
	private int timeBroken;
	
	/**
	 * Constructor for Kiosk
	 * Creates CustomerQueue kioskQueue
	 * Sets isBroken to false
	 */
	public Kiosk()
	{
		kioskQueue = new CustomerQueue();
		isBroken = false;
	}
	
	/**
	 * Constructor for KioskCashier of CAPACITY capacity
	 * Creates a kioskQueue kioskQueue
	 * Creates CustomerQueue kioskQueue
	 * Sets isBroken to false
	 * @param capacity the capacity of the queue
	 */
	public Kiosk(int capacity)
	{
		kioskQueue = new CustomerQueue(capacity);
		isBroken = false;
	}
	
	/**
	 * Sets isBroken to b
	 * @param b the truth value for whether Kiosk is broken
	 */
	public void setStatus(boolean b)
	{
		isBroken = b;
	}
	
	/**
	 * If isBroken is false and kioskQueue is not empty
	 * Decrease Customer's timeLeft and if it is 0, dequeue the Customer and return Customer's timeSpent
	 * If Customer's timeLeft is not 0, return -1
	 * @return the Customer's timeSpent if Customer's timeLeft is 0, -1 otherwise
	 */
	public int act()
	{
		if(!isBroken)
		{
			if(!kioskQueue.isEmpty())
			{
				kioskQueue.peek().decrement();
				kioskQueue.increaseAllTimeSpent();
				if(kioskQueue.peek().getTimeLeft() == 0)
				{
					int timeSpent = kioskQueue.peek().getTimeSpent();
					kioskQueue.dequeue();
					return timeSpent;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Gets the queue kioskQueue
	 * @return the array holding the queue kioskQueue
	 */
	public CustomerQueue getKioskQueue()
	{
		return kioskQueue;
	}
	
	/**
	 * Gets whether the Kiosk is broken
	 * @return whether Kiosk is broken
	 */
	public boolean getStatus()
	{
		return isBroken;
	}
	
	/**
	 * Gets the time Kiosk broke
	 * @return time Kiosk broke
	 */
	public int getTimeBroken()
	{
		return timeBroken;
	}
	
	/**
	 * Sets the time Kiosk is broken
	 * @param t the time Kiosk breaks
	 */
	public void setTimeBroken(int t)
	{
		timeBroken = t;
	}
}
