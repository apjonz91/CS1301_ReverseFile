import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/*
 * Project scans inputed .txt file, flips the order of the file; 
 * and writes the new orientation into a separate file specified by the user.
 * 
 * @author Aaron Jones
 * @version 04/19/2021
 */
public class ReverseFile 
{
	public static void main (String [] args)
	{
		//Scanner object for collecting user input
		Scanner input = new Scanner (System.in);
		boolean parameterMet = false;
		//Prompt user for input .txt file to be scanned, and the output .txt file to be overwritten.
		while (!parameterMet)
		{
			 try
			 {
				System.out.print("Please enter the file name for input: ");
			 	String inputFileName = input.next();
			 	ArrayList<String> inputFileArrayList = readFile(inputFileName);
			 	System.out.print("Please enter the file name for the output: ");
			 	String outputFileName = input.next();
			 	writeInReverse(outputFileName, inputFileArrayList);
			 	parameterMet= true;
			 }
			 catch (FileNotFoundException exception)
			 {
				 System.out.println("File not found.");
			 }
			 catch (NoSuchElementException exception)
			 {
				 System.out.println("File contents invalid.");
			 }
			 catch (IOException exception)
			 {
				 exception.printStackTrace();
			 }
		}
	}
	
	/*
	 * creates File object from input file, scans the file,
	 * passes the scanner object through a separate method (readLinesOfArray()),
	 * and returns a ArrayList String type.
	 * @param inputFileName (String)
	 * @return ArrayList<String> value for inputFileArrayListLines.
	 */
	public static ArrayList<String> readFile(String fileName) throws IOException
	{
		File inFile = new File(fileName);
		try (Scanner inputFile = new Scanner(inFile))
		{
			return readLinesOfArray(inputFile);
		}
	}
	
	/*
	 *  uses scanner object and creates an ArrayList<String>;
	 *  each index position of the ArrayList is populated by-- 
	 *  the string values of each line in Scanner object.
	 *  @param Scanner Object
	 *  @return ArrayList<String> inputFilesListLines
	 */
	public static ArrayList<String> readLinesOfArray(Scanner inputFile) throws IOException
	{
		ArrayList<String> inputFileArrayListLines= new ArrayList<String>();
		while (inputFile.hasNextLine())
		{
			String line = inputFile.nextLine();
			inputFileArrayListLines.add(line);			
		}
		inputFile.close();
		return inputFileArrayListLines;
	}
	
	/*
	 * reverses the order of the ArrayList parameter
	 * and prints each index value into an output file specified by the user.
	 * @param name of output file, ArrayList of inputFile.
	 * @return N/A
	 */
	public static void writeInReverse (String fileName, ArrayList<String> inputFileArrayListLines)
	{
		try
		{
			PrintWriter out = new PrintWriter(fileName);
			for (int i = inputFileArrayListLines.size()-1; i >= 0; i--)
			{
				String arrayLine = inputFileArrayListLines.get(i);
				out.println(arrayLine);
			}
			out.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File cannot be open for writing");
		}	
	}
}
