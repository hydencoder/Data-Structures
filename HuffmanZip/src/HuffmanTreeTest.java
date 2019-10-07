import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HuffmanTreeTest 
{
	HuffmanTree tree;

	@BeforeEach
	void setUp() 
	{
		TreeMap<Character, Integer> tmap = new TreeMap<Character, Integer>();
		tmap.put('a', 100);
		tmap.put('b', 80);
		tmap.put('c', 85);
		tmap.put('d', 43);

		tree = new HuffmanTree(tmap);
	}

	@Test 
	void test_HNode() 
	{
		HNode node = new HNode('a', 100);
		assertTrue(node.contains('a'));
		assertEquals(node.toString(), "a:100");
		assertFalse(node.contains('b'));
		assertTrue(node.isLeaf());

		HNode node2 = new HNode('b', 50);
		assertEquals(node2.toString(), "b:50");

		HNode node3 = new HNode(node, node2);
		assertEquals(node3.toString(), "ab:150");
		assertFalse(node3.isLeaf());

		HNode node4 = new HNode(node2, node);
		assertEquals(node4.toString(), "ba:150");

		assertFalse(node4.isLeaf());
		assertEquals(node4.getRight(), node);
		assertEquals(node4.getLeft(), node2);
		assertEquals(node4.getFreq(), 150);
		assertEquals(node4.getStr(), "ba");
	}
	
	@Test
	void test_HuffmanTree() 
	{
		assertEquals(tree.encodeLoop('a'), "11");
		assertEquals(tree.encodeLoop('b'), "01");
		assertEquals(tree.encodeLoop('c'), "10");
		assertEquals(tree.encodeLoop('d'), "00");

		assertEquals(tree.encode('a'), "11");
		assertEquals(tree.encode('b'), "01");
		assertEquals(tree.encode('c'), "10");
		assertEquals(tree.encode('d'), "00");

		assertEquals(tree.decode("11"), 'a');
		assertEquals(tree.decode("01"), 'b');
		assertEquals(tree.decode("10"), 'c');
		assertEquals(tree.decode("00"), 'd');

		assertEquals(tree.decode("110"), '\0');
		assertEquals(tree.decode("000"), '\0');
		assertEquals(tree.decode("0"), '\0');
		assertEquals(tree.decode("02"), '\0');
		assertEquals(tree.decode("112"), '\0');
		assertEquals(tree.decode(""), '\0');
	}
}
