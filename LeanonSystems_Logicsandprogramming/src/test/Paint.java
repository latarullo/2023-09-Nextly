package test;

/*
 * 4 - Your friend is developing a small image processing program and has asked for your help
 * in implementing MS-Paint's “paint bucket”-like functionality. Their program represents
 * images using arrays of characters, with each array value representing a pixel and letters and
 * symbols representing different colors. For example, the following 4x6 matrix represents the
 * letter P in color "#", with background color "." (dot)
 * 
 * .###..
 * .#..#.
 * .###..
 * .#....
 * 
 * Your subroutine should take a pixel and a new color and paint the region of that pixel with
 * the new color, like MS-Paint's “paint bucket” tool.
 * Examples:
 * Pixel (0,1) and new color 'O' | Pixel (1,3) and new color 'o' | Pixel (1,3) and new color '#' |
 * -----------------------------------------------------------------------------------------------
 * Before      After             | Before      After             | Before      After             |
 * -----------------------------------------------------------------------------------------------    
 * .###..      .OOO..            | .###.       .###..            | .###..      .###..            |
 * .#..#.      .O..#.            | .#..#.      .#oo#.            | .#..#.      .####.            |
 * .###..      .OOO..            | .###.       .###..            | .###..      .###..            |
 * .#....      .O....            | .#....      .#....            | .#....      .#....            |
 * -----------------------------------------------------------------------------------------------
 */

public class Paint {
	private static int ROWS = 4;
	private static int COLUMNS = 6;
	private static char BLANK = '.';
	private static char DRAWN = '#';
	private static char COLOR_O = 'O';
	private static char COLOR_o = 'o';
	private static char COLOR_AT = '@';
	private static char COLOR_DOLAR = '$';

	private char[][] defaultBoard = {
			{BLANK, DRAWN, DRAWN, DRAWN, BLANK, BLANK},
			{BLANK, DRAWN, BLANK, BLANK, DRAWN, BLANK},
			{BLANK, DRAWN, DRAWN, DRAWN, BLANK, BLANK},
			{BLANK, DRAWN, BLANK, BLANK, BLANK, BLANK}
	};
	
	public char[][] paint(Point point, char color){
		return paint(getDefaultBoard(), point, color);
	}
	
	public char[][] paint(char[][] board, Point point, char color){
		if (point == null || point.getColumn() < 0 || point.getColumn() >= COLUMNS || point.getRow() < 0 || point.getRow() >= ROWS) {
			throw new IllegalArgumentException("In order to paint-bucket a valid value for point argument is required!");
		}
		
		char[][] paintedBoard = clone(board);
		
		char oldPixelColor = paintedBoard[point.getRow()][point.getColumn()];

		if (oldPixelColor != color) {
			paintPixelAndNeighbors(paintedBoard, point, oldPixelColor, color);
		}
		
		return paintedBoard;
	}
	
	public void paintPixelAndNeighbors(char[][] board, Point point, char oldPixelColor, char newPixelColor ) {
		//recursive ending pixel out of bounds
		if (point.getColumn() < 0 || point.getColumn() >= COLUMNS || point.getRow() < 0 || point.getRow() >= ROWS) {
			return;
		}
		
		//if pixel has to be painted, then check for neighbors
		//according to the sample, non diagonal checkings are made
		if (board[point.getRow()][point.getColumn()] == oldPixelColor) {
			board[point.getRow()][point.getColumn()] = newPixelColor;
			
			paintPixelAndNeighbors(board, new Point(point.getRow(), point.getColumn() - 1), oldPixelColor, newPixelColor);
			paintPixelAndNeighbors(board, new Point(point.getRow(), point.getColumn() + 1), oldPixelColor, newPixelColor);
			paintPixelAndNeighbors(board, new Point(point.getRow() - 1, point.getColumn()), oldPixelColor, newPixelColor);
			paintPixelAndNeighbors(board, new Point(point.getRow() + 1, point.getColumn()), oldPixelColor, newPixelColor);
		}
	}
	
	public void printPaintBoard(char[][] paintBoard) {
		for (char[] cs : paintBoard) {
			System.out.println(cs);
		}
		System.out.println();
	}
	
	public void print() {
		for (char[] cs : defaultBoard) {
			System.out.println(cs);
		}
		System.out.println();
	}
	
	public char[][] getDefaultBoard() {
		return defaultBoard;
	}
	
	/*
	 * Reminder: arrays are pointers - their values are mutable
	 * 
	 * Clone method required to make the board "immutable-like" 
	 */
	private char[][] clone(char [][] board) {
		char[][] clonedBoard = new char[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				clonedBoard[i][j] = board[i][j];
			}	
		}
		return clonedBoard;
	}

	public static void main(String[] args) {
		Paint paint = new Paint();

		char[][] paintScenario1 = paint.paint(new Point(0,1), COLOR_O);
		System.out.println("SCENARIO 1");
		System.out.println("-------------------------");
		System.out.println("BEFORE");
		paint.print();
		System.out.println("-------------------------");
		System.out.println("AFTER");
		paint.printPaintBoard(paintScenario1);
		System.out.println("-------------------------");
		
		char[][] paintScenario2 = paint.paint(new Point(1,3), COLOR_o);
		System.out.println("SCENARIO 2");
		System.out.println("-------------------------");
		System.out.println("BEFORE");
		paint.print();
		System.out.println("-------------------------");
		System.out.println("AFTER");
		paint.printPaintBoard(paintScenario2);
		System.out.println("-------------------------");
		
		char[][] paintScenario3 = paint.paint(new Point(1,3), DRAWN);
		System.out.println("SCENARIO 3");
		System.out.println("-------------------------");
		System.out.println("BEFORE");
		paint.print();
		System.out.println("-------------------------");
		System.out.println("AFTER");
		paint.printPaintBoard(paintScenario3);
		System.out.println("-------------------------");
		
		char[][] paintScenario4 = paint.paint(new Point(0,0), COLOR_AT);
		System.out.println("SCENARIO 4");
		System.out.println("-------------------------");
		System.out.println("BEFORE");
		paint.print();
		System.out.println("-------------------------");
		System.out.println("AFTER");
		paint.printPaintBoard(paintScenario4);
		System.out.println("-------------------------");
		
		char[][] paintScenario5 = paint.paint(new Point(0,5), COLOR_DOLAR);
		System.out.println("SCENARIO 5-1");
		System.out.println("-------------------------");
		System.out.println("BEFORE");
		paint.print();
		System.out.println("-------------------------");
		System.out.println("AFTER");
		paint.printPaintBoard(paintScenario5);
		System.out.println("-------------------------");
		
		paintScenario5 = paint.paint(paintScenario5, new Point(0,0), COLOR_AT);
		System.out.println("SCENARIO 5-2");
		System.out.println("-------------------------");
		System.out.println("BEFORE");
		paint.print();
		System.out.println("-------------------------");
		System.out.println("AFTER");
		paint.printPaintBoard(paintScenario5);
		System.out.println("-------------------------");
		
		paintScenario5 = paint.paint(paintScenario5, new Point(2,2), COLOR_DOLAR);
		System.out.println("SCENARIO 5-3");
		System.out.println("-------------------------");
		System.out.println("BEFORE");
		paint.print();
		System.out.println("-------------------------");
		System.out.println("AFTER");
		paint.printPaintBoard(paintScenario5);
		System.out.println("-------------------------");
		
		paintScenario5 = paint.paint(paintScenario5, new Point(1,2), COLOR_DOLAR);
		System.out.println("SCENARIO 5-4");
		System.out.println("-------------------------");
		System.out.println("BEFORE");
		paint.print();
		System.out.println("-------------------------");
		System.out.println("AFTER");
		paint.printPaintBoard(paintScenario5);
		System.out.println("-------------------------");
	}
}

/*
 * Support class - test purpose
 */
class Point {
	private int row;
	private int column;

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	public Point(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	@Override
	public String toString() {
		return "Point [row=" + row + ", column=\" + column + \"]";
	}
}
