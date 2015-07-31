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

public class MemoryBlock 
{
	private int address;
	private int size;
	private String status;
	private MemoryBlock next;
	private MemoryBlock prev;
	
	/**
	 * Default constructor for MemoryBlock
	 */
	public MemoryBlock()
	{
		
	}
	
	/**
	 * Constructor of MemoryBlock that initializes address, size, and status
	 * @param address the address of the MemoryBlock
	 * @param size the size of the MemoryBlock
	 * @param status the status that says whether MemoryBlock is free or allocated
	 */
	public MemoryBlock(int address, int size, String status)
	{
		this.address = address;
		this.size = size;
		this.status = status;
	}

	/**
	 * Gets the address of MemoryBlock
	 * @return address of Memory Block
	 */
	public int getAddress()
	{
		return address;
	}
	
	/**
	 * Gets the size of MemoryBlock
	 * @return size of MemoryBlock
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Gets the status of MemoryBlock
	 * @return size of MemoryBlock
	 */
	public String getStatus()
	{
		return status;
	}
	
	/**
	 * Gets the MemoryBlock block in variable next
	 * @return the MemoryBlock in the variable next of MemoryBlock
	 */
	public MemoryBlock getNext()
	{
		return next;
	}
	
	/**
	 * Gets the MemoryBlock block in the variable prev
	 * @return the MemoryBlock in the variable prev of MemoryBlock
	 */
	public MemoryBlock getPrev()
	{
		return prev;
	}
	
	/**
	 * Sets the address of MemoryBlock to parameter
	 * @param address the address of MemoryBlock desired
	 */
	public void setAddress(int address)
	{
		this.address = address;
	}
	
	/**
	 * Sets the size of MemoryBlock to parameter
	 * @param size the size of MemoryBlock desired
	 */
	public void setSize(int size)
	{
		this.size = size;
	}
	
	/**
	 * Sets the status of MemoryBlock to parameter
	 * @param status the status of MemoryBlock desired
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	/**
	 * Sets the next variable to parameter
	 * @param next the MemoryBlock desired to be set next
	 */
	public void setNext(MemoryBlock next)
	{
		this.next = next;
	}
	
	/**
	 * Sets the prev variable to parameter
	 * @param prev the MemoryBlock desired to be set prev
	 */
	public void setPrev(MemoryBlock prev)
	{
		this.prev = prev;
	}
}
