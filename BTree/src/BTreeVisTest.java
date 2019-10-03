
public class BTreeVisTest
{
	public static void main(String[] args)
	{
		BTreeComparator comp = new BTreeComparator();
		
		BTree<Integer> btree = new BTree<Integer>(3, comp);
		
		Integer[] values = {5, 6, 7, 9};

		for (Integer v: values) 
		{ 
			btree.add(v);
			new BTreeVis<Integer>(btree.getRoot(), "added " + v);    // visualize after current value
		}
	}
}