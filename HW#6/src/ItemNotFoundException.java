/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 6
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

public class ItemNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls Exception's constructor
	 */
	public ItemNotFoundException()
	{
		super();
	}
	
	/**
	 * Calls Exceptiion's constructor
	 * @param message the message exception will show
	 */
	public ItemNotFoundException(String s)
	{
		super(s);
	}
}