package eg.edu.alexu.csd.datastructure.mailServer;

public interface IPriorityQueue {
	/**
	 * Inserts an item with priority key. key "1" is the highest priority.
	 */
	public void insert(Object item, int key);

	/**
	 * Removes the object with the highest priority.
	 */
	public Object removeMin();

	/**
	 * Return the object with the highest priority.
	 */
	public Object min();

	/**
	 * Tests if this queue is empty.
	 */
	public boolean isEmpty();

	/**
	 * Returns the number of elements in the queue
	 */
	public int size();
}
