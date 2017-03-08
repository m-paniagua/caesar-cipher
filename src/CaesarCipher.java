import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CaesarCipher {

	public static void main(String[] args) throws FileNotFoundException {
		final int shift = 13;
		String choice;
		char newChar;
		
		choice = JOptionPane.showInputDialog("Enter encrypt or decrypt: ");				//prompts user
		if(choice == null )	 																										//exits if user clicks cancel
		{
			System.exit(0);
		}
		
		while(!(choice.equalsIgnoreCase("encrypt")) && !(choice.equalsIgnoreCase("decrypt"))) 											//if invalid input, stuck in loop
		{
			choice = JOptionPane.showInputDialog("Invalid input!\nOnly valid options are encrypt or decrypt: ");					//prompts user for valid input
			if(choice == null )	
			{
				System.exit(0);
			}
		}
		
		String inputFile = JOptionPane.showInputDialog("Please enter the input file name: ");												//prompts user for file name
		if(inputFile == null )																																										//ends program if user clicks cancel
			System.exit(0);
		
		File file = new File(inputFile);																																					//checks if file exists
		while (!file.exists()) 																																										//stuck in loop if file does not exist
		{
			inputFile = JOptionPane.showInputDialog("File  " + inputFile + " does not exist.\n" + "Please enter the file name again:");		//prompts user for new input
			 if(inputFile == null)																																									//ends program if user clicks cancel
					System.exit(0);
			 file = new File(inputFile);
		}
		
		String outputFile = JOptionPane.showInputDialog("Please enter output file name: ");												//prompts for output file
		if(outputFile == null )																																									//ends program if user clicks cancel
			System.exit(0);
		
		PrintWriter writeFile = new PrintWriter(outputFile);

		Scanner  readFile = new Scanner(file);
		
		if(choice.equalsIgnoreCase("encrypt"))																																						//executes if user inputs encrypt
		{
			while(readFile.hasNextLine())																																				//loops as long as file has data 
			{
				String line = readFile.nextLine();
				for(int i = 0; i < line.length(); i++)																																		//loops through each char
				{
					if(line.charAt(i) >= 32 && line.charAt(i) <= 114)																											//space to 'r' shifts 13 chars
					{
						newChar = (char) (line.charAt(i) + shift);																													
						writeFile.print(newChar);																																			//writes encrypted char to file
					}
					else																																													//'s' to delete shifts -83 chars
					{
						newChar = (char) (line.charAt(i) - (96 - shift));							
						writeFile.print(newChar);																																			//writes encrypted char to file
					}
				}
				writeFile.println();																																									//prints new line
			}
		}
		else 																																													//executes if user inputs decrypt
		{
			while(readFile.hasNextLine())
			{
				String line = readFile.nextLine();
				for(int i = 0; i < line.length(); i++)
				{
					if(line.charAt(i) >= 32 && line.charAt(i) <= 44)
					{
						newChar = (char) (line.charAt(i) + (96 - shift));
						writeFile.print(newChar);
					}
					else
					{
						newChar = (char) (line.charAt(i) - shift);
						writeFile.print(newChar);
					}
				}
				writeFile.println();
			}
		}
		
		readFile.close();
		writeFile.close();
		System.exit(0);
	}
}
