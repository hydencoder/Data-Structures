import java.util.Comparator;

//Assume:
//a is less than b.
//Assume Comparator interface definition is given in Test class in constructor. 

public class CountRangeVisitor<E> implements Visitor<E>
{
	private E a;
	private E b;

	private int count;
	private Comparator<E> comparator;

	private String str;       

	public CountRangeVisitor(Comparator<E> comp, E a, E b)
	{
		str = "";

		this.comparator = comp;

		this.a = a;
		this.b = b;
	}

	public void visit(E item) 
	{
		if ((comparator.compare(item, a) >= 0)) 
		{
			if ((comparator.compare(item, b) <= 0))
			{
				count++;
				str += item + " ";         
			}
		}
	}

	public String getValue()       // use after the traversal to get the overall string
	{
		return "[" + str.trim() + "]";
	}

	public int getCount()    
	{
		return count;
	}

	public void emptyStr()      
	{
		str = "";
	}
	
	public void emptyCount()      
	{
		count = 0;
	}
}
