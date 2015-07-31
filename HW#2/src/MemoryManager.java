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

import java.util.Scanner;

public class MemoryManager 
{
	public static void main(String[] args)
	{
		MainMemory memory= new MainMemory(1000);//Instantiates a MainMemory object that is at address 0, of size 1000, and free
		String command = "";
		Scanner scan = new Scanner(System.in);
		
		/*
		 * Prints Menu
		 */
		System.out.println("A) Add memory");
		System.out.println("D) Display memory contents");
		System.out.println("M) malloc");
		System.out.println("F) free");
		System.out.println("S) Set memory allocation algorithm");
		System.out.println("V) Get available memory");
		System.out.println("W) Get allocated memory");
		System.out.println("L) Get largest available block size");
		System.out.println("Q) Quit");
		System.out.print("\n" + "Select a menu command: ");
		
		command = scan.nextLine();//takes input for menu option
		
		if(command.equalsIgnoreCase("Q")) //closes program if command is q
			System.out.println("\n" + "The program will now exit.");
		
		while(!command.equalsIgnoreCase("Q"))//loops until input is q for quitting program
		{
			/**
			 * Adds memory to MainMemory
			 */
			if(command.equalsIgnoreCase("A"))
			{
				int size = 0;
				System.out.print("\n" + "Enter the amount of memory to add: ");
				size = scan.nextInt();
				memory.addMemory(size);
				scan.nextLine();
				System.out.println("\n" + "Added " + size + " bytes of memory.");
			}
			
			/**
			 * Displays the MainMemory in a specific format
			 */
			else if(command.equalsIgnoreCase("D"))
			{
				System.out.print("\n" + memory);
			}
			
			/**
			 * Allocates memory based on set algorithm.
			 * Throws an exception is size is greater than total memory.
			 */
			else if(command.equalsIgnoreCase("M"))
			{
				int size = 0;
				System.out.print("\n" + "Enter the number of bytes to allocate: ");
				size = scan.nextInt();
				try
				{
					memory.malloc(size);
				}
				catch(OutOfMemoryException e)
				{
					System.out.println("\n" + e.getMessage());
				}
				catch(UnoptimallyAllocatedMemoryException e)
				{
					System.out.println("\n" + e.getMessage());
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("\n" + e.getMessage());
				}
				scan.nextLine();
			}
			
			/**
			 * Frees a memory block at the request address
			 * Throws exception if memory block already freed
			 * Throws exception if no block begins at the address
			 */
			else if(command.equalsIgnoreCase("F"))
			{
				int addr = 0;
				System.out.print("\n" + "Enter the address of the block to free: ");
				addr = scan.nextInt();
				try
				{
					memory.free(addr);
					System.out.println("\n" + "The block at address " + addr + " is now free.");
				}
				catch(BlockNotFoundException e)
				{
					System.out.println("\n" + e.getMessage());
				}
				catch(AlreadyFreeException e)
				{
					System.out.println("\n" + e.getMessage());
				}
				scan.nextLine();
			}
			
			/**
			 * Sets the algorithm for malloc
			 * Throws exception if algorithm is not best fit or first fit
			 */
			else if(command.equalsIgnoreCase("S"))
			{
				String algo = "";
				System.out.print("\n" + "Enter the new memory allocation algorithm: ");
				algo = scan.nextLine();
				try
				{
					memory.setAlgorithm(algo);
					System.out.println("\n" + "Algorithm set to " + algo);
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("\n" + e.getMessage());
				}	
			}
			
			/**
			 * Shows total available memory
			 */
			else if(command.equalsIgnoreCase("V"))
			{
				System.out.println("\n" + "Total available memory is " + memory.getAvailableMemory() + " bytes.");
			}
			
			/**
			 * Shows total allocated memory
			 */
			else if(command.equalsIgnoreCase("W"))
			{
				System.out.println("\n" + "Total allocated memory is " + memory.getTotalAllocatedMemory() + " bytes.");	
			}
			
			/**
			 * Shows largest available memory block
			 */
			else if(command.equalsIgnoreCase("L"))
			{
				System.out.println("\n" + "Largest memoryBlock is " + memory.getLargestAvailableMemoryBlock() + " bytes.");
			}
			
			/**
			 * Prints notification of terminating program.
			 */
			else if(command.equalsIgnoreCase("Q"))
			{
				System.out.println("\n" + "The program will now exit.");
			}
			
			/**
			 * If no options are satisfied, alert user
			 */
			else
			{
				System.out.println("\n" + "Option " + command + " does not exist.");
			}
			System.out.print("\n" + "Select a menu command: ");
			command = scan.nextLine();
		}
		scan.close();
	}
}
