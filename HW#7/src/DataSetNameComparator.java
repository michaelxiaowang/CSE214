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

public class DataSetNameComparator implements Comparator<DataSet>
{
	/**
	 * Default constructor
	 */
	public DataSetNameComparator()
	{
		
	}
	
	/**
	 * Compares two DataSets
	 * Returns -1 if dataSet1's name comes alphabetically before dataSet2's name
	 * Returns 0 if dataSet1's name is the same as dataSet2's name
	 * Returns 1 if dataSet1's name comes alphabetically after dataSet2's name
	 */
	public int compare(DataSet dataSet1, DataSet dataSet2) 
	{
		return dataSet1.getName().compareTo(dataSet2.getName());
	}

	/**
	 * Checks to see if this object is the same as another
	 * Object is equal if it does not have a different class, and is not null
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != this.getClass())
			return false;
		return true;
	}
}
