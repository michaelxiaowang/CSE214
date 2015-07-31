/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 7
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

import java.util.*;

public class DataSetViewer
{
	/**
	 * Prints a formatted text for ArrayList<DataSet> that contains the order of the DataSet in the list, name of the DataSet, number of rows, and averages of each race.
	 * @param s the ArrayList<DataSet> to print information for.
	 */
	public static void printData(ArrayList<DataSet> s)
	{
		System.out.println(String.format("%-3s%-15s%-12s%-10s%-8s%-8s%-8s%-8s%-8s", "", "Name:", "Num Rows:", "Average:", "race1", "race2", "race3", "race4", "race5")
				+ "\n" + String.format("%-3s%-15s%-12s%-10s%-8s%-8s%-8s%-8s%-8s", "", "-----", "--------", "--------", "-----", "-----", "-----", "-----", "-----"));
		for(int i=0; i<s.size(); i++)
		{
			System.out.print((i+1) + ". " + s.get(i).toString() + "\n");
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args)
	{
		ArrayList<DataSet> sets= new ArrayList<DataSet>();//Creates an ArrayList<DataSet> that holds all the DataSets.
		Scanner scan = new Scanner(System.in);//Creates a scanner for user input.
		String input = "";//String to hold input.
		
		//Prints the menu.
		System.out.println("A) Add a data source" + "\n" + 
				"N) Sort by name" + "\n" + 
				"R) Sort by row" + "\n" + 
				"V) Sort by average" + "\n" +
				"G) Get data" + "\n" +
				"Q) Quit" + "\n");
		
		//Repeat while user does not select "Q"
		while(!input.equalsIgnoreCase("Q"))
		{
			
			System.out.print("Select an option: ");//Prompts input.	
			input = scan.nextLine();//Receives input.
			
			//Runs the menu option based on user input, otherwise inform user selection was invalid.
			switch(input.toUpperCase())
			{
				//If user selects "A", prompt .csv file name and name of DataSet.
				//If DataSet's name is null, make default name the .csv file name without the.csv extension.
				//If name already exists, inform user. Otherwise, create new DataSet and add to sets.
				//If added, print out a summary of the DataSet that was added.
				//If .csv file is not found, catch FileNotFoundException.
				case "A":
					try
					{
						System.out.print("Enter the filename: ");
						String csv = scan.nextLine();
						System.out.print("Enter the name of the data set: ");
						String name = scan.nextLine();
						DataSet newData = null;
						switch(name.trim())
						{
							case "":
								newData = new DataSet(csv);
								break;
							default:
								newData = new DataSet(csv, name);
								
								break;
						}
						boolean nameExists = false;
						for(DataSet d: sets)
						{
							if(d.getName().equalsIgnoreCase(newData.getName()))
								nameExists = true;
						}
						if(nameExists)
							System.out.println("A DataSet with that name already exists" + "\n");
						else
						{
							sets.add(newData);
							System.out.println("\n" + "Data set " + "\"" + newData.getName() + "\"" + " added with " + newData.numRows() + " rows.");
							System.out.println(String.format("%-8s%-10s", "Race", "Average") + "\n" + 
									"----    -------" + "\n" + 
									String.format("%-8s%-10s", "1", newData.getAverage()[0]) + "\n" +
									String.format("%-8s%-10s", "2", newData.getAverage()[1]) + "\n" +
									String.format("%-8s%-10s", "3", newData.getAverage()[2]) + "\n" +
									String.format("%-8s%-10s", "4", newData.getAverage()[3]) + "\n" +
									String.format("%-8s%-10s", "5", newData.getAverage()[4]) + "\n");
						}
					}
					catch(FileNotFoundException e)
					{
						System.out.println(e.getMessage());
					}
					break;
				
				//If user selects "N", sort ArrayList<DataSet> by name.
				//Print summary of ArrayList<DataSet>.
				case "N":
					switch(sets.size())
					{
						case 0:
							System.out.println("There are currently no data sets." + "\n");
							break;
						default:
							boolean sorted = true;
							for(int i = 0; i < sets.size()-1; i++)
							{
								if(sets.get(i).getName().compareTo(sets.get(i+1).getName()) > 0)
								{
									sorted = false;
									break;
								}
							}
							if(sorted)
							{
								Collections.sort(sets, new DataSetNameComparator());
								Collections.reverse(sets);
							}
							else
								Collections.sort(sets, new DataSetNameComparator());
							printData(sets);
							break;
					}
					break;
				
				//If user selects "N", sort ArrayList<DataSet> by number of rows.
				//Print summary of ArrayList<DataSet>.
				case "R":
					switch(sets.size())
					{
						case 0:
							System.out.println("There are currently no data sets." + "\n");
							break;
						default:
							boolean sorted = true;
							for(int i = 0; i < sets.size()-1; i++)
							{
								if(sets.get(i).numRows() > sets.get(i+1).numRows())
								{
									sorted = false;
									break;
								}
							}
							if(sorted)
							{
								Collections.sort(sets, new DataSetNumRowsComparator());
								Collections.reverse(sets);
							}
							else
								Collections.sort(sets, new DataSetNumRowsComparator());
							printData(sets);
							break;
					}
					break;
				
				//If user selects "V", prompt user for a race number. Sort ArrayList<DataSet> by number of average of race numbers.
				//Print summary of ArrayList<DataSet>.
				case "V":
					System.out.print("Enter the column to sort by: ");
					int race = scan.nextInt();
					scan.nextLine();
					try
					{
						switch(sets.size())
						{
							case 0:
								System.out.println("There are currently no data sets." + "\n");
								break;
							default:
								boolean sorted = true;
								for(int i = 0; i < sets.size()-1; i++)
								{
									if(sets.get(i).getAverage()[race-1] > sets.get(i+1).getAverage()[race-1])
									{
										sorted = false;
										break;
									}
								}
								if(sorted)
								{
									Collections.sort(sets, new DataSetRaceAverageComparator(race));
									Collections.reverse(sets);
								}
								else
									Collections.sort(sets, new DataSetRaceAverageComparator(race));
								printData(sets);
								break;
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						System.out.println("There is no row with the given number." + "\n");
					}
					break;
				
				//If user selects "G", prompt user for number data set to access and number of the model in that set
				//Print the summary of that model
				case "G":
					System.out.print("Enter the number of the data set to access: ");
					int dataSetNum = scan.nextInt();
					scan.nextLine();
					System.out.print("Enter the row to access: ");
					int carNum = scan.nextInt();
					scan.nextLine();
					try
					{
						RaceResult car = sets.get(dataSetNum-1).getData().get(carNum-1);
						System.out.println(car.toString() + "\n");
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						System.out.println("There is no row with the given number." + "\n");
					}
					break;
				
				//If user selects "Q", terminate the program.
				case "Q":
					scan.close();//Closes scanner
					System.out.print("Program terminated.");
					break;
				
				default:
					System.out.println(input + " is not a valid menu option.");
					break;
			}
		}
	}
}
