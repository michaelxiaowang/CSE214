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

public class StringStack 
{
	private StringNode top;
	private StringNode nodePtr;
	
	/**
	 * Default constructor for StringStack
	 */
	public StringStack()
	{
		top = null;
	}
	
	/**
	 * Checks to see if stack is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return top == null;
	}
	
	/**
	 * Adds StringNode with data s to the top of the stack
	 * @param s the data for StringNode on top of the stack
	 */
	public void push(String s)
	{
			StringNode newNode = new StringNode(s);
			newNode.setLink(top);
			top = newNode;
	}
	
	/**
	 * Removes the top StringNode of StringStack
	 * @return the removed StringNode's data
	 */
	public String pop()
	{
		String s;
		if(isEmpty())
			throw new EmptyStackException("The stack is empty");
		s = top.getData();
		top = top.getLink();
		return s;
	}
	
	/**
	 * Creates a clone of the StringStack that can be modified without affecting original
	 */
	public StringStack clone()
	{
		StringStack clonedStack = new StringStack();
		nodePtr = top;
		while (nodePtr!=null)
		{
			clonedStack.push(nodePtr.getData());
			nodePtr = nodePtr.getLink();
		}
		return clonedStack;
	}
}
