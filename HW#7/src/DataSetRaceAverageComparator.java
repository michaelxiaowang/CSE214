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

public class DataSetRaceAverageComparator implements Comparator<DataSet>
{
	private int raceNum;
	
	/**
	 * Default constructor
	 */
	public DataSetRaceAverageComparator()
	{
		
	}
	
	/**
	 * Constructor of DataSetRaceAverageComparator that sets raceNum to int race
	 * @param race the race average that is used to compare to dataSets
	 */
	public DataSetRaceAverageComparator(int race)
	{
		raceNum = race;
	}
	
	/**
	 * Compares two DataSets
	 * Returns -1 if dataSet1 has a lower average in the specified race than dataSet2
	 * Returns 0 if dataSet1 has the same average in the specified race than dataSet2
	 * Returns 1 if dataSet1 has a greater average in the specified race than dataSet2
	 */
	public int compare(DataSet dataSet1, DataSet dataSet2) 
	{
		Double m = new Double(dataSet1.getAverage()[raceNum-1]);
		Double n = new Double(dataSet2.getAverage()[raceNum-1]);
		return m.compareTo(n);
	}
	
	/**
	 * Gets the race number
	 * @return the race number
	 */
	public int getRaceNum()
	{
		return raceNum;
	}
	
	/**
	 * Checks to see if this object is the same as another
	 * Object is equal if it does not have a different class, is not null, and has the same race number
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != this.getClass())
			return false;
		if(this.getRaceNum() != ((DataSetRaceAverageComparator)obj).getRaceNum())
			return false;
		return true;
	}
}
