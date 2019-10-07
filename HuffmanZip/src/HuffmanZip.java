import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

public class HuffmanZip 
{
	private static TreeMap<Character, Integer> map;
	private static HuffmanTree tree;

	/**
	 * Encodes the text file with the given name using the Huffman Encoding Algorithm 
	 * @param filename: file's string name to encode
	 * @throws IOException if stream fails
	 */
	public void encode(String filename) throws IOException 
	{   	
		tree = buildHuffmanTree(filename);
		
		FileInputStream stream = new FileInputStream(filename);
		BitOutputStream compressedFile = new BitOutputStream(filename + ".hz");
		
		compressedFile.writeObject(map);
		
		int read;
		while((read = stream.read()) != -1)
		{
			char symbol = (char) read;
			tree.writeCode(symbol, compressedFile);
		}

		stream.close();
		compressedFile.close();
	} 
	
	/**
	 * Builds the Huffman tree from the file's characters
	 * @param filename: file's string name to build Huffman tree from
	 * @return Huffman tree build from the file
	 * @throws IOException if stream fails
	 */
	private HuffmanTree buildHuffmanTree(String filename) throws IOException
	{
		FileInputStream stream = new FileInputStream(filename);

		map = new TreeMap<Character, Integer>();
		
		int read;
		while((read = stream.read()) != -1) 
		{
			char symbol = (char) read;
			if(map.containsKey(symbol))
			{
				map.put(symbol, map.get(symbol) + 1);
			} 

			else 
			{
				map.put(symbol, 1);
			} 
		}

		stream.close();
		return new HuffmanTree(map); 
	}

	/**
	 * Decodes the text file with the given name using the Huffman Encoding Algorithm
	 * @param filename: file's string name to decode 
	 * @throws IOException if stream fails
	 * @throws ClassNotFoundException if definition of class not found
	 */
	public void decode(String filename) throws IOException, ClassNotFoundException
	{
		BitInputStream streamRead = new BitInputStream(filename);
		FileOutputStream streamWrite = new FileOutputStream(filename.replace(".hz", ".huz"));

		tree = new HuffmanTree((TreeMap<Character, Integer>) streamRead.readObject());

		while(streamRead.hasNext()) 		
		{	
			char read = tree.readCode(streamRead);
			
			if(read != '\0')
			{
				streamWrite.write(read);
			}
		}	

		streamWrite.close();
		streamRead.close();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		HuffmanZip zip = new HuffmanZip();
		
		if(args[0].equals("-encode"))
		{
			zip.encode(args[1]);
		}

		else if(args[0].equals("-decode"))
		{
			zip.decode(args[1]);
		}
	}
}
