package uk.ac.gla.dcs.tp3.w.algorithm;

public class Path {

	private int[] path;
	private int capacity;

	public Path(int[] path, int capacity) {
		this.path = path;
		this.capacity = capacity;
	}

	public int[] getPath() {
		return path;
	}

	public void setPath(int[] path) {
		this.path = path;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
