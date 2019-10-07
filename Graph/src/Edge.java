import java.util.Scanner;

public class Edge 
{
	 private Vertex source;
	 private Vertex target;
	 private double weight;
	
	public Edge(Vertex source, Vertex target, double weight)
	{
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
	
	public Vertex getSource()
	{
		return source;
	}
	
	public Vertex getTarget()
	{
		return target;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	
	
	public static char toUpper(char lower)
	{
		return (char) (lower & 0xDF);
	}
	
	public static char toLower(char upper)
	{
		return (char) (upper | 0x20);
	}
	
	public static String binaryToHex(String binary)
	{
		String[][] table = {{"0", "1", "2", "3"},
							{"4", "5", "6", "7"},
							{"8", "9", "A", "B"},
							{"C", "D", "E", "F"}};

		String result = "";
		String str = "";
		
		for(int i = 0; i < binary.length(); i++)
		{
			str = str + binary.charAt(i);
			
			if(str.length() == 4)
			{
				String twoBit = str.substring(0,2);
				String secondBits = str.substring(2, str.length());
				
				if(twoBit.equals("00"))
				{
					if(secondBits.equals("OO"))
					{
						result = result + table[0][0];
					}
					
					else if(secondBits.equals("01"))
					{
						result = result + table[0][1];
					}
					
					else if(secondBits.equals("10"))
					{
						result = result + table[0][2];
					}
					
					else if(secondBits.equals("11"))
					{
						result = result + table[0][3];
					}
				}
				
				else if(twoBit.equals("01"))
				{
					if(secondBits.equals("00"))
					{
						result = result + table[1][0];
					}
					
					else if(secondBits.equals("01"))
					{
						result = result + table[1][1];
					}
					
					else if(secondBits.equals("10"))
					{
						result = result + table[1][2];
					}
					
					else if(secondBits.equals("11"))
					{
						result = result + table[1][3];
					}
				}
				
				else if(twoBit.equals("10"))
				{
					if(secondBits.equals("00"))
					{
						result = result + table[2][0];
					}
					
					else if (secondBits.equals("01"))
					{
						result = result + table[2][1];
					}
					
					else if (secondBits.equals("10"))
					{
						result = result + table[2][2];
					}
					
					else if (secondBits.equals("11"))
					{
						result = result + table[2][3];
					}
				}
				
				else if(twoBit.equals("11"))
				{
					if(secondBits.equals("00"))
					{
						result = result + table[3][0];
					}
					
					else if(secondBits.equals("01"))
					{
						result = result + table[3][1];
					}
					
					else if(secondBits.equals("10"))
					{
						result = result + table[3][2];
					}
					
					else if(secondBits.equals("11"))
					{
						result = result + table[3][3];
					}
				}
				
				str = "";
			}
		}
		
		return result;
	}

	public static void ASCIItoInt()
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		int outputNumber = 0;
		
		for(int i = 0; i < input.length(); i++)
		{
			char inputChar = input.charAt(i);

			int num = inputChar - '0';

			outputNumber = outputNumber * 10 + num;
		}
		
		System.out.println(outputNumber);
	}
	
	static double compare(double x, double y){
		    int value = 0;
		    if(x < y){
		      value = -1;
		   }
		     else if(x > y){
		     value = 1;
	    }
	   return value;
		 }

	public static void main(String[] args)
	{
//		System.out.println(toLower('A'));
//		System.out.println(toUpper('a'));
//		
//		ASCIItoInt();
//		
//		System.out.println(binaryToHex("10001111"));
		
		System.out.println(compare(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY));
	}
}