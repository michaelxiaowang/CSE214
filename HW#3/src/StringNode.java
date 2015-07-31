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

public class StringNode 
{
	private String data;
	private StringNode link;
	
	/**
	 * Default constructor for StringNode
	 */
	public StringNode()
	{

	}
	
	/**
	 * Constructor for StringNode that creates a node with data equal to s
	 * @param s the string value for private variable data
	 */
	public StringNode(String s)
	{
		data = s;
		link = null;
	}
	
	/**
	 * Returns the string value data
	 * @return the value held in data
	 */
	public String getData()
	{
		return data;
	}
	
	/**
	 * Returns the StringNode link
	 * @return the link of current node
	 */
	public StringNode getLink()
	{
		return link;
	}
	
	/**
	 * Sets data to String s
	 * @param s the String to set data to
	 */
	public void setData(String s)
	{
		data = s;
	}
	
	/**
	 * Sets link to StringNode node
	 * @param node the node to set current node's link to
	 */
	public void setLink(StringNode node)
	{
		link = node;
	}
}
