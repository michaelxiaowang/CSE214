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

public class DataSetNumRowsComparator implements Comparator<DataSet>
{
	/**
	 * Default constructor
	 */
	public DataSetNumRowsComparator()
	{
		
	}
	
	/**
	 * Compares two DataSets
	 * Return -1 if dataSet1 has less rows than dataSet2
	 * Return 0 if dataSet2 has the same number of rows as dataSet2
	 * Return 1 if dataSet1 has more rows than dataSet2
	 */
	public int compare(DataSet dataSet1, DataSet dataSet2) 
	{
		Integer m = new Integer(dataSet1.numRows());
		Integer n = new Integer(dataSet2.numRows());
		return m.compareTo(n);
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
