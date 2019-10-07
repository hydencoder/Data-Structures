import java.util.Comparator;

public class BSTreeVisualize
{
	public static void main(String[] args) throws Exception
	{
		BSTree<Integer> bst = new BSTree<Integer>(new Comparator<Integer>()
		{
			public int compare(Integer o1, Integer o2)
			{
				if (o1 < o2)
				{
					return -1;
				}

				else if (o1 > o2)
				{
					return 1;
				}

				return 0;
			}
		});

		Integer[] values = {3, 7, 1, 2, 0, 10, 5, 15, 8, 6, -7, 18};

		for (Integer v: values) 
		{ 
			bst.addLoop(v);
			//new BSTreeVis<Integer>(bst.getRoot(), "added " + v);   // visualize after current value
		}
		
		//System.out.print(bst);
	}
}