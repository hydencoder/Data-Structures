
public class Vertex 
{
	String vertex;
	double distance; 
	Vertex parent;
	boolean visited;

	public Vertex(String vertex)
	{
		this.vertex = vertex;
		this.distance = 0;
		this.parent = null;
		this.visited = false;
	}
	
	public String toString()
	{
		return vertex;
	}
}
