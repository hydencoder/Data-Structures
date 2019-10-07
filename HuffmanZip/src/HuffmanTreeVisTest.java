import java.util.TreeMap;

public class HuffmanTreeVisTest 
{
	public static void main(String[] args)
	{
		TreeMap<Character, Integer> tmap = new TreeMap<Character, Integer>();

		tmap.put('a', 100);
		tmap.put('b', 80);
		tmap.put('c', 85);
		tmap.put('d', 43);
		tmap.put('k', 43);

		HuffmanTree tree = new  HuffmanTree(tmap);      

		new HuffmanVis(tree.getRoot(), "1"); 
	}
}
