/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 7
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

public class RaceResult 
{
	private String model;
	private double[] times;
	
	/**
	 * Default constructor of RaceResult.
	 */
	public RaceResult()
	{
		
	}
	
	/**
	 * Constructor of RaceResult that sets name and array of times.
	 * @param s the name of the car
	 * @param times the array that contains the times of the car
	 */
	public RaceResult(String s, double[] t)
	{
		model = s;
		times = t;
	}
	
	/**
	 * Sets model to String s
	 * @param s the new model of car
	 */
	public void setModel(String s)
	{
		model = s;
	}
	
	/**
	 * Sets times to array t
	 * @param t the new array of times
	 */
	public void setTimes(double[] t)
	{
		times = t;
	}
	
	/**
	 * Gets the model of the car
	 * @return the model of the car
	 */
	public String getModel()
	{
		return model;
	}
	
	/**
	 * Gets the times of car
	 * @return the times of car
	 */
	public double[] getTimes()
	{
		return times;
	}
	
	public String toString()
	{
		return String.format("%-15s%-10s%-8s%-8s%-8s%-8s%-8s", "Model", "", "race1", "race2", "race3", "race4", "race5") + "\n" + 
				String.format("%-15s%-10s%-8s%-8s%-8s%-8s%-8s", "-----", "", "-----", "-----", "-----", "-----", "-----") + "\n" + 
				String.format("%-15s%-10s%-8s%-8s%-8s%-8s%-8s", model, "", times[0], times[1], times[2], times[3], times[4]);	
	}
}
