//24 OCT 2018
//BTree.Java
//BTree data structure with dealings(all methods) with node.
//Includes Visualizer

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BTree<E>
{
	private Node<E> root;
	private Comparator<E> comparator;
	private int order;  

	public BTree(int order, Comparator<E> comp)
	{
		this.root = new Node<>(order, comp); 
		this.order = order;
		this.comparator = comp;
	}

	/**
	 * Determines if this tree is empty or not
	 * @return true if tree is empty, false otherwise 
	 */
	public boolean isEmpty() 
	{
		return root.isEmpty();
	}

	/**
	 * Adds the given item to the tree at the correct node
	 * @param item: item to insert in tree
	 */
	public void add(E item) 
	{
		Node<E> leaf = findLeaf(root, item);

		leaf.leafAdd(item);

		Node<E> current = leaf;

		while(current.hasOverflow()) 
		{
			current.split();
			current = current.getParent();
		}	 
		
		if(root.getParent() != null)
		{
			root = root.getParent();
		}
	}
	
	/**
	 * Finds the leaf node in the sub-tree rooted at the given node where the given item should be inserted
	 * @param node: node to begin search from(root)
	 * @param item: item to insert in node
	 * @return leaf node where to insert item
	 */
	private Node<E> findLeaf(Node<E> node, E item)
	{ 
		Node<E> current = node;

		while(!current.isLeaf())
		{
			current = current.childToFollow(item);
		}

		return current;
	}

	/**
	 * Determines if the tree contains the given item
	 * @param item: item to find in the tree
	 * @return true if tree contains given item, false otherwise
	 */
	public boolean contains(E item)
	{
		return findNode(root, item) != null;
	}

	/**
	 * Finds the node with the given item in the subtree with the given root
	 * @param root: root of the tree
	 * @param item: item to search for
	 * @return node which has the given item
	 */
	private Node<E> findNode(Node<E> root, E item)
	{
		Node<E> current = root;

		while(!current.contains(item))
		{
			if(current.isLeaf())
			{
				return null;
			}

			current = current.childToFollow(item);
		}

		return current;
	}

	/**
	 * Performs inorder traversal of this tree
	 * @param visitor: visitor to collect items in nodes
	 */
	public void inorder(Visitor<E> visitor)
	{
		inorder(visitor, root);
	}

	/**
	 * Performs in order traversal of the tree with the given root
	 * @param visitor: visitor to collect items in nodes
	 * @param root: root of the tree to transverse through
	 */
	private void inorder(Visitor<E> visitor, Node<E> root)
	{
		if(root.isLeaf())
		{
			for(E data: root.getData())
			{
				visitor.visit(data);
			}
		}

		else
		{
			int i = 0;

			for(Node<E> child: root.getChildren())
			{
				inorder(visitor, child);

				if(i < root.getData().size())
				{
					visitor.visit(root.getData().get(i));
				}

				i++;
			}
		}
	}

	/**
	 * Returns a string representation of this tree in level-order traversal
	 * @return returns a string representation of the tree
	 */ 
	public String toString()
	{ 
		String str = "";

		Queue<Node<E>> queue = new LinkedList<Node<E>>();

		queue.offer(root);

		while(!queue.isEmpty())
		{ 
			Node<E> node = queue.poll();

			str = str + node.getData() + " ";

			queue.addAll(node.getChildren());
		}

		str = str.replaceAll(",", "");
		return str = "[" + str.trim() + "]";
	}
	
	/**
	 * Finds the node with the largest value in the tree starting from curr node
	 * @param curr: the current(root) node
	 * @return node with the largest value in the tree
	 */
	public Node<E> findMaxNode(Node<E> curr)
	{
		if(curr.isLeaf()) 
		{
			return curr; 
		}

		else
		{
			return findMaxNode(curr.getChildren().getLast());
		}
	}
	
	/**
	 * Removes the given item from tree
	 * @param item: item to remove
	 * @return true if remove item, false otherwise
	 */
	public boolean remove(E item)
	{
		Node<E> node = findNode(root, item);

		if(node == null) 
		{
			return false;
		}

		else
		{
			if(node.isLeaf())
			{
				node.leafRemove(item);
				repair(node); //node must be a leaf

				return true;
			}

			else
			{ 
				int index = node.getData().indexOf(item);

				Node<E> nodeMax = findMaxNode(node.getChildren().get(index)); 

				E element = nodeMax.getData().removeLast();
				node.getData().set(index, element);

				repair(nodeMax); //nodeMax must be a leaf

				return true;
			}
		} 
	} 
	
	/**
	 * Repairs the given leaf after removal
	 * @param leaf: the leaf node to repair
	 */
	public void repair(Node<E> leaf)
	{
		Node<E> current = leaf;

		while(current != root && current.isDeficient())
		{
			current.borrow();
			current = current.getParent();
		} 

		if(root.isEmpty() && !root.isLeaf())
		{
			root = root.getChildren().getFirst();
		}
	}
	
	/**
	 * For Visualizer
	 * @return root of the tree
	 */
	public Node<E> getRoot()
	{ 
		return root;
	} 
}
