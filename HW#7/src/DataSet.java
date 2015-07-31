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

import big.data.DataSource;
import big.data.DataSourceIterator;
import java.util.*;

public class DataSet
{
	private ArrayList<RaceResult> data;
	private String name;
	private double[] average;
	
	/**
	 * Default constructor of DataSet
	 */
	public DataSet()
	{
		
	}
	
	/**
	 * Tries to open csv file with name csvName and names it csvName without the .csv extension
	 * Creates a race result for each column
	 * @param csvName the name of the csv file to open
	 * @param s the name of the DataSet
	 * @throws FileNotFoundException when file is not found
	 */
	public DataSet(String csvName) throws FileNotFoundException
	{
		try
		{
			name = csvName.substring(0, csvName.indexOf(".csv"));
			data = new ArrayList<RaceResult>();
			average = new double[5];
			DataSource ds = DataSource.connectCSV(csvName);
			ds.load();
			DataSourceIterator iter = ds.iterator();
			while(iter.hasData())
			{
				String model = iter.fetchString("model");
				double[] times = new double[5];
				times[0] = iter.fetchDouble("race1");
				times[1] = iter.fetchDouble("race2");
				times[2] = iter.fetchDouble("race3");
				times[3] = iter.fetchDouble("race4");
				times[4] = iter.fetchDouble("race5");
				RaceResult car = new RaceResult(model, times);
				data.add(car);
				iter.loadNext();
			}
			setAverage();
		}
		catch(Exception e)
		{
			throw new FileNotFoundException("An error occurred while attempting to open the file.");
		}
	}
	
	/**
	 * Tries to open csv file with name csvName and names it String s
	 * Creates a race result for each column
	 * @param csvName the name of the csv file to open
	 * @param s the name of the DataSet
	 * @throws FileNotFoundException when file is not found
	 */
	public DataSet(String csvName, String s) throws FileNotFoundException
	{
		try
		{
			name = s;
			data = new ArrayList<RaceResult>();
			average = new double[5];
			DataSource ds = DataSource.connectCSV(csvName);
			ds.load();
			DataSourceIterator iter = ds.iterator();
			while(iter.hasData())
			{
				String model = iter.fetchString("model");
				double[] times = new double[5];
				times[0] = iter.fetchDouble("race1");
				times[1] = iter.fetchDouble("race2");
				times[2] = iter.fetchDouble("race3");
				times[3] = iter.fetchDouble("race4");
				times[4] = iter.fetchDouble("race5");
				RaceResult car = new RaceResult(model, times);
				data.add(car);
				iter.loadNext();
			}
			setAverage();
		}
		catch(Exception e)
		{
			throw new FileNotFoundException("An error occurred while attempting to open the file.");
		}
	}
	
	/**
	 * Sets the average of each race
	 */
	public void setAverage()
	{
		for(int i = 0; i < 5; i++)
		{
			double sum = 0.0;
			for(RaceResult r: data)
			{
				sum += r.getTimes()[i];
			}
			average[i] = (double)Math.round(sum/data.size()*100)/100;
		}
	}
	
	/**
	 * Sets the name of DataSet to string s
	 * @param s the new name of DataSet
	 */
	public void setName(String s)
	{
		name = s;
	}
	
	/**
	 * Returns the RaceResult that is the i'th element of the ArrayList<RaceResult> data
	 * @param i the index of the desired RaceResult
	 * @return the RaceResult that is the i'th element of the ArrayList<RaceResult> data
	 */
	public RaceResult getResult(int i)
	{
		return data.get(i);
	}
	
	/**
	 * Gets the number of RaceResults in ArrayList<RaceResult> data
	 * @return the number of RaceResults in ArrayList<RaceResult> data
	 */
	public int numRows()
	{
		return data.size();
	}
	
	/**
	 * Gets the ArrayList<RaceResult> data
	 * @return ArrayList<RaceResult> data
	 */
	public ArrayList<RaceResult> getData()
	{
		return data;
	}
	
	/**
	 * Gets the name of the DataSet
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Get the average for each race
	 * @return double[] average
	 */
	public double[] getAverage()
	{
		return average;
	}
	
	public String toString()
	{
		return String.format("%-15s%-12s%-10s%-8s%-8s%-8s%-8s%-8s", name, numRows(), "", getAverage()[0], getAverage()[1], getAverage()[2], getAverage()[3], getAverage()[4]);	
	}
}
