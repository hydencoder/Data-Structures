import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class WeightedGraph<V> extends WeightAbstractGraph<V>
{
	public static void main(String[] args) throws Exception
	{
//		String[] vertices = {"Seattle", "San Francisco", "Los Angeles","Denver", 
//				"Kansas City", "Chicago", "Boston", "New York","Atlanta", "Miami", "Dallas", "Houston"};
//
//		int[][] edges = 
//		{
//					{0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
//					{1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
//					{2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
//					{3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, 
//					{3, 5, 1003},
//					{4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260},
//					{4, 8, 864}, {4, 10, 496},
//					{5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, 
//					{5, 6, 983}, {5, 7, 787},
//					{6, 5, 983}, {6, 7, 214},
//					{7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
//					{8, 4, 864}, {8, 7, 888}, {8, 9, 661}, 
//					{8, 10, 781}, {8, 11, 810},
//					{9, 8, 661}, {9, 11, 1187},
//					{10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
//					{11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
//		};
//
//
//		WeightedGraph<String> graph = new WeightedGraph(vertices, edges);
//		graph.printEdges();
		
		int[] vertices = {1,2,3, 4};
		
	    int[][] ed =
	    {
	      {0, 1, 10}, {0, 2, 4},  {0, 3, 9},
	      {1, 0, 10}, {1, 2, 5}, {1, 4, 2},
	      {2, 0, 4}, {2, 1, 5}, {2, 3, 11},
	      {3, 0, 9}, {3, 2, 11}, {3, 4, 1},
	      {4, 1, 2}, {4, 3, 1}
	    };
	    
	    WeightedGraph<Integer> graph2 = new WeightedGraph<>(ed, 5);
//	    graph2.printEdges();
	    
	    
	    //WeightedGraph<Integer> graph1 = new WeightedGraph<Integer>(vertices, ed);
	    System.out.println(graph2.MST(0));
	}
	
	public WeightedGraph()
	{
		super();
	}
	
	public WeightedGraph(V[] vertices, int[][] edges)
	{
		super(vertices, edges);
	}
	
	public WeightedGraph(List<V> vertices, List<WeightEdge> edges)
	{
		super(vertices, edges);
	}
	
	public WeightedGraph(int[][] edges, int numberOfvertices)
	{
		super(edges, numberOfvertices);
	}
}

abstract class WeightAbstractGraph<V> implements WeightGraph<V> 
{
	protected List<V> vertices = new ArrayList<>(); // Store vertices
	protected List<List<WeightEdge>> neighbors = new ArrayList<>(); // Adjacency lists
	
	
	/** Construct an empty graph */
	protected WeightAbstractGraph() 
	{
	}

	/** Construct a graph from vertices and edges stored in arrays */
	protected WeightAbstractGraph(V[] vertices, int[][] edges) 
	{
		for (V v : vertices)
		{
			addVertex(v);
		}

		createAdjacencyLists(edges, edges.length);
	}
	
	/** Construct a graph from vertices and edges stored in List */
	protected WeightAbstractGraph(List<V> vertices, List<WeightEdge> edges) 
	{
		for (V v : vertices)
		{
			addVertex(v);
		}

		createAdjacencyLists(edges, vertices.size());
	}
	
	/** Construct a graph for integer vertices 0, 1, 2 and edge list */
	protected WeightAbstractGraph(List<WeightEdge> edges, int numberOfVertices) 
	{
		for (int i = 0; i < numberOfVertices; i++) 
		{
			addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}
		}

		createAdjacencyLists(edges, edges.size());
	}
	
	/** Construct a graph from integer vertices 0, 1, and edge array */
	protected WeightAbstractGraph(int[][] edges, int numberOfVertices) 
	{
		for (int i = 0; i < numberOfVertices; i++) 
		{
			addVertex((V) new Integer(i)); // vertices is {0, 1, ...}
		}

		createAdjacencyLists(edges, edges.length);
	}

	/** Create adjacency lists for each vertex */
	private void createAdjacencyLists(List<WeightEdge> edges, int numberOfVertices) 
	{
		for (WeightEdge edge: edges) 
		{
			addEdge(edge.u, edge.v, edge.weight);
		}
	}
	
	/** Create adjacency lists for each vertex */
	private void createAdjacencyLists(int[][] edges, int numberOfVertices) 
	{
		for (int i = 0; i < numberOfVertices; i++) 
		{
			addEdge(edges[i][0], edges[i][1], edges[i][2]);
		}
	}

	@Override /** Add a vertex to the graph */  
	public boolean addVertex(V vertex) 
	{
		if (!vertices.contains(vertex)) 
		{
			vertices.add(vertex);
			neighbors.add(new ArrayList<WeightEdge>());
			return true;
		}
		
		return false;
	}

	@Override /** Add an edge to the graph */  
	public boolean addEdge(int u, int v, int weight) 
	{
		return addEdge(new WeightEdge(u, v, weight)); //&& addEdge(new WeightEdge(v, u, weight));
	}

	/** Add an edge to the graph */  
	protected boolean addEdge(WeightEdge e) 
	{
		if (e.u < 0 || e.u > getSize() - 1) 
		{
			throw new IllegalArgumentException("No such index: " + e.u);
		}

		if (e.v < 0 || e.v > getSize() - 1) 
		{
			throw new IllegalArgumentException("No such index: " + e.v);
		}

		if (!neighbors.get(e.u).contains(e)) 
		{
			neighbors.get(e.u).add(e);
			return true;
		}

		else 
		{
			return false;
		}
	}
	
	public double getWeight(int u, int v) throws Exception
	{
		for (WeightEdge e : neighbors.get(u))
		{
			if (e.v == v)
			{
				return e.weight;
			}
		}
		
		throw new Exception("No such edge with u and v");
	}
	
	// Modularize code
	// Write comments

	// Find 1 minimum spanning tree. Assume graph is connected and undirected.
	public String MST(int v) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		boolean[] isVisited = new boolean[getSize()]; 
		isVisited[v] = true; 
		int count = 1;
		
		List<Integer> visited = new ArrayList<>(); 
		List<Integer> nei;
		
		int current = v;
		int previous = -1;
		double smallest = Double.POSITIVE_INFINITY;
		
		int parentIndex;
		int check;
		double value;
		
		while (isVisited.length != count)
		{
			nei = getNeighbors(v);
			for (int i = 0; i < nei.size(); ++i) // select smallest weight from the edges of current vertice.
			{
				check = nei.get(i);
				if (isVisited[check] == false)
				{
					value = getWeight(v, check);
					
					if (isVisited[check] == false && value < smallest)
					{
						previous = v;
						current = check;
						smallest = value;
					}
				}
			}
			for (int i = 0; i < visited.size(); ++i) // Now check selected smallest weight with edges of previous visited vertices.
			{
				nei = getNeighbors(visited.get(i));
				parentIndex = visited.get(i);
				
				for (int j = 0; j < nei.size(); j++)
				{
					check = nei.get(j);
					
					if (isVisited[check] == false)
					{
						value = getWeight(parentIndex, check);

						if (isVisited[check] == false && value < smallest)
						{
							previous = parentIndex;
							current = check;
							smallest = value;
						}
					}
				} 
			}
			
			sb.append("(" + getVertex(previous) + ", " + getVertex(current) + ") ");
			
			visited.add(v);
			isVisited[current] = true;
			v = current;
			count++;
			smallest = Double.POSITIVE_INFINITY; 
		}
		
		return sb.toString();
	}
	
//	public String GSP(int u, int v) throws Exception
//	{
//		StringBuilder Sb = new StringBuilder();
//		
//		boolean[] isVisited = new boolean[getSize()]; 
//		
//		double[] shortestSoruceDist = new double[getSize()];
//		
//		int[] visited = new int[getSize()];
//		int[] previousVertex = new int[getSize()];	
//		
//
//		int[] unVisited = new int[getSize()];
//		
//		for (int i = 0; i < unVisited.length; i++)
//		{
//			unVisited[i] = getIndex(getVertex(i));
//		}
//
//		for (int i = 0; i < shortestSoruceDist.length; i++)
//		{
//			if (i == u)
//			{
//				shortestSoruceDist[u] = 0;
//			}
//			
//			else
//			{
//				shortestSoruceDist[i] = Double.POSITIVE_INFINITY;
//			}
//		}
//
//		int current;
//		double value;
//		double smallest = Double.POSITIVE_INFINITY;
//		int j = 0;
//		
//		List<Integer> nei;
//		
//		while (visited.length != getSize())
//		{
//			current = u;
//			nei = getNeighbors(current);
//			
//			for (int i = 0; i < nei.size(); ++i) 
//			{
//				if (isVisited[nei.get(i)] == false)
//				{
//					value = shortestSoruceDist[current] + getWeight(current, nei.get(i));
//					
//					if (value < shortestSoruceDist[nei.get(i)])
//					{
//						shortestSoruceDist[nei.get(i)] = value;
//						previousVertex[nei.get(i)] = visited[current];
//					}
//				}
//			}	
//			
//			visited[j] = current;
//			isVisited[current] = true;
//			unVisited[current] = -1;
//			
//			for (int i = 0; i < unVisited.length; i++)
//			{
//				if (isVisited[i] == false && smallest < shortestSoruceDist)
//				{
//					current = shortestSoruceDist[i];
//				}
//			
//			}
//		
//			
//		
//			
//			
//			
//			
//			
//			j++;
//			
//		}
//	
//		return Sb.toString();
//	}

	public int getSize()
	{
		return vertices.size();
	}

	public void clear()
	{
		vertices.clear(); 
		neighbors.clear();
	}

	public List<V> getVertices()
	{
		return vertices;
	}

	public V getVertex(int index)
	{
		return vertices.get(index);
	}

	public int getIndex(V vertex)
	{
		return vertices.indexOf(vertex);
	}

	public void printEdges() 
	{
		int i = 0;

		for (List<WeightEdge> list: neighbors)
		{
			System.out.print(vertices.get(i) + "(" + i + "): ");

			for (WeightEdge e: list)
			{
				System.out.print("(" + e.u + ", " + e.v + ", " + e.weight + ") ");
			}

			System.out.println();
			i++;
		}
	}

	@Override /** Return the degree for a specified vertex */
	public int getDegree(int v)
	{
		return neighbors.get(v).size();
	}
	
	@Override /** Return the neighbors of the specified vertex */
	public List<Integer> getNeighbors(int index) 
	{
		List<Integer> result = new ArrayList<>();

		for (WeightEdge e: neighbors.get(index))
		{
			result.add(e.v);
		}

		return result;	
	}

	public String dft(int v) 
	{
		StringBuilder sb = new StringBuilder();
		Stack<V> s = new Stack<>();

		boolean[] isVisited = new boolean[getSize()];
		isVisited[v] = true;

		int count = 1;
		int i;

		V current = getVertex(v);
		V previous = null;

		s.push(current);

		while (isVisited.length != count)
		{
			List<Integer> nei = getNeighbors(v);
			i = 0;

			while (i < nei.size())
			{
				if (isVisited[nei.get(i)] == false)
				{
					previous = getVertex(v);
					current = getVertex(nei.get(i));
					sb.append( "(" + previous + ", " + current + ")" + " ");
					s.push(current);
					v = getIndex(current);
					isVisited[v] = true;
					count++;
					break;
				}

				i++;
			}

			if (i == nei.size()) // All the neighbors have been visited. Go back to the previous one. 
			{
				s.pop();
				v = getIndex(s.peek());
			}
		}

		return sb.toString();
	}

	public String bft(int v)
	{
		StringBuilder sb = new StringBuilder(); 

		Queue<V> q = new LinkedList<>();

		boolean[] isVisited = new boolean[getSize()];
		isVisited[v] = true;

		int count = 1;

		V current = getVertex(v);
		V next = null;

		q.offer(current);

		while (isVisited.length != count)
		{
			List<Integer> nei = getNeighbors(v);

			for (int i = 0; i < nei.size(); i++)
			{
				if (isVisited[nei.get(i)] == false)
				{
					next = getVertex(nei.get(i));
					current = getVertex(v);
					sb.append( "(" + current + ", " + next + ")" + " ");
					q.offer(next);
					isVisited[getIndex(next)] = true;
					count++;		
				}
			}

			q.poll();
			current = q.peek();
			v = getIndex(current);
		}	

		return sb.toString();
	}

	@Override /** Obtain a DFS tree starting from vertex v */
	/** To be discussed in Section 28.6 */
	public Tree dfs(int v) 
	{
		List<Integer> searchOrder = new ArrayList<>();

		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
		{
			parent[i] = -1; // Initialize parent[i] to -1
		}

		// Mark visited vertices
		boolean[] isVisited = new boolean[vertices.size()];

		// Recursively search
		dfs(v, parent, searchOrder, isVisited);

		// Return a search tree
		return new Tree(v, parent, searchOrder);
	}

	/** Recursive method for DFS search */
	private void dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited) 
	{
		// Store the visited vertex
		searchOrder.add(u);
		isVisited[u] = true; // Vertex v visited

		for (WeightEdge e : neighbors.get(u)) 
		{
			if (!isVisited[e.v]) 
			{
				parent[e.v] = u; // The parent of vertex e.v is u
				dfs(e.v, parent, searchOrder, isVisited); // Recursive search
			}
		}
	}

	/** Tree inner class inside the AbstractGraph class */
	/** To be discussed in Section 28.5 */
	public class Tree 
	{
		private int root; // The root of the tree
		private int[] parent; // Store the parent of each vertex
		private List<Integer> searchOrder; // Store the search order

		/** Construct a tree with root, parent, and searchOrder */
		public Tree(int root, int[] parent, List<Integer> searchOrder) 
		{
			this.root = root;
			this.parent = parent;
			this.searchOrder = searchOrder;
		}

		/** Return the root of the tree */
		public int getRoot() 
		{
			return root;
		}

		/** Return the parent of vertex v */
		public int getParent(int v) 
		{
			return parent[v];
		}

		/** Return an array representing search order */
		public List<Integer> getSearchOrder() 
		{
			return searchOrder;
		}

		/** Return number of vertices found */
		public int getNumberOfVerticesFound() 
		{
			return searchOrder.size();
		}

		/** Return the path of vertices from a vertex to the root */
		public List<V> getPath(int index) 
		{
			ArrayList<V> path = new ArrayList<>();

			do 
			{
				path.add(vertices.get(index));
				index = parent[index];
			} while (index != -1);

			return path;
		}

		/** Print a path from the root to vertex v */
		public void printPath(int index) 
		{
			List<V> path = getPath(index);
			System.out.print("A path from " + vertices.get(root) + " to " + vertices.get(index) + ": ");
			for (int i = path.size() - 1; i >= 0; i--)
			{
				System.out.print(path.get(i) + " ");
			}
		}

		/** Print the whole tree */
		public void printTree() 
		{
			System.out.println("Root is: " + vertices.get(root));
			System.out.print("Edges: ");
			for (int i = 0; i < parent.length; i++) 
			{
				if (parent[i] != -1) 
				{
					// Display an edge
					System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
				}
			}

			System.out.println();
		}
	}

	@Override /** Starting bfs search from vertex v */
	/** To be discussed in Section 28.7 */
	public Tree bfs(int v) 
	{
		List<Integer> searchOrder = new ArrayList<>();
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
		{
			parent[i] = -1; // Initialize parent[i] to -1
		}

		java.util.LinkedList<Integer> queue = new java.util.LinkedList<>(); // list used as a queue
		boolean[] isVisited = new boolean[vertices.size()];
		queue.offer(v); // Enqueue v
		isVisited[v] = true; // Mark it visited

		while (!queue.isEmpty()) 
		{
			int u = queue.poll(); // Dequeue to u
			searchOrder.add(u); // u searched
			for (WeightEdge e: neighbors.get(u)) 
			{
				if (!isVisited[e.v]) 
				{
					queue.offer(e.v); // Enqueue w
					parent[e.v] = u; // The parent of w is u
					isVisited[e.v] = true; // Mark it visited
				}
			}
		}

		return new Tree(v, parent, searchOrder);
	}
}

class WeightEdge implements Comparable<WeightEdge>
{
	public int u;
	public int v;
	public int weight;

	public WeightEdge(int u, int v, int weight)
	{
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

	@Override
	public boolean equals(Object o)
	{
		return u == ((WeightEdge)o).u && v == ((WeightEdge)o).v;
	}
	
	@Override
	public int compareTo(WeightEdge edge)
	{
		if (weight > edge.weight)
		{
			return 1;
		}
		
		else if (weight < edge.weight)
		{
			return -1;
		}
		
		return 0;
	}
}

interface WeightGraph<V> 
{
	/** Return the number of vertices in the graph */
	public int getSize();

	/** Return the vertices in the graph */
	public List<V> getVertices();

	/** Return the object for the specified vertex index */
	public V getVertex(int index);

	/** Return the index for the specified vertex object */
	public int getIndex(V v);

	/** Return the neighbors of vertex with the specified index */
	public List<Integer> getNeighbors(int index);

	/** Return the degree for a specified vertex */
	public int getDegree(int v);

	/** Print the edges */
	public void printEdges();

	/** Clear the graph */
	public void clear();

	/** Add a vertex to the graph */  
	public boolean addVertex(V vertex);

	/** Add an edge to the graph */  
	public boolean addEdge(int u, int v, int weight);

	/** Obtain a depth-first search tree */
	public WeightAbstractGraph<V>.Tree dfs(int v);

	/** Obtain a breadth-first search tree */
	public WeightAbstractGraph<V>.Tree bfs(int v);
}
