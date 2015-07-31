/**
 * Required Information for Submission 
 * Michael Wang
 * 109055666
 * Homework Assignment 4
 * CSE214 - R01
 * Recitation TA: Sun Lin
 * Grading TA's Name
 * @author Michael Wang
 */

public class CustomerQueue
{
	private Customer[] queue;
	private int front;
	private int rear;
	private final int CAPACITY;
	
	/**
	 * Default constructor for CustomerQueue
	 * Sets front and rear to -1, queue size to CAPACITY
	 */
	public CustomerQueue()
	{
		front = -1;
		rear = -1;
		CAPACITY = 100;
		queue = new Customer[CAPACITY];
	}
	
	public CustomerQueue(int capacity)
	{
		front = -1;
		rear = -1;
		CAPACITY = capacity;
		queue = new Customer[CAPACITY];
	}
	
	/**
	 * Enqueues a customer
	 * Throws FullQueueException if queue is full
	 * Sets front and rear to 0 if queue is empty
	 * Sets rear to next available spot in the queue
	 * Assigns rear the Customer c
	 * @param c the Customer to enqueue
	 */
	public void enqueue(Customer c)
	{
		if((rear+1) % CAPACITY == front)
			throw new FullQueueException("The queue is full");
		if(isEmpty())
		{
			front = 0;
			rear = 0;
		}
		else
			rear = (rear+1) % CAPACITY;
		queue[rear] = c;
	}
	
	/**
	 * Removes first Customer from queue and returns it
	 * Throws EmptyQueueException if queue is empty
	 * Sets front to next spot in queue
	 * @return the first Customer in the queue
	 */
	public Customer dequeue()
	{
		if(isEmpty())
			throw new EmptyQueueException("The queue is empty.");
		Customer hasBeenServed = queue[front];
		if(front == rear)
		{
			front = -1;
			rear = -1;
		}
		else
			front = (front+1) % CAPACITY;
		return hasBeenServed;
	}
	
	/**
	 * Gets the first Customer in the queue
	 * Throws EmptyListException if queue is empty
	 * @return the first Customer in the queue
	 */
	public Customer peek()
	{
		if(isEmpty())
			throw new EmptyQueueException("The queue is empty.");
		return queue[front];
	}
	
	/**
	 * Gets the number of Customers in queue between front and rear from left to right
	 * @return the number of active Customers in queue
	 */
	public int size()
	{
		if(isEmpty())
			return 0;
		if(front == rear)
			return 1;
		int queueSize = 0;
		while(front != rear)
		{
			queueSize++;
			front = (front+1) % CAPACITY;
		}
		return queueSize;
	}
	
	public void increaseAllTimeSpent()
	{
		int counter = front;
		queue[counter].increaseTimeSpent();
		if(counter<CAPACITY - 1)
			counter++;
		else
			counter = 0;
		while(counter != rear)
		{
			if(counter >= 0 && queue[counter] != null)
				queue[counter].increaseTimeSpent();
			if(counter<CAPACITY - 1)
				counter++;
			else
				counter = 0;
		}
	}
	
	public int getFront()
	{
		return front;
	}
	
	public int getRear()
	{
		return rear;
	}
	
	public int getCapacity()
	{
		return CAPACITY;
	}
	
	public Customer[] getQueue()
	{
		return queue;
	}
	
	/**
	 * Gets whether or not the queue is empty
	 * @return truth value for the queue is empty
	 */
	public boolean isEmpty()
	{
		return front == -1;
	}
	
	public String toString()
	{
		int counter = rear;
		String returnThis = "";
		if(!isEmpty())
		{
			if(rear == front)
				returnThis += "[(" + queue[counter].getTimeLeft() + ", " + queue[counter].getTimeEntered() + ")]";
			else
			{
				while(counter != front)
				{
					if(counter != -1 && queue[counter] != null && counter == rear)
						returnThis += "[(" + queue[counter].getTimeLeft() + ", " + queue[counter].getTimeEntered() + "), ";
					else if(counter != -1 && queue[counter] != null && counter != rear && queue[counter].getTimeLeft() != 0)
						returnThis += "(" + queue[counter].getTimeLeft() + ", " + queue[counter].getTimeEntered() + "), ";
					if(counter!=0)
						counter--;
					else
						counter = CAPACITY-1;
					if(front == counter)
						returnThis += "(" + queue[counter].getTimeLeft() + ", " + queue[counter].getTimeEntered() + ")]";
				}
			}
		}
		
		return returnThis;
	}
}
