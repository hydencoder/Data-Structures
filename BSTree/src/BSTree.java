import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class BSTree<E> implements Iterable<E>, Cloneable
{
	private class Node
	{
		private E data;
		private Node leftChild;
		private Node rightChild;
		private Node parent;

		public Node(E data)
		{
			this.data = data;
		}

		// For Visualizer:
		E getData()
		{
			return data;
		}

		Node getLeft()
		{
			return leftChild;
		}

		Node getRight()
		{
			return rightChild;
		}

		Node getParent()
		{
			return parent;
		}

		@Override
		public Object clone() throws CloneNotSupportedException
		{
			return super.clone();
		}
	}

	private Node root;

	private Comparator<E> comparator;

	public BSTree(Comparator<E> comp)
	{
		root = null;
		comparator = comp;
	}

	// For Visualizer:
	public Node getRoot()
	{
		return root;
	}

	/**
	 * Checks if the tree is empty
	 * @return true if the map is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return root == null;
	}

	/**
	 * Adds a node to the tree using a loop
	 * No duplicates allowed 
	 * @param item: Item of the node to be added in the tree
	 */
	public void addLoop(E item)
	{
		Node node = new Node(item);

		if (isEmpty())
		{
			root = node;
			return;
		}

		Node current;
		Node parent;
		current = parent = root;

		while (current != null)
		{
			if (comparator.compare(item, current.data) < 0)
			{
				parent = current;
				current = current.leftChild;
			}

			else
			{
				parent = current;
				current = current.rightChild;
			}
		}

		node.parent = parent;

		if (comparator.compare(item, parent.data) < 0)
		{
			parent.leftChild = node;
		}

		else
		{
			parent.rightChild = node;
		}
	}

	/**
	 * Finds the maximum value in the tree
	 * @return the maximum value in the tree
	 * @throws NoSuchElementException if tree is empty
	 */
	public E maxValueLoop()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}

		Node node = findMaxNodeLoop(root);
		return node.data;	
	}

	/**
	 * Finds the node with the maximum value in the tree
	 * @param current: Node where to begin the search
	 * @return the node with the maximum element in the tree
	 */
	private Node findMaxNodeLoop(Node current)
	{
		while (current.rightChild == null)
		{
			return current;
		}

		return current = current.rightChild;
	}

	/**
	 * Finds the minimum value in the tree
	 * @return the minimum value in the tree
	 * @throws NoSuchElementException if tree is empty
	 */
	public E minValueLoop()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}

		Node node = findMinNodeLoop(root);
		return node.data;	
	}

	/**
	 * Finds the node with the minimum value in the tree
	 * @param current: Node where to begin the search
	 * @return the node with the minimum element in the tree
	 */
	private Node findMinNodeLoop(Node current)
	{
		while (current.leftChild == null)
		{			
			return current;
		}

		return current = current.leftChild;
	}

	/**
	 * Checks if the tree contains the given item using loop
	 * @param item: item of the node to be found
	 * @return true if item exists, false otherwise 
	 */
	public boolean containsLoop(E item)
	{
		return findNodeLoop(root, item) != null;
	}

	/**
	 * Checks if the tree contains the node with the given item
	 * @param current: Node where to begin the search
	 * @param item: item of the node to look for
	 * @return Node which has the given item, or null if node doesn't exist
	 */
	private Node findNodeLoop(Node current, E item)
	{
		while (current != null)
		{
			if (comparator.compare(item, current.data) < 0)
			{
				current = current.leftChild;
			}

			else if (comparator.compare(item, current.data) > 0)
			{
				current = current.rightChild;
			}

			else
			{
				return current;
			}
		}

		return null; 
	}

	/**
	 * Adds a node to the tree using recursion 
	 * No duplicates allowed 
	 * @param item: Item of the node to added in the tree
	 */
	public void add(E item)
	{
		add(root, item);
	}

	/**
	 * Adds a node to the tree using recursion 
	 * No duplicates allowed 
	 * @param curr: The node where to begin finding appropriate adding place
	 * @param item: Item of the node to added in the tree
	 */
	private void add(Node curr, E item)
	{
		if (curr != null)
		{
			if (comparator.compare(item, curr.data) < 0)
			{
				if (curr.leftChild != null)
				{
					add(curr.leftChild, item);
				}

				else
				{
					Node node = new Node(item);
					curr.leftChild = node;
					node.parent = curr;
					return;
				}
			}

			else
			{
				if (curr.rightChild != null)
				{
					add(curr.rightChild, item);
				}

				else
				{
					Node node = new Node(item);
					curr.rightChild = node;
					node.parent = curr;
					return;
				}
			}
		}

		else
		{
			Node node = new Node(item);
			root = node;
		}
	}

	/**
	 * Finds the maximum value in the tree
	 * @return the maximum value in the tree
	 * @throws NoSuchElementException if tree is empty
	 */
	public E maxValue()
	{
		Node node = findMaxNode(root);

		if (node == null) 
		{
			throw new NoSuchElementException();
		}

		return node.data;
	}

	/**
	 * Finds the node with the maximum value in the tree
	 * @param curr: Node where to begin the search
	 * @return the node with the maximum element in the tree
	 */
	private Node findMaxNode(Node curr)
	{
		if (curr == null)
		{
			return null;
		}
		
		else
		{
			if (curr.rightChild != null)
			{
				return findMaxNode(curr.rightChild);
			}
			
			return curr;
		}
	}

	/**
	 * Finds the minimum value in the tree
	 * @return the minimum value in the tree
	 * @throws NoSuchElementException if tree is empty
	 */
	public E minValue()
	{
		Node node = findMinNode(root);

		if (node == null)
		{
			throw new NoSuchElementException();
		}

		return node.data;
	}

	/**
	 * Finds the node with the minimum value in the tree
	 * @param curr: Node where to begin the search
	 * @return the node with the minimum element in the tree
	 */
	private Node findMinNode(Node curr)
	{
		if (curr != null)
		{
			if (curr.leftChild != null)
			{
				return findMinNode(curr.leftChild);
			}
		}

		return curr;
	}

	/**
	 * Checks if the tree contains the given item using recursion
	 * @param item: item of the node to be found
	 * @return true if item exists, false otherwise 
	 */
	public boolean contains(E item)
	{
		return findNode(root, item) != null;
	}

	/**
	 * Checks if the tree contains the node with the given item using recursion
	 * @param curr: Node where to begin the search
	 * @param item: item of the node to look for
	 * @return Node which has the given item, or null if node doesn't exist
	 */
	private Node findNode(Node curr, E item)
	{
		if (curr != null)
		{
			if (comparator.compare(item, curr.data) < 0)
			{
				return findNode(curr.leftChild, item);
			}

			else if (comparator.compare(item, curr.data) > 0)
			{
				return findNode(curr.rightChild, item);
			}
		}

		return curr;
	}

	/**
	 * Removes the given item from the tree
	 * @param item: item of the node to look for
	 * @return true if node with item removed, false if didn't remove because item doesn't exist
	 */
	public boolean remove(E item)
	{
		Node node = findNode(root, item);

		if (node != null)
		{
			if (node.leftChild == null || node.rightChild == null) 
			{
				removeMissing(node);
			}

			else
			{
				removeHasBoth(node); // Has both children.
			}

			return true;
		}

		return false; // item does not exist.
	}

	/**
	 * Removes the node(with the specified item) with missing children(s)
	 * @param node: node with the item and missing children(s)
	 */
	private void removeMissing(Node node) 
	{
		Node child;

		if (node.leftChild != null)
		{
			child = node.leftChild;
		}

		else 
		{
			child = node.rightChild;
		}

		if (child == null) // node has no children
		{ 
			if (node == root) // Node is root and a leaf
			{
				root = null;
			}

			// Does node exist to left or right of its parent. We know node is a leaf
			else if (node == (node.parent).leftChild)
			{
				(node.parent).leftChild = null;
			}

			else
			{
				(node.parent).rightChild = null;	
			}

			node.parent = null;
		}

		else 
		{
			if (node == root)
			{
				child.parent = null;
				root = child;
				return;
			}

			if (node == (node.parent).leftChild) // Does node exist to right or left its parent. We know child exists to left.
			{
				(node.parent).leftChild = child;
			}

			else
			{
				(node.parent).rightChild = child;
			}

			child.parent = node.parent;
			node.parent = null;
		}
	}

	/**
	 * Removes the node(with the specified item) which has both children
	 * @param node: node with the item and both children(s)
	 */
	private void removeHasBoth(Node node) 
	{
		Node leftChild = node.leftChild;

		Node max = findMaxNode(leftChild); 
		node.data = max.data;
		removeMissing(max); // max must not have rightChild. Can or cannot have left child.
	}

	/**
	 * Represents the BSTree in preorder traversal style
	 * @param visitor: collects the items in nodes by visiting them in preorder traversal style
	 */
	public void preorder(Visitor<E> visitor) // + 1 2
	{
		preorder(visitor, root);
	}

	/**
	 * Represents the BSTree in preorder traversal style
	 * @param visitor: collect the items in nodes by visiting them in preorder traversal style
	 * @param curr: the current node which is being visited
	 */
	private void preorder(Visitor<E> visitor, Node curr)
	{
		if (curr == null)
		{
			return;
		}

		visitor.visit(curr.data); 
		preorder(visitor, curr.leftChild);
		preorder(visitor, curr.rightChild);
	}

	/**
	 * Represents the BSTree in inorder traversal style
	 * @param visitor: collects the items in nodes by visiting them in inorder traversal style
	 */
	public void inorder(Visitor<E> visitor) // 1 + 2
	{
		inorder(visitor, root);
	}

	/**
	 * Represents the BSTree in inorder traversal style
	 * @param visitor: collects the items in nodes by visiting them in inorder traversal style
	 * @param curr: the current node which is being visited
	 */
	private void inorder(Visitor<E> visitor, Node curr)
	{
		if (curr == null)
		{
			return;
		}

		inorder(visitor, curr.leftChild);
		visitor.visit(curr.data); 
		inorder(visitor, curr.rightChild);
	}

	/** 
	 * Represents the BSTree in postorder traversal style
	 * @param visitor: collects the items in nodes by visiting them in postorder traversal style
	 */
	public void postorder(Visitor<E> visitor) // 1 2 +
	{
		postorder(visitor, root);
	}

	/**
	 * Represents the BSTree in postorder traversal style
	 * @param visitor: collects the items in nodes by visiting them in postorder traversal style
	 * @param curr: the current node which is being visited
	 */
	private void postorder(Visitor<E> visitor, Node curr)
	{
		if (curr == null)
		{
			return;
		}

		postorder(visitor, curr.leftChild);
		postorder(visitor, curr.rightChild);
		visitor.visit(curr.data); 
	}

	/**
	 * Returns an Iterator object
	 * @return Iterator object
	 */
	@Override
	public Iterator<E> iterator()
	{
		return new BSTIterator(); 
	}

	/**
	 * Returns an Iterator object
	 * @return Iterator object
	 */
	public Iterator<E> preorderIterator()
	{
		return new PreOrderIterator();
	}

	// Iterator class for BSTree
	private class BSTIterator implements Iterator<E> //Level order
	{
		private Queue<Node> queue;

		public BSTIterator()
		{
			queue = new LinkedList<Node>();

			if (root != null) // Do not push null root -> next will throw NullpointerException
			{
				queue.offer(root);
			}
		}

		@Override
		public boolean hasNext() 
		{
			return !queue.isEmpty();
		}

		@Override
		public E next() 
		{
			if (hasNext())
			{
				Node node = queue.poll();

				if (node.leftChild != null)
				{
					queue.offer(node.leftChild);
				}

				if (node.rightChild != null)
				{
					queue.offer(node.rightChild);
				}

				return node.data;
			}

			else
			{
				throw new NoSuchElementException();
			}
		}
	}

	private class PreOrderIterator implements Iterator<E>
	{
		private Stack<Node> stack;

		public PreOrderIterator()
		{
			stack = new Stack<>();

			if (root != null) // Do not push null root-> next will throw NullpointerException
			{
				stack.push(root); 
			}
		}

		@Override
		public boolean hasNext() 
		{
			return !stack.isEmpty();
		} 

		@Override
		public E next() 
		{
			if (hasNext())
			{
				Node node = stack.pop();

				if (node.rightChild != null)
				{
					stack.push(node.rightChild);
				}

				if (node.leftChild != null)
				{
					stack.push(node.leftChild);
				}

				return node.data;
			}

			else
			{
				throw new NoSuchElementException();
			}
		}
	}

	/**
	 * Represents the BSTree as a string in level wise order
	 * @return the string representation of the tree
	 */
	@Override
	public String toString()
	{
		String str = "";

		Iterator<E> iterator = iterator();

		while (iterator.hasNext())
		{
			str += iterator.next() + " ";
		}

		return "[" + str.trim() + "]";
	}

	/**
	 * Represents the BSTree as a string in preorder 
	 * @return the string representation of the tree in preorder
	 */
	public String toStringPre()
	{
		String str = "";

		Iterator<E> iterator = preorderIterator();

		while (iterator.hasNext())
		{
			str += iterator.next() + " ";
		}

		return "[" + str.trim() + "]";
	}

	/**
	 * Checks if the given object is equal to the calling BSTree 
	 * @param obj: object to be checked if it is equal to the calling BSTree
	 * @return true if the given object is equal, false otherwise
	 */
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (!(obj instanceof BSTree))
		{
			return false;
		}

		return equals(root, ((BSTree)obj).root);
	}

	/**
	 * Checks if the given object is equal to the calling BSTree
	 * @param root1: starting node of the calling tree 
	 * @param root2: starting node of the tree to check
	 * @return true if given tree is equal, false otherwise
	 */
	private boolean equals(Node root1, Node root2)
	{
		if (root1 == null &&  root2 == null) // both null
		{
			return true;
		}

		if (root1 == null || root2 == null) // one of them null, other is not
		{
			return false;
		}

		if (root1.data.equals(root2.data))
		{
			if (equals(root1.leftChild, root2.leftChild))
			{
				if (equals(root1.rightChild, root2.rightChild))
				{
					return true;
				}

				else
				{
					return false;
				}
			}

			else
			{
				return false;
			}	
		}

		else
		{
			return false;
		}
	}

	/**
	 * Returns a copy of this tree
	 * @return deep cloned BSTree
	 */
	@Override
	public Object clone()
	{
		try
		{
			BSTree<E> copy = (BSTree<E>) super.clone();
			copy.comparator = this.comparator;
			copy.root = copyTree(this.root);

			return copy;
		}

		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns a copy of the tree rooted at the given node
	 * @return the node(root) of the copy tree
	 */
	private Node copyTree(Node curr) 
	{
		if (curr == null)
		{
			return null;
		}

		else
		{
			Node node = new Node(curr.data);

			node.leftChild = copyTree(curr.leftChild);
			node.rightChild = copyTree(curr.rightChild);

			if (node.leftChild != null)
			{
				node.leftChild.parent = node;
			}

			if (node.rightChild != null)
			{
				node.rightChild.parent = node;
			}

			return node;
		}
	}

	/**
	 * Creates the tree from the given preorder array of items
	 * @param items: values in preorder to give to new tree's nodes
	 * @param comp: comparator of the new tree
	 */
	public BSTree(E[] items, Comparator<E> comp)
	{
		this.comparator = comp;
		this.root = rebuildPreorder(items, 0, items.length);
	}

	/**
	 * Creates a tree from the given preorder array of items contained between indices [i , j)
	 * @param i: starting index(included)
	 * @param j: ending index(not included)
	 * @return the node which will become the root of the tree 
	 */
	private Node rebuildPreorder(E[] items, int i, int j) 
	{
		if (i >= j) 
		{
			return null;
		}

		Node node = new Node(items[i]);
		int range = i + 1;

		while (range < j && comparator.compare(items[i], items[range]) == 1)
		{
			range++;
		}

		node.leftChild = rebuildPreorder(items, ++i, range); // range not included
		node.rightChild = rebuildPreorder(items, range, j);	// j not included

		if (node.leftChild != null)
		{
			node.leftChild.parent = node;
		}

		if (node.rightChild != null)
		{
			node.rightChild.parent = node;
		}

		return node;
	}
}
