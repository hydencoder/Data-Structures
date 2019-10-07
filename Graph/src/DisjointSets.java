import java.util.HashMap;

public class DisjointSets<E>
{ 
	private class Node
	{
		private E item;
		private int rank;
		private Node parent;

		public Node(E item) 
		{
			this.item = item;
			this.rank = 0;
			this.parent = this; 
		}
	}

	private HashMap<E, Node> set; 

	public DisjointSets(Iterable<E> items)
	{
		set = new HashMap<>();

		for(E item: items)
		{		
			makeSet(item);
		}
	} 
	
	/**
	 * Adds a new set containing the given item, unless the item is already in a set.
	 * @param item: the item in the set
	 * @return boolean if item(node) exists, false otherwise
	 */
	public boolean makeSet(E item)
	{
		if(!set.containsKey(item))
		{
			set.put(item, new Node(item));
			return true;
		}

		return false;
	}

	/**
	 * Returns the representative of the set that contains the given element
	 * @param a: node to find representative of
	 * @return node representative of this set
	 */
	private Node findRep(Node a)
	{
		if(a == a.parent)
		{
			return a;
		}

		else
		{
			Node representative = findRep(a.parent);
			a.parent = representative;
			return representative;
		}
	}
	
	/**
	 * Determines if the given elements a and b are in the same set.
	 * @param a: element to check if in set
	 * @param b: element to check if in set
	 * @return boolean true if a and b are the same set.
	 */
	public boolean sameSet(E a, E b)
	{
		System.out.print((set.get(a)).equals(set.get(b)));
		return findRep(set.get(a)).equals(set.get(b)); 
	}

	public void union(E a, E b)
	{
		Node repA = findRep(set.get(a));
		Node repB = findRep(set.get(b));

		if(repA.rank < repB.rank)
		{ 
			repA.parent = repB;
		}

		else if(repA.rank > repB.rank)
		{
			repB.parent = repA;
		}

		else
		{
			repA.parent = repB;
			repB.rank++;
		}
	}
}