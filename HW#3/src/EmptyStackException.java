/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 3
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

public class EmptyStackException extends IllegalArgumentException 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls IllegalArgumentException's constructor
	 */
	public EmptyStackException()
	{
		super();
	}
	
	/**
	 * Calls IllegalArgumentExceptiion's constructor
	 * @param message the message exception will show
	 */
	public EmptyStackException(String message)
	{
		super(message);
	}
}