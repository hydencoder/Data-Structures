public class HNode
{
	private String str;
	private HNode left;
	private HNode right;
	private int freq;

	public HNode(char c, int f)
	{
		this.str = "" + c;
		this.freq = f;

		this.left = null;
		this.right = null;
	}

	public HNode(HNode left, HNode right)
	{
		this.str = left.str + right.str;
		this.freq = left.freq + right.freq;

		this.left = left;
		this.right = right;
	}

	/**
	 * Checks if the node is a leaf
	 * @return true if the node is a leaf, false otherwise
	 */
	public boolean isLeaf()
	{
		return left == null && right == null;
	}
	
	/**
	 * Checks if the node contains the given character
	 * @param ch: character to check if it is in the node
	 * @return true if the character is in the node, false otherwise
	 */
	public boolean contains(char ch)
	{
		return str.contains(Character.toString(ch));
	}

	/**
	 * @return the left
	 */
	public HNode getLeft() 
	{
		return left;
	}

	/**
	 * @return the right
	 */
	public HNode getRight() 
	{
		return right;
	}
	
	/**
	 * @return the str
	 */
	public String getStr() 
	{
		return str;
	}
	
	/**
	 * @return the freq
	 */
	public int getFreq() 
	{
		return freq;
	}

	/**
	 * Represents the node as a string
	 * @return the string representation of the node
	 */
	@Override
	public String toString()
	{
		String sTr = this.str + ":" + this.freq;
		return sTr;
	}
}
