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

public class MainMemory 
{
	private MemoryBlock head;
	private MemoryBlock tail;
	private MemoryBlock nodePtr;
	private int largestAvailableMemoryBlock;
	private int totalAvailableMemory;
	private int totalAllocatedMemory;
	private String algorithm;
	
	/**
	 * The default constructor of MainMemory
	 */
	public MainMemory()
	{
		
	}
	
	/**
	 * Constructor of MainMemory that initializes head, tail, algorithm, totalAvailableMemory
	 * Creates a MemoryBlock with address 0, with size of size, status of free
	 * Sets head and tail to initial block
	 * @param size the size used to create initial MemoryBlock of status free
	 */
	public MainMemory(int size)
	{
		head = new MemoryBlock(0, size, "free");
		tail = head;
		setAlgorithm("first fit");
		totalAvailableMemory = size;
		totalAllocatedMemory = 0;
		largestAvailableMemoryBlock = size;
	}
	
	/**
	 * 
	 * @return
	 */
	public MemoryBlock getNodePtr()
	{
		return nodePtr;
	}
	
	/**
	 * Merges two blocks of status free
	 * freeBlock1 expands size to sum of freeBlock1 and freeBlock2 size
	 * sets next and prev of each node appropriately
	 * @param freeBlock1 first MemoryBlock to merge
	 * @param freeBlock2 second MemoryBlock to merge
	 */
	public void merge(MemoryBlock freeBlock1, MemoryBlock freeBlock2)
	{
		if(freeBlock1.getStatus().equalsIgnoreCase("free") && freeBlock2.getStatus().equalsIgnoreCase("free"))
			freeBlock1.setSize(freeBlock1.getSize()+freeBlock2.getSize());
		if(freeBlock2.getNext()!=null)
		{
			freeBlock1.setNext(freeBlock2.getNext());
			freeBlock2.getNext().setPrev(freeBlock1);
		}
		else
		{
			freeBlock1.setNext(null);
			freeBlock2.setPrev(null);
		}
	}
	
	/**
	 * Finds an allocated MemoryBlock and frees it
	 * Throws an exception is MemoryBlock is already freed
	 * Throws an exception if no block is found with that address
	 * Updates totalAllocatedMemory
	 * Merges adjacent free blocks
	 * @param addr the address of the MemoryBlock to free
	 */
	public void free(int addr)
	{
		nodePtr = head;
		while(nodePtr!=null && nodePtr.getAddress() != addr)
		{
			nodePtr = nodePtr.getNext();
		}
		if(nodePtr == null)
		{
			throw new BlockNotFoundException("No memory block beginning at address 50.");
		}
		else if(nodePtr.getStatus().equalsIgnoreCase("free"))
		{
			throw new AlreadyFreeException("The block at the address is already free.");
		}
		else
		{	
			nodePtr.setStatus("free");
			totalAllocatedMemory -= nodePtr.getSize();
			totalAvailableMemory += nodePtr.getSize();
			if(nodePtr.getPrev()==null)
			{
				if(nodePtr.getNext()!=null && nodePtr.getNext().getStatus().equalsIgnoreCase("free"))
				{
					merge(nodePtr, nodePtr.getNext());
				}
			}
			else if(nodePtr.getNext()==null)
			{
				if(nodePtr.getPrev().getStatus().equalsIgnoreCase("free"))
				{
					merge(nodePtr.getPrev(), nodePtr);
				}
			}
			else
			{
				if(nodePtr.getNext()!=null && nodePtr.getNext().getStatus().equalsIgnoreCase("free"))
				{
					merge(nodePtr, nodePtr.getNext());
				}
				if(nodePtr.getPrev()!=null && nodePtr.getPrev().getStatus().equalsIgnoreCase("free"))
				{
					if(nodePtr.getPrev()!=null)
					{
					merge(nodePtr.getPrev(), nodePtr);
					}
				}
			}
		}
	}
	
	public void setAlgorithm(String algo)
	{
		if(!(algo.equalsIgnoreCase("first fit")) && !(algo.equalsIgnoreCase("best fit")))
			throw new IllegalArgumentException("No such memory allocation algorithm");
		algorithm = algo;
	}
	
	/**
	 * Check whether the program will find the first fit or best fit block to allocate memory.
	 * Finds either best fit or first fit node, and assigns nodePtr to that node.
	 * Splits a free block into an allocated part and free part, reassigns next and previous nodes appropriately.
	 * Will not split block if size is equal to size of block found.
	 * Updates totalAllocatedMemory
	 * Updates largestMemoryBlock
	 * Merges free blocks with adjacent free blocks.
	 * @param size the size that user wants to allocate from free memory
	 * @return the address of the allocated block
	 */
	public int malloc(int size)
	{
		if(size>totalAvailableMemory)
			throw new UnoptimallyAllocatedMemoryException("There are at least " + size + " bytes of memory available, but the current configuration of allocated memory does not have a contiguous free block of that size.");
		nodePtr = head;
		MemoryBlock newBlock;
		if(algorithm.equalsIgnoreCase("first fit"))
		{
			while(nodePtr != null && (!(nodePtr.getStatus().equalsIgnoreCase("free")) || nodePtr.getSize()<size))
			{
				nodePtr = nodePtr.getNext();
			}
		}
		else if(algorithm.equalsIgnoreCase("best fit"))
		{
			int bestFitSize = 9999999;
			MemoryBlock bestFitBlock = new MemoryBlock(0,0,"free");
			while(nodePtr!=null)
			{
				if((nodePtr.getStatus().equalsIgnoreCase("free")) && size <= nodePtr.getSize())
				{
					if(nodePtr.getSize() - size < bestFitSize)
					{
						bestFitBlock = nodePtr;
						bestFitSize = nodePtr.getSize()-size;
					}
				}
				nodePtr = nodePtr.getNext();
				
			}
			nodePtr = bestFitBlock;
		}
		if(nodePtr == null)
		{
			throw new UnoptimallyAllocatedMemoryException("There is no block with memory large enough to accommodate desired size.");
		}
		else if(nodePtr.getSize()==size)
		{
			nodePtr.setStatus("allocated");
			totalAllocatedMemory += size;
			totalAvailableMemory -= size;
		}
		else
		{
			totalAllocatedMemory += size;
			totalAvailableMemory -= size;
			newBlock = new MemoryBlock(nodePtr.getAddress() + size, nodePtr.getSize() - size, "free");
			if(nodePtr.equals(head)) 
			{
				newBlock.setNext(nodePtr.getNext());
				newBlock.setPrev(nodePtr);
				if(nodePtr.getNext() != null)
					nodePtr.getNext().setPrev(newBlock);
				nodePtr.setNext(newBlock);
				if(newBlock.getNext() != null && newBlock.getNext().getStatus().equalsIgnoreCase("free"))
				{
					merge(newBlock, newBlock.getNext());
				}
			}
			else if(nodePtr.equals(tail))
			{
				newBlock.setNext(null);
				nodePtr.setNext(newBlock);
				newBlock.setPrev(nodePtr);
				
			}
			else
			{
				newBlock.setNext(nodePtr.getNext());
				newBlock.setPrev(nodePtr);
				nodePtr.getNext().setPrev(newBlock);
				nodePtr.setNext(newBlock);
				
				if(newBlock.getNext()!=null && newBlock.getNext().getStatus().equalsIgnoreCase("free"))
				{
					merge(newBlock, newBlock.getNext());
				}
			}
			if(newBlock.getNext()==null)
			{
				tail = newBlock;
			}
		}
		nodePtr.setStatus("allocated");
		nodePtr.setSize(size);
		int thisAddress = nodePtr.getAddress();
		nodePtr = head;
		largestAvailableMemoryBlock = 0;
		while(nodePtr!=null)
		{
			if(nodePtr.getSize()>largestAvailableMemoryBlock && nodePtr.getStatus().equalsIgnoreCase("free"))
			{
				largestAvailableMemoryBlock = nodePtr.getSize();
			}
			nodePtr = nodePtr.getNext();
		}
		return thisAddress;
	}
	
	/**
	 * Adds memory to totalMemory
	 * @param size the size of memory to add
	 */
	public void addMemory(int size)
	{
		MemoryBlock newBlock = new MemoryBlock(tail.getAddress()+tail.getSize(), size, "free");
		totalAvailableMemory += size;
		if(tail.getStatus().equalsIgnoreCase("free"))
		{
			merge(tail, new MemoryBlock(tail.getAddress()+tail.getSize(), size, "free"));
		}
		else
		{
			tail.setNext(newBlock);
			tail = newBlock;
		}
	}
	
	/**
	 * gets totalAllocatedMemory
	 * @return the private variable totalAllocatedMemory
	 */
	public int getTotalAllocatedMemory()
	{
		return totalAllocatedMemory;
	}
	
	/**
	 * gets the totalAvailableMemory
	 * @return the totalAvailableMemory
	 */
	public int getAvailableMemory()
	{
		return totalAvailableMemory;
	}
	
	/**
	 * gets the largest memory block
	 * @return the largest memory block
	 */
	public int getLargestAvailableMemoryBlock()
	{
		return largestAvailableMemoryBlock;
	}
	
	/**
	 * Formats a string that represents MainMemory
	 * @return a string that represents MainMemory
	 */
	public String toString()
	{
		nodePtr = head;
		String returnThis = "";
		String divider = "";
		for(int i=0; i<50; i++)
		{
			divider += "-";
		}
		while(nodePtr!=null)
		{
			returnThis += String.format("%-15s%-20s%-15s", nodePtr.getAddress(), nodePtr.getStatus(), nodePtr.getSize()) + "\n";
			nodePtr = nodePtr.getNext();
		}
		return String.format("%-15s%-20s%-15s", "Address", "Status", "Size") + "\n" + divider + "\n"+ returnThis;
	}
}
