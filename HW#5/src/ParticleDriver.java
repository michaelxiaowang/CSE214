/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 5
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

import java.util.Scanner;
import java.util.*;

public class ParticleDriver
{
	public static void main(String[] args)
	{
		ParticleTree test = new ParticleTree();//Initialize null tree
		
		Scanner scan = new Scanner(System.in);//Create Scanner
		
		/**
		 * Prints menu
		 */
		System.out.println("T)  Build a tree");
		System.out.println("F)  Find a particle");
		System.out.println("P)  Print all particles");
		System.out.println("H)  Depth");
		System.out.println("D)  Compute Decay Modes");
		System.out.println("Q)  Quit" + "\n");
		
		/**
		 * Takes input
		 */
		System.out.print("Choose an option: ");
		String input = scan.nextLine();
		System.out.print("");
		
		/**
		 * While input is not T, print "Cannot do anything until a tree is built"
		 */
		while(!input.equalsIgnoreCase("T") && !input.equalsIgnoreCase("Q"))
		{
			System.out.println("Cannot do anything until a tree is built");
			System.out.print("Choose an option: ");
			input = scan.nextLine();
		}
		
		boolean constructed = false;//Confirms tree was constructed
		
		/**
		 * While tree not constructed, prompt for new name
		 */
		if(input.equalsIgnoreCase("T"))
		{
			while(!constructed)
			{
				System.out.print("\n" + "Enter the file name: ");
				input = scan.nextLine();
				try
				{
					test = new ParticleTree(input);//construct the tree based on file name
					constructed = true;//tree was constructed
					System.out.println("\n" + "Created tree");
				}
				catch(Exception e)
				{
					System.out.println("\n" + "File not found or text is in wrong format.");
				}	
			}
		}
		
		System.out.println("");//formatting purposes
		
		/**
		 * Loops forever until input is q
		 */
		while(!input.equalsIgnoreCase("Q"))
		{
			/**
			 * Prompts input
			 */
			System.out.print("Choose an option: ");
			input = scan.nextLine();
			System.out.println();
			
			/**
			 * If input is F, find the particle and print the particle and it's left and right element
			 * If particle not found, throw exception
			 */
			if(input.equalsIgnoreCase("F"))
			{
				System.out.print("Enter the particle name: ");
				input = scan.nextLine();
				try
				{
					ParticleNode n = test.findParticle(input);
					System.out.println(n.getName() + " " + n.getLeft().getName() + " " + n.getRight().getName() + "\n");
				}
				catch(IllegalArgumentException e)
				{
					e.getMessage();
				}
			}
			
			/**
			 * Prints the particles in tree
			 */
			else if(input.equalsIgnoreCase("P"))
			{
				test.printAllParticles();
				System.out.println("");
			}
			
			/**
			 * Prints the height of the tree
			 */
			else if(input.equalsIgnoreCase("H"))
			{
				System.out.println("The depth of the tree is " + test.depth() + "\n");
			}
			
			/**
			 * Prints the decay modes of desired particle, throw exception if particle not found
			 */
			else if(input.equalsIgnoreCase("D"))
			{
				System.out.print("Enter the particle name: ");
				input = scan.nextLine();
				try
				{
					if(test.findParticle(input).getLeft() == null)
						System.out.println(input + " does not decay.");
					else
					{
						ArrayList<ArrayList<String>> decayModes = test.getDecayModes(input);
						
						System.out.println(input + " can decay into: ");
						
						for(ArrayList<String> d: decayModes)
						{
							String append = "";
							for(String s: d)
								append += s + " ";
							System.out.println(append);
						}
					}
					System.out.println();
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("The particle does not exist" + "\n");
				}
			}
			
			/**
			 * Terminates
			 */
			else if(input.equalsIgnoreCase("Q"))
			{
				System.out.print("Program terminated.");
			}
			
			/**
			 * Inform user that input was not valid
			 */
			else
			{
				System.out.println("Not a valid menu option" + "\n");
			}
		}
		scan.close();
	}
}
