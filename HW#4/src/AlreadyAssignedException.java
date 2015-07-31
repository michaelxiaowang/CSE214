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

public class AlreadyAssignedException extends IllegalArgumentException 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls IllegalArgumentException's constructor
	 */
	public AlreadyAssignedException()
	{
		super();
	}
	
	/**
	 * Calls IllegalArgumentExceptiion's constructor
	 * @param message the message exception will show
	 */
	public AlreadyAssignedException(String message)
	{
		super(message);
	}
}
