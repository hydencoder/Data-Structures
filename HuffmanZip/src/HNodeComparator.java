import java.util.Comparator;

public class HNodeComparator implements Comparator<HNode>
{
	/**
	 * Compares two nodes based on their frequencies 
	 * @param o1: first node
	 * @param o2: second node
	 * @return -1 if o1's frequency is less than o2's frequency(or string, if freq is equals), 1 otherwise, 0 if equals freq and string
	 */
	@Override
	public int compare(HNode o1, HNode o2)
	{
		if (o1.getFreq() < o2.getFreq())
		{
			return -1;
		}

		if (o1.getFreq() > o2.getFreq())
		{
			return 1;
		}

		else
		{
			if (o1.getStr().compareTo(o2.getStr()) < 0)
			{
				return -1;
			}

			if (o1.getStr().compareTo(o2.getStr()) > 0)
			{
				return 1;
			}

			else
			{
				return 0;
			}
		}
	}
}

