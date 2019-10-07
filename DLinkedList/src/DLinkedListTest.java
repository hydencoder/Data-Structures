import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class DLinkedListTest
{
	/**
	 * Lists for the three different configurations to test.
	 */
	private DLinkedList<Integer> empty;   // empty list
	private DLinkedList<Integer> single;  // one-element list
	private DLinkedList<Integer> multi;   // multi-element list

	/**
	 * Re-initializes the lists for the three different configurations.
	 * Called automatically by the JUnit framework before each of the
	 * test methods below.
	 */
	@Before
	public void setUp()
	{
		empty = new DLinkedList<Integer>();         // []
		assertEquals(empty.toString(), "[]");

		single = new DLinkedList<Integer>();        // [8]
		single.addFirst(8);
		assertEquals(single.toString(), "[8]");

		multi = new DLinkedList<Integer>();         // [4 3 5 7 1 6]
		multi.addFirst(6);
		assertEquals(multi.toString(), "[6]");
		assertEquals(multi.toStringRev(), "[6]");

		multi.addFirst(1);
		assertEquals(multi.toString(), "[1 6]");
		assertEquals(multi.toStringRev(), "[6 1]");

		multi.addFirst(7);
		assertEquals(multi.toString(), "[7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7]");
				
		multi.addFirst(5);
		assertEquals(multi.toString(), "[5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5]");
		
		multi.addFirst(3);
		assertEquals(multi.toString(), "[3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3]");
		
		multi.addFirst(4);
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
	}
	
	/**
	 * Test the isEmpty method.
	 */
	@Test
	public void test_isEmpty()
	{
		assertTrue(empty.isEmpty());
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");

		assertFalse(single.isEmpty());
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");

		assertFalse(multi.isEmpty());
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
	}

	/**
	 * Test the addFirst method. The elements were added in *setUp*
	 */
	@Test
	public void test_addFirst()
	{
		empty.addFirst(4); 		// []
		assertEquals(empty.toString(), "[4]");    
		assertEquals(empty.toStringRev(), "[4]");

		single.addFirst(9); 	// [8]
		assertEquals(single.toString(), "[9 8]"); 
		assertEquals(single.toStringRev(), "[8 9]");

		multi.addFirst(2);  	// [4 3 5 7 1 6]
		assertEquals(multi.toString(), "[2 4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4 2]");
	}

	/**
	 * Test the addLast method.
	 */
	@Test
	public void test_addLast()
	{
		empty.addLast(4);
		assertEquals(empty.toString(), "[4]");
		assertEquals(empty.toStringRev(), "[4]");

		single.addLast(7);
		assertEquals(single.toString(), "[8 7]");
		assertEquals(single.toStringRev(), "[7 8]");

		multi.addLast(9);
		assertEquals(multi.toString(), "[4 3 5 7 1 6 9]");
		assertEquals(multi.toStringRev(), "[9 6 1 7 5 3 4]");
	}
	
	/**
	 * Test the add
	 * @throws IndexOutOfBoundsException 
	 */
	@Test
	public void test_add() 
	{
		try
		{
			empty.add(-1, 3);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			empty.add(1, 3);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		empty.add(0, 3); 	// []
		assertEquals(empty.toString(), "[3]");
		assertEquals(empty.toStringRev(), "[3]");
		
		try
		{
			single.add(-1, 10);  // [8]
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		try
		{
			single.add(2, 10);  // [8]
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		single.add(0, 10);  // [8]
		assertEquals(single.toString(), "[10 8]");
		assertEquals(single.toStringRev(), "[8 10]");

		single = new DLinkedList<>(); // reset // [8]
		single.addFirst(8);
		
		single.add(1, 10);

		assertEquals(single.toString(), "[8 10]");
		assertEquals(single.toStringRev(), "[10 8]");
		
		try
		{
			multi.add(-1, 10);   // [4 3 5 7 1 6]
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		try
		{
			multi.add(7, 10);  
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		multi.add(0, 25);
		assertEquals(multi.toString(), "[25 4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4 25]");
		
		multi.add(1, 75);
		assertEquals(multi.toString(), "[25 75 4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4 75 25]");
		
		multi.add(8, 100);
		assertEquals(multi.toString(), "[25 75 4 3 5 7 1 6 100]");
		assertEquals(multi.toStringRev(), "[100 6 1 7 5 3 4 75 25]");
		
		multi.add(9, 13);
		assertEquals(multi.toString(), "[25 75 4 3 5 7 1 6 100 13]");
		assertEquals(multi.toStringRev(), "[13 100 6 1 7 5 3 4 75 25]");
	}
	
	/**
	 * Test the getFirst() method.
	 * @throws NoSuchElementException
	 */
	@Test
	public void test_getFirst() 
	{
		try 
		{ 
			empty.getFirst();
			fail();  // test failed	
		}
		
		catch (NoSuchElementException e) 
		{ 	/* test passed */ 	
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		assertTrue(single.getFirst().equals(8));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertFalse(single.getFirst().equals(7));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");

		assertTrue(multi.getFirst().equals(4));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertFalse(multi.getFirst().equals(10));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
	}
	
	/**
	 * Test the getLast() method.
	 * @throws NoSuchElementException
	 */
	@Test
	public void test_getLast() 
	{
		try 
		{ 
			empty.getLast();
			fail();  // test failed	
		}
		
		catch (NoSuchElementException e) 
		{ 	/* test passed */ 
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
	
		assertTrue(single.getLast().equals(8));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertFalse(single.getLast().equals(9));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");

		assertTrue(multi.getLast().equals(6));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertFalse(multi.getLast().equals(100));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
	}
	
	/**
	 * Test the getLast() method.
	 * @throws NoSuchElementException
	 */
	@Test
	public void test_contains() 
	{
		assertFalse(empty.contains(1));  // []
		assertEquals(empty.toString(), "[]");

		assertTrue(single.contains(8)); // [8]
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertFalse(single.contains(5));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");

		assertFalse(multi.contains(10)); // [4 3 5 7 1 6]
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertTrue(multi.contains(7)); 
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertTrue(multi.contains(4));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertTrue(multi.contains(6));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
	}
	
	/**
	 * Test the get() method.
	 * @throws NoSuchElementException
	 */
	@Test
	public void test_get()
	{
		try
		{
			empty.get(-1);
			fail();
		}
		
		catch (IndexOutOfBoundsException e) 
		{ 	/* test passed */ 	
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			empty.get(0);
			fail();
		}
		
		catch (IndexOutOfBoundsException e) 
		{ 	/* test passed */ 	
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			empty.get(1);
			fail();
		}
		
		catch (IndexOutOfBoundsException e) 
		{ 	/* test passed */ 	
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			single.get(-1);
			fail();
		}
		
		catch (IndexOutOfBoundsException e) 
		{ 	/* test passed */ 	
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		try
		{
			single.get(1);
			fail();
		}
		
		catch (IndexOutOfBoundsException e) 
		{	/* test passed */ 	
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
	
		assertTrue(single.get(0).equals(8));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		try
		{
			multi.get(-1);
			fail();
		}
		
		catch (IndexOutOfBoundsException e) 
		{ 	/* test passed */ 	
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		try
		{
			multi.get(6);
			fail();
		}
		
		catch (IndexOutOfBoundsException e) 
		{ 	/* test passed */ 	
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		assertTrue(multi.get(0).equals(4));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertTrue(multi.get(5).equals(6));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertTrue(multi.get(3).equals(7));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
	}
	
	/**
	 * Test the set() method.
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	public void test_set() throws IndexOutOfBoundsException
	{
		try
		{
			empty.set(-1, 5);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex) 
		{ 	/* test passed */ 	
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			empty.set(0, 5);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex) 
		{ 	/* test passed */ 	
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			empty.set(1, 5);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex) 
		{	 /* test passed */ 	
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			single.set(-1, 100);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		try
		{
			single.set(1, 100);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		assertTrue(single.set(0, 100).equals(8));
		assertEquals(single.toString(), "[100]");
		assertEquals(single.toStringRev(), "[100]");
			
		try
		{
			multi.set(-1, 150);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex) 
		{ 	/* test passed */ 	
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		try
		{
			multi.set(6, 150);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex) 
		{ 	/* test passed */ 	
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		assertTrue(multi.set(5, 101).equals(6));
		assertEquals(multi.toString(), "[4 3 5 7 1 101]");
		assertEquals(multi.toStringRev(), "[101 1 7 5 3 4]");
		
		
		assertTrue(multi.set(0, 55).equals(4));
		assertEquals(multi.toString(), "[55 3 5 7 1 101]");
		assertEquals(multi.toStringRev(), "[101 1 7 5 3 55]");
		
		assertTrue(multi.set(4, 60).equals(1));
		assertEquals(multi.toString(), "[55 3 5 7 60 101]");
		assertEquals(multi.toStringRev(), "[101 60 7 5 3 55]");
	}
	
	/**
	 * Test the clear() method.
	 */
	@Test
	public void test_clear() 
	{
		empty.clear();
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");

		single.clear();
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");

		multi.clear();
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
	}	
	
	/**
	 * Test the containsIter() method
	 */
	@Test
	public void test_containsIter() 
	{
		assertFalse(empty.containsIter(2));
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");

		assertTrue(single.containsIter(8));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertFalse(single.containsIter(7));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertTrue(multi.containsIter(4));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertTrue(multi.containsIter(6));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertTrue(multi.containsIter(5));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertFalse(multi.containsIter(0));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
	}	
	
	/**
	 * Test the removeFirst() method
	 * @throws NoSuchElementException
	 */
	@Test
	public void test_removeFirst() 
	{
		try
		{
			empty.removeFirst();	// []
			fail();  
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(empty.toString(), "[]"); 	
			assertEquals(empty.toStringRev(), "[]");
		}
		
		assertTrue(single.removeFirst().equals(8)); 	// [8]
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");
		
		try
		{
			assertFalse(single.removeFirst().equals(8));
			fail();
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(single.toString(), "[]");
			assertEquals(single.toStringRev(), "[]");
		}
		
		assertTrue(multi.removeFirst().equals(4)); 		// [4 3 5 7 1 6]
		assertEquals(multi.toString(), "[3 5 7 1 6]"); 	
		assertEquals(multi.toStringRev(), "[6 1 7 5 3]");
		
		assertTrue(multi.removeFirst().equals(3));
		assertEquals(multi.toString(), "[5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5]");
		
		assertTrue(multi.removeFirst().equals(5));
		assertEquals(multi.toString(), "[7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7]");
		
		assertTrue(multi.removeFirst().equals(7));
		assertEquals(multi.toString(), "[1 6]");
		assertEquals(multi.toStringRev(), "[6 1]");
		
		assertFalse(multi.removeFirst().equals(6));
		assertEquals(multi.toString(), "[6]");
		assertEquals(multi.toStringRev(), "[6]");
		
		assertTrue(multi.removeFirst().equals(6));
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
		
		try
		{
			multi.removeFirst();
			fail();
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(multi.toString(), "[]");
			assertEquals(multi.toStringRev(), "[]");
		}
	}	
	
	/**
	 * Test the removeLast() method
	 * @throws NoSuchElementException
	 */
	@Test
	public void test_removeLast() 
	{
		try
		{
			empty.removeLast();
			fail();
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		assertTrue(single.removeLast().equals(8));
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");
		
		try 
		{
			assertFalse(single.removeLast().equals(8));
			fail();
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(single.toString(), "[]");
			assertEquals(single.toStringRev(), "[]");
		}

		assertTrue(multi.removeLast().equals(6)); 	// [4 3 5 7 1 6]
		assertEquals(multi.toString(), "[4 3 5 7 1]");
		assertEquals(multi.toStringRev(), "[1 7 5 3 4]");
		
		assertTrue(multi.removeLast().equals(1));
		assertEquals(multi.toString(), "[4 3 5 7]");
		assertEquals(multi.toStringRev(), "[7 5 3 4]");
		
		assertTrue(multi.removeLast().equals(7));
		assertEquals(multi.toString(), "[4 3 5]");
		assertEquals(multi.toStringRev(), "[5 3 4]");
		
		assertFalse(multi.removeLast().equals(3));
		assertEquals(multi.toString(), "[4 3]");
		assertEquals(multi.toStringRev(), "[3 4]");
		
		assertTrue(multi.removeLast().equals(3));
		assertEquals(multi.toString(), "[4]");
		assertEquals(multi.toStringRev(), "[4]");
		
		assertTrue(multi.removeFirst().equals(4));
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
		
		try
		{
			multi.removeLast();
			fail();
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(multi.toString(), "[]");
			assertEquals(multi.toStringRev(), "[]");
		}
	}
	
	/**
	 * Test the remove() method
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	public void test_remove() 
	{
		try
		{
			empty.remove(-1);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			empty.remove(0);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			empty.remove(1);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			single.remove(-1);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		try
		{
			single.remove(1);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		single.remove(0);
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");

		try
		{
			single.remove(0);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[]");
			assertEquals(single.toStringRev(), "[]");
		}

		try
		{
			multi.remove(-1);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		try
		{
			multi.remove(6);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		assertTrue(multi.remove(0).equals(4));
		assertEquals(multi.toString(), "[3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3]");

		assertTrue(multi.remove(4).equals(6));
		assertEquals(multi.toString(), "[3 5 7 1]");
		assertEquals(multi.toStringRev(), "[1 7 5 3]");
		
		try
		{
			multi.remove(4);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[3 5 7 1]");
			assertEquals(multi.toStringRev(), "[1 7 5 3]");
		}
		
		assertTrue(multi.remove(2).equals(7));
		assertEquals(multi.toString(), "[3 5 1]");
		assertEquals(multi.toStringRev(), "[1 5 3]");
		
		assertFalse(multi.remove(2).equals(5));
		assertEquals(multi.toString(), "[3 5]");
		assertEquals(multi.toStringRev(), "[5 3]");
		
		assertTrue(multi.remove(1).equals(5));
		assertEquals(multi.toString(), "[3]");
		assertEquals(multi.toStringRev(), "[3]");
		
		try
		{
			multi.remove(-1);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[3]");
			assertEquals(multi.toStringRev(), "[3]");
		}	
		
		try
		{
			multi.remove(1);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[3]");
			assertEquals(multi.toStringRev(), "[3]");
		}	
		
		assertTrue(multi.remove(0).equals(3));
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
		
		try
		{
			multi.remove(0);
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[]");
			assertEquals(multi.toStringRev(), "[]");
		}	
	}
	
	/**
	 * Test the removeItem() method
	 */
	@Test
	public void test_removeItem() 
	{
		assertFalse(empty.removeItem(7));
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");
		
		assertFalse(single.removeItem(9));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertTrue(single.removeItem(8));
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");
		
		assertFalse(single.removeItem(8));
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");
		
		multi.addLast(4);
		assertEquals(multi.toString(), "[4 3 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 3 4]");
		multi.addFirst(4);
		assertEquals(multi.toString(), "[4 4 3 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 3 4 4]");
		multi.add(3, 4);
		assertEquals(multi.toString(), "[4 4 3 4 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 4 3 4 4]");
		
		assertFalse(multi.removeItem(9));
		assertEquals(multi.toString(), "[4 4 3 4 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 4 3 4 4]");
		
		assertTrue(multi.removeItem(4));
		assertEquals(multi.toString(), "[4 3 4 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 4 3 4]");
		
		assertTrue(multi.removeItem(7));
		assertEquals(multi.toString(), "[4 3 4 5 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 5 4 3 4]");
		
		assertTrue(multi.removeItem(4));
		assertEquals(multi.toString(), "[3 4 5 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 5 4 3]");
		
		assertTrue(multi.removeItem(6));
		assertEquals(multi.toString(), "[3 4 5 1 4]");
		assertEquals(multi.toStringRev(), "[4 1 5 4 3]");
		
		assertFalse(multi.removeItem(2));
		assertEquals(multi.toString(), "[3 4 5 1 4]");
		assertEquals(multi.toStringRev(), "[4 1 5 4 3]");
		
		assertTrue(multi.removeItem(5));
		assertEquals(multi.toString(), "[3 4 1 4]");
		assertEquals(multi.toStringRev(), "[4 1 4 3]");
		
		assertTrue(multi.removeItem(1));
		assertEquals(multi.toString(), "[3 4 4]");
		assertEquals(multi.toStringRev(), "[4 4 3]");
		
		assertTrue(multi.removeItem(4));
		assertEquals(multi.toString(), "[3 4]");
		assertEquals(multi.toStringRev(), "[4 3]");
		
		assertTrue(multi.removeItem(4));
		assertEquals(multi.toString(), "[3]");
		assertEquals(multi.toStringRev(), "[3]");
		
		assertFalse(multi.removeItem(2));
		assertEquals(multi.toString(), "[3]");
		assertEquals(multi.toStringRev(), "[3]");
		
		assertTrue(multi.removeItem(3));
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
		
		assertFalse(multi.removeItem(0));
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
	}
	
	/**
	 * Test the removeAll() method
	 */
	@Test
	public void test_removeAll() 
	{
		assertFalse(empty.removeAll(7));
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");
		
		assertFalse(single.removeAll(9));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertTrue(single.removeAll(8));
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");
		
		assertFalse(single.removeAll(10));
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");
		
		multi.addLast(4);
		assertEquals(multi.toString(), "[4 3 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 3 4]");
		multi.addFirst(4);
		assertEquals(multi.toString(), "[4 4 3 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 3 4 4]");
		multi.add(3, 4);
		assertEquals(multi.toString(), "[4 4 3 4 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 4 3 4 4]");
		
		assertFalse(multi.removeAll(9));
		assertEquals(multi.toString(), "[4 4 3 4 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 4 3 4 4]");
		
		assertTrue(multi.removeAll(5));
		assertEquals(multi.toString(), "[4 4 3 4 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 4 3 4 4]");
		
		assertTrue(multi.removeAll(4));
		assertEquals(multi.toString(), "[3 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 3]");
		
		assertTrue(multi.removeAll(3));
		assertEquals(multi.toString(), "[7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7]");
		
		assertFalse(multi.removeAll(5));
		assertEquals(multi.toString(), "[7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7]");
		
		assertTrue(multi.removeAll(1));
		assertEquals(multi.toString(), "[7 6]");
		assertEquals(multi.toStringRev(), "[6 7]");
		
		assertTrue(multi.removeAll(7));
		assertEquals(multi.toString(), "[6]");
		assertEquals(multi.toStringRev(), "[6]");
		
		assertFalse(multi.removeAll(3));
		assertEquals(multi.toString(), "[6]");
		assertEquals(multi.toStringRev(), "[6]");
		
		assertTrue(multi.removeAll(6));
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
		
		assertFalse(multi.removeAll(0));
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
	}
	
	/**
	 * Test the equal() method
	 */
	@Test
	public void test_equal() 
	{
		assertTrue(empty.equals(empty));
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");
		
		assertFalse(empty.equals(single));
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");
		
		assertFalse(empty.equals(multi));
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");
		
		String s = "HAIDER";
		assertFalse(empty.equals(s));
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");

		assertTrue(single.equals(single));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertFalse(single.equals(empty));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertFalse(single.equals(multi));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");

		assertFalse(single.equals(s));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		DLinkedList<Integer> secSingle = new DLinkedList<>();
		secSingle.addFirst(8);
		assertTrue(single.equals(secSingle));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		secSingle.addFirst(8); // [8, 8] != [8]
		assertFalse(single.equals(secSingle));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertTrue(multi.equals(multi));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertFalse(multi.equals(empty));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertFalse(multi.equals(single));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		assertFalse(multi.equals(s));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");

		DLinkedList<Integer> secMulti = new DLinkedList<Integer>();
		secMulti.addFirst(6);
		assertEquals(secMulti.toString(), "[6]");
		assertEquals(secMulti.toStringRev(), "[6]");
		secMulti.addFirst(1);
		assertEquals(secMulti.toString(), "[1 6]");
		assertEquals(secMulti.toStringRev(), "[6 1]");

		assertFalse(multi.equals(secMulti));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		secMulti.addFirst(7);
		assertEquals(secMulti.toString(), "[7 1 6]");
		assertEquals(secMulti.toStringRev(), "[6 1 7]");
		secMulti.addFirst(5);
		assertEquals(secMulti.toString(), "[5 7 1 6]");
		assertEquals(secMulti.toStringRev(), "[6 1 7 5]");
		secMulti.addFirst(3);
		assertEquals(secMulti.toString(), "[3 5 7 1 6]");
		assertEquals(secMulti.toStringRev(), "[6 1 7 5 3]");
	
		assertFalse(multi.equals(secMulti));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		secMulti.addFirst(4);
		assertEquals(secMulti.toString(), "[4 3 5 7 1 6]");
		assertEquals(secMulti.toStringRev(), "[6 1 7 5 3 4]");
		assertTrue(multi.equals(secMulti));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		secMulti.addFirst(10);
		assertEquals(secMulti.toString(), "[10 4 3 5 7 1 6]");
		assertEquals(secMulti.toStringRev(), "[6 1 7 5 3 4 10]");
		assertFalse(multi.equals(secMulti));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		multi.addFirst(10);
		assertEquals(multi.toString(), "[10 4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4 10]");
		assertTrue(multi.equals(secMulti));
		assertEquals(multi.toString(), "[10 4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4 10]");
		
		DLinkedList<Integer> thirdMulti = new DLinkedList<Integer>(); // same elements, different order
		thirdMulti.addFirst(6);
		assertEquals(thirdMulti.toString(), "[6]");
		assertEquals(thirdMulti.toStringRev(), "[6]");
		thirdMulti.addFirst(1);
		assertEquals(thirdMulti.toString(), "[1 6]");
		assertEquals(thirdMulti.toStringRev(), "[6 1]");
		thirdMulti.addFirst(5); // switch
		assertEquals(thirdMulti.toString(), "[5 1 6]");
		assertEquals(thirdMulti.toStringRev(), "[6 1 5]");
		thirdMulti.addFirst(7); // switch
		assertEquals(thirdMulti.toString(), "[7 5 1 6]");
		assertEquals(thirdMulti.toStringRev(), "[6 1 5 7]");
		thirdMulti.addFirst(3);
		assertEquals(thirdMulti.toString(), "[3 7 5 1 6]");
		assertEquals(thirdMulti.toStringRev(), "[6 1 5 7 3]");
		thirdMulti.addFirst(4);
		assertEquals(thirdMulti.toString(), "[4 3 7 5 1 6]");
		assertEquals(thirdMulti.toStringRev(), "[6 1 5 7 3 4]");
		thirdMulti.addFirst(10);
		assertEquals(thirdMulti.toString(), "[10 4 3 7 5 1 6]");
		assertEquals(thirdMulti.toStringRev(), "[6 1 5 7 3 4 10]");
		
		assertFalse(multi.equals(thirdMulti));
		assertEquals(multi.toString(), "[10 4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4 10]");
	}

	/**
	 * Test the clone() method
	 */
	@Test
	public void test_clone() 
	{
		DLinkedList<Integer> emptyClone = (DLinkedList<Integer>) empty.clone();
		
		assertEquals(emptyClone.toString(), "[]");
		assertEquals(emptyClone.toStringRev(), "[]");
		
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");
		
		empty.addFirst(6);
		assertEquals(empty.toString(), "[6]");
		assertEquals(empty.toStringRev(), "[6]");
		empty.addLast(7);
		assertEquals(empty.toString(), "[6 7]");
		assertEquals(empty.toStringRev(), "[7 6]");
		
		assertEquals(emptyClone.toString(), "[]");
		assertEquals(emptyClone.toStringRev(), "[]");
		
		DLinkedList<Integer> singleClone = (DLinkedList<Integer>) single.clone();
		
		assertEquals(singleClone.toString(), "[8]");
		assertEquals(singleClone.toStringRev(), "[8]");
		
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		single.addFirst(6);
		assertEquals(single.toString(), "[6 8]");
		assertEquals(single.toStringRev(), "[8 6]");
		single.addLast(7);
		assertEquals(single.toString(), "[6 8 7]");
		assertEquals(single.toStringRev(), "[7 8 6]");
		
		assertEquals(singleClone.toString(), "[8]");
		assertEquals(singleClone.toStringRev(), "[8]");
		
		DLinkedList<Integer> multiClone = (DLinkedList<Integer>) multi.clone();
		
		assertEquals(multiClone.toString(), "[4 3 5 7 1 6]");
		assertEquals(multiClone.toStringRev(), "[6 1 7 5 3 4]");
		
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		
		multi.addFirst(6);
		assertEquals(multi.toString(), "[6 4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4 6]");
		multi.addLast(7);
		assertEquals(multi.toString(), "[6 4 3 5 7 1 6 7]");
		assertEquals(multi.toStringRev(), "[7 6 1 7 5 3 4 6]");	
		
		assertEquals(multiClone.toString(), "[4 3 5 7 1 6]");
		assertEquals(multiClone.toStringRev(), "[6 1 7 5 3 4]");
	}
	
	/**
	 * Test the addAll() method
	 */
	@Test
	public void test_addAll()
	{
		try
		{
			empty.addAll(-1, Arrays.asList(1));
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			empty.addAll(1, Arrays.asList(1));
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}
		
		try
		{
			empty.addAll(2, Arrays.asList(1));
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}

		empty.addAll(0, Arrays.asList(1, 2));
		assertEquals(empty.toString(), "[1 2]");
		assertEquals(empty.toStringRev(), "[2 1]");
		
		try
		{
			single.addAll(-1, Arrays.asList(1));
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		try
		{
			single.addAll(2, Arrays.asList(1));
			fail();
		}

		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		try
		{
			single.addAll(3, Arrays.asList(1));
			fail();
		}

		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]");
		}
		
		single.addAll(1, Arrays.asList(1));		
		assertEquals(single.toString(), "[8 1]");
		assertEquals(single.toStringRev(), "[1 8]");
		
		single.addAll(0, Arrays.asList(0, 5));		
		assertEquals(single.toString(), "[0 5 8 1]");
		assertEquals(single.toStringRev(), "[1 8 5 0]");
		
		single.addAll(1, Arrays.asList(3, 5));		
		assertEquals(single.toString(), "[0 3 5 5 8 1]");
		assertEquals(single.toStringRev(), "[1 8 5 5 3 0]");
		
		single.addAll(6, Arrays.asList(100, 101));		
		assertEquals(single.toString(), "[0 3 5 5 8 1 100 101]");
		assertEquals(single.toStringRev(), "[101 100 1 8 5 5 3 0]");
		
		single.addAll(4, Arrays.asList(67, 88));		
		assertEquals(single.toString(), "[0 3 5 5 67 88 8 1 100 101]");
		assertEquals(single.toStringRev(), "[101 100 1 8 88 67 5 5 3 0]");
		
		try
		{
			multi.addAll(-1, Arrays.asList(1));
			fail();
		}
		
		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		try
		{
			multi.addAll(7, Arrays.asList(1));
			fail();
		}

		catch (IndexOutOfBoundsException ex)
		{
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}
		
		multi.addAll(0, Arrays.asList(-1, -2));		
		assertEquals(multi.toString(), "[-1 -2 4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4 -2 -1]");
		
		multi.addAll(8, Arrays.asList(100));		
		assertEquals(multi.toString(), "[-1 -2 4 3 5 7 1 6 100]");
		assertEquals(multi.toStringRev(), "[100 6 1 7 5 3 4 -2 -1]");
		
		multi.addAll(8, Arrays.asList(98, 99));		
		assertEquals(multi.toString(), "[-1 -2 4 3 5 7 1 6 98 99 100]");
		assertEquals(multi.toStringRev(), "[100 99 98 6 1 7 5 3 4 -2 -1]");
		
		multi.addAll(9, Arrays.asList(150));		
		assertEquals(multi.toString(), "[-1 -2 4 3 5 7 1 6 98 150 99 100]");
		assertEquals(multi.toStringRev(), "[100 99 150 98 6 1 7 5 3 4 -2 -1]");
	}
	
	/**
	 * Test the removeAllIter method
	 */
	@Test
	public void test_removeAllIter()
	{
		assertFalse(empty.removeAllIter(7));
		assertEquals(empty.toString(), "[]");
		assertEquals(empty.toStringRev(), "[]");
		
		assertFalse(single.removeAllIter(9));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]");
		
		assertTrue(single.removeAllIter(8));
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");
		
		assertFalse(single.removeAllIter(10));
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]");
		
		multi.addLast(4);
		assertEquals(multi.toString(), "[4 3 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 3 4]");
		multi.addFirst(4);
		assertEquals(multi.toString(), "[4 4 3 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 3 4 4]");
		multi.add(3, 4);
		assertEquals(multi.toString(), "[4 4 3 4 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 4 3 4 4]");
		
		assertFalse(multi.removeAllIter(9));
		assertEquals(multi.toString(), "[4 4 3 4 5 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 5 4 3 4 4]");
		
		assertTrue(multi.removeAllIter(5));
		assertEquals(multi.toString(), "[4 4 3 4 7 1 6 4]");
		assertEquals(multi.toStringRev(), "[4 6 1 7 4 3 4 4]");
		
		assertTrue(multi.removeAllIter(4));
		assertEquals(multi.toString(), "[3 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 3]");
		
		multi.addLast(6);
		assertEquals(multi.toString(), "[3 7 1 6 6]");
		assertEquals(multi.toStringRev(), "[6 6 1 7 3]");
	
		assertTrue(multi.removeAllIter(6));
		assertEquals(multi.toString(), "[3 7 1]");
		assertEquals(multi.toStringRev(), "[1 7 3]");
		
		assertTrue(multi.removeAllIter(7));
		assertEquals(multi.toString(), "[3 1]");
		assertEquals(multi.toStringRev(), "[1 3]");
		
		assertTrue(multi.removeAllIter(3));
		assertEquals(multi.toString(), "[1]");
		assertEquals(multi.toStringRev(), "[1]");
		
		assertFalse(multi.removeAllIter(2));
		assertEquals(multi.toString(), "[1]");
		assertEquals(multi.toStringRev(), "[1]");
		
		assertTrue(multi.removeAllIter(1));
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
		
		assertFalse(multi.removeAllIter(5));
		assertEquals(multi.toString(), "[]");
		assertEquals(multi.toStringRev(), "[]");
	}
	
	/**
	 * Test the exceptions in next and remove method
	 */
	@Test
	public void test_iter_fails() 
	{
		Iterator<Integer> emptyIterator = empty.iterator();
		Iterator<Integer> singleIterator = single.iterator();
		Iterator<Integer> multiIterator = multi.iterator();

		try
		{
			emptyIterator.remove();
			fail();
		}

		catch (IllegalStateException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}

		try
		{
			emptyIterator.next();
			fail();
		}

		catch (NoSuchElementException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}

		try
		{
			emptyIterator.remove();
			fail();
		}

		catch (IllegalStateException ex)
		{
			assertEquals(empty.toString(), "[]");
			assertEquals(empty.toStringRev(), "[]");
		}

		try
		{
			singleIterator.remove();
			fail();
		}

		catch (IllegalStateException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]"); 
		}

		try
		{
			singleIterator.remove();
			fail();
		}

		catch (IllegalStateException ex)
		{
			assertEquals(single.toString(), "[8]");
			assertEquals(single.toStringRev(), "[8]"); 
		}

		assertTrue(singleIterator.next().equals(8));
		assertEquals(single.toString(), "[8]");
		assertEquals(single.toStringRev(), "[8]"); 

		singleIterator.remove();
		assertEquals(single.toString(), "[]");
		assertEquals(single.toStringRev(), "[]"); 

		try
		{
			singleIterator.remove();
			fail();
		}

		catch (IllegalStateException ex)
		{
			assertEquals(single.toString(), "[]");
			assertEquals(single.toStringRev(), "[]"); 
		}

		try
		{
			singleIterator.next();
			fail();
		}

		catch (NoSuchElementException ex)
		{
			assertEquals(single.toString(), "[]");
			assertEquals(single.toStringRev(), "[]"); 
		}

		try
		{
			multiIterator.remove();
			fail();
		}

		catch (IllegalStateException ex)
		{
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}

		try
		{
			multiIterator.remove();
			fail();
		}

		catch (IllegalStateException ex)
		{
			assertEquals(multi.toString(), "[4 3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");
		}

		assertTrue(multiIterator.next().equals(4));
		assertEquals(multi.toString(), "[4 3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3 4]");


		multiIterator.remove();
		assertEquals(multi.toString(), "[3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3]");

		try
		{
			multiIterator.remove();
			fail();
		}

		catch (IllegalStateException ex)
		{
			assertEquals(multi.toString(), "[3 5 7 1 6]");
			assertEquals(multi.toStringRev(), "[6 1 7 5 3]");
		}
		
		assertTrue(multiIterator.next().equals(3));
		assertEquals(multi.toString(), "[3 5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5 3]");
		
		multiIterator.remove();
		assertEquals(multi.toString(), "[5 7 1 6]");
		assertEquals(multi.toStringRev(), "[6 1 7 5]");

	}	 
}