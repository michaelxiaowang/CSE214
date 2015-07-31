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

public class Inventory implements Serializable
{
	  private static final long serialVersionUID = 1L;
	  
	  private Hashtable<String, Integer> quantities; //a Hashtable whose keys are item names, and whose values are the quantity of the corresponding item
	  private Hashtable<String, String> itemToLocation; //a Hashtable whose keys are item names, and whose values are the location of the corresponding item
	  private Hashtable<String, ArrayList<String>> locationToItem; //a Hashtable whose keys are locations, and whose values are a list of all the items at that location
	  
	  public Inventory()
	  {
		  quantities = new Hashtable<String, Integer>();
		  itemToLocation = new Hashtable<String, String>();
		  locationToItem = new Hashtable<String, ArrayList<String>>();
	  }
	  
	  /**
	   * Insert a new item into the store. All three Hashtables will need to be updated. 
	   * @param item the item to store
	   * @param location the location to place the item
	   * @param quantity the amount of item to store
	   * @throws ItemAlreadyExistsException if the item is already in the store.
	   */
	  public void newItem(String item, String location, int quantity) throws ItemAlreadyExistsException
	  {
		  if(hasItem(item))
			  throw new ItemAlreadyExistsException("Item is already in store.");
		  itemToLocation.put(item, location);
		  if(locationToItem.get(itemToLocation.get(item)) != null)
				  locationToItem.get(itemToLocation.get(item)).add(item);
		  else
		  {
			  ArrayList<String> items = new ArrayList<String>();
			  items.add(item);
			  locationToItem.put(location, items);
		  }
		  quantities.put(item, quantity);
	  }
	  
	  /**
	   * remove an item from the store. All three Hashtables will need to be updated. 
	   * @param item the item to remove
	   * @throws ItemNotFoundException if the item could not be found.
	   */
	  public void removeItem(String item) throws ItemNotFoundException
	  {
		  if(!hasItem(item))
			  throw new ItemNotFoundException("Item cannot be found.");
		  if(locationToItem.get(itemToLocation.get(item)).size() == 1)
			  locationToItem.remove((itemToLocation.get(item)));
		  else
			  locationToItem.get(itemToLocation.get(item)).remove(item);
		  itemToLocation.remove(item);
		  quantities.remove(item);
	  }

	  /**
	   * change the location of the given item to the new value. Only itemToLocation and locationToItem will need to be updated. 
	   * @param item the item to move
	   * @param newLocation the new location for the item
	   * @throws ItemNotFoundException if the item could not be found.
	   * @throws ItemAlreadyExistsException 
	   */
	  public void moveItem(String item, String newLocation) throws ItemNotFoundException, ItemAlreadyExistsException
	  {
		  if(!hasItem(item))
			  throw new ItemNotFoundException("Item cannot be found.");
		  int quantity = quantities.get(item);
		  removeItem(item);
		  if(hasItem(item))
			  throw new ItemAlreadyExistsException("Item is already in store.");
		  newItem(item, newLocation, quantity);
	  }

	  /**
	   * change the quantity of the given item to the new value. Only quantities will need to be updated. 
	   * @param item the item to update
	   * @param newQuantity the new quantity of the item
	   * @throws ItemNotFoundException if the item could not be found.
	   */
	  public void updateQuantity(String item, int newQuantity) throws ItemNotFoundException
	  {
		  if(!hasItem(item))
			  throw new ItemNotFoundException("Item cannot be found.");
		  quantities.remove(item);
		  quantities.put(item, newQuantity);
	  }

	  /**
	   * return true if the given item is in the store, false otherwise.
	   * @param item the item to check for
	   * @return truth value for the item is in stock
	   */
	  public boolean hasItem(String item)
	  {
		  return !(itemToLocation.get(item) == null);
	  }

	  /**
	   * return the location of the given item. 
	   * @param item the item to search for
	   * @return the location of the item
	   * @throws ItemNotFoundException if the item could not be found.
	   */
	  public String locationOf(String item) throws ItemNotFoundException
	  {
		  if(!hasItem(item))
			  throw new ItemNotFoundException("Item cannot be found.");
		  return itemToLocation.get(item);
	  }

	  /**
	   * return the quantity of the given item. 
	   * @param item the item to check amount of
	   * @return the amount of the item
	   * @throws ItemNotFoundException if the item could not be found.
	   */
	  public int quantityOf(String item) throws ItemNotFoundException
	  {
		  if(!hasItem(item))
			  throw new ItemNotFoundException("Item cannot be found.");
		  return quantities.get(item);
	  }

	  /**
	   * return the list of items at the given location. 
	   * @param location the location to check
	   * @return the items in the location
	   * @throws LocationNotFoundException if the location does not exist.
	   */
	  public ArrayList<String> itemsAt(String location) throws LocationNotFoundException
	  {
		  if(locationToItem.get(location) == null)
			  throw new LocationNotFoundException("The location cannot be found.");
		  return locationToItem.get(location);
	  }

	  /**
	   * return the name of the item with the highest quantity at the given location. 
	   * @param location the location to check
	   * @return the item with highest quantity
	   * @throws LocationNotFoundException if the location does not exist.
	   */
	  public String maxStockAt(String location) throws LocationNotFoundException
	  {
		  if(locationToItem.get(location) == null)
			  throw new LocationNotFoundException("The location cannot be found.");
		  ArrayList<String> items = locationToItem.get(location);
		  int highestQuantity = 0;
		  String itemWithMost = "";
		  for(String s: items)
		  {
			  if(quantities.get(s) > highestQuantity)
			  {
				  highestQuantity = quantities.get(s);
				  itemWithMost = s;
			  }
		  }
		  return itemWithMost;
	  }

	  /**
	   * print each location in the store followed by a list of each item name and quantity in that location
	   */
	  public void printAllItems()
	  {
		  String printThis = "";
		  Set<String> keys = locationToItem.keySet();
		  for(String s: keys)
		  {
			  printThis+=(s + ": " + "\n");
			  ArrayList<String> items = locationToItem.get(s);
			  printThis += String.format("%-15s%-15s", "Item", "Quantity") + "\n";
			  printThis += String.format("%-15s%-15s", "----", "--------") + "\n";
			  for(String t: items)
			  {
				  printThis += String.format("%-15s%-15d", t, quantities.get(t)) + "\n";
			  }
			  printThis += "\n";
		  }
		  System.out.print(printThis);
	  }
	  
	  /**
	   * Gets truth value of Inventory is empty
	   * @return whether Inventory is empty
	   */
	  public boolean isEmpty()
	  {
		  return itemToLocation.isEmpty();
	  }
}
