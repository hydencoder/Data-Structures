// DLinkedList.java
// Data structure for a double linked list(head and tail pointers) with an iterator
// 13 SEP 2018

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DLinkedList<E> implements Iterable<E>, Cloneable
{
	private Node head;
	private Node tail;

	private class Node
	{
		E element;
		Node next;
		Node previous;
		
		Node(E element)
		{
			this.element = element;
		}
	}

	public DLinkedList()
	{
		head = tail = new Node(null); // Dummy node with null next and previous;
	}
	
	/**
	 * Checks if list is empty
	 * @return true if list is empty, false otherwise
	 */
	public boolean isEmpty() 
	{
		return head.next == null;
	}

	/**
	 * Adds a new node to the front of the list
	 * @param element: element of the new first node
	 */
	public void addFirst(E element)
	{
		Node newNode = new Node(element);
		
		Node temp = head.next; // may (single list) or may not (multi list) be tail
		
		head.next = newNode;
		newNode.previous = head;
		
		if (temp == null)
		{
			tail = newNode;
		}
		
		else
		{
			newNode.next = temp;
			temp.previous = newNode;
		}
	}

	/**
	 * Adds a new node to the end of the list
	 * @param element: element of the new last node
	 */
	public void addLast(E element)
	{
		Node newNode = new Node(element);

		tail.next = newNode;
		newNode.previous = tail;
		tail = newNode;
	}

	/**
	 * Adds new node at the given index to the list
	 * @param index: index of the new node
	 * @param element: element of the new node
	 * @throws IndexOutOfBoundsException if wrong index given
	 */
	public void add(int index, E element) 
	{		
		if (index < 0)
		{
			throw new IndexOutOfBoundsException();
		}

		if (isEmpty() && index == 0)
		{
			addFirst(element);
			return;
		}

		int count = 0;

		try
		{
			Node current = head.next;
			count = 1; 

			while (index >= count)  
			{
				current = current.next; // can throw exception 
				count++;
			}

			Node node = new Node(element);

			(current.previous).next = node; // can throw exception 
			node.previous = current.previous;
			node.next = current;
			current.previous = node;
		}

		catch (NullPointerException ex)
		{
			if (index == count-1)
			{
				addLast(element);
				return;
			}
			
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Returns the element of the first node in the list
	 * @return element of the first node in the list
	 * @throws NoSuchElementException if list is empty
	 */
	public E getFirst()
	{
		try
		{
			return (head.next).element;
		}
		
		catch (NullPointerException ex)
		{
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Returns the element of the last node
	 * @return element of the last node
	 * @throws NoSuchElementException if list is empty
	 */
	public E getLast() 
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		return tail.element;
	}
	
	/**
	 * Returns the element at the specified index
	 * @param index: index of the node
	 * @return element of the node at index
	 * @throws IndexOutOfBoundsException if invalid index is given
	 */
	public E get(int index)
	{
		if (index < 0 || isEmpty())
		{
			throw new IndexOutOfBoundsException();
		}
		
		int count = 0;
		
		try
		{
			Node current = head;
			
			while (index >= count) 
			{
				current = current.next; // can throw exception 
				count++;
			}
			
			return current.element;  // can throw exception 
		}
		
		catch (NullPointerException ex)
		{		
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Sets the new element of the node at the specified index
	 * @param index of the specified node
	 * @param element: the new element of the index specified node
	 * @return old element of node
	 * @throws IndexOutOfBoundsException if invalid index is given
	 */
	public E set(int index, E element)
	{
		if (index < 0 || isEmpty())
		{
			throw new IndexOutOfBoundsException();
		}
		
		int count = 0;
				
		try
		{
			Node current = head;
			
			while (index >= count)
			{
				current = current.next; // can throw exception 
				count++;
			}
			
			E e = current.element; // can throw exception
			current.element = element;
			return e;
		}
		
		catch (NullPointerException ex)
		{
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Checks if the node with element specified is in the list
	 * @param element: element of the node to be found
	 * @return true if such node exists, false otherwise
	 */
	public boolean contains(E element)
	{
		Node current = head.next;
		
		while (current != null)
		{
			if (current.element.equals(element))
			{
				return true;
			}
			
			current = current.next;
		}
		
		return false;
	}
	
	/**
	 * Clears the list by setting each node's previous and next to null
	 * and sets tail back to dummy node
	 */
	public void clear()
	{
		Node current = head;
		tail = head; // reset
		
		Node temp = current;
		
		while (temp != null)
		{
			temp = current.next;
			current.previous = null;
			current.next = null;
			current = temp;
		}
	}
	
	/**
	 * Represents the list as a string
	 * @return a string representation of the list
	 */
	@Override
	public String toString()
	{
		String str = "";

		Node current = head.next;

		while (current != null)
		{
			str += current.element + " ";
			current = current.next;
		}

		str = "[" + str.trim() + "]";
		return str;
	}
	
	/**
	 * Represents the list from end to beginning
	 * @return a string representation of the list in reverse order
	 */
	public String toStringRev()
	{	
		String str = "";
		Node current = tail;

		while (current != head)
		{
			str += current.element + " ";
			current = current.previous;
		}

		str = "[" + str.trim() + "]";
		return str;
	}
	
	/**
	 * Returns an Iterator object
	 * @return Iterator object
	 */
	@Override
	public Iterator<E> iterator() 
	{
		return new DLinkedListIterator();
	}
	
	// Iterator class for DlinkedList
	private class DLinkedListIterator implements Iterator<E>
	{
		private Node current;
		private boolean nextCalled;
		
		public DLinkedListIterator()
		{
			current = head.next;
		}
		
		@Override
		public boolean hasNext() 
		{
			return current != null;
		}

		@Override
		public E next() 
		{		
			if (hasNext())
			{
				E element = current.element;
				current = current.next;
				nextCalled = true;
				return element;
			}
			
			else
			{
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove()
		{
			if (nextCalled == true)
			{		
				if (current == null)
				{
					removeLast();
					nextCalled = false;
					return;
				}

				Node nextNode = current;
				Node prevNode = current.previous.previous;
				
				current.previous.next = null;
				current.previous.previous = null;
				
				prevNode.next = current;
				nextNode.previous = prevNode;
				
				nextCalled = false;
			} 
			
			else
			{
				throw new IllegalStateException();
			}
		}
	}

	/**
	 * Checks if the list contains a node with the element specified using Iterator
	 * @param element: element of the node to be found in the list
	 * @return true if a node with element is found; false otherwise
	 */
	public boolean containsIter(E element)
	{
		for (E item: this)
		{
			if (item.equals(element))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Removes the first element of the list
	 * @return element of the first node
	 * @throws NoSuchElementException if list is empty
	 */
	public E removeFirst() 
	{
		if (isEmpty()) 
		{
			throw new NoSuchElementException();
		}
		
		Node curr = head.next;
		
		if (curr == tail) // only one element
		{
			curr.previous = null;
			head.next = null;
			tail = head;
			return curr.element;
		}
		
		Node node = curr.next; 
		
		node.previous = head;
		curr.previous = null;
		curr.next = null;
		head.next = node;
		return curr.element;
	}
	
	/**
	 * Removes the last element of the list
	 * @return element of the last node
	 * @throws NoSuchElementException if list is empty
	 */
	public E removeLast() 
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		Node node = tail.previous; // cannot be head
		
		tail.previous = null;
		node.next = null;
		E ele = tail.element;
		tail = node;
		
		return ele;
	}
	
	/**
	 * Removes the element at the index node
	 * @param index: index of the node to be removed
	 * @return element of the removed node
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public E remove(int index) 
	{
		if (index < 0 || isEmpty())
		{
			throw new IndexOutOfBoundsException();
		}
		
		Node current = head.next; 
		int count = 0;
		
		while (index > count) 
		{
			current = current.next;	
	
			if (current == null)
			{
				throw new IndexOutOfBoundsException();
			}
			
			count++;
		}
		
		if (current == tail)
		{
			return removeLast();
		}
		
		Node prevNode = current.previous;
		Node nextNode = current.next;
		
		nextNode.previous = current.previous;
		prevNode.next = current.next;
		
		current.previous = null;
		current.next = null;
		
		return current.element;
	}
	
	/**
	 * Removes the first occurrence of the given element
	 * @param element: element of the node to be removed
	 * @return true if removing is successful, false otherwise
	 */
	public boolean removeItem(E element)
	{
		Node current = head.next;
		
		while (current != null)
		{
			if (current.element.equals(element))
			{
				if (current == tail)
				{
					removeLast();
					return true;
				}
				
				Node prevNode = current.previous;
				Node nextNode = current.next;
			
				nextNode.previous = current.previous;
				prevNode.next = current.next;
				
				current.previous = null;
				current.next = null;
				
				return true;
			}
			
			current = current.next;
		}
		
		return false;
	}
	
	/**
	 * Removes all occurrences of the element in the list
	 * @param element: element of the nodes to be removed
	 * @return true if removing is successful, false otherwise
	 */
	public boolean removeAll(E element) 
	{
		Node current = head.next;
		boolean removed = false;
		
		Node previous;
		Node nextNode;

		while (current != null)
		{
			if (current.element.equals(element))
			{ 
				if (current == tail)
				{
					removeLast();
					current = current.next;
					removed = true;
				}
				
				else
				{
					previous = current.previous;	//  Node one before current
					nextNode = current.next; 	//	Node one after current
					current.next = null;
					current.previous = null;
					previous.next = nextNode;
					nextNode.previous = previous;
					
					current = nextNode; 
					removed = true;
				}
			}

			else
			{
				current = current.next;
			}
		}
		
		return removed;
	}
	
	/**
	 * Checks if the given object is equal to the calling DlinkedList object
	 * @param list: list to be checked if it is equal to the calling DlinkedList object
	 * @return true if given object is equal, false otherwise
	 */
	public boolean equals(Object list) 
	{
		if (this == list) 
		{
			return true;
		}
		
		if (!(list instanceof DLinkedList)) 
		{   
			return false;
		}

		Iterator<E> thisIter = this.iterator();
		Iterator<E> listIter = ((Iterable<E>) list).iterator();

		while (thisIter.hasNext())
		{
			if (listIter.hasNext())
			{
				if (!thisIter.next().equals(listIter.next())) 
				{
					return false; // both had elements, but not equal
				}
			}

			else 
			{
				return false;  // thisIter has elements, but listIter does not
			}
		}

		if (listIter.hasNext())
		{
			return false; //  listIter has elements, but thisIter does not
		}

		return true; 
	}
	
	/**
	 * Makes a shallow copy of the calling DLinkedList
	 * @return return the copied list
	 */
	public Object clone()
	{
	    try 
	    {
	        DLinkedList<E> copyList = (DLinkedList<E>) super.clone(); // shallow copy -> Class type data fields(head and tail) shared

	        copyList.head = new Node(null); 
	        copyList.tail = copyList.head; 

	        for (E element : this)
	        {
	        	copyList.addLast(element);
	        }

	        return copyList;  
	    }
	    
	    catch (CloneNotSupportedException e) 
	    {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * Inserts all elements of the given collection at the given index
	 * @param index: index where to insert first new node with first element from collection
	 * @param collection: collection that contains elements to be put in new nodes
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public void addAll(int index, Iterable<E> collection) 
	{
		if (index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		
		Node current = head;
		
		for (int i = 0; i < index; i++)
		{
			current = current.next; 	
			
			if (current == null)
			{
				throw new IndexOutOfBoundsException();
			}
		}

		for (E element : collection)
		{
			Node node = new Node(element);
			
			if (current == tail)
			{
				current.next = node;
				node.previous = current;
				current = node;
				tail = node;
			}
			
			else
			{
				(current.next).previous = node;
				node.next = current.next;
				current.next = node;
				node.previous = current;
				
				current = node;
			}
		}
	}
	
	/**
	 * Removes all occurrences of the element in the list using iterator
	 * @param element: element of the nodes to be removed
	 * @return true if removing is successful, false otherwise
	 */
	public boolean removeAllIter(E element) 
	{
		Iterator<E> iterator = this.iterator();
		
		boolean removed = false;
		
		while (iterator.hasNext())
		{
			if (iterator.next().equals(element))
			{
				  iterator.remove();
				  removed = true;
			}
		}
		
		return removed;
	}
}
