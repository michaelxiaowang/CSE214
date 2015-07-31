
/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 1
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

public class FullTeamException extends IllegalArgumentException 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls IllegalArgumentException's constructor
	 */
	public FullTeamException()
	{
		super();
	}
	
	/**
	 * Calls IllegalArgumentExceptiion's constructor
	 * @param message the message exception will show
	 */
	public FullTeamException(String message)
	{
		super(message);
	}
}
