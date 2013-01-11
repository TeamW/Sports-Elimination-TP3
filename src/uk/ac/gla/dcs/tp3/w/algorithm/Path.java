package uk.ac.gla.dcs.tp3.w.algorithm;

/**
 * Class to represent a path through a graph. In the context of the project this
 * path is the residual path through the residual graph generated during the
 * Ford-Fulkerson algorithm.
 * 
 * @author Team W
 * @version 1.0
 */
public class Path {

	private int[] path;
	private int capacity;

	/**
	 * Basic no parameter constructor.
	 */
	public Path() {
		this(null, 0);
	}

	/**
	 * Constructor which stores the path given the arrays of integer indices and
	 * path capacity.
	 * 
	 * @param path
	 *            integer array containing indices of successive vertices in the
	 *            path
	 * @param capacity
	 *            overall capacity of the given path
	 */
	public Path(int[] path, int capacity) {
		this.path = path;
		this.capacity = capacity;
	}

	/**
	 * Get the array of integers representing the path.
	 * 
	 * @return integer array representing the vertices in the path
	 */
	public int[] getPath() {
		return path;
	}

	/**
	 * Set the array of integers representing the path.
	 * 
	 * @param path
	 *            integer array representing the vertices in the path
	 */
	public void setPath(int[] path) {
		this.path = path;
	}

	/**
	 * Get the capacity of the path.
	 * 
	 * @return integer representing capacity of the path
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Set the capacity of the path.
	 * 
	 * @param integer representing capacity of the path
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
