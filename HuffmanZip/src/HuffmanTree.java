import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HuffmanTree 
{
	private HNode root;

	private HNodeComparator comparator;

	public HuffmanTree(TreeMap<Character, Integer> frequencies)
	{ 
		comparator = new HNodeComparator();

		PriorityQueue<HNode> pq = new PriorityQueue<HNode>(frequencies.size(), comparator); 

		for (Map.Entry<Character, Integer> entry: frequencies.entrySet()) 
		{
			HNode node = new HNode(entry.getKey(), entry.getValue());
			pq.add(node);	
		}

		int size = pq.size();

		for (int i = 0; i < size-1; i++)
		{
			HNode node1 = pq.poll();
			HNode node2 = pq.poll();

			HNode node3 = new HNode(node1, node2); 
			pq.add(node3);
		}

		root = pq.poll();
	}

	/**
	 * Encodes the given symbol as a string of '0' and '1' characters 
	 * @param symbol: character to encode
	 * @return the binary encoding of the given symbol
	 */
	public String encodeLoop(char symbol)
	{
		String str = "";
		HNode current = root; 

		while (!current.isLeaf())
		{
			if (current.getLeft().contains(symbol))
			{
				str = str + "0";
				current = current.getLeft();
			}

			else 
			{
				str = str + "1";
				current = current.getRight();
			}
		}

		return str;
	}

	/**
	 * Encodes the given symbol as a string of '0' and '1' characters using recursion
	 * @param symbol: character to encode
	 * @return the binary encoding of the given symbol as a string
	 */
	public String encode(char symbol)
	{
		return encode(symbol, root);
	}

	/**
	 * Encodes the given symbol as a string of '0' and '1' characters using recursion
	 * @param symbol: character to encode
	 * @param node: the beginning node
	 * @return the binary encoding of the given symbol as a string
	 */
	public String encode(char symbol, HNode node)
	{
		if (node.isLeaf())
		{
			return "";
		}

		else
		{
			if (node.getLeft().contains(symbol))
			{
				return "0" + encode(symbol, node.getLeft());
			}

			else
			{  
				return "1" + encode(symbol, node.getRight()); 
			}
		}
	}

	/**
	 * Decodes the code of '0' and '1' characters to find the corresponding character
	 * @param code: code as a string to decode 
	 * @return the char represented by the code
	 */
	public char decode(String code)
	{
		HNode current = root;

		if (code.length() == 0 || root == null) // empty string
		{
			return '\0';
		}

		int size = code.length();

		for (int i = 0; i < size; i++)
		{
			if (current.isLeaf())
			{
				return '\0';
			}

			else if (code.charAt(i) == '0')
			{
				current = current.getLeft();
			}

			else if (code.charAt(i) == '1')
			{
				current = current.getRight();
			}

			else  // code contains other than 0 or 1
			{
				return '\0';
			}
		}

		if (current.isLeaf())
		{
			return current.getStr().charAt(0);
		}

		else // code too short
		{
			return '\0';
		}
	}

	/**
	 * Encodes in a stream the given symbol as a string of '0' and '1' characters 
	 * @param symbol: character to encode
	 * @param stream: stream to write '0' and '1' characters in
	 * @throws IOException if stream fails
	 */
	public void writeCode(char symbol, BitOutputStream stream) throws IOException
	{
		HNode current = root; 

		while (!current.isLeaf())
		{
			if (current.getLeft().contains(symbol))
			{
				stream.writeBit(0);
				current = current.getLeft();
			}

			else 
			{
				stream.writeBit(1);
				current = current.getRight();
			}
		}
	}

	/**
	 * Reads from the given stream the '0' and '1's and returns the corresponding character
	 * @param stream: stream to read '0' and '1' from
	 * @return the corresponding character
	 * @throws IOException if stream fails
	 */
	public char readCode(BitInputStream stream) throws IOException
	{
		HNode current = root;

		if (root == null) 
		{
			return '\0';
		}

		while (!current.isLeaf()) // hasNext called in HuffmanZip
		{			
			int bit = stream.readBit();

			if (bit == 0)
			{
				current = current.getLeft();
			}

			else if (bit == 1)
			{
				current = current.getRight();
			}

			else  // stream contains other than 0 or 1
			{
				return '\0';
			}
		}

		if (current.isLeaf())
		{
			return current.getStr().charAt(0);
		}

		else // code too short
		{
			return '\0';
		}
	}

	// For Visualizer 
	public HNode getRoot() 
	{
		return root;
	}
}
