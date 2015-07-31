/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 2
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

public class BlockNotFoundException extends IllegalArgumentException 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls IllegalArgumentException's constructor
	 */
	public BlockNotFoundException()
	{
		super();
	}
	
	/**
	 * Calls IllegalArgumentExceptiion's constructor
	 * @param message the message exception will show
	 */
	public BlockNotFoundException(String message)
	{
		super(message);
	}
}
