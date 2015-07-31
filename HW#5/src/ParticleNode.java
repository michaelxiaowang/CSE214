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

import java.util.ArrayList;

public class ParticleNode
{
	private String particleName;
	private ParticleNode left;
	private ParticleNode right;
	
	/**
	 * Default constructor for ParticleNode
	 */
	public ParticleNode()
	{
		
	}
	
	/**
	 * Constructor for ParticleNode that sets particleName to s
	 * @param s the name of ParticleNode
	 */
	public ParticleNode(String s)
	{
		particleName = s;
	}
	
	/**
	 * Sets the left of ParticleNode to l
	 * @param l the left ParticleNode of ParticleNode
	 */
	public void setLeft(ParticleNode l)
	{
		left = l;
	}
	
	/**
	 * Sets the right of ParticleNode to r
	 * @param r the right ParticleNode of ParticleNode
	 */
	public void setRight(ParticleNode r)
	{
		right = r;
	}
	
	/**
	 * Gets the name of ParticleNode
	 * @return the name of ParticleNode
	 */
	public String getName()
	{
		return particleName;
	}
	
	/**
	 * Gets the left of ParticleNode
	 * @return the left of ParticleNode
	 */
	public ParticleNode getLeft()
	{
		return left;
	}
	
	/**
	 * Gets the right of ParticleNode
	 * @return the right of ParticleNode
	 */
	public ParticleNode getRight()
	{
		return right;
	}
	
	/**
	 * Looks for a particle with name s, if found returns the ParticleNode, else return null
	 * @param s the particle we are looking for's name
	 * @return the ParticleNode with name s, or null
	 */
	public ParticleNode checkForWantedParticle(String s)
	{
		ParticleNode exists = null;
		if(particleName.equalsIgnoreCase(s))
		{
			exists = this;
		}
		if(getLeft() != null && exists == null)
			exists = getLeft().checkForWantedParticle(s);
		if(getRight() != null && exists == null)
			exists = getRight().checkForWantedParticle(s);
		return exists;
	}
	
	/**
	 * Gets the ParticleNode we are looking for based on name, otherwise throw exception
	 * @param s the name of the particle we are looking for
	 * @return the ParticleNode we are looking for or throw exception if null
	 */
	public ParticleNode getWantedParticle(String s)
	{
		ParticleNode exists = checkForWantedParticle(s);
		if(exists != null)
			return exists;
		throw new IllegalArgumentException("Particle not found.");
	}
	
	/**
	 * Prints all particles of the tree if this ParticleNode was the root
	 */
	public void printParticles()
	{
		ParticleNode cursor = this;
		if(cursor.getLeft()!=null)
			cursor.getLeft().printParticles();
		if(cursor.getLeft() != null && cursor.getRight() != null)
			System.out.println(cursor.getName() + " " + cursor.getLeft().getName() + " " + cursor.getRight().getName());
		else
		{
			System.out.println(cursor.getName());
		}
		if(cursor.getRight()!=null)
			cursor.getRight().printParticles();
	}
	
	/**
	 * Gets the depth of the tree if this ParticleNode was the root
	 * @return the depth of the leaf of this ParticleNode
	 */
	public int depth()
	{
		int sum = 0;
		int leftDepth = 0, rightDepth = 0;
		if(getLeft()!=null)
			leftDepth = 1 + getLeft().depth();
		if(getRight()!=null)
			rightDepth = 1 + getRight().depth();
		if(leftDepth > rightDepth)
			sum = leftDepth;
		else
			sum = rightDepth;
		return sum;
	}
	
	/**
	 * Returns an ArrayList<ArrayList<String>> of all the modes n can decay into
	 * @param n the particle decay modes are desired for
	 * @return an ArrayList<ArrayList<String>> of all the modes n can decay into
	 */
	public ArrayList<ArrayList<String>> getDecayModes(ParticleNode n)
	{
		ArrayList<ArrayList<String>> aDecays = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> bDecays = new ArrayList<ArrayList<String>>();
		
		ArrayList<ArrayList<String>> allDecays = new ArrayList<ArrayList<String>>();
		
		if(n.getLeft() != null)
		{
			aDecays = getDecayModes(n.getLeft());
			bDecays = getDecayModes(n.getRight());
			ArrayList<String> modeA = new ArrayList<String>();
			ArrayList<String> modeB = new ArrayList<String>();
			modeA.add(n.getLeft().getName());
			modeB.add(n.getRight().getName());
			aDecays.add(0, modeA);
			bDecays.add(0, modeB);
		}
		
		
		for(ArrayList<String> a: aDecays)
		{
			for(ArrayList<String> b: bDecays)
			{
				ArrayList<String> addAll = new ArrayList<String>();
				addAll.addAll(a);
				addAll.addAll(b);
				allDecays.add(addAll);
			}
		}
		
		return allDecays;
	}
}
