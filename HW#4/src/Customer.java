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

public class Customer
{
	private int timeEntered;
	private int timeLeft;
	private int timeSpent;
	
	/**
	 * Default constructor for Customer
	 */
	public Customer()
	{
		
	}
	
	/**
	 * Constructor for customer that sets timeEntered and timeToServe to arguments, sets timeSpent to 0
	 * @param entered the time Customer arrives
	 * @param timeToServe the time required to serve Customer
	 */
	public Customer(int entered, int timeToServe)
	{
		timeEntered = entered;
		timeLeft = timeToServe;
		timeSpent = 0;
	}

	/**
	 * Reduces time left to serve Customer
	 */
	public void decrement()
	{
		timeLeft--;
	}
	
	/**
	 * Increases time Customer spent on line
	 */
	public void increaseTimeSpent()
	{
		timeSpent++;
	}
	
	/**
	 * Get time Customer arrived
	 * @return the time Customer arrived
	 */
	public int getTimeEntered()
	{
		return timeEntered;
	}
	
	/**
	 * Get time left for the Customer to be served
	 * @return the time left to finish serve the Customer
	 */
	public int getTimeLeft()
	{
		return timeLeft;
	}
	
	/**
	 * Gets the time the Customer spent on line
	 * @return the time Customer spent on line
	 */
	public int getTimeSpent()
	{
		return timeSpent;
	}
}
