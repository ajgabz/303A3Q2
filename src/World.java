import java.util.ArrayList;

public class World {
	private WorldObject[][] myWorld;
	private int numRows;
	private int numCols;
	
	//private ArrayList<Autonomous> autonomousBlocks;
	
	public World(int rows, int columns){
		this.numRows = rows;
		this.numCols = columns;
		
		//this.autonomousBlocks = new ArrayList<Position>();
		this.myWorld = new WorldObject[numRows][numCols];
	}
	
	public void step() {
		Direction attemptedStep;
		
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0;  j < this.numCols; j++) {
				if (myWorld[i][j] instanceof Autonomous) {
					
					attemptedStep = ((Autonomous) myWorld[i][j]).step();
					
				}
			}
		}
	}
	
	private boolean canMove(int i, int j, Direction attemptedMove) {
		int xSearch = i; 
		int ySearch = j;
		
		switch (attemptedMove) {
		case NORTH:
			ySearch--;
			break;
		case SOUTH:
			ySearch++;
			break;
		case EAST:
			xSearch--;
			break;
		case WEST:
			xSearch++;
			break;
		case NULLSTEP:
			return true;
		}
		
		// First, check the boundary conditions
		if (isValidIndex(xSearch, ySearch)) {
			if (myWorld[xSearch][ySearch] == null) {
				return true;
			} else if (myWorld[xSearch][ySearch] instanceof Immovable) {
				return false;
			} else if (myWorld[xSearch][ySearch] instanceof Movable) {
				return canMove(xSearch, ySearch, attemptedMove);
			}
		} else {
			return false;
		}
		
	}
	
	private boolean move(int i, int j, Direction attemptedMove) {
		int xSearch = i; int ySearch = j; int delta; int boundary;
		
		
		
		//Check border conditions first
		if (isMoveWithinBorders(i, j, attemptedMove)) {
			
			if (attemptedMove == Direction.NORTH) {
				for (int i = x; i >= 0; i--) {
					
			}
			
			
			switch (attemptedMove) {
				case NORTH:
					boundary = 0;
					delta = -1;
					break;
				case SOUTH:
					boundary = this.numRows - 1;
					delta = 1;
					break;
				case EAST:
					boundary = this.numCols - 1;
					delta = 1;
					break;
				case WEST:
					boundary = 0;
					delta = -1;
					break;
			}
			
			while 
			
		}
		
	}
	
	private boolean isMoveWithinBorders(int i, int j, Direction attemptedMove) {
		int newRow = i;
		int newCol = j;
		
		switch (attemptedMove) {
			case NORTH:
				newRow--;
				break;
			case SOUTH:
				newRow++;
				break;
			case EAST:
				newCol++;
				break;
			case WEST:
				newCol--;
				break;
			case NULLSTEP:
				return true;
		}
		
		return (isValidIndex(newRow, newCol));
		}
		
		
	}
	
	
	
	public WorldObject[][] getMyWorld(){
		WorldObject[][] clone = this.myWorld.clone();
		return clone; 
	}
	
	
	public void display(){
		int h = this.numRows; 
		int w = this.numCols; 
		
		for(int j = 0; j < h; j++){
			for(int i = 0; i < w; i++){
				if(this.myWorld[i][j] == null){
					System.out.print(" ");
				}else{
					System.out.print(this.myWorld[i][j].getToken());
				}
			}
		}
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isCellEmpty(int x, int y){
		return (this.myWorld[x][y] == null);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isValidIndex(int x, int y) {
		int maxAccessibleHeight = this.numRows - 1;
		int maxAccessibleWidth = this.numCols - 1;
		
		return ((0 <= x) && (x <= maxAccessibleHeight) && (0 <= y) && (y <= maxAccessibleWidth));
	}
	
	
	public void add(WorldObject obj, int x, int y){
		
		if (isCellEmpty(x,y)) {
			this.myWorld[x][y] = obj;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
