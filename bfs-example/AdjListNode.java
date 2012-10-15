
/**
 class to represent an entry in the adjacency list of a vertex
 in a graph
 */
public class AdjListNode {

	private int vertexNumber;
	// could be other fields, for example representing
	// properties of the edge - weight, capacity, ...
	
    /* creates a new instance */
	public AdjListNode(int n){
		vertexNumber = n;
	}
	
	public int getVertexNumber(){
		return vertexNumber;
	}
	
	public void setVertexNumber(int n){
		vertexNumber = n;
	}
	
}
