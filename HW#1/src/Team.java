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


public class Team 
{
	private Player[] playerTeam;
	
	/**
	 * Creates a new blank array of Players with 40 elements, with indexes 0-39
	 */
	public Team()
	{
		playerTeam = new Player[40];
	}
	
	/**
	 * Gets a Team object that holds the same values as the original team but does not share the same reference
	 * @return a cloned copy of Team
	 */
	public Object Clone()
	{
		Team cloned = new Team();
		for(int i = 0; i<playerTeam.length; i++)
		{
			cloned.getPlayerTeam()[i] = new Player(playerTeam[i].getName(), playerTeam[i].getHits(), playerTeam[i].getErrors());
		}
		return cloned;
	}
	
	/**
	 * Receives the number of Players in a particular Team
	 * @return number of Player objects in an array of Players
	 */
	public int size()
	{
		int size = 0;
		for(int i=0; i<40; i++)
		{
			if(!(playerTeam[i] == null))
					size++;
		}
		return size;
	}
	
	/**
	 * Checks to see if all indexes of an array of Players are occupied by a Player object
	 * @return true if all indexes of an array of Players are occupied by a Player object, false otherwise
	 */
	public boolean teamFull()
	{
		for(int i=0; i<40; i++)
		{
			if(playerTeam[i]==null)
				return false;
		}
		return true;
	}
	
	/**
	 * Adds a Player object to in a specific index of an array of Players.
	 * If a player already exists in that position, the new Player object will take that position.
	 * The old Player objects are all shifted to the next index.
	 * Throws an exception when all positions are already occupied or the position is not within valid range.
	 * @param p the Player object being added to the array
	 * @param position the desired position to place the Player object
	 */
	public void addPlayer(Player p, int position)
	{
		if(teamFull())
			throw new FullTeamException("Error: The team is at maximum capacity");
		else if(position<0 || position>40)
			throw new IllegalArgumentException("Error: Position is not within the valid range");
		if(playerTeam[position-1]==null)
			playerTeam[position-1] = p;
		else
		{
			for(int i=position; i<playerTeam.length+1; i++)
			{
				Player temp = playerTeam[i-1];
				playerTeam[i-1]=p;
				p=temp;
			}
		}
	}
	
	/**
	 * Removes a player from the team, shifts all player positions to accommodate removed player
	 * Throws an exception when the position is invalid
	 * @param position the position where the desired player should be removed
	 */
	public void removePlayer(int position)
	{
		if(position<1 || position>40)
			throw new IllegalArgumentException("Error: Position is not in the valid range");
		Player[] tempTeam = new Player[40];
		for(int i=1; i<position; i++)
		{
			tempTeam[i-1]=playerTeam[i-1];
		}
		for(int i = position; i<playerTeam.length+1; i++)
		{
			if(i<40)
				tempTeam[i-1]=playerTeam[i];
		}
		playerTeam=tempTeam;
	}
	
	/**
	 * Gets the Player occupying the position entered.
	 * @param position the position out the player to be retrieved.
	 * @return the Player occupying the given position.
	 */
	public Player getPlayer(int position)
	{
		if(position<1 || position>40)
			throw new IllegalArgumentException("Error: Position is not in the valid range");
		return playerTeam[position-1];
	}
	
	/**
	 * Get the Player with the name requested
	 * @param name the name of the Player to be requested
	 * @return the Player with the name requested
	 */
	public Player getPlayerByName(String name)
	{
		for(int i=0; i<playerTeam.length; i++)
		{
			if(playerTeam[i].getName().equalsIgnoreCase(name))
				return playerTeam[i];
		}
		throw new IllegalArgumentException("The player does not exist in this team");
	}
	
	/**
	 * Gets the Player with the best score in the requested statistic
	 * @param score the desired statistic used to find the player with the best score
	 * @return the Player with the best score in the requested statistic
	 */
	public Player getLeader(String score)
	{
		int playerIndex = 0;
		if(score.equalsIgnoreCase("hits"))
		{
			int bestScore = 0;
			for(int i=0; i<playerTeam.length; i++)
			{
				if(playerTeam[i] != null)
				{
					if(playerTeam[i].getHits()>bestScore)
					{
						bestScore=playerTeam[i].getHits();
						playerIndex = i;
					}
				}
			}
		}
		else if(score.equalsIgnoreCase("errors"))
		{
			int bestScore = 9999;
			for(int i=0; i<playerTeam.length; i++)
			{
				if(playerTeam[i] != null)
				{
					if(playerTeam[i].getErrors()<bestScore)
					{
						bestScore = playerTeam[i].getErrors();
						playerIndex = i;
					}
				}
			}
		}
		else
			throw new IllegalArgumentException("The given statistic was not \"hits\" or \"errors\"");
		return playerTeam[playerIndex];
	}
	
	/**
	 * Compares the Team with an Object obj for equivalence of all Players in the Team
	 * @param obj the object Team is being compared with
	 * @return true if all Players in the both Team and obj are equivalent, false otherwise
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != this.getClass())
			return false;
		Team newteam = (Team)obj;
		for(int i=0; i<playerTeam.length; i++)
		{
			if(this.getPlayerTeam()[i] == null && newteam.getPlayerTeam()[i] != null)
				return false;
			if(this.getPlayerTeam()[i] != null)
			{
				if(newteam.getPlayerTeam()[i] == null)
					return false;
				if(!(this.getPlayer(i+1).samePlayer(newteam.getPlayer(i+1))))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Gets the array containing the Players in the Team
	 * @return an array containing the Player objects in the Team
	 */
	public Player[] getPlayerTeam()
	{
		return playerTeam;
	}
	
	/**
	 * Prints all the Players' name, hits, and errors in a nice format
	 */
	public void PrintAllPlayers()
	{
		String divider = "";
		for(int i=0; i<65; i++)
		{
			divider += "-";
		}
		String printThis = "";
		for(int i=0; i<playerTeam.length; i++)
			if(playerTeam[i]!=null)
				printThis += String.format("%-15d",(i+1)) + String.format("%-30s%-10d%-10d", playerTeam[i].getName(), playerTeam[i].getHits(), playerTeam[i].getErrors()) + "\n";
		System.out.println(String.format("%-15s%-30s%-10s%-10s", "Player#", "Name", "Hits", "Errors") + "\n" + divider + "\n"+ printThis);
	}
	/**
	 * Gets a string containing all the Players' names, hits, and errors in a nice format
	 * @return a string containing all the Players' names, hits and errors in a nice format
	 */
	public String toString()
	{
		String divider = "";
		for(int i=0; i<65; i++)
		{
			divider += "-";
		}
		String printThis = "";
		for(int i=0; i<playerTeam.length; i++)
			if(playerTeam[i]!=null)
				printThis += String.format("%-15d",(i+1)) + String.format("%-30s%-10d%-10d", playerTeam[i].getName(), playerTeam[i].getHits(), playerTeam[i].getErrors()) + "\n";
		return String.format("%-15s%-30s%-10s%-10s", "Player#", "Name", "Hits", "Errors") + "\n" + divider + "\n"+ printThis;
	}
}
