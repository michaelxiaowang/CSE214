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

import java.io.*;
import java.util.*;

public class ParticleTree
{
	private ParticleNode root;
	
	/**
	 * Default constructor for ParticleTree
	 */
	public ParticleTree()
	{
		
	}
	
	/**
	 * Creates a tree based on a text document with name filename
	 * @param filename the name of text file used to construct tree
	 */
	public ParticleTree(String filename) throws Exception
	{

			FileInputStream fis = new FileInputStream(filename); 
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader stdin = new BufferedReader(inStream);
			String data = stdin.readLine();
			if(data != null)
			{
				root = new ParticleNode(data.substring(0, data.indexOf(' ')));
				data = data.substring(data.indexOf(' ') + 1);
				root.setLeft(new ParticleNode(data.substring(0, data.indexOf(' '))));
				data = data.substring(data.indexOf(' ') + 1);
				root.setRight(new ParticleNode(data));
			}
			while((data = stdin.readLine()) != null)
			{
				ParticleNode tempReference = findParticle(data.substring(0, data.indexOf(' ')));
				data = data.substring(data.indexOf(' ') + 1);
				tempReference.setLeft(new ParticleNode(data.substring(0, data.indexOf(' '))));
				data = data.substring(data.indexOf(' ') + 1);
				tempReference.setRight(new ParticleNode(data));
			}
			fis.close();
	}
	
	/**
	 * Prints the maximum depth of the tree
	 * @return the maximum depth of the tree
	 */
	public int depth()
	{
		return root.depth();
	}
	
	/**
	 * Finds particle based on name
	 * @param particleName the name of particle we are searching for
	 * @return the particle being searched for
	 */
	public ParticleNode findParticle(String particleName)
	{
		ParticleNode cursor = root;
		return cursor.getWantedParticle(particleName);
	}
	
	/**
	 * Prints all the particles of the tree and their left and right child
	 */
	public void printAllParticles()
	{
		root.printParticles();
	}
	
	public ArrayList<ArrayList<String>> getDecayModes(String particleName)
	{
		return findParticle(particleName).getDecayModes(findParticle(particleName));
	}
}
