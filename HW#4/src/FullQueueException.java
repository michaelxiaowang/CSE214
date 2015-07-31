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

public class FullQueueException extends IllegalArgumentException 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls IllegalArgumentException's constructor
	 */
	public FullQueueException()
	{
		super();
	}
	
	/**
	 * Calls IllegalArgumentException's constructor
	 * @param message the message exception will show
	 */
	public FullQueueException(String message)
	{
		super(message);
	}
}