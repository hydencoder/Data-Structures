import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphAlgos 
{  
	/**
	 * performs breadth first search starting from source vertex
	 * @param graph: graph to perform bfs on
	 * @param source: starting vertex(in string)
	 */
	public static void bfs(Graph graph, String source)
	{      
		StringBuilder sb = new StringBuilder(); 
		StringBuilder debug = new StringBuilder();  

		Queue<Vertex> queue = new LinkedList<>(); 
		Vertex current = graph.getVertex(source);

		sb.append(current + ":" + current.distance + " ");

		Set<Vertex> vertices = graph.getVertices(); 

		for(Vertex vertex: vertices)
		{
			vertex.visited = false;
		}

		current.visited = true;
		queue.offer(current);

		while(!queue.isEmpty()) 
		{  
			current = queue.poll(); 
			List<Edge> neighbours = graph.getAdjacent(current); 

			for(Edge edge: neighbours) 
			{
				if(!edge.getTarget().visited)
				{
					Vertex next = edge.getTarget();
					queue.offer(next);	
					next.visited = true;

					next.distance = edge.getSource().distance + 1;

					debug.append("(" + current + ", " + next + ")" + " ");
					sb.append(next + ":" + next.distance + " ");
				}
			} 
		}	

		System.out.print(debug);
		System.out.println(sb);
	}
	
	/**
	 * performs a depth first search staring from source vertex
	 * @param graph: graph to perform dfs on
	 * @param source: starting vertex(in string)
	 */
	public static void dfs(Graph graph, String source) 
	{ 
		//dfs(graph, graph.getVertex(source)); 
		
		dfsLoop(graph, graph.getVertex(source)); 
	}

	/**
	 * performs a depth first search staring from source vertex
	 * @param graph: graph to perform dfs on
	 * @param source: starting vertex
	 */
	public static void dfs(Graph graph, Vertex curr)
	{ 
		List<Edge> neighbours = graph.getAdjacent(curr); 
		curr.visited = true;

		System.out.print(curr + ":" + curr.distance + " ");

		for(Edge edge: neighbours)
		{
			if(!edge.getTarget().visited)
			{ 
				edge.getTarget().distance = curr.distance + 1; 
				System.out.print("(" + curr + ", " + edge.getTarget()  + ")" + " ");
				dfs(graph, edge.getTarget());	
			}
		}
	}
	
	public static void dfsLoop(Graph graph, Vertex curr) 
	{
		Stack<Vertex> stack = new Stack<>();

		Vertex current = curr;
		current.visited = true;
		stack.push(current);
 
		while(!stack.isEmpty())
		{    
			current = stack.peek();
			List<Edge> nei = graph.getAdjacent(current);
			int size = nei.size();
			int count = 0;
			
			Iterator<Edge> iterator = nei.iterator();
 
			while(iterator.hasNext()) 
			{
				Edge edge = iterator.next();

				if(edge.getTarget().visited == false)
				{ 
					edge.getTarget().visited = true;
					edge.getTarget().distance = current.distance + 1; 
					System.out.print("(" + current + ", " + edge.getTarget()  + ")" + " ");
					stack.push(edge.getTarget());
					break; 
				}	
				
				count++;
			}
			 
			System.out.println(stack);
			
			if(count == size)
			{
				stack.pop();
			}
		}	
	}  	 
	
	public static void dijkstra(Graph graph, String source) 
	{ 
		Set<Vertex> vertices = graph.getVertices(); 
		VertexComparator vertexComparator = new VertexComparator();
		PriorityQueue<Vertex> pq = new PriorityQueue<>(vertexComparator);

		for(Vertex vertex: vertices)
		{
			vertex.distance = Double.POSITIVE_INFINITY; 
			vertex.parent = null;
			vertex.visited = false;
			pq.offer(vertex);	
		} 

		Vertex vertex = graph.getVertex(source);

		pq.remove(vertex);
		vertex.distance = 0; 
		pq.add(vertex);
 
		while(!pq.isEmpty())
		{
			Vertex current = pq.poll();
			current.visited = true;
			
			List<Edge> neighbours = graph.getAdjacent(current); 

			for(Edge edge: neighbours)
			{ 
				if(!edge.getTarget().visited && edge.getTarget().distance > current.distance + edge.getWeight())
				{
					pq.remove(edge.getTarget());
					edge.getTarget().distance = current.distance + edge.getWeight();
					edge.getTarget().parent = current;
					pq.add(edge.getTarget());
				} 
			} 
					
			printPath(vertex, current); 
			printPathRec(vertex, current);
			System.out.println();
		}	 
	}
 
	public static void printPath(Vertex source, Vertex vertex) 
	{
		double total = 0;

		while(vertex != source) 
		{
			double edgeWeight = vertex.distance - vertex.parent.distance;
			System.out.print(vertex.vertex + " " + "--" + edgeWeight + "-->" + " ");
			total = total + edgeWeight;
			vertex = vertex.parent;
		}
 
		System.out.println(source.vertex + " " + "(total length " + total + ")");
	}
 
	public static void printPathRec(Vertex source, Vertex vertex)
	{
		if (source == vertex)
		{ 
			System.out.print(source.vertex);
		}
        
		else 
		{
			printPathRec(source, vertex.parent);
			double edgeWeight = vertex.distance - vertex.parent.distance;
			System.out.print(" --" + edgeWeight+ "--> " + vertex.vertex);
		}
	}

	/**
	 * performs minimum spanning tree on given graph
	 * @param graph: graph to perform prims algorithm
	 * @param source: starting string vertex
	 */
	public static Graph prim(Graph graph, String source)
	{ 
		Set<Vertex> vertices = graph.getVertices();			

		VertexComparator vertexComparator = new VertexComparator();
		PriorityQueue<Vertex> pq = new PriorityQueue<>(vertexComparator);

		for(Vertex vex: vertices) 
		{
			vex.distance = Double.POSITIVE_INFINITY;
			vex.visited = false;
			vex.parent = null;
			pq.offer(vex);	
		} 

		Vertex vertex = graph.getVertex(source);		
		pq.remove(vertex);
		vertex.distance = 0;
		pq.offer(vertex); 

		Graph mstGraph = new Graph();
		double total = 0;

		while(!pq.isEmpty())
		{ 			 					
			Vertex current = pq.poll();  
			current.visited = true;

			if(current.parent != null)
			{
				System.out.println(current.parent.vertex + "--" + current.distance + "--" + current.vertex );
				total = total + current.distance;
				mstGraph.addEdge(current.parent.vertex, current.vertex, current.distance);
			} 
			
			List<Edge> neighbours = graph.getAdjacent(current); 
			for(Edge edge: neighbours)
			{ 
				if(!edge.getTarget().visited && edge.getTarget().distance > edge.getWeight())
				{
					pq.remove(edge.getTarget());
					edge.getTarget().distance = edge.getWeight();
					edge.getTarget().parent = current;
					pq.add(edge.getTarget());
				} 
			}
		} 

		System.out.println(total);
		return mstGraph;	
	} 
	 
	public static Graph kruskal(Graph graph)
	{
		List<Edge> edges = graph.getEdges();  // O(E)
		Collections.sort(edges, new EdgeComparator()); // O(E * logE)

		DisjointSets<Vertex> disjointSet = new DisjointSets<>(graph.getVertices());

		Graph kruskal = new Graph(); 
		double total = 0;
 
		for(Edge edge: edges)
		{
			Vertex src = edge.getSource();
			Vertex tgt = edge.getTarget();
			double weight = edge.getWeight();

			if(!disjointSet.sameSet(src, tgt)) //O(logV)
			{
				kruskal.addEdge(src.vertex, tgt.vertex, weight);
				total = total + weight;
				System.out.println(src.vertex + "--" + weight + "--" + tgt.vertex);
				disjointSet.union(src, tgt);
			}
		}

		System.out.print(total);
		return kruskal;
	}
	
	public static int[][] initPredecessor(double[][] D)
	{
		int[][] predecessor = new int[D.length][D.length];

		for(int i = 0; i < predecessor.length; i++)
		{
			for(int j = 0; j < predecessor.length; j++)
			{
				if(i != j && D[i][j] != Double.POSITIVE_INFINITY)
				{
					predecessor[i][j] = i; 
				}

				else
				{
					predecessor[i][j] = -1;
				}
			}
		}

		return predecessor;
	}

	public static void floydWarshall(double[][] D, int[][] P)
	{ 
		for(int k = 0; k < D.length; k++)
		{
			for(int i = 0; i < D.length; i++)
			{
				for(int j = 0; j < D.length; j++)
				{
					if(D[i][k] + D[k][j] < D[i][j]) 
					{ 
						D[i][j] = D[i][k] + D[k][j];
						P[i][j] = P[k][j];
					}
				}
			}
		} 
	}
	
	public static void floydWarshall(Graph G)
	{
		double[][] dist = G.getMatrix();
		int[][] predecessor = initPredecessor(dist);

		floydWarshall(dist, predecessor);
		printAllPaths(dist, predecessor, G.getLabels());
		System.out.println();
	}
	
	public static void printAllPaths(double[][] D, int[][] P, String[] labels)
	{
		for(int i = 0; i < D.length; i++)
		{
			for(int j = 0; j < D.length; j++)
			{  
				if(D[i][j] != Double.POSITIVE_INFINITY)
				{
					printPath(i, j, D, P, labels);
					System.out.println();
				}
			}
		}
	}

	public static void printPath(int i, int j, double[][] D, int[][] P, String[] labels)
	{
		if(i == j)
		{
			System.out.print(labels[i]);
		}

		else 
		{
			printPath(i, P[i][j], D, P, labels);
			System.out.print("--" + (D[i][j] - D[i][P[i][j]]) + "-->" + labels[j]);
		}
	} 

	public static void main(String[] args) throws FileNotFoundException
	{ 
		//bfs
//		Graph g = new Graph("mm.txt");
//		bfs(g, "0");
//		Graph g2 = new Graph("directed.txt");
//		bfs(g2, "a");

//		Graph g = new Graph("mm.txt");
//		dfs(g, "0");
		
//		System.out.println();	
//		Graph g3 = new Graph("mm.txt");
//		dfs(g3, "0");	
		
		//dfs
//		Graph g3 = new Graph("mm.txt");
//		dfs(g3, "0");	
//		System.out.println();	
//		Graph g4 = new Graph("undirected.txt");
//		dfs(g4, "a"); 

//		//Dijkstra
//		System.out.println();	
//		Graph g5 = new Graph("mm.txt");
//		dijkstra(g5, "0");
	
//		//MST
//		System.out.println();	
//		Graph g6 = new Graph("mm.txt");
//		prim(g6, "0");
		
//		Graph g6 = new Graph("mm.txt");
//		prim(g6, "0");
//		
//		System.out.println();	
//		
		Graph g5 = new Graph("mm.txt");
		kruskal(g5);
	}  
}
