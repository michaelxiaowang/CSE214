/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 6
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

import java.io.*;
import java.util.*;

public class InventoryManager 
{
	public static void main(String[] args)
	{
		/**
		 * Tries to open existing Inventory object
		 * If it doesn't exist, create a new Inventory
		 */
		Inventory inventory = new Inventory();
		try
		{
			FileInputStream in = new FileInputStream("store.obj");
			ObjectInputStream s = new ObjectInputStream(in);
			inventory = (Inventory) s.readObject();
			s.close();
		}
		catch(Exception e)
		{
			//Do nothing
		}
		
		Scanner scan = new Scanner(System.in);//Creates a scanner for user input
		String selection = "";//Variable for user input
		
		/**
		 * Displays menu
		 */
		System.out.println("A) Add item");
		System.out.println("R) Remove item");
		System.out.println("M) Move item");
		System.out.println("I) Get item");
		System.out.println("U) Update quantity");
		System.out.println("L) List all items at a given location");
		System.out.println("O) Get item with max quantity at a location");
		System.out.println("P) Print all items in the store grouped by location");
		System.out.println("Q) Quit");
		System.out.println();
		
		/**
		 * While selection is not "Q", run loop
		 */
		while(!selection.equalsIgnoreCase("Q"))
		{
			System.out.print("Make a selection: "); //Prompt user input
			selection = scan.nextLine(); //Stores user input
			
			/**
			 * If A selected, prompt item name, quantity, and location
			 * Try to add, catch exception if ItemAlreadyExistsException is thrown
			 */
			if(selection.equalsIgnoreCase("A"))
			{
				System.out.print("Enter the name of the item: ");
				String item = scan.nextLine();
				System.out.print("Enter the quantity: ");
				int quantity = scan.nextInt();
				scan.nextLine();
				System.out.print("Enter the location: ");
				String location = scan.nextLine();
				try
				{
					inventory.newItem(item, location, quantity);
					System.out.println("Item added.");
				}
				catch(ItemAlreadyExistsException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * If R selected, prompt item to remove
			 * Try to remove, catch exception if ItemNotFoundException is thrown
			 */
			else if(selection.equalsIgnoreCase("R"))
			{
				System.out.print("Enter the name of the item to remove: ");
				String item = scan.nextLine();
				
				try
				{
					inventory.removeItem(item);
					System.out.println("Removed " + "\"" + item + "\"");
				}
				catch(ItemNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * If M selected, prompt item to move and new location
			 * Try to move, catch exception if ItemNotFoundException is thrown
			 */
			else if(selection.equalsIgnoreCase("M"))
			{
				System.out.print("Enter the name of the item to move: ");
				String item = scan.nextLine();
				System.out.print("Enter the new location: ");
				String location = scan.nextLine();
				try
				{
					inventory.moveItem(item, location);
					System.out.println("Item moved.");
				}
				catch(ItemNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				catch(ItemAlreadyExistsException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * If I selected, prompt item to display information for
			 * Try to display item, catch exception if ItemNotFoundException is thrown
			 */
			else if(selection.equalsIgnoreCase("I"))
			{
				System.out.print("Enter the name of the item: ");
				String item = scan.nextLine();
				try
				{
					if(inventory.hasItem(item))
						System.out.println("Name: " + item);
					System.out.println("Location: " + inventory.locationOf(item));
					System.out.println("Quantity: " + inventory.quantityOf(item));
				}
				catch(ItemNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * If U selected, prompt item to update
			 * Try to update item, catch exception if ItemNotFoundException is thrown
			 */
			else if(selection.equalsIgnoreCase("U"))
			{
				System.out.print("Enter the name of the item: ");
				String item = scan.nextLine();
				System.out.print("Enter the new quantity: ");
				int quantity = scan.nextInt();
				scan.nextLine();
				try
				{
					inventory.updateQuantity(item, quantity);
					System.out.println("Quantity updated.");
				}
				catch(ItemNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * If L selected, prompt location to print items for
			 * Try to print list of items, catch exception if LocationNotFoundException is thrown
			 */
			else if(selection.equalsIgnoreCase("L"))
			{
				System.out.print("Enter a location: ");
				String location = scan.nextLine();
				try
				{
					String printThis = "";
					printThis+=(location + ": " + "\n");
					ArrayList<String> items = inventory.itemsAt(location);
					printThis += String.format("%-15s%-15s", "Item", "Quantity") + "\n";
					printThis += String.format("%-15s%-15s", "----", "--------") + "\n";
					for(String t: items)
					{
						printThis += String.format("%-15s%-15d", t, inventory.quantityOf(t)) + "\n";
					}
					System.out.println(printThis);
				}
				catch(LocationNotFoundException e)
				{
					System.out.println(e.getMessage());
				} 
				catch (ItemNotFoundException e) 
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * If O selected, prompt location to find highest quantity item
			 * Try to print item with highest quantity, catch exception if LocationNotFoundException is thrown
			 */
			else if(selection.equalsIgnoreCase("O"))
			{
				System.out.print("Enter a location: ");
				String location = scan.nextLine();
				try
				{
					System.out.println("The item with highest quantity at location " + "\"" + location + "\" is \"" + inventory.maxStockAt(location) + "\"");
				}
				catch(LocationNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			/**
			 * If P selected, print all items in inventory
			 */
			else if(selection.equalsIgnoreCase("P"))
			{
				if(inventory.isEmpty())
					System.out.println("The inventory is empty.");
				else
					inventory.printAllItems();
			}
			
			/**
			 * If Q selected, write object to file and terminate
			 */
			else if(selection.equalsIgnoreCase("Q"))
			{
				try
				{
					FileOutputStream f = new FileOutputStream("store.obj");
					ObjectOutputStream s = new ObjectOutputStream(f);
					Inventory store = inventory;
					s.writeObject(store);
					s.close();
					System.out.print("Saving data to “store.obj” and quitting.");
				}
				catch(Exception e)
				{
					
				}
			}
			
			/**
			 * Else inform user that input was invalid
			 */
			else
			{
				System.out.println("Menu option " + selection + " does not exist.");
			}
			
			System.out.println();
		}
		
		scan.close();//Closes scanner
	}
}
