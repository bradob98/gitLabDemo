import java.io.*;
import java.util.Scanner;
public class RiverArea {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the name of the file containing the map to be calculated: ");
		String fileName = scan.next();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			int rowCount = 0;
			int rows = 0, columns = 0, startColumn = 0;
			int[][] map = null;
			String line = in.readLine();
			while(line != null) {
				scan = new Scanner(line);
				if(rowCount == 0) {
					rows = scan.nextInt();
					columns = scan.nextInt();
					startColumn = scan.nextInt();
					map = new int[rows][columns];
				}
				else {
					for(int i=0; i<columns; i++) {
						map[rowCount-1][i] = scan.nextInt();
					}
				}
				rowCount++;
				line = in.readLine();
			}
			System.out.println("The area of the river is: " + findArea(map, 0, startColumn, rows, columns));
			
		} catch (FileNotFoundException e) {
			System.out.println("That file cannot be found. "
					+ "Please try to run the program again, "
					+ "checking that the spelling is correct.");
		}
		catch (IOException e) {
			System.out.println("Problems reading the map file. Please ensure that all values are integers,"
					+ "with each integer separated by a space. "
					+ "\nThe first three integers should be on a line of their own, denoting, in order: "
					+ "Number of Rows, Number of Columns, and the Index of the first column to search from."
					+ "\nEnsure that the integers in the map fit within the given rows and columns.");
		}
	}
	private static int findArea(int[][] map, int startRow, int startColumn, int numRows, int numColumns) {
		//int areaCount = 0;
		//Base case
		
		//Check above
		if (startRow > 0) {
			return findArea(map, startRow - 1, startColumn, numRows, numColumns);
		}
		//Check below
		if (startRow < numRows) {
			return findArea(map, startRow + 1, startColumn, numRows, numColumns);
		}
		//Check left
		if (startColumn > 0) {
			return findArea(map, startRow, startColumn - 1, numRows, numColumns);
		}
		//Check right
		if (startColumn < numColumns) {
			return findArea(map, startRow, startColumn + 1, numRows, numColumns);
		}
		else if (map[startRow][startColumn] == 0) {
			map[startRow][startColumn] = 2; //cover tracks
			return 1;
		}
		else { return 0; }
	}
}

