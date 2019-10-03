//24 OCT 2018
//Node.Java
//Node Structure for Btree

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class Node<E> 
{
	private int order;
	private Comparator<E> comparator;
	private Node<E> parent;
	private LinkedList<E> data;
	private LinkedList<Node<E>> children;

	public Node(int order, Comparator<E> comp)
	{ 
		this.order = order;
		this.comparator = comp;
		this.parent = null;
		this.data = new LinkedList<E>();
		this.children = new LinkedList<Node<E>>();
	}

	public Node(int order, Comparator<E> comp, Node<E> left, E item, Node<E> right)
	{ 
		this(order, comp);
 
		data.add(item);
		children.add(left);
		children.add(right);
		left.parent = this;
		right.parent = this;
	}
	
	public Node(int order, Comparator<E> comp, Node<E> p, LinkedList<E> data, LinkedList<Node<E>> children)
	{ 
		this(order, comp);

		this.parent = p;
		this.data = data;
		this.children = children;	
		
		for(Node<E> node: children)
		{
			node.parent = this;
		}
	}

	/**
	 * Determines if this node is filled beyond capacity and needs to be split
	 * @return true if needs to split, false otherwise 
	 */
	public boolean hasOverflow()
	{
		return data.size() > order;
	}

	/**
	 * Determines if this node is empty(data)
	 * @return true if node is empty, false otherwise 
	 */
	public boolean isEmpty()
	{
		return data.isEmpty();
	}

	/**
	 * Determines if this node is a leaf
	 * @return true if node is leaf, false otherwise 
	 */
	public boolean isLeaf()
	{
		return children.isEmpty();
	}

	/**
	 * Returns the next child to follow in order to locate the given item
	 * @param item: item to search for
	 * @return node to follow for inserting item 
	 */
	public Node<E> childToFollow(E item) 
	{
		int i = 0;

		for(E element: data)
		{
			if(comparator.compare(item, element) < 0)
			{
				return children.get(i);
			}

			i++;
		}

		return children.getLast();
	}

	/**
	 * Inserts the given item in the correct position among this node's data items
	 * @param item: item to insert in node
	 */
	public void leafAdd(E item)
	{		
		if(isLeaf())
		{
			int i = 0;

			for(E element: data)
			{
				if(comparator.compare(item, element) < 0)
				{
					data.add(i, item);
					return;
				}

				i++;
			}

			data.addLast(item);
		}
	}

	/**
	 * Splits this node 
	 */
	public void split()
	{ 
		int itemIndex = data.size()/2;
		E item = data.get(itemIndex);

		LinkedList<E> rightData = new LinkedList<E>(data.subList(itemIndex+1, data.size()));
		data.subList(itemIndex, data.size()).clear();
		
		LinkedList<Node<E>> rightChildren = new LinkedList<Node<E>>();
		
		if(!isLeaf()) // leaf doesn't have children
		{
			rightChildren = new LinkedList<Node<E>>(children.subList(itemIndex+1, children.size()));
			children.subList(itemIndex+1, children.size()).clear();
		} 
		
		Node<E> nodeRight = new Node<E>(order, comparator, parent, rightData, rightChildren);		
		 
		if(parent == null) 
		{
			Node<E> nodeParent = new Node<E>(order, comparator, this, item, nodeRight);
		}
	
		else
		{
			int index = parent.children.indexOf(this); 
			parent.data.add(index, item);
			parent.children.add(index+1, nodeRight);   
		}
	}

	/**
	 * Determines if this node contains the given item
	 * @param item: item to check if it is in this node
	 * @return true if node contains item, false otherwise
	 */
	public boolean contains(E item)
	{
		return data.contains(item);
	}

	/**
	 * For Visualizer
	 * @return order of the node
	 */
	public int getOrder()
	{
		return order;
	}

	/**
	 * For Visualizer
	 * @return parent of the node
	 */
	public Node<E> getParent()
	{
		return parent;
	}
	
	/**
	 * For Visualizer
	 * @return data of the node
	 */
	public LinkedList<E> getData()
	{
		return data;
	}

	/**
	 * For Visualizer
	 * @return children of the node
	 */
	public LinkedList<Node<E>> getChildren()
	{
		return children;
	}
	
	/**
	 * Checks if the node is filled below the minimum capacity
	 * @return true if efficient, false otherwise
	 */
	public boolean isDeficient() 
	{
		return data.size() < order/2;	
	}
	
	/**
	 * Checks if node can donate a data member without becoming deficient itself
	 * @return true if node can donate, false otherwise
	 */
	public boolean canDonate()
	{
		return data.size() > order/2;
	}
	
	/**
	 * Removes the given item from node's data
	 * @param item: item in node to remove
	 */
	public void leafRemove(E item)
	{
		Iterator<E> iterator = data.iterator();

		while(iterator.hasNext())
		{
			if(comparator.compare(iterator.next(), item) == 0)
			{
				iterator.remove();
				return;
			}
		}
	}
	
	/**
	 * Manages the process of borrowing an item
	 */
	public void borrow() // made sure node has parent(not root) //we already made sure parent has children(not a leaf)
	{
		int myIndex = parent.children.indexOf(this); 
		Node<E> mySibling;

		if(myIndex == 0) // I am first
		{
			mySibling = parent.children.get(myIndex+1); // One after me
		}

		else if(myIndex == parent.children.size()-1) // I am last
		{
			mySibling = parent.children.get(myIndex-1); // One before me
		}

		else // I am in middle
		{
			int leftSize = parent.children.get(myIndex-1).data.size();
			int rightSize = parent.children.get(myIndex+1).data.size();

			if(leftSize >= rightSize) // left used for random picking
			{
				mySibling = parent.children.get(myIndex-1); 
			}

			else 
			{
				mySibling = parent.children.get(myIndex+1);
			}
		}
	
		if(myIndex < parent.children.indexOf(mySibling))
		{
			borrowRight(mySibling);
		}

		else
		{
			borrowLeft(mySibling);
		}
	}

	/**
	 * Borrows or fuse with a sibling on the right
	 * @param sibling: node to borrow from or fuse into
	 */
	public void borrowRight(Node<E> sibling)
	{
		if(sibling.canDonate())
		{
			int index = parent.children.indexOf(this);

			E data = sibling.data.removeFirst();
			E parentData = parent.data.get(index);

			parent.data.set(index, data);

			this.data.add(parentData);	//add at end

			if(!sibling.isLeaf())
			{
				Node<E> child = sibling.children.removeFirst();
				children.addLast(child);
				child.parent = this; // sibling is no longer the parent
			}
		} 		

		else 
		{
			int index = parent.children.indexOf(this);
			E parentData = parent.data.remove(index);

			data.add(parentData);

			data.addAll(sibling.data);

			for(Node<E> child: sibling.children)
			{
				children.add(child);
				child.parent = this;
			}

			parent.children.remove(index+1);
		}
	}

	/**
	 * Borrows or fuse with a sibling on the left
	 * @param sibling: node to borrow from or fuse into
	 */
	public void borrowLeft(Node<E> sibling)
	{
		if(sibling.canDonate()) 
		{
			int index = parent.children.indexOf(this);

			E data = sibling.data.removeLast();
			E parentData = parent.data.get(index-1);

			parent.data.set(index-1, data);

			this.data.addFirst(parentData);	

			if(!sibling.isLeaf())
			{
				Node<E> child = sibling.children.removeLast();
				children.addFirst(child);
				child.parent = this; // sibling is no longer the parent
			}
		}		

		else 
		{
			int index = parent.children.indexOf(this);
			E parentData = parent.data.remove(index-1);
			
			data.add(parentData);
			data.addAll(0, sibling.data); 

			children.addAll(0, sibling.children); 
			
			for(int i = 0; i < sibling.children.size(); i++)
			{
				Node<E> child = sibling.children.get(i);
				children.add(i, child);
				child.parent = this;	
			}

			parent.children.remove(index-1);
		}
	}
}