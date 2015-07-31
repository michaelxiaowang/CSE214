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

public class Player
{
	private String playerName;
	private int playerHits;
	private int playerErrors;
	
	/**
	 * Creates a new Player object with blank parameters
	 */
	public Player()
	{
		playerName = null;
		playerHits = 0;
		playerErrors = 0;
	}
	
	/**
	 * Creates a new Player object using given values. Overloaded constructor
	 * @param name name of the Player
	 */
	public Player(String name)
	{
		playerName=name;
		playerHits = 0;
		playerErrors = 0;
	}
	
	/**
	 * Creates a new Player object using given values. Overloaded constructor
	 * @param name name of the player
	 * @param hits player's number of hits
	 * @param errors player's number of errors
	 */
	public Player(String name, int hits, int errors)
	{
		playerName=name;
		setHits(hits);
		setErrors(errors);
	}
	
	/**
	 * Receives the name of the Player
	 * @return the name of a particular Player
	 */
	public String getName()
	{
		return playerName;
	}
		
	/**
	 * Receives the Player's number of hits
	 * @return the number of hits of a particular Player
	 */
	public int getHits()
	{
		return playerHits;
	}
		
	/**
	 * Receives the Player's number of errors
	 * @return the number of errors of a particular Player
	 */
	public int getErrors()
	{
		return playerErrors;
	}
	
	/**
	 * Sets the Player's name to given value
	 * @param name new name desired for a particular Player
	 */
	public void setName(String name)
	{
		playerName = name;
	}
	
	/**
	 * Sets the Player's hits to a given value
	 * @param hits number of hits designated to a particular Player
	 */
	public void setHits(int hits)
	{
		playerHits = hits;
	}
	
	/**
	 * Sets the Player's errors to a given value
	 * @param errors number of errors designated to a particular Player
	 */
	public void setErrors(int errors)
	{
		playerErrors = errors;
	}
	
	/**
	 * Compares two Player objects. Checks for equivalence of name, number of hits and number of errors
	 * @param player the Player object to compare with
	 * @return true if equivalent name, number of hits and number of errors, false otherwise
	 */
	public Boolean samePlayer(Player player)
	{
		if (getName().equalsIgnoreCase(player.getName()) && getHits() == player.getHits() && getErrors() == player.getErrors())
			return true;
		else
			return false;
	}
	
	/**
	 * Receives a string description of the player (name, hits, errors)
	 * @return string description of the player containing the name, hits, and errors
	 */
	public String toString()
	{
		return getName() + " - " + getHits() + " hits, " + getErrors() + " errors";
	}
}
