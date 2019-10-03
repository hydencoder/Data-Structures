import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BTreeTest
{
	private BTree<Integer> empty;
	private BTree<Integer> single;
	private BTree<Integer> multi; 
	
	private BTreeComparator bstComp = new BTreeComparator(); 
	
	@BeforeEach
	void setUp()
	{
		empty = new BTree<Integer>(4, bstComp);
		assertEquals(empty.toString(), "[[]]");
		
		single = new BTree<Integer>(4, bstComp);
		assertEquals(single.toString(), "[[]]");
		single.add(20);
		assertEquals(single.toString(), "[[20]]");
		
		multi = new BTree<Integer>(4, bstComp);
		multi.add(100);
		assertEquals(multi.toString(), "[[100]]");
		multi.add(55);
		assertEquals(multi.toString(), "[[55 100]]");
		multi.add(145);
		assertEquals(multi.toString(), "[[55 100 145]]");
		multi.add(105);
		assertEquals(multi.toString(), "[[55 100 105 145]]");
		multi.add(75);
		assertEquals(multi.toString(), "[[100] [55 75] [105 145]]");
		multi.add(65);	
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145]]");
	}
	
	@Test
	void test_isEmpty() 
	{
		assertTrue(empty.isEmpty());
		assertEquals(empty.toString(), "[[]]");
		
		assertFalse(single.isEmpty());
		assertEquals(single.toString(), "[[20]]");
		
		assertFalse(multi.isEmpty());
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145]]");
	}
	
	@Test
	void test_add()
	{
		empty.add(100);
		assertEquals(empty.toString(), "[[100]]");
		empty.add(55);
		assertEquals(empty.toString(), "[[55 100]]");

		single.add(100); 
		assertEquals(single.toString(), "[[20 100]]");
		single.add(40);
		assertEquals(single.toString(), "[[20 40 100]]");
		single.add(1);
		assertEquals(single.toString(), "[[1 20 40 100]]");
		single.add(50);
		assertEquals(single.toString(), "[[40] [1 20] [50 100]]");
		
		multi.add(20);
		assertEquals(multi.toString(), "[[100] [20 55 65 75] [105 145]]");
		multi.add(175);
		assertEquals(multi.toString(), "[[100] [20 55 65 75] [105 145 175]]");
		multi.add(35);
		assertEquals(multi.toString(), "[[55 100] [20 35] [65 75] [105 145 175]]");
	}
	
	@Test
	void test_contains() 
	{
		assertFalse(empty.contains(7));
		assertEquals(empty.toString(), "[[]]");

		assertFalse(single.contains(5));
		assertEquals(single.toString(), "[[20]]");
		
		assertTrue(single.contains(20));
		assertEquals(single.toString(), "[[20]]");

		assertFalse(multi.contains(64));
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145]]");
		
		assertTrue(multi.contains(75));
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145]]");
		
		assertTrue(multi.contains(145));
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145]]");
	}
	
	@Test
	void test_inorder() 
	{
		StringVisitor<Integer> visitor = new StringVisitor<Integer>();
		empty.inorder(visitor);
		assertEquals(visitor.getValue() , "[]");
		
		StringVisitor<Integer> visitor2 = new StringVisitor<Integer>();
		single.inorder(visitor2);
		assertEquals(visitor2.getValue() , "[20]");
		
		StringVisitor<Integer> visitor3 = new StringVisitor<Integer>();
		multi.inorder(visitor3);
		assertEquals(visitor3.getValue() , "[55 65 75 100 105 145]");
	}
	
	@Test
	void test_toString() 
	{
		assertEquals(empty.toString(), "[[]]");
		assertEquals(single.toString(), "[[20]]");
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145]]");
	}
	
	@Test
	void test_remove()
	{ 
		assertFalse(empty.remove(5));
		assertEquals(empty.toString(), "[[]]");
		
		assertFalse(single.remove(10));
		assertEquals(single.toString(), "[[20]]");
		
		assertTrue(single.remove(20));
		assertEquals(single.toString(), "[[]]");
		
		assertFalse(multi.remove(20));
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145]]");
		
		assertTrue(multi.remove(105));
		assertEquals(multi.toString(), "[[75] [55 65] [100 145]]");
		
		assertFalse(multi.remove(105));
		assertEquals(multi.toString(), "[[75] [55 65] [100 145]]");
		
		assertTrue(multi.remove(55));
		assertEquals(multi.toString(), "[[65 75 100 145]]");
		
		assertTrue(multi.remove(65));
		assertEquals(multi.toString(), "[[75 100 145]]");
		
		assertTrue(multi.remove(100));
		assertEquals(multi.toString(), "[[75 145]]");
		
		assertTrue(multi.remove(145));
		assertEquals(multi.toString(), "[[75]]");
		
		assertTrue(multi.remove(75));
		assertEquals(multi.toString(), "[[]]");
		
		multi = new BTree<Integer>(4, bstComp);
		multi.add(100);
		assertEquals(multi.toString(), "[[100]]");
		multi.add(55);
		assertEquals(multi.toString(), "[[55 100]]");
		multi.add(145);
		assertEquals(multi.toString(), "[[55 100 145]]");
		multi.add(105);
		assertEquals(multi.toString(), "[[55 100 105 145]]");
		multi.add(75);
		assertEquals(multi.toString(), "[[100] [55 75] [105 145]]");
		multi.add(65);	
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145]]");
		multi.remove(100);
		assertEquals(multi.toString(), "[[75] [55 65] [105 145]]");
		multi.remove(75);  
		assertEquals(multi.toString(), "[[55 65 105 145]]");
		
		multi = new BTree<Integer>(4, bstComp);
		multi.add(100);
		assertEquals(multi.toString(), "[[100]]");
		multi.add(55);
		assertEquals(multi.toString(), "[[55 100]]");
		multi.add(145);
		assertEquals(multi.toString(), "[[55 100 145]]");
		multi.add(105);
		assertEquals(multi.toString(), "[[55 100 105 145]]");
		multi.add(75);
		assertEquals(multi.toString(), "[[100] [55 75] [105 145]]");
		multi.add(65);	
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145]]");
		multi.add(598);	
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145 598]]");
		multi.add(185);	
		assertEquals(multi.toString(), "[[100] [55 65 75] [105 145 185 598]]");
		multi.add(128);	
		assertEquals(multi.toString(), "[[100 145] [55 65 75] [105 128] [185 598]]");
		multi.add(44);	
		assertEquals(multi.toString(), "[[100 145] [44 55 65 75] [105 128] [185 598]]");
		multi.add(70);	
		assertEquals(multi.toString(), "[[65 100 145] [44 55] [70 75] [105 128] [185 598]]");
		multi.add(200);	
		assertEquals(multi.toString(), "[[65 100 145] [44 55] [70 75] [105 128] [185 200 598]]");
		multi.remove(128);	
		assertEquals(multi.toString(), "[[65 100 185] [44 55] [70 75] [105 145] [200 598]]");
		multi.add(77);	
		assertEquals(multi.toString(), "[[65 100 185] [44 55] [70 75 77] [105 145] [200 598]]");
		multi.remove(145);	
		assertEquals(multi.toString(), "[[65 77 185] [44 55] [70 75] [100 105] [200 598]]");
		multi.remove(70);	
		assertEquals(multi.toString(), "[[77 185] [44 55 75 65] [100 105] [200 598]]");
		
		BTree<Integer> multi2 = new BTree<Integer>(2, bstComp);
		
		multi2.add(100);
		multi2.add(90);
		multi2.add(60);
		multi2.add(79);
		multi2.add(120);
		multi2.add(134);
		multi2.add(36);
		multi2.add(56);
		multi2.add(111);
		multi2.add(89);
		multi2.add(80);
		assertEquals(multi2.toString(), "[[90] [60 80] [120] [36 56] [79] [89] [100 111] [134]]");
		
		multi2.remove(111);
		multi2.remove(120);
		assertEquals(multi2.toString(), "[[80] [60] [90] [36 56] [79] [89] [100 134]]");
	}
}