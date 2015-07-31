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

import java.util.Scanner;

public class TeamOperations 
{
	public static void main(String[] args)
	{
		Team baseballTeam = new Team(); //Creates a new Team object
		//The following print statement prints the menu
		System.out.println("A) Add Player " + "\n" + "G) Get Player" + "\n" + "L) Get Leader in a stat" + "\n" + "R) Remove Player" + "\n" + "P) Print All Players" + "\n" + "S) Size" + "\n" + "H) Update Hits" + "\n" + "E) Update Errors" + "\n" + "Q) Quit ");
		Scanner scan = new Scanner(System.in);
		String menuOption = ""; //Creates a variable to hold user input
		while(!menuOption.equalsIgnoreCase("Q")) //Loop will continue until user inputs "Q" for their menu option
		{
			System.out.print("\n" + "Select a menu option: ");//Prompts user for menu option
			menuOption = scan.nextLine();
			
			/**
			 * If the selected option is "A", prompt user for name, hits, errors and position.
			 * Checks to see if hits and errors are greater than or equal to zero.
			 * If condition not met, prompts user for new input.
			 * Attempt to create a player using input.
			 * Throws an exception when team is full or position is not within valid range.
			 */
			if(menuOption.equalsIgnoreCase("A"))
			{
				System.out.print("\n" + "Enter the player's name: ");
				String name = scan.nextLine();
				System.out.print("Enter the number of hits: ");
				int hits = scan.nextInt();
				while(hits<0)
				{
					System.out.print("Please enter a value greater than or equal to 0." + "\n");
					System.out.print("Enter the number of hits: ");
					hits = scan.nextInt();
					scan.nextLine();
				}
				System.out.print("Enter the number of errors: ");
				int errors = scan.nextInt();
				while(errors<0)
				{
					System.out.println("Please enter a value greater than or equal to 0." + "\n");
					System.out.print("Enter the number of errors: ");
					errors = scan.nextInt();
					scan.nextLine();
				}
				System.out.print("Enter the position: ");
				int position = scan.nextInt();
				scan.nextLine();
				try
				{
					baseballTeam.addPlayer(new Player(name,hits,errors),position);
					System.out.println("Player added: " + baseballTeam.getPlayer(position));
				}
				catch(FullTeamException e)
				{
					System.out.println(e.getMessage());
				}
				catch(IllegalArgumentException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * Prints the player in the position requested, prompts user for position.
			 * Throws exception when position not within valid range.
			 */
			if(menuOption.equalsIgnoreCase("G"))
			{
				System.out.print("Enter the position of the Player requested:");
				int position = scan.nextInt();
				scan.nextLine();
				try
				{
					System.out.println(baseballTeam.getPlayer(position));
				}
				catch(IllegalArgumentException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * Prints the player who has the best score in a given statistic, prompts user for statistic
			 * Throws exception when user requests stat other than "hits" or "errors"
			 * Throws exception when there are no players in the Team.
			 */
			if(menuOption.equalsIgnoreCase("L"))
			{
				System.out.print("Enter the stat:");
				String stat = scan.nextLine();
				try
				{
					if(stat.equalsIgnoreCase("hits"))
						System.out.println("Leader in hits: " + baseballTeam.getLeader(stat).toString());
					else
						System.out.println("Leader in errors: " + baseballTeam.getLeader(stat).toString());
				}
				catch(IllegalArgumentException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NullPointerException e)
				{
					System.out.println("There are currently no players in the team.");
				}
			}
			
			/**
			 * Removes a player from a given position. Prompts user for the position.
			 * Throws exception when position requested is not in valid range.
			 */
			if(menuOption.equalsIgnoreCase("R"))
			{
				System.out.print("Enter the position: ");
				int position = scan.nextInt();
				scan.nextLine();
				try
				{
					if(baseballTeam.getPlayer(position) == null)
						System.out.println("No player exists at that position");
					else
					{
						baseballTeam.removePlayer(position);
						System.out.println("Player removed at position " + position);
					}
				}
				catch(IllegalArgumentException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * Prints all the players in the team and their stats in a nice format.
			 */
			if(menuOption.equalsIgnoreCase("P"))
			{
				System.out.print("\n");
				baseballTeam.PrintAllPlayers();
			}
			
			/**
			 * Prints the number of players currently in the team.
			 */
			if(menuOption.equalsIgnoreCase("S"))
			{
				System.out.println("There are " + baseballTeam.size() + " player(s) in the current Team");
			}
			
			/**
			 * Updates the number of hits of a player. Prompts user for player's name.
			 * Throws exception when player nonexistent.
			 */
			if(menuOption.equalsIgnoreCase("H"))
			{
				System.out.print("\n" + "Enter the player to update: ");
				String name = scan.nextLine();
				System.out.print("Enter the new number of hits:");
				int hits = scan.nextInt();
				scan.nextLine();
				while(hits<0)
				{
					System.out.print("Please enter a value greater than or equal to 0");
					System.out.print("\n" + "Enter the new number of hits:");
					hits = scan.nextInt();
					scan.nextLine();
				}
				try
				{
					baseballTeam.getPlayerByName(name).setHits(hits);
					System.out.println("Updated " + name + " hits");
				}
				catch(NullPointerException e)
				{
					System.out.println("The player is not currently in the Team");
				}
			}
			
			/**
			 * Updates the number of errors of a player. Prompts user for player's name.
			 * Throws exception when player nonexistent.
			 */
			if(menuOption.equalsIgnoreCase("E"))
			{
				System.out.print("\n" + "Enter the player to update: ");
				String name = scan.nextLine();
				System.out.print("Enter the new number of errors:");
				int errors = scan.nextInt();
				scan.nextLine();
				while(errors<0)
				{
					System.out.print("Please enter a value greater than or equal to 0");
					System.out.print("\n" + "Enter the new number of errors:");
					errors = scan.nextInt();
					scan.nextLine();
				}
					
					try
					{
						baseballTeam.getPlayerByName(name).setErrors(errors);
						System.out.println("Updated " + name + " errors");
					}
					catch(NullPointerException e)
					{
						System.out.println("The player is not currently in the Team");
					}
			}
		}
		scan.close();
		System.out.println("Program terminated.");
	}
}



 




