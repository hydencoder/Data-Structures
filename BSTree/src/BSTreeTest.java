import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BSTreeTest
{
	private BSTree<Integer> empty;
	private BSTree<Integer> single;
	private BSTree<Integer> multi; 
	private BSTree<Integer> multiLarger; 
	private BSTreeComparator bstComp = new BSTreeComparator();
	private BSTreeStringComparator bstStringComp = new BSTreeStringComparator();

	@BeforeEach
	void setUp()
	{
		empty = new BSTree<Integer>(bstComp);
		single = new BSTree<Integer>(bstComp);

		single.add(20);
		assertEquals(single.toString(), "[20]");
		
		multi = new BSTree<Integer>(bstComp);
		multi.add(100);
		assertEquals(multi.toString(), "[100]");
		multi.add(55);
		assertEquals(multi.toString(), "[100 55]");
		multi.add(145);
		assertEquals(multi.toString(), "[100 55 145]");
		multi.add(105);
		assertEquals(multi.toString(), "[100 55 145 105]");
		multi.add(75);
		assertEquals(multi.toString(), "[100 55 145 75 105]");
		multi.add(65);	
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");
		
		multiLarger = new BSTree<Integer>(bstComp);
	}
	
	@Test
	void test_getRoot() 
	{
		assertEquals(empty.getRoot(), null);
	}

	@Test
	void test_isEmpty() 
	{
		assertTrue(empty.isEmpty());
		assertEquals(empty.toString(), "[]");
		
		assertFalse(single.isEmpty());
		assertEquals(single.toString(), "[20]");
		assertFalse(single.remove(19));
		assertEquals(single.toString(), "[20]");
		assertTrue(single.remove(20));
		assertEquals(single.toString(), "[]");
		assertTrue(single.isEmpty());
		assertEquals(single.toString(), "[]");
		
		assertFalse(multi.isEmpty());
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");
	}
	
	@Test
	void test_addLoop()
	{
		empty.addLoop(5);
		assertEquals(empty.toString(), "[5]");
		empty.addLoop(8);
		assertEquals(empty.toString(), "[5 8]");
		empty.addLoop(2);
		assertEquals(empty.toString(), "[5 2 8]");
		empty.addLoop(6);
		assertEquals(empty.toString(), "[5 2 8 6]");
		
		single.addLoop(30);
		assertEquals(single.toString(), "[20 30]");
		single.addLoop(5);
		assertEquals(single.toString(), "[20 5 30]");
		single.addLoop(100);
		assertEquals(single.toString(), "[20 5 30 100]");
		single.addLoop(1);
		assertEquals(single.toString(), "[20 5 30 1 100]");
		
		multi.addLoop(5);
		assertEquals(multi.toString(), "[100 55 145 5 75 105 65]");
		multi.addLoop(80);
		assertEquals(multi.toString(), "[100 55 145 5 75 105 65 80]");
		multi.addLoop(15);
		assertEquals(multi.toString(), "[100 55 145 5 75 105 15 65 80]");
	}
	
	@Test
	void test_maxValueLoop() 
	{
		try
		{
			empty.maxValueLoop();
			fail();
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(empty.toString(), "[]");
		}
		
		assertTrue(single.maxValueLoop().equals(20));
		assertEquals(single.toString(), "[20]");
		assertFalse(single.maxValueLoop().equals(10));
		assertEquals(single.toString(), "[20]");
		
		assertTrue(multi.maxValueLoop().equals(145));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");
		assertFalse(multi.maxValueLoop().equals(105));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");	
	}
	
	@Test
	void test_minValueLoop() 
	{
		try
		{
			empty.minValueLoop();
			fail();
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(empty.toString(), "[]");
		}
		
		assertTrue(single.minValueLoop().equals(20));
		assertEquals(single.toString(), "[20]");
		assertFalse(single.minValueLoop().equals(10));
		assertEquals(single.toString(), "[20]");
		
		assertTrue(multi.minValueLoop().equals(55));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");
		assertFalse(multi.minValueLoop().equals(105));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");	
	}
	
	@Test
	void test_containsLoop() 
	{
		assertFalse(empty.containsLoop(7));
		assertEquals(empty.toString(), "[]");
		
		assertTrue(single.containsLoop(20));
		assertEquals(single.toString(), "[20]");
		assertFalse(single.containsLoop(2));
		assertEquals(single.toString(), "[20]");
		
		assertTrue(multi.containsLoop(145));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");	
		assertFalse(multi.containsLoop(76));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");	
	}
	
	@Test
	void test_add()
	{
		empty.add(5);
		assertEquals(empty.toString(), "[5]");
		
		single.add(30);
		assertEquals(single.toString(), "[20 30]");
		single.add(5);
		assertEquals(single.toString(), "[20 5 30]");
		single.add(100);
		assertEquals(single.toString(), "[20 5 30 100]");
		single.add(1);
		assertEquals(single.toString(), "[20 5 30 1 100]");
		
		multi.add(5);
		assertEquals(multi.toString(), "[100 55 145 5 75 105 65]");
		multi.add(80);
		assertEquals(multi.toString(), "[100 55 145 5 75 105 65 80]");
		multi.add(15);
		assertEquals(multi.toString(), "[100 55 145 5 75 105 15 65 80]");
	}
	
	@Test
	void test_maxValue() 
	{
		try
		{
			empty.maxValue();
			fail();
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(empty.toString(), "[]");
		}
		
		assertTrue(single.maxValue().equals(20));
		assertEquals(single.toString(), "[20]");
		assertFalse(single.maxValue().equals(10));
		assertEquals(single.toString(), "[20]");
		
		assertTrue(multi.maxValue().equals(145));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");
		assertFalse(single.maxValue().equals(105));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");	
	}
	
	@Test
	void test_minValue() 
	{
		try
		{
			empty.minValue();
			fail();
		}
		
		catch (NoSuchElementException ex)
		{
			assertEquals(empty.toString(), "[]");
		}
		
		assertTrue(single.minValue().equals(20));
		assertEquals(single.toString(), "[20]");
		assertFalse(single.minValue().equals(10));
		assertEquals(single.toString(), "[20]");
		
		assertTrue(multi.minValue().equals(55));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");
		assertFalse(multi.minValue().equals(105));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");	
	}
	
	@Test
	void test_contains() 
	{
		assertFalse(empty.contains(7));
		assertEquals(empty.toString(), "[]");
		
		assertTrue(single.contains(20));
		assertEquals(single.toString(), "[20]");
		assertFalse(single.contains(2));
		assertEquals(single.toString(), "[20]");
		
		assertTrue(multi.contains(145));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");	
		assertFalse(multi.contains(76));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");	
		assertTrue(multi.contains(65));
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");	
	}

	@Test
	void test_remove()
	{
		assertFalse(empty.remove(7));
		assertEquals(empty.toString(), "[]");
		
		assertTrue(single.remove(20));
		assertEquals(single.toString(), "[]");
		assertFalse(single.contains(20));
		assertEquals(single.toString(), "[]");
		
		assertTrue(multi.remove(145));
		assertEquals(multi.toString(), "[100 55 105 75 65]");	
		assertFalse(multi.contains(145));
		assertEquals(multi.toString(), "[100 55 105 75 65]");
		
		assertTrue(multi.remove(100));
		assertEquals(multi.toString(), "[75 55 105 65]");	
		assertFalse(multi.contains(100));
		assertEquals(multi.toString(), "[75 55 105 65]");
		
		assertTrue(multi.remove(65));
		assertEquals(multi.toString(), "[75 55 105]");	
		assertFalse(multi.contains(65));
		assertEquals(multi.toString(), "[75 55 105]");	
		
		assertTrue(multi.remove(55));
		assertEquals(multi.toString(), "[75 105]");	
		assertFalse(multi.contains(55));
		assertEquals(multi.toString(), "[75 105]");
		
		assertTrue(multi.remove(75));
		assertEquals(multi.toString(), "[105]");	
		assertFalse(multi.contains(75));
		assertEquals(multi.toString(), "[105]");
		
		assertTrue(multi.remove(105));
		assertEquals(multi.toString(), "[]");	
		assertFalse(multi.contains(105));
		assertEquals(multi.toString(), "[]");
		
		assertFalse(multi.remove(105));
		assertTrue(multi.isEmpty());
		assertEquals(multi.toString(), "[]");

		multiLarger.add(100);
		assertEquals(multiLarger.toString(), "[100]");
		multiLarger.add(150);
		assertEquals(multiLarger.toString(), "[100 150]");
		multiLarger.add(250);
		assertEquals(multiLarger.toString(), "[100 150 250]");

		assertTrue(multiLarger.remove(150));
		assertEquals(multiLarger.toString(), "[100 250]");
		assertTrue(multiLarger.remove(250));
		assertEquals(multiLarger.toString(), "[100]");

		multiLarger.add(50);
		assertEquals(multiLarger.toString(), "[100 50]");	

		multiLarger.add(70);
		assertTrue(multiLarger.remove(50));
		assertEquals(multiLarger.toString(), "[100 70]");
		assertTrue(multiLarger.remove(70));
		assertEquals(multiLarger.toString(), "[100]");
		multiLarger.add(50);
		assertEquals(multiLarger.toString(), "[100 50]");

		multiLarger.add(45);
		assertEquals(multiLarger.toString(), "[100 50 45]");
		assertTrue(multiLarger.remove(50));
		assertEquals(multiLarger.toString(), "[100 45]");
		assertTrue(multiLarger.remove(45));
		assertEquals(multiLarger.toString(), "[100]");
		multiLarger.add(50);
		assertEquals(multiLarger.toString(), "[100 50]");

		assertTrue(multiLarger.remove(100));
		assertEquals(multiLarger.toString(), "[50]");
		assertTrue(multiLarger.remove(50));
		assertEquals(multiLarger.toString(), "[]");

		multiLarger.add(100);
		assertEquals(multiLarger.toString(), "[100]");
		multiLarger.add(50);	
		assertEquals(multiLarger.toString(), "[100 50]");
		multiLarger.add(15);
		assertEquals(multiLarger.toString(), "[100 50 15]");
		multiLarger.add(60);
		assertEquals(multiLarger.toString(), "[100 50 15 60]");
		multiLarger.add(55);
		assertEquals(multiLarger.toString(), "[100 50 15 60 55]");
		multiLarger.add(70);	
		assertEquals(multiLarger.toString(), "[100 50 15 60 55 70]");

		assertTrue(multiLarger.remove(50));
		assertEquals(multiLarger.toString(), "[100 15 60 55 70]");
		multiLarger.add(10);
		assertEquals(multiLarger.toString(), "[100 15 10 60 55 70]");
		multiLarger.add(13);
		assertEquals(multiLarger.toString(), "[100 15 10 60 13 55 70]");
		assertTrue(multiLarger.remove(15));
		assertEquals(multiLarger.toString(), "[100 13 10 60 55 70]");
		multiLarger.add(6);
		assertEquals(multiLarger.toString(), "[100 13 10 60 6 55 70]");
		assertTrue(multiLarger.remove(13));
		assertEquals(multiLarger.toString(), "[100 10 6 60 55 70]");
		assertTrue(multiLarger.remove(10));
		assertEquals(multiLarger.toString(), "[100 6 60 55 70]");	
	}

	@Test
	void test_preorder()
	{
		StringVisitor<Integer> strVisitor = new StringVisitor<Integer>();
		empty.preorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[]" );

		strVisitor.emptyStr();
		single.preorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[20]" );

		strVisitor.emptyStr();
		multi.preorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[100 55 75 65 145 105]" );
	}

	@Test
	void test_inorder()
	{
		StringVisitor<Integer> strVisitor = new StringVisitor<Integer>();
		empty.inorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[]" );

		strVisitor.emptyStr();
		single.inorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[20]" );

		strVisitor.emptyStr();
		multi.inorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[55 65 75 100 105 145]" );
	}

	@Test
	void test_postorder()
	{
		StringVisitor<Integer> strVisitor = new StringVisitor<Integer>();
		empty.postorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[]" );

		strVisitor.emptyStr();
		single.postorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[20]" );

		strVisitor.emptyStr();
		multi.postorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[65 75 55 105 145 100]" );
	}

	@Test
	void test_CountRangeVisitor()
	{
		CountRangeVisitor<Integer> countVisitor = new CountRangeVisitor<Integer>(bstComp, -6, 100);
		empty.preorder(countVisitor);
		assertEquals(countVisitor.getValue(), "[]" );
		assertEquals(countVisitor.getCount(), 0 );

		countVisitor.emptyStr();
		countVisitor.emptyCount();
		single.preorder(countVisitor);
		assertEquals(countVisitor.getValue(), "[20]" );
		assertEquals(countVisitor.getCount(), 1);

		countVisitor.emptyStr();
		countVisitor.emptyCount();
		multi.preorder(countVisitor);
		assertEquals(countVisitor.getValue(), "[100 55 75 65]" );
		assertEquals(countVisitor.getCount(), 4);

		CountRangeVisitor<Integer> countVisitor2 = new CountRangeVisitor<Integer>(bstComp, 50, 99);
		multi.preorder(countVisitor2);
		assertEquals(countVisitor2.getValue(), "[55 75 65]" );
		assertEquals(countVisitor2.getCount(), 3);

		countVisitor2.emptyStr();
		countVisitor2.emptyCount();
		multi.inorder(countVisitor2);
		assertEquals(countVisitor2.getValue(), "[55 65 75]" );
		assertEquals(countVisitor2.getCount(), 3);

		CountRangeVisitor<String> countVisitor3 = new CountRangeVisitor<String>(bstStringComp, "B", "F");
		BSTree<String> treeString = new BSTree<String>(bstStringComp);
		assertEquals(countVisitor3.getCount(), 0);
		treeString.add("B");
		treeString.add("C");
		treeString.preorder(countVisitor3);
		assertEquals(countVisitor3.getCount(), 2);
	}

	@Test
	void test_toString()
	{
		assertEquals(empty.toString(), "[]");
		assertEquals(single.toString(), "[20]");
		assertEquals(multi.toString(), "[100 55 145 75 105 65]");
	}

	@Test
	void test_toStringPre()
	{
		assertEquals(empty.toStringPre(), "[]");
		assertEquals(single.toStringPre(), "[20]");
		assertEquals(multi.toStringPre(), "[100 55 75 65 145 105]");
	}

	@Test
	void test_Equals()
	{
		BSTree<Integer> empty2 = new BSTree<Integer>(bstComp);
		assertTrue(empty.equals(empty));
		assertTrue(empty.equals(empty2));
		assertEquals(empty.toString(), "[]");
		assertEquals(empty2.toString(), "[]");

		empty.add(20);
		assertEquals(empty.toString(), "[20]");
		assertFalse(empty.equals(empty2));
		assertEquals(empty.toString(), "[20]");
		assertEquals(empty2.toString(), "[]");
		assertFalse(empty.equals("5.5"));

		single.add(15);
		assertEquals(single.toString(), "[20 15]");
		assertFalse(single.equals(empty));
		single.remove(15);
		assertEquals(single.toString(), "[20]");

		assertTrue(single.equals(single));
		assertTrue(single.equals(empty));

		empty.add(60);
		assertFalse(single.equals(empty));
		single.add(60);
		assertTrue(single.equals(empty));
		assertFalse(single.equals("5.5"));

		BSTree<Integer> multi2 = new BSTree<Integer>(bstComp);
		multi2.add(100);
		assertEquals(multi2.toString(), "[100]");
		multi2.add(55);
		assertEquals(multi2.toString(), "[100 55]");
		multi2.add(145);
		assertEquals(multi2.toString(), "[100 55 145]");
		multi2.add(105);
		assertEquals(multi2.toString(), "[100 55 145 105]");
		multi2.add(75);
		assertEquals(multi2.toString(), "[100 55 145 75 105]");
		multi2.add(65);	
		assertEquals(multi2.toString(), "[100 55 145 75 105 65]");
		
		assertTrue(multi.equals(multi));
		assertFalse(multi.equals(empty));
		assertFalse(multi.equals(single));
		assertTrue(multi.equals(multi2));
		multi2.add(500);
		assertEquals(multi2.toString(), "[100 55 145 75 105 500 65]");
		assertFalse(multi.equals(multi2));
		multi.add(500);	
		assertTrue(multi.equals(multi2));
		assertFalse(multi.equals("5.5"));
	}
	
	@Test
	void test_Clone()
	{
		BSTree<Integer> emptyClone = (BSTree<Integer>) empty.clone();
		empty.add(5);
		assertEquals(empty.toString(), "[5]");
		assertEquals(emptyClone.toString(), "[]");
		
		emptyClone.add(50);
		assertEquals(empty.toString(), "[5]");
		assertEquals(emptyClone.toString(), "[50]");
		
		empty.remove(5);
		assertEquals(empty.toString(), "[]");
		assertEquals(emptyClone.toString(), "[50]");
	
		emptyClone.remove(50);
		assertEquals(empty.toString(), "[]");
		assertEquals(emptyClone.toString(), "[]");
		
		empty.add(5);
		assertEquals(empty.toString(), "[5]");
		assertEquals(emptyClone.toString(), "[]");
		
		emptyClone.add(50);
		assertEquals(empty.toString(), "[5]");
		assertEquals(emptyClone.toString(), "[50]");
	
		BSTree<Integer> singleClone = (BSTree) single.clone();
		single.add(100);
		assertEquals(single.toString(), "[20 100]");
		assertEquals(singleClone.toString(), "[20]");	
		
		singleClone.add(150);
		assertEquals(single.toString(), "[20 100]");
		assertEquals(singleClone.toString(), "[20 150]");
		
		single.remove(20);
		assertEquals(single.toString(), "[100]");
		assertEquals(singleClone.toString(), "[20 150]");
		
		singleClone.remove(20);
		assertEquals(single.toString(), "[100]");
		assertEquals(singleClone.toString(), "[150]");
		
		single.remove(100);
		assertEquals(single.toString(), "[]");
		assertEquals(singleClone.toString(), "[150]");
		
		singleClone.remove(150);
		assertEquals(single.toString(), "[]");
		assertEquals(singleClone.toString(), "[]");
		
		singleClone.add(150);
		assertEquals(single.toString(), "[]");
		assertEquals(singleClone.toString(), "[150]");
		
		BSTree<Integer> multiClone = (BSTree) multi.clone();
		multi.add(1000);
		assertEquals(multi.toString(), "[100 55 145 75 105 1000 65]");
		assertEquals(multiClone.toString(), "[100 55 145 75 105 65]");
		
		multiClone.add(1001);
		assertEquals(multi.toString(), "[100 55 145 75 105 1000 65]");
		assertEquals(multiClone.toString(), "[100 55 145 75 105 1001 65]");
		
		multi.remove(100);
		assertEquals(multi.toString(), "[75 55 145 65 105 1000]");
		assertEquals(multiClone.toString(), "[100 55 145 75 105 1001 65]");
	}
	
	@Test
	void test_BST()
	{
		Integer[] items = {};
		Integer[] items2 = {50};
		Integer[] items3 = {100, 55, 75, 65, 145, 105};

		BSTree<Integer> newTree = new BSTree<Integer>(items, bstComp);
		assertEquals(newTree.toString(), "[]");
		newTree.add(60);
		assertEquals(newTree.toString(), "[60]");
		newTree.remove(60);
		assertEquals(newTree.toString(), "[]");
		newTree.add(60);
		assertEquals(newTree.toString(), "[60]");
	
		BSTree<Integer> newTree2 = new BSTree<Integer>(items2, bstComp);
		assertEquals(newTree2.toString(), "[50]");
		newTree2.remove(50);
		assertEquals(newTree2.toString(), "[]");
		newTree2.add(50);
		assertEquals(newTree2.toString(), "[50]");
		newTree2.add(6);	
		assertEquals(newTree2.toString(), "[50 6]");
		newTree2.add(150);
		assertEquals(newTree2.toString(), "[50 6 150]");
		newTree2.remove(50);
		assertEquals(newTree2.toString(), "[6 150]");
		
		BSTree<Integer> newTree3 = new BSTree<Integer>(items3, bstComp);
		assertEquals(newTree3.toString(), "[100 55 145 75 105 65]");		
		assertEquals(newTree3.toStringPre(), "[100 55 75 65 145 105]");
		newTree3.remove(100);
		assertEquals(newTree3.toString(), "[75 55 145 65 105]");	
	}
}
