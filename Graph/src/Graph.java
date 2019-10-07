import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Graph 
{
	private HashMap<Vertex, LinkedList<Edge>> graph;

	public Graph()
	{
		graph = new HashMap<>(); 
	}

	public Graph(String filename) throws FileNotFoundException 
	{ 
		graph = new HashMap<>(); 

		File file = new File(filename);
		Scanner scan = new Scanner(file);

		while(scan.hasNext()) 
		{
			addEdge(scan.next(), scan.next(), scan.nextDouble());
		}

		scan.close();
	} 

	/**
	 * Adds an edge to the graph
	 * @param src: source of edge
	 * @param tgt: target of edge
	 * @param w: weight of edge
	 */
	public void addEdge(String src, String tgt, double w) 
	{
		Vertex source = getVertex(src);
		Vertex target = getVertex(tgt);

		if(source == null)
		{
			LinkedList<Edge> list = new LinkedList<>();
			source = new Vertex(src);
			graph.put(source, list);		
		}

		if(target == null)
		{
			LinkedList<Edge> list = new LinkedList<>();
			target = new Vertex(tgt);
			graph.put(target, list);
		} 

		graph.get(source).add(new Edge(source, target, w));
	}
	
	/**
	 * returns the vertex that matches label
	 * @param label: label of the vertex
	 * @return vertex that matches string label
	 */
	public Vertex getVertex(String label)
	{
		for(Entry<Vertex, LinkedList<Edge>> entry: graph.entrySet()) 
		{
			if(entry.getKey().vertex.equals(label))
			{
				return entry.getKey();
			}
		}

		return null;
	}

	/**
	 * returns an unmodifiable list of the edges that have the given vertex as their source
	 * @param src: vertex src 
	 * @return list of edges of src
	 */
	public List<Edge> getAdjacent(Vertex src)
	{
		return Collections.unmodifiableList(graph.get(src)); 
	}

	/**
	 * returns an unmodifiable set of the vertices in the graph
	 * @return a set of vertices in the graph
	 */
	public Set<Vertex> getVertices()
	{
		return Collections.unmodifiableSet(graph.keySet()); 
	} 

	/**
	 * returns an array representing the labels in the graph
	 * @return String array which represents the labels in the graph
	 */
	public String[] getLabels()
	{ 
		Set<Vertex> vertices = getVertices();
		String[] array = new String[vertices.size()];
		
		int i = 0;
		
		for(Vertex vertex: vertices)
		{
			array[i] = vertex.vertex;
			i++;
		}
		
		return array;
	}

	/**
	 * returns the adjacency matrix of this graph
	 * @return 2D array representing adjacency matrix of this graph
	 */
	public double[][] getMatrix()
	{
		HashMap<String, Integer> temp = new HashMap<>();

		int i = 0; 

		for(Vertex vertex: getVertices())
		{
			temp.put(vertex.vertex, i);
			i++;
		}

		double[][] matrix = new double[temp.size()][temp.size()];

		for(Entry<Vertex, LinkedList<Edge>> entry: graph.entrySet())
		{
			for(Edge e: entry.getValue())
			{
				int indexSrc = temp.get(e.getSource().vertex);
				int indexTar = temp.get(e.getTarget().vertex);
				matrix[indexSrc][indexTar] = e.getWeight();
			}
		}

		for(int r = 0; r < graph.size(); r++)
		{ 
			for(int c = 0; c < graph.size(); c++)  
			{
				if(r == c)
				{
					matrix[r][c] = 0;
				}

				else if(matrix[r][c] == 0.0)
				{
					matrix[r][c] = Double.POSITIVE_INFINITY;
				}
			}
		}

		return matrix;
	}
	
	void printArray(String[] array)
    { 
        int n = array.length; 
        System.out.print("[");
        
        for (int i = 0; i < n; ++i)
        {
            System.out.print(array[i]);
        }
        
        System.out.print("]");
        System.out.println();
    }   
	
	void printArray(double[][] data)
	{ 
		int m = data.length;
 
		for (int r = 0; r < m; ++r)
		{
			double[] currentRow = data[r];
			System.out.println(Arrays.toString(currentRow));
		}
	}
	  
	public List<Edge> getEdges()
	{
		LinkedList<Edge> list = new LinkedList<>();
 
		for(Vertex vert: graph.keySet())
		{
			List<Edge> edges = getAdjacent(vert);
			list.addAll(edges);
		}

		return list;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		Graph graph = new Graph("mm.txt");
		String[] labels = graph.getLabels();
		graph.printArray(labels);
		double[][] array = graph.getMatrix();
		graph.printArray(array);
	}
}
