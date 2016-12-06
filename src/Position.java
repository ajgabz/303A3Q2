// Aaron Gaba (Student ID: 260450580)
// Benjamin Taubenblatt (Student ID: 260626105)


/**
 * A Position object is a simple implementation of a two integer tuple
 * that is used primarily for keeping track of positions in a 2D World array.
 *
 */
public class Position {
	private int row;
	private int column;
	
	/**
	 * Constructs a new Position object with the given row and column
	 * @param row
	 * @param column
	 */
	public Position(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Returns the row of the given Position object
	 * @return
	 */
	public int getRow(){
		return this.row;
	}
	
	/**
	 * Returns the column of the given Position object
	 * @return
	 */
	public int getColumn(){
		return this.column;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (other == null) {
			return false;
		} else if (other instanceof Position) {
			return ((this.row == ((Position) other).getRow()) && (this.column == ((Position) other).getColumn()));
		}
			return false;
	}
}